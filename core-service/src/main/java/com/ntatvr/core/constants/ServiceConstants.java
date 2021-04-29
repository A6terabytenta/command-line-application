package com.ntatvr.core.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceConstants {

  public static final String INTRODUCTION = "Type 'quit' to exit at any time. Press 'Enter' to continue"
      + "\n\n"
      + "\tSelect search options:\n"
      + "\t* Press 1 to search\n"
      + "\t* Press 2 to view a list of searchable fields\n"
      + "\t* Type 'quit' to exit\n";
  public static final String SEARCH_MODELS = "Select 1) Users or 2) Tickets or 3) Organizations";
  public static final String SEARCH_TERM = "Enter search term";
  public static final String SEARCH_VALUE = "Enter search value";
}
