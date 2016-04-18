package lv.autentica.controllres.staff;


import lv.autentica.DAO.staff.StaffDAO;
import lv.autentica.DAO.staff.StaffTypeDAO;
import lv.autentica.domain.staff.Staff;
import lv.autentica.dto.staff.StaffDTO;
import lv.autentica.exception.StaffException;
import lv.autentica.service.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StaffController {
    @Autowired
    StaffDAO staffDAO;

    @Autowired
    StaffTypeDAO staffTypeDAO;

    @Autowired
    StaffService staffService;

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public String redirectToStaff(Model model) {
        model.addAttribute("staff", staffDAO.getAll());
        return "staff";
    }

    @RequestMapping(value = "/staff/add", method = RequestMethod.GET)
    public ModelAndView redirectToAddClient(Model model) {
        ModelAndView modelAndView = new ModelAndView("addStaff");
        if(model.asMap().get("worker") != null) {
            modelAndView.addObject("worker", model.asMap().get("worker"));
        } else {
            modelAndView.addObject("worker", new StaffDTO());
        }
        modelAndView.addObject("staffTypes", staffTypeDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/staff/add", method = RequestMethod.POST)
    public String addStaff(@ModelAttribute StaffDTO worker, RedirectAttributes redirectAttributes) {
        try {
            staffService.upsertStaff(worker);
        } catch (StaffException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("worker",worker);
            return "redirect:/staff/add";
        }
        return "redirect:/staff";
    }

    @RequestMapping(value = "/staff/{id}/delete", method = RequestMethod.GET)
    public String deleteStaff(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Staff worker = new Staff();
        worker.setId(id);
        try {
            staffDAO.delete(worker);
        } catch (Exception e ){
            redirectAttributes.addFlashAttribute("error",e.getCause());
        }
        return "redirect:/staff";
    }

    @RequestMapping(value = "/staff/{id}/edit", method = RequestMethod.GET)
    @Transactional
    public ModelAndView editStaff(@PathVariable("id") Long id,Model model) {
        ModelAndView modelAndView = new ModelAndView("editStaff");
        Staff staff = staffDAO.get(Staff.class,id);
        StaffDTO worker = new StaffDTO(staff);
        modelAndView.addObject("worker", worker);
        modelAndView.addObject("staffTypes", staffTypeDAO.getAll());
        return  modelAndView;
    }

    @RequestMapping(value = "/staff/edit", method = RequestMethod.POST)
    public String editStaff(@ModelAttribute("worker") StaffDTO worker, RedirectAttributes redirectAttributes) {
        try {
            staffService.upsertStaff(worker);
        } catch (StaffException e) {
            e.printStackTrace();
        }
        return "redirect:/staff";
    }
}
