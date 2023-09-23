package com.example.a31b;

public class Users {

    public String lastname;
    public String firstname;
    public String middlename;
    public String username;
    public String address;
    public String birthday;
    public String gender;
    public String contact;
    public String email;
    public String password;
    public String userID;

    public Users(){

    }

    public Users(String lastname, String firstname, String middlename,
            String username, String address, String birthday,
            String gender, String contact, String email,
            String password, String userID){

        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.username = username;
        this.address = address;
        this.birthday   = birthday;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.userID = userID;
    }
}
