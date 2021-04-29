package com.ntatvr.core.scanner;

import java.util.Scanner;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public abstract class AbstractHandler implements ScannerHandler {

  protected final Scanner scanner;
  public static final String QUIT_OPTION = "quit";

  @Override
  public String getInput(final boolean checkQuitOption) {
    if (scanner.hasNext()) {
      final String nextValue = scanner.next();
      if (checkQuitOption && nextValue.equals(QUIT_OPTION)) {
        scanner.close();
        System.exit(0); // NOPMD
      } else {
        return nextValue;
      }
    }
    return StringUtils.EMPTY;
  }
}
