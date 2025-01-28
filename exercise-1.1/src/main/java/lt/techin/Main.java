package lt.techin;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean stop = false;
    while (!stop) {
      System.out.println("Yes or no?");
      String input = scanner.nextLine().trim().toLowerCase();

      switch (input) {
        case "yes" -> {
          continue;
        }
        case "no" -> {
          stop = true;
        }
      }


    }
  }
}