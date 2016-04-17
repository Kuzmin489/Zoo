package lv.autentica.controllres.cage;

import lv.autentica.DAO.cage.CageCleanDAO;
import lv.autentica.DAO.cage.CageDAO;
import lv.autentica.DAO.staff.StaffDAO;
import lv.autentica.domain.cages.CageClean;
import lv.autentica.dto.cage.CageCleanDTO;
import lv.autentica.exception.CageException;
import lv.autentica.service.cage.CageCleanService;
import lv.autentica.service.cage.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CageCleanController {

    @Autowired
    private CageCleanDAO cageCleanDAO;

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private CageDAO cageDAO;

    @Autowired
    private CageCleanService cageCleanService;

    @RequestMapping(value = "/cage/clean", method = RequestMethod.GET)
    public String redirectToCageClean(Model model) {
        model.addAttribute("cages", cageCleanDAO.getALL());
        return "cageCleanList";
    }

    @RequestMapping(value = "/cage/clean/add", method = RequestMethod.GET)
    @Transactional
    public ModelAndView redirectToAddCageClean(Model model) {
        ModelAndView modelAndView = new ModelAndView("addCageClean");
        if(model.asMap().get("cageClean") != null) {
            modelAndView.addObject("cageClean", model.asMap().get("cageClean"));
        } else {
            modelAndView.addObject("cageClean", new CageCleanDTO());
        }

        modelAndView.addObject("cageList", cageDAO.getAll());
        modelAndView.addObject("workerList", staffDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/cage/clean/add", method = RequestMethod.POST)
    public String addCageClean(@ModelAttribute("cageClean") CageCleanDTO cageClean,
                              RedirectAttributes redirectAttributes) {
        try {
            cageCleanService.upsertCageClean(cageClean);
        } catch (CageException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("cageClean",cageClean);
            return "redirect:/cage/clean/add";
        }
        return "redirect:/cage/clean";
    }

    @RequestMapping(value = "/cage/clean/{id}/delete", method = RequestMethod.GET)
    public String deleteCageClean(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        CageClean cageClean = new CageClean();
        cageClean.setId(id);
        try {
            cageCleanDAO.delete(cageClean);
        } catch (Exception e ){
            redirectAttributes.addFlashAttribute("error",e.getCause());
        }
        return "redirect:/cage/clean";
    }

    @RequestMapping(value = "/cage/clean/{id}/edit", method = RequestMethod.GET)
    @Transactional
    public ModelAndView editCageClean(@PathVariable("id") Long id,Model model) {
        ModelAndView modelAndView = new ModelAndView("editCageClean");
        CageClean cageClean = cageCleanDAO.get(CageClean.class,id);
        CageCleanDTO cageCleanDTO = new CageCleanDTO(cageClean);
        model.addAttribute("cageClean", cageCleanDTO);
        modelAndView.addObject("cageList", cageDAO.getAll());
        modelAndView.addObject("workerList", staffDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/cage/clean/edit", method = RequestMethod.POST)
    public String editCageClean(@ModelAttribute("cageClean") CageCleanDTO cageClean,
                               RedirectAttributes redirectAttributes) {
        try {
            cageCleanService.upsertCageClean(cageClean);
        } catch (CageException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("cageClean",cageClean);
            return "redirect:/cage/clean/edit";
        }
        return "redirect:/cage/clean";
    }
}
