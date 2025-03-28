package org.NowruzProject.Accounts;

import java.util.*;
import java.util.regex.*; //check for valid password and email

public class AccountManager {
    private Map<String, Account> accounts; // Save accounts by username

    public AccountManager() {
        this.accounts = new HashMap<>();
    }

    // check validity of email
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9]+[a-zA-Z0-9._%+-]+[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // check for set a valid password
    private boolean isValidPassword(String password) {
        String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //sign in new Account
    public boolean register(String username, String password, String email, String fullName , int age ,String accountType) {
        //check username
        if (accounts.containsKey(username)) {
            System.out.println("This username is already exist");
            return false;
        }

        // validity of email
        if (!isValidEmail(email)) {
            System.out.println("Email is not available");
            return false;
        }

        // validity of password
        if (!isValidPassword(password)) {
            System.out.println("Username should be at least 8 characters with numbers and {!,@,#,$,%,%,^,&,*} ");
            return false;
        }

        // create new account
        Account newAccount;
        switch (accountType.toLowerCase()) {
            case "user":
                newAccount = new User(username, password, email, fullName, age);
                break;
            case "artist":
                newAccount = new Artist(username, password, email, fullName, age);
                break;
            case "admin":
                newAccount = new Admin(username, password, email, fullName, age);
                break;
            default:
                System.out.println("Account type is not exist");
                return false;
        }

        accounts.put(username, newAccount);
        System.out.println("Sign in is complete : " + username);
        return true;
    }

    // login
    public Account login(String username, String password) {
        Account account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            System.out.println("Sign in successfully : " + username);
            return account;
        } else {
            System.out.println("Username or password is incorrect");
            return null;
        }
    }

    // logout
    public void logout(Account account) {
        if (account != null) {
            System.out.println("Logout successfully : " + account.getUsername());
        } else {
            System.out.println("You haven't logged in");
        }
    }

    // get Account by username
    public Account getUserByUsername(String username) {
        return accounts.get(username);
    }
}
