package lv.autentica.controllres.staff;

import lv.autentica.DAO.staff.StaffDutiesDAO;
import lv.autentica.DAO.staff.StaffTypeDAO;
import lv.autentica.domain.staff.StaffDuties;
import lv.autentica.dto.staff.StaffDutyDTO;
import lv.autentica.exception.StaffException;
import lv.autentica.service.staff.StaffDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StaffDutyController {

    @Autowired
    StaffDutiesDAO staffDutiesDAO;

    @Autowired
    StaffTypeDAO staffTypeDAO;

    @Autowired
    StaffDutyService staffDutyService;


    @RequestMapping(value = "/staff/duties", method = RequestMethod.GET)
    public String redirectToStaffDuty(Model model) {
        model.addAttribute("staffDuties", staffDutiesDAO.getAll());
        return "staffDuties";
    }

    @RequestMapping(value = "/staff/duty/add", method = RequestMethod.GET)
    public ModelAndView addStaffType(Model model) {
        ModelAndView modelAndView = new ModelAndView("addStaffDuty");
        if(model.asMap().get("staffDuty") != null) {
            modelAndView.addObject("staffDuty", model.asMap().get("staffDuty"));
        } else {
            modelAndView.addObject("staffDuty", new StaffDutyDTO());
        }
        modelAndView.addObject("staffTypeList",staffTypeDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/staff/duty/add", method = RequestMethod.POST)
    public String addStaff(@ModelAttribute("staffDuty") StaffDutyDTO staffDuty, RedirectAttributes redirectAttributes) {
        return this.upsertStaff(staffDuty,redirectAttributes);
    }

    @RequestMapping(value = "/staff/duty/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editStaffType(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("editStaffDuty");
        StaffDuties staffDuty = staffTypeDAO.get(StaffDuties.class,id);
        StaffDutyDTO staffType = new StaffDutyDTO(staffDuty);
        model.addAttribute("staffDuty", staffType);
        modelAndView.addObject("staffTypeList", staffTypeDAO.getAll());
        return  modelAndView;
    }

    @RequestMapping(value = "/staff/duty/{id}/delete", method = RequestMethod.GET)
    public String deleteStaffType(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        StaffDuties staffDuty = new StaffDuties();
        staffDuty.setId(id);
        try {
            staffTypeDAO.delete(staffDuty);
        } catch (Exception e ){
            redirectAttributes.addFlashAttribute("error",e.getCause());
        }
        return "redirect:/staff/duties";
    }

    @RequestMapping(value = "/staff/duty/edit", method = RequestMethod.POST)
    public String editStaffType(@ModelAttribute("staffDuty") StaffDutyDTO staffDuty,
                                RedirectAttributes redirectAttributes) {
        return this.upsertStaff(staffDuty,redirectAttributes);
    }

    protected String upsertStaff(StaffDutyDTO staffDuty, RedirectAttributes redirectAttributes) {
        try {
            staffDutyService.upsertStaffType(staffDuty);
        } catch (StaffException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("staffType",staffDuty);
            return "redirect:/staff/duty/add";
        }
        return "redirect:/staff/duties";
    }
}
