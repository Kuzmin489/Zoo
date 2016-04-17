package lv.autentica.controllres.animals;

import lv.autentica.DAO.animal.AnimalTypeDAO;
import lv.autentica.domain.animal.AnimalType;
import lv.autentica.dto.animal.AnimalTypeDTO;
import lv.autentica.exception.AnimalException;
import lv.autentica.service.animal.AnimalTypeService;
import org.hibernate.JDBCException;
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
public class AnimalTypeController {
    @Autowired
    AnimalTypeDAO animalTypeDAO;

    @Autowired
    AnimalTypeService animalTypeService;

    @RequestMapping(value = "/animals/types", method = RequestMethod.GET)
    public String redirectToStaff(Model model) {
        model.addAttribute("animalsTypes", animalTypeDAO.getAll());
        return "animalsTypes";
    }

    @RequestMapping(value = "/animal/type/add", method = RequestMethod.GET)
    public ModelAndView redirectToAddAnimalType (Model model) {
        ModelAndView modelAndView = new ModelAndView("addAnimalType");
        if(model.asMap().get("animalType") != null) {
            modelAndView.addObject("animalType", model.asMap().get("animalType"));
        } else {
            modelAndView.addObject("animalType", new AnimalTypeDTO());
        }
        modelAndView.addObject("animalTypeList", animalTypeDAO.getAll());
        return modelAndView;
    }
    @RequestMapping(value = "animal/type/add", method = RequestMethod.POST)
    public String addAnimalType(@ModelAttribute("animalType") AnimalTypeDTO animalType,
                                RedirectAttributes redirectAttributes) {
        try {
            animalTypeService.upsertAnimal(animalType);
        } catch (AnimalException e ){
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("animalType",animalType);
            return "redirect:/animal/type/add";
        }
        return "redirect:/animals/types";
    }

    @RequestMapping(value = "/animal/type/{id}/edit", method = RequestMethod.GET)
    public ModelAndView redirectToAddAnimalType (@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editAnimalType");
        AnimalTypeDTO animalType = new AnimalTypeDTO(animalTypeDAO.get(AnimalType.class,id));
        modelAndView.addObject("animalType",animalType);
        modelAndView.addObject("animalTypeList", animalTypeDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/animal/type/{id}/delete", method = RequestMethod.GET)
    public String deleteAnimalType (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        AnimalType animalType = new AnimalType();
        animalType.setId(id);
        try {
            animalTypeDAO.delete(animalType);
        } catch (JDBCException e ) {
            redirectAttributes.addFlashAttribute("error", e.getSQLException());
        }
        return "redirect:/animals/types";
    }

    @RequestMapping(value = "animal/type/edit", method = RequestMethod.POST)
    public String editAnimalType(@ModelAttribute("animalType") AnimalTypeDTO animalType,
                                RedirectAttributes redirectAttributes) {
        try {
            animalTypeService.upsertAnimal(animalType);
        } catch (AnimalException e ){
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("animalType",animalType);
            return "redirect:/animal/type/edit";
        }
        return "redirect:/animals/types";
    }
}
