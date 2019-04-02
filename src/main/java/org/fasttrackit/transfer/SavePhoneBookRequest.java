package org.fasttrackit.transfer;

public class SavePhoneBookRequest {

    private String id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SavePhoneBookRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneBook='" + phoneNumber + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

