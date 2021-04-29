package com.ntatvr.core.scanner;

import java.util.Scanner;

public class SearchableFieldsHandler extends AbstractHandler {

  public SearchableFieldsHandler(final Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute() {
    System.out.println("Print Searchable Fields");
  }
}
