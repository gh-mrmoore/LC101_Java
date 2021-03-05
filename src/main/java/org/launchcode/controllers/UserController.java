package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDao usersDao;

    @RequestMapping(value = "")
    public String index(Model model) {


        model.addAttribute("title", "User Sign-In for Cheese");
        model.addAttribute("users", usersDao.findAll());

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
                                 Errors errors) {
        model.addAttribute("title", "Process New User Input");

        String password = user.getPassword();
        String verify = user.getVerify();

        if (errors.hasErrors()) {
            //User fixUser = new User();
            model.addAttribute("title", "Fix errors in form input");
            model.addAttribute("errors", errors);
            model.addAttribute("user", user);
            return "user/add";
        } else if (password.equals(verify) == false) {
            //User fixUser = new User();
            model.addAttribute("title", "Passwords did not match");
            model.addAttribute("errors", errors);
            model.addAttribute("user", user);
            return "user/add";
        } else {
            usersDao.save(user);
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
