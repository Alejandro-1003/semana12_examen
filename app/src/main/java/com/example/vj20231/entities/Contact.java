package com.example.vj20231.entities;

public class Contact {
    private int id;
    private String nameContact;
    private String imgContact;

    public Contact(int id, String nameContact, String imgContact) {
        this.id = id;
        this.nameContact = nameContact;
        this.imgContact = imgContact;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getImgContact() {
        return imgContact;
    }

    public void setImgContact(String imgContact) {
        this.imgContact = imgContact;
    }
}
