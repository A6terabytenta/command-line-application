package com.ntatvr.core.scanner;

import java.util.Scanner;


public class MainHandler extends AbstractHandler {

  private static final String INTRODUCTION = "\nType 'quit' to exit at any time. Press 'Enter' to continue";
  private static final String INTRODUCTION_OPTIONS = "\nSelect search options:\n"
      + "* Press 1 to search\n"
      + "* Press 2 to view a list of searchable fields\n"
      + "* Type 'quit' to exit";

  private final SearchHandler searchHandler;
  private final SearchableFieldsHandler searchableFieldsHandler;

  public MainHandler(final Scanner scanner) {
    super(scanner);
    searchHandler = new SearchHandler(scanner);
    searchableFieldsHandler = new SearchableFieldsHandler(scanner);
  }

  @Override
  public void execute() {
    System.out.println(INTRODUCTION);
    boolean isContinue = true;

    do {
      System.out.println();
      System.out.println(INTRODUCTION_OPTIONS);
      final String input = getInput(false);
      switch (input) {
        case "1":
          searchHandler.execute();
          break;
        case "2":
          searchableFieldsHandler.execute();
          break;
        case QUIT_OPTION:
          isContinue = false;
          break;
        default:
          System.err.println("Selected option is invalid. Please try again.");
          break;
      }
    } while (isContinue);
  }
}
