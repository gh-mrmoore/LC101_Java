package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;

    public int getId() {
        return id;
    }

    //constructors
    public Menu() { }

    public Menu(String name) { this.name = name; }


    //getter-setter pairs
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    //method to add items to menu
    public void addItem(Cheese item) {
        cheeses.add(item);
    }

    @Override
    public String toString() {
        return "MENU-ID: " + id + " CHEESES: " + cheeses;
    }

}
