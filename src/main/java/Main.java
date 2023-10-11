import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RequestorForgottenPassw requestor = new DBProcRequestForgottenPassw("jdbc:postgresql://localhost/testing?escapeSyntaxCallMode=callIfNoReturn");

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