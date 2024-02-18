package org.example.model;

import java.util.ArrayList;

public class Popup{
    private ArrayList<MenuItem> menuitem;

    public ArrayList<MenuItem> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(ArrayList<MenuItem> menuitem) {
        this.menuitem = menuitem;
    }
    public void setMenuitem(String value, String onclick){
        MenuItem menuItem = new MenuItem();
        menuItem.setValue(value);
        menuItem.setOnclick(onclick);
        menuitem.add(menuItem);
    }

    public MenuItem getMenuItem(int index){
        return menuitem.get(index);
    }
}
