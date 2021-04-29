package com.ntatvr.core.scanner;

import java.util.Scanner;

public class SearchHandler extends AbstractHandler {

  private static final String SEARCH_MODEL_OPTIONS = "\nPlease select search model:\n"
      + "* Press 1 to search Users\n"
      + "* Press 2 to search Tickets\n"
      + "* Press 3 to search Organizations";

  public static final String SEARCH_TERM = "Enter search term";
  public static final String SEARCH_VALUE = "Enter search value";

  public SearchHandler(final Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute() {
    System.out.println(SEARCH_MODEL_OPTIONS);
    switch (getInput(true)) {
      case "1":
        System.out.println("Selected model Users");
        break;
      case "2":
        System.out.println("Selected model Tickets");
        break;
      case "3":
        System.out.println("Selected model Organizations");
        break;
      default:
        System.err.println("Selected model is invalid. Please try again.");
        execute();
        break;
    }
  }
}
