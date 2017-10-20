package Run;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner str_reader = new Scanner(System.in);
        System.out.println(("Login:"));
        String user = str_reader.next();

        System.out.println("Hello " + user + "!");

    }
}