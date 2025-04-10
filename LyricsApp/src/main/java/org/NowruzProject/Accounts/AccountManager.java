package org.NowruzProject.Accounts;

import java.util.*;
import java.util.regex.*; //check for valid password and email

import static org.NowruzProject.ColoredOutput.GREEN;
import static org.NowruzProject.ColoredOutput.RED;

public class AccountManager {
    private Map<String, Account> accounts;// Save accounts by username
    private static List<Account> admintlist = new ArrayList<>();
    private static List<Admin> pendingAdmins = new ArrayList<>();

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
            System.out.println(RED+"This username is already exist");
            return false;
        }

        // validity of email
        if (!isValidEmail(email)) {
            System.out.println(RED+"Email is not available");
            return false;
        }

        // validity of password
        if (!isValidPassword(password)) {
            System.out.println(RED+"Username should be at least 8 characters with numbers and {!,@,#,$,%,%,^,&,*} ");
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
                accounts.put(username, newAccount);
                requestAdminApproval((Admin) newAccount);
                System.out.println(GREEN + "Admin account created but needs approval.");
                return true;

            default:
                System.out.println(RED+"Account type is not exist");
                return false;
        }

        accounts.put(username, newAccount);
        System.out.println(GREEN+"Sign in is complete : " + username);
        return true;
    }

    // login
    public Account login(String username, String password) {
        Account account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            System.out.println(GREEN+"Sign in successfully : " + username);
            return account;
        } else {
            System.out.println(RED+"Username or password is incorrect");
            return null;
        }
    }

    // logout
    public void logout(Account account) {
        if (account != null) {
            System.out.println(GREEN+"Logout successfully : " + account.getUsername());
        } else {
            System.out.println(RED+"You haven't logged in");
        }
    }

    // get Account by username
    public Account getUserByUsername(String username) {
        return accounts.get(username);
    }


    public static void addAdmin(Admin admin) {
        admintlist .add(admin);
    }

    public void addAccount(Account account) {
        if (!accounts.containsKey(account.getUsername())) {
            accounts.put(account.getUsername(), account);
        }
    }




    public void requestAdminApproval(Admin admin) {
        pendingAdmins.add(admin);
    }

    public static List<Admin> getPendingAdmins() {
        return pendingAdmins;
    }

    public static void approveAdmin(Admin admin) {
        AccountManager.addAdmin(admin);
        pendingAdmins.remove(admin);
        System.out.println("Admin " + admin.getUsername() + " has been approved!");
    }


}