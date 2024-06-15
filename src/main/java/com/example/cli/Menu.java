package com.example.cli;

import com.example.exceptions.IncorrectInput;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private static Command[] commands = new Command[]{
            new AddChild(),
            new AddGroup(),
            new ReadChildren(),
            new ReadGroups(),
            new UpdateChild(),
            new UpdateGroup(),
            new RemoveChild(),
            new RemoveGroup(),
    };

    public static void run() throws IncorrectInput, SQLException {
        while (true) {
            System.out.println("Choose activity:");
            System.out.println("0. Exit");
            for (int i = 0; i < commands.length; i++) {
                System.out.println(i + 1 + ". " + commands[i].getCommandName());
            }
            Scanner scanner = new Scanner(System.in);
            int k = scanner.nextInt();
            if (k == 0) {
                break;
            }
            commands[k - 1].execute();
        }
    }
}
