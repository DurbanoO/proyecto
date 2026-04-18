package com.store.utils;

public enum MenuCategory {
    Home(" Home"),
    Special(" Special"),
    Blog(" Blog"),
    Mega_Menu(" Mega Menu"),
    AddOns(" AddOns");
//    JEWELRY("Jewelry"),
//    GIFT_CARDS("Gift Cards");

    private final String label;

    MenuCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
