package org.NowruzProject.Dashboards;
import org.NowruzProject.Accounts.Account;

import java.util.Scanner;

public abstract class Dashboard {
    protected Account account;
    protected Scanner scanner = new Scanner(System.in);

    public Dashboard(Account account) {
        this.account = account;
    }

    public abstract void showMenu();

    public void start() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer
            if (!handleChoice(choice)) break;  //if logout exit from loop
        }
    }

    protected abstract boolean handleChoice(int choice);
}
