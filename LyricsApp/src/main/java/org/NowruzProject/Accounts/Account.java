package org.NowruzProject.Accounts;

import org.NowruzProject.AccountType;

public abstract class Account
{
    final private String username;
    final private String password;
    final private String email;
    final private String fullName;
    final private int age;
    final private AccountType accountType;


    //constructor
    public Account(String username , String password , String email , String fullName , int age , AccountType accountType){
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.accountType = accountType;
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    //check for equal password
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    //display Infos
    public void displayAccountInfo() {
        System.out.println("Username: " + username);
        System.out.println("Full Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
        System.out.println("Account Type: " + accountType);
    }


}
