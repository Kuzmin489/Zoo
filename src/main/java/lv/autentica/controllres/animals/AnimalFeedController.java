package lv.autentica.controllres.animals;

import lv.autentica.DAO.animal.AnimalDAO;
import lv.autentica.DAO.animal.AnimalFeedsDAO;
import lv.autentica.DAO.staff.StaffDAO;
import lv.autentica.domain.animal.AnimalFeeds;
import lv.autentica.dto.animal.AnimalFeedDTO;
import lv.autentica.exception.AnimalException;
import lv.autentica.service.animal.AnimalFeedService;
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
public class AnimalFeedController {
    @Autowired
    private AnimalFeedsDAO animalFeedsDAO;

    @Autowired
    private AnimalDAO animalDAO;

    @Autowired
    private AnimalFeedService animalFeedServicel;

    @Autowired
    StaffDAO staffDAO;

    @RequestMapping(value = "/animals/feeding", method = RequestMethod.GET)
    public String redirectToStaff(Model model) {
        model.addAttribute("animalsFeedings", animalFeedsDAO.getAll());
        return "animalsFeedings";
    }

    @RequestMapping(value = "/animals/feed/add", method = RequestMethod.GET)
    public ModelAndView redirectToAddAnimalType (Model model) {
        ModelAndView modelAndView = new ModelAndView("addAnimalFeed");
        if(model.asMap().get("animalFeed") != null) {
            modelAndView.addObject("animalFeed", model.asMap().get("animalFeed"));
        } else {
            modelAndView.addObject("animalFeed", new AnimalFeedDTO());
        }
        modelAndView.addObject("animalList", animalDAO.getAll());
        modelAndView.addObject("workerList", staffDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "animals/feed/add", method = RequestMethod.POST)
    public String addAnimalFeed(@ModelAttribute("animalFeed") AnimalFeedDTO animalFeed,
                                RedirectAttributes redirectAttributes) {
        try {
            animalFeedServicel.upsertAnimalFeed(animalFeed);
        } catch (AnimalException e ){
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("animalFeed",animalFeed);
            return "redirect:/animals/feed/add";
        }
        return "redirect:/animals/feeding";
    }

    @RequestMapping(value = "/animals/feed/{id}/edit", method = RequestMethod.GET)
    public ModelAndView redirectToAddAnimalType (@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editAnimalFeed");
        AnimalFeedDTO animalFeed = new AnimalFeedDTO(animalFeedsDAO.get(AnimalFeeds.class,id));
        modelAndView.addObject("animalFeed",animalFeed);
        modelAndView.addObject("animalList", animalDAO.getAll());
        modelAndView.addObject("workerList", staffDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/animals/feed/{id}/delete", method = RequestMethod.GET)
    public String deleteAnimalType (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        AnimalFeeds animalFeeds = new AnimalFeeds();
        animalFeeds.setId(id);
        try {
            animalFeedsDAO.delete(animalFeeds);
        } catch (JDBCException e ) {
            redirectAttributes.addFlashAttribute("error", e.getSQLException());
        }
        return "redirect:/animals/feeding";
    }

    @RequestMapping(value = "animals/feed/edit", method = RequestMethod.POST)
    public String editAnimalType(@ModelAttribute("animalFeed") AnimalFeedDTO animalFeed,
                                 RedirectAttributes redirectAttributes) {
        try {
            animalFeedServicel.upsertAnimalFeed(animalFeed);
        } catch (AnimalException e ){
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("animalFeed",animalFeed);
            return "redirect:/animals/feed/edit";
        }
        return "redirect:/animals/feeding";
    }
}
