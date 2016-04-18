package lv.autentica.controllres.cage;

import lv.autentica.DAO.animal.AnimalDAO;
import lv.autentica.DAO.animal.AnimalTypeDAO;
import lv.autentica.DAO.cage.CageDAO;
import lv.autentica.DAO.cage.CageLoadDAO;

import lv.autentica.domain.cages.Cage;
import lv.autentica.domain.cages.CageLoad;
import lv.autentica.dto.cage.CageLoadDTO;
import lv.autentica.service.cage.CageLoadService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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

    @Autowired
    CageLoadService cageLoadService;

    @RequestMapping(value = "/cage/load", method = RequestMethod.GET)
    @Transactional
    public String redirectToCageLoad(Model model) {
        List<CageLoad> badList = cageLoadDAO.getAll();
        List<CageLoad> goodList = badList.stream().distinct().collect(Collectors.toList());
        model.addAttribute("cageLoads", goodList);
        return "cageLoadList";
    }

    @RequestMapping(value = "/cage/load/add", method = RequestMethod.GET)
    @Transactional
    public ModelAndView redirectToAddCageLoad(Model model) {
        ModelAndView modelAndView = new ModelAndView("addCageLoad");
        if(model.asMap().get("cageLoad") != null) {
            modelAndView.addObject("cageLoad", model.asMap().get("cageLoad"));
        } else {
            modelAndView.addObject("cageLoad", new CageLoadDTO());
        }
        modelAndView.addObject("cageList", cageDAO.getAll());
        modelAndView.addObject("animalList", animalDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/cage/load/add", method = RequestMethod.POST)
    public String addCageType(@ModelAttribute("cageType") CageLoadDTO cageLoad, RedirectAttributes redirectAttributes) {
        try {
            cageLoadService.upsertCageLoad(cageLoad);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("cageLoad",cageLoad);
            return "redirect:/cage/load/add";
        }
        return "redirect:/cage/load";
    }

    @RequestMapping(value = "/cage/load/{id}/edit", method = RequestMethod.GET)
    @Transactional
    public ModelAndView editCageLoad(@PathVariable("id") Long id,Model model) {
        ModelAndView modelAndView = new ModelAndView("editCageLoad");
        CageLoad cageLoad = cageLoadDAO.get(CageLoad.class,id);
        CageLoadDTO cageDTO = new CageLoadDTO(cageLoad);
        model.addAttribute("cageLoad", cageDTO);
        modelAndView.addObject("cageList", cageDAO.getAll());
        modelAndView.addObject("animalList", animalDAO.getAll());
        return  modelAndView;
    }

    @RequestMapping(value = "/cage/load/edit", method = RequestMethod.POST)
    public String editCageType(@ModelAttribute("cageType") CageLoadDTO cageLoad, RedirectAttributes redirectAttributes) {
        try {
            cageLoadService.upsertCageLoad(cageLoad);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",e);
            redirectAttributes.addFlashAttribute("cageLoad",cageLoad);
            return "redirect:/cage/load/edit";
        }
        return "redirect:/cage/load";
    }


    @RequestMapping(value = "/cage/load/{id}/delete", method = RequestMethod.GET)
    public String deleteCageType(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        CageLoad cageLoad = new CageLoad();
        cageLoad.setId(id);
        try {
            cageLoadDAO.delete(cageLoad);
        } catch (Exception e ){
            redirectAttributes.addFlashAttribute("error",e.getCause());
        }
        return "redirect:/cage/load";
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
