package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Menu Home Page");
        model.addAttribute("menus", menuDao.findAll());

        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenuForm(Model model) {
        model.addAttribute("title", "Add a Menu");
        model.addAttribute(new Menu());

        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenuForm(Model model, @ModelAttribute @Valid Menu newMenu, Errors errors)  {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu - Error");
            model.addAttribute("errors", errors);
            return "menu/add";
        } else {
            menuDao.save(newMenu);
            return "redirect:view/" + newMenu.getId();
        }
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int id) {
    //public String viewMenu(Model model, @PathVariable int id ) {
        Menu menu = menuDao.findOne(id);
        model.addAttribute("title", menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menuId", menu.getId());

        /*
        model.addAttribute("title", "View Menu");
        int thisId = Integer.parseInt(id);
        Menu thisMenu = menuDao.findOne(thisId);
        model.addAttribute("menu", thisMenu);
        */

        return "menu/view";
    }

    @RequestMapping(value = "add-item/{id}", method = RequestMethod.GET)
    public String displayAddMenuItemForm(Model model, @PathVariable int id) {
        model.addAttribute("title", "Add Cheese Item to Menu");

        Menu modifyMenu = menuDao.findOne(id);

        //Iterable <Cheese> cheeses = cheeseDao.findAll();
        AddMenuItemForm form = new AddMenuItemForm(modifyMenu, cheeseDao.findAll());
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("MODIFYMENU OBJECT: " + modifyMenu);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("ADD-MENU-ITEM-FORM: " + form);

        model.addAttribute("form", form);

        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String processAddMenuItemForm(Model model,
                                         @RequestParam int menuId,
                                         @RequestParam int cheeseId,
                                         @ModelAttribute @Valid AddMenuItemForm form,
                                         Errors errors) {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("OBJECT RECEIVED: " + form);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("MENU_ID: " + menuId + " CHEESE_ID: " + cheeseId);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Please Correct Error(s)");
            model.addAttribute("errors", errors);

            return "menu/add-item";
        } else {
            //Cheese addedCheese = cheeseDao.findOne(form.getCheeseId());   ///how this SHOULD look
            Cheese addedCheese = cheeseDao.findOne(cheeseId);

            //Menu updatedMenu = menuDao.findOne(form.getMenuId());   //how this SHOULD look

            Menu updatedMenu = menuDao.findOne(menuId);

            updatedMenu.addItem(addedCheese);

            menuDao.save(updatedMenu);

            return "redirect:/menu/view/" + updatedMenu.getId();
        }
    }
}
