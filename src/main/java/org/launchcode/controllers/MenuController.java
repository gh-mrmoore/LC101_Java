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
            return "cheese/add";
        } else {
            menuDao.save(newMenu);
            return "redirect:view/" + newMenu.getId();
        }
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable("id") String id) {
        model.addAttribute("title", "View Menu");
        int thisId = Integer.parseInt(id);
        Menu thisMenu = menuDao.findOne(thisId);
        model.addAttribute("menu", thisMenu);

        return "menu/view";
    }

    @RequestMapping(value = "add-item/{id}", method = RequestMethod.GET)
    public String displayAddMenuItemForm(Model model, @PathVariable("id") String id) {
        model.addAttribute("title", "Add Cheese Item to Menu");
        int thisId = Integer.parseInt(id);
        Menu modifyMenu = menuDao.findOne(thisId);
        Iterable <Cheese> cheeses = cheeseDao.findAll();
        AddMenuItemForm form = new AddMenuItemForm(modifyMenu, cheeses);
        model.addAttribute("form", form);

        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String processAddMenuItemForm(@ModelAttribute @RequestParam int cheeseId,
                                         @RequestParam int menuId, Errors errors,
                                         Model model) {
        Menu updatedMenu = menuDao.findOne(menuId);
        Cheese cheesesAdded = cheeseDao.findOne(cheeseId);

        updatedMenu.addItem(cheesesAdded);

        menuDao.save(updatedMenu);

        return "redirect:view/" + updatedMenu.getId();
    }

    /*
    @RequestMapping(value="add-item/{id}", method = RequestMethod.POST)
    public String processAddMenuItemForm(Model model, @ModelAttribute @Valid AddMenuItemForm newForm, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Please correct any errors listed below");
            return "menu/add-item";
        } else {
            //get the correct menu
            int updateMenuId = newForm.getMenuId();
            Menu updatedMenu = menuDao.findOne(updateMenuId);

            //get the correct cheese
            int updateAddedCheese = newForm.getCheeseId();
            Cheese cheesesAdded = cheeseDao.findOne(updateAddedCheese);

            updatedMenu.addItem(cheesesAdded);


            menuDao.save(updatedMenu);

            return "redirect:view/" + updatedMenu.getId();
        }
    }
    */


}
