package org.launchcode.controllers;

import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "User Sign-In for Cheese");

        return"user/index";
    }

    @RequestMapping(value = "add")
    public String displayAddForm(Model model) {
        model.addAttribute("title", "Add New User");
        model.addAttribute(new User());

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(Model model,
                                 @ModelAttribute @Valid User user,
                                 Errors errors,
                                 @RequestParam String verify) {
        model.addAttribute("title", "Process New User Input");

        String password = user.getPassword();
        if (errors.hasErrors()) {
            User fixUser = new User();
            model.addAttribute("user", fixUser);
            model.addAttribute("errors", errors);
            model.addAttribute("title", "Fix errors in form input");
            System.out.println("ERRORS");
            System.out.println(errors);
            return "user/add";
        } else if (password.equals(verify) == false) {
            User userFixit = new User();
            model.addAttribute("user", userFixit);
            model.addAttribute("title", "Passwords did not match");
            System.out.println("PASSWORD");
            return "user/add";
        } else {
            model.addAttribute("title", "Welcome New User!");
            model.addAttribute("user", user.getUsername());
            System.out.println("OK");
            return "user/index";
        }

        /*
        if (password.equals(verifypassword)) {
            model.addAttribute("title", "Welcome User");
            String currentUser = user.getUsername();
            model.addAttribute("user", user);
            System.out.println(user);
            System.out.println(user.getUsername());
            System.out.println(currentUser);
            return "user/index";
        } else {
            return "user/add";
        }
        */
    }

}
