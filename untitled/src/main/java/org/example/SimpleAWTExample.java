package org.example;

import java.awt.*;
import javax.swing.*;

public class SimpleAWTExample {
    public static void main(String[] args) {
        // ایجاد یک پنجره جدید
        Frame frame = new Frame("AWT Example");

        // ایجاد یک دکمه
        Button button = new Button("Click Me");

        // افزودن دکمه به پنجره
        frame.add(button);

        // تنظیم اندازه پنجره
        frame.setSize(300, 200);

        // نمایش پنجره
        frame.setVisible(true);
    }
}
