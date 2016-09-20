package pl.natekrank.web;

import org.springframework.security.core.Authentication;
import pl.natekrank.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registration(Authentication authentication, Model model) {

        return "admin/index";
    }
}
