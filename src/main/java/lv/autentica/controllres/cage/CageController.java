package lv.autentica.controllres.cage;

import lv.autentica.DAO.cage.CageDAO;
import lv.autentica.DAO.cage.CageTypeDAO;
import lv.autentica.domain.cages.Cage;
import lv.autentica.dto.cage.CageDTO;
import lv.autentica.exception.CageException;
import lv.autentica.service.cage.CageService;
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
public class CageController {

    @Autowired
    CageDAO cageDAO;

    @Autowired
    CageTypeDAO cageTypeDAO;

    @Autowired
    CageService cageService;

    @RequestMapping(value = "/cage", method = RequestMethod.GET)
    public String redirectToStaff(Model model) {
        model.addAttribute("cages", cageDAO.getAll());
        return "cageList";
    }

    @RequestMapping(value = "/cage/add", method = RequestMethod.GET)
    public ModelAndView redirectToAddCage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addCage");
        if(model.asMap().get("cage") != null) {
            modelAndView.addObject("cage", model.asMap().get("cage"));
        } else {
            modelAndView.addObject("cage", new CageDTO());
        }
        modelAndView.addObject("cageTypeList", cageTypeDAO.getALL());
        return modelAndView;
    }

    @RequestMapping(value = "/cage/add", method = RequestMethod.POST)
    public String addCage(@ModelAttribute("cage") CageDTO cage, RedirectAttributes redirectAttributes) {
        try {
            cageService.upsertCage(cage);
        } catch (CageException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("cage",cage);
            return "redirect:/cage/add";
        }
        return "redirect:/cage";
    }

    @RequestMapping(value = "/cage/{id}/delete", method = RequestMethod.GET)
    public String deleteCage(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Cage cage = new Cage();
        cage.setId(id);
        try {
            cageDAO.delete(cage);
        } catch (Exception e ){
            redirectAttributes.addFlashAttribute("error",e.getCause());
        }
        return "redirect:/cage";
    }

    @RequestMapping(value = "/cage/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editStaff(@PathVariable("id") Long id,Model model) {
        ModelAndView modelAndView = new ModelAndView("editCage");
        Cage cage = cageDAO.get(Cage.class,id);
        CageDTO cageDTO = new CageDTO(cage);
        model.addAttribute("cage", cageDTO);
        modelAndView.addObject("cageTypeList", cageTypeDAO.getALL());
        return  modelAndView;
    }

    @RequestMapping(value = "/cage/edit", method = RequestMethod.POST)
    public String editCage(@ModelAttribute("cage") CageDTO cage, RedirectAttributes redirectAttributes) {
        try {
            cageService.upsertCage(cage);
        } catch (CageException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("cage",cage);
            return "redirect:/cage/edit";
        }
        return "redirect:/cage";
    }


}
