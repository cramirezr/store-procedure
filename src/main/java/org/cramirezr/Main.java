package org.cramirezr;

import org.cramirezr.procedures.DBProcRequestForgottenPassw;
import org.cramirezr.procedures.RequestorForgottenPassw;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Please provide db url, user and password as parameters");
            System.exit(0);
        } else {
            final String dbUrl = args[0];
            final String user = args[1];
            final String password = args[2];

            Scanner sc = new Scanner(System.in);
            RequestorForgottenPassw requestor = new DBProcRequestForgottenPassw(dbUrl, user, password);

            System.out.println("Please enter user id: ");
            int userId = sc.nextInt();

            int result = requestor.requestForgottenPassword(userId);

            if (result == 0) {
                System.out.println("Request for forgotten password failed for user " + userId);
            } else {
                System.out.println("Request for forgotten password was succesful for user " + userId);
            }
        }
    }
}