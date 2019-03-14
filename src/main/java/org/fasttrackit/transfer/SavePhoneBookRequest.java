package org.fasttrackit.transfer;

public class SavePhoneBookRequest {

    private String name;
    private String surname;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "SavePhoneBookRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneBook='" + phoneNumber + '\'' +
                '}';
    }
}

