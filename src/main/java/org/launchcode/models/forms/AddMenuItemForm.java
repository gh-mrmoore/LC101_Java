package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {
    @NotNull
    private Menu menu;

    @NotNull
    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

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
}
