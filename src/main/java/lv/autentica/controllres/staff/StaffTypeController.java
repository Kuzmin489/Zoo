package lv.autentica.controllres.staff;

import lv.autentica.DAO.staff.StaffTypeDAO;
import lv.autentica.domain.staff.StaffType;
import lv.autentica.dto.staff.StaffTypeDTO;
import lv.autentica.exception.StaffException;
import lv.autentica.service.staff.StaffTypeService;
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
public class StaffTypeController {
    @Autowired
    StaffTypeDAO staffTypeDAO;

    @Autowired
    StaffTypeService staffTypeService;

    @RequestMapping(value = "/staff/types", method = RequestMethod.GET)
    public String redirectToStaff(Model model) {
        model.addAttribute("staffTypes", staffTypeDAO.getAll());
        return "staffTypes";
    }

    @RequestMapping(value = "/staff/type/add", method = RequestMethod.GET)
    public ModelAndView addStaffType(Model model) {
        ModelAndView modelAndView = new ModelAndView("addStaffType");
        if(model.asMap().get("staffType") != null) {
            modelAndView.addObject("staffType", model.asMap().get("staffType"));
        } else {
            modelAndView.addObject("staffType", new StaffTypeDTO());
        }
        modelAndView.addObject("staffTypeList",staffTypeDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/staff/type/add", method = RequestMethod.POST)
    public String addStaff(@ModelAttribute("staffType") StaffTypeDTO staffType, RedirectAttributes redirectAttributes) {
        return this.upsertStaff(staffType,redirectAttributes);
    }

    @RequestMapping(value = "/staff/type/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editStaffType(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("editStaffType");
        StaffType staff = staffTypeDAO.get(StaffType.class,id);
        StaffTypeDTO staffType = new StaffTypeDTO(staff);
        model.addAttribute("staffType", staffType);
        modelAndView.addObject("staffTypeList", staffTypeDAO.getAll());
        return  modelAndView;
    }

    @RequestMapping(value = "/staff/type/{id}/delete", method = RequestMethod.GET)
    public String deleteStaffType(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        StaffType staffType = new StaffType();
        staffType.setId(id);
        try {
            staffTypeDAO.delete(staffType);
        } catch (Exception e ){
            redirectAttributes.addFlashAttribute("error",e.getCause());
        }
        return "redirect:/staff/types";
    }

    @RequestMapping(value = "/staff/type/edit", method = RequestMethod.POST)
    public String editStaffType(@ModelAttribute("staffType") StaffTypeDTO staffType,
                                RedirectAttributes redirectAttributes) {
         return this.upsertStaff(staffType,redirectAttributes);
    }


    protected String upsertStaff(StaffTypeDTO staffType, RedirectAttributes redirectAttributes) {
        try {
            staffTypeService.upsertStaffType(staffType);
        } catch (StaffException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("staffType",staffType);
            return "redirect:/staff/type/add";
        }
        return "redirect:/staff/types";
    }
}
