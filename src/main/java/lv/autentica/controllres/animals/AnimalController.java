package lv.autentica.controllres.animals;

import lv.autentica.DAO.animal.AnimalDAO;
import lv.autentica.DAO.animal.AnimalTypeDAO;
import lv.autentica.domain.animal.Animal;
import lv.autentica.domain.animal.AnimalType;
import lv.autentica.dto.animal.AnimalDTO;
import lv.autentica.dto.animal.AnimalTypeDTO;
import lv.autentica.exception.AnimalException;
import lv.autentica.service.animal.AnimalService;
import org.hibernate.JDBCException;
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
public class AnimalController {
    @Autowired
    AnimalDAO animalDAO;

    @Autowired
    AnimalTypeDAO animalTypeDAO;

    @Autowired
    AnimalService animalService;

    @RequestMapping(value = "/animals", method = RequestMethod.GET)
    public String redirectToStaff(Model model) {
        model.addAttribute("animals", animalDAO.getAll());
        return "animals";
    }

    @RequestMapping(value = "/animal/add", method = RequestMethod.GET)
    @Transactional
    public ModelAndView redirectToAddAnimal(Model model) {
        ModelAndView modelAndView = new ModelAndView("addAnimal");
        if(model.asMap().get("animal") != null) {
            modelAndView.addObject("animal", model.asMap().get("animal"));
        } else {
            modelAndView.addObject("animal", new AnimalDTO());
        }
        List<AnimalType> animalTypeList = animalTypeDAO.getAll().stream()
                .filter(e-> e.getSubTypes().isEmpty()).collect(Collectors.toList());
        modelAndView.addObject("animalTypeList", animalTypeList);
        return modelAndView;
    }

    @RequestMapping(value = "animal/add", method = RequestMethod.POST)
    public String addAnimal(@ModelAttribute("animal") AnimalDTO animal,
                                RedirectAttributes redirectAttributes) {
        try {
            animalService.upsertAnimal(animal);
        } catch (AnimalException e ){
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("animal",animal);
            return "redirect:/animal/add";
        }
        return "redirect:/animals";
    }

    @RequestMapping(value = "/animal/{id}/edit", method = RequestMethod.GET)
    @Transactional
    public ModelAndView redirectToEditAnimal (@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editAnimal");
        AnimalDTO animal = new AnimalDTO(animalDAO.get(Animal.class,id));
        modelAndView.addObject("animal",animal);
        List<AnimalType> animalTypeList = animalTypeDAO.getAll().stream()
                .filter(e-> e.getSubTypes().isEmpty()).collect(Collectors.toList());
        modelAndView.addObject("animalTypeList", animalTypeList);
        return modelAndView;
    }

    @RequestMapping(value = "/animal/{id}/delete", method = RequestMethod.GET)
    public String deleteAnimal (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Animal animal = new Animal();
        animal.setId(id);
        try {
            animalDAO.delete(animal);
        } catch (JDBCException e ) {
            redirectAttributes.addFlashAttribute("error", e.getSQLException());
        }
        return "redirect:/animals";
    }

    @RequestMapping(value = "animal/edit", method = RequestMethod.POST)
    public String editAnimalType(@ModelAttribute("animal") AnimalDTO animal,
                                 RedirectAttributes redirectAttributes) {
        try {
            animalService.upsertAnimal(animal);
        } catch (AnimalException e ){
            redirectAttributes.addFlashAttribute("error",e);
            return "redirect:/animal/"+animal.getId()+"/edit";
        }
        return "redirect:/animals";
    }
}
