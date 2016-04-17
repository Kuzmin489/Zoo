package lv.autentica.controllres.cage;

import lv.autentica.DAO.animal.AnimalTypeDAO;
import lv.autentica.DAO.cage.CageTypeDAO;
import lv.autentica.domain.animal.AnimalType;
import lv.autentica.domain.cages.CageType;
import lv.autentica.dto.cage.CageTypeDTO;
import lv.autentica.exception.CageException;
import lv.autentica.service.cage.CageTypeService;
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
public class CageTypeController {

    @Autowired
    CageTypeDAO cageTypeDAO;

    @Autowired
    AnimalTypeDAO animalTypeDAO;

    @Autowired
    CageTypeService cageTypeService;

    @RequestMapping(value = "/cage/type", method = RequestMethod.GET)
    public String redirectToStaff(Model model) {
        model.addAttribute("cageTypes", cageTypeDAO.getALL());
        return "cageTypeList";
    }

    @RequestMapping(value = "/cage/type/add", method = RequestMethod.GET)
    @Transactional
    public ModelAndView redirectToAddCageType(Model model) {
        ModelAndView modelAndView = new ModelAndView("addCageType");
        if(model.asMap().get("cageType") != null) {
            modelAndView.addObject("cageType", model.asMap().get("cageType"));
        } else {
            modelAndView.addObject("cageType", new CageTypeDTO());
        }
        List<AnimalType> animalTypeList = animalTypeDAO.getAll().stream()
                .filter(e-> e.getBaseType() == null).collect(Collectors.toList());
        modelAndView.addObject("animalTypeList", animalTypeList);
        return modelAndView;
    }

    @RequestMapping(value = "/cage/type/add", method = RequestMethod.POST)
    public String addCageType(@ModelAttribute("cageType") CageTypeDTO cageType, RedirectAttributes redirectAttributes) {
        try {
            cageTypeService.upsertCageType(cageType);
        } catch (CageException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("cageType",cageType);
            return "redirect:/cage/type/add";
        }
        return "redirect:/cage/type";
    }

    @RequestMapping(value = "/cage/type/{id}/delete", method = RequestMethod.GET)
    public String deleteCageType(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        CageType cageType = new CageType();
        cageType.setId(id);
        try {
            cageTypeDAO.delete(cageType);
        } catch (Exception e ){
            redirectAttributes.addFlashAttribute("error",e.getCause());
        }
        return "redirect:/cage/type";
    }

    @RequestMapping(value = "/cage/type/{id}/edit", method = RequestMethod.GET)
    @Transactional
    public ModelAndView editCageType(@PathVariable("id") Long id,Model model) {
        ModelAndView modelAndView = new ModelAndView("editCageType");
        CageType cageType = cageTypeDAO.get(CageType.class,id);
        CageTypeDTO cageTypeDTO = new CageTypeDTO(cageType);
        model.addAttribute("cageType", cageTypeDTO);
        List<AnimalType> animalTypeList = animalTypeDAO.getAll().stream()
                .filter(e-> e.getBaseType() == null).collect(Collectors.toList());
        modelAndView.addObject("animalTypeList", animalTypeList);
        return  modelAndView;
    }

    @RequestMapping(value = "/cage/type/edit", method = RequestMethod.POST)
    public String editCageType(@ModelAttribute("cageType") CageTypeDTO cageType,
                           RedirectAttributes redirectAttributes) {
        try {
            cageTypeService.upsertCageType(cageType);
        } catch (CageException e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("cageType",cageType);
            return "redirect:/cage/type/edit";
        }
        return "redirect:/cage/type";
    }
}
