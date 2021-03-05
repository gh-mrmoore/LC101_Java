package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {
    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    private Menu menu;

    private Iterable<Cheese> cheeses;


    //getters, aka accessors

    public Menu getMenu() {
        return menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public int getMenuId() { return menuId; }

    public int getCheeseId() { return cheeseId; }

    //constructors
    public AddMenuItemForm() { }

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }
    @Override
    public String toString() {
        return "MENU-ID: " + menuId + " CHEESE-ID " + cheeseId + " MENU " + menu + " CHEESES: " + cheeses;
    }
}
