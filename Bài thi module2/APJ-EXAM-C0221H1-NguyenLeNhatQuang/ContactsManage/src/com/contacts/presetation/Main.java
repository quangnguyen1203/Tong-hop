package com.contacts.presetation;

import com.contacts.service.ContactService;
import com.contacts.service.MenuService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        ContactService contactService = new ContactService();
        try {
            contactService.loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuService.performContact();
    }
}
