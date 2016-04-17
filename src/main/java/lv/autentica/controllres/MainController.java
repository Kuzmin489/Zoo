package lv.autentica.controllres;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String redirectToMainPage(HttpServletRequest req) {
        return "redirect:/staff";
    }
}

