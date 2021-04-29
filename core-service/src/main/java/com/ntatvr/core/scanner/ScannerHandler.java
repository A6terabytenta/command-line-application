package com.ntatvr.core.scanner;

public interface ScannerHandler {

  String getInput(boolean stopImmediately);

  void execute();
}
