package lv.autentica.controllres.cage;

import lv.autentica.DAO.animal.AnimalDAO;
import lv.autentica.DAO.animal.AnimalTypeDAO;
import lv.autentica.DAO.cage.CageDAO;
import lv.autentica.DAO.cage.CageLoadDAO;
import lv.autentica.domain.animal.Animal;
import lv.autentica.domain.animal.AnimalType;
import lv.autentica.domain.cages.Cage;
import lv.autentica.dto.cage.CageLoadDTO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by german on 4/17/16 for MyTi project.
 */
@Controller

public class CageLoadController {

    @Autowired
    CageLoadDAO cageLoadDAO;

    @Autowired
    CageDAO cageDAO;

    @Autowired
    AnimalDAO animalDAO;

    @Autowired
    AnimalTypeDAO animalTypeDAO;

    @RequestMapping(value = "/cage/load", method = RequestMethod.GET)
    @Transactional
    public String redirectToCageLoad(Model model) {
        model.addAttribute("cageLoads", cageLoadDAO.getAll());
        return "cageLoadList";
    }

    @RequestMapping(value = "/cage/load/add", method = RequestMethod.GET)
    @Transactional
    public ModelAndView redirectToAddCageLoad(Model model) {
        ModelAndView modelAndView = new ModelAndView("addCageLoad");
        if(model.asMap().get("cageLoad") != null) {
            modelAndView.addObject("cageLoad", model.asMap().get("cageType"));
        } else {
            modelAndView.addObject("cageLoad", new CageLoadDTO());
        }
        modelAndView.addObject("cageList", cageDAO.getAll());
        modelAndView.addObject("animalList", animalDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/cage/load/cage/{id}/animals", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody
    String deleteCage(@PathVariable("id") Long id) {
        JSONObject resultJson = new JSONObject();
        Cage cage = cageDAO.get(Cage.class,id);
        resultJson.put("capacity",cage.getCapacity());
        return resultJson.toJSONString();
    }

}
