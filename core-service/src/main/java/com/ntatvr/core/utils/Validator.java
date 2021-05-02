package com.ntatvr.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.math.NumberUtils;

import com.ntatvr.core.exceptions.BooleanFormatException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {

  private static final Long INVALID_NUMBER = 0L;

  public static void validateNumberFormat(final String value) {
    if (NumberUtils.toLong(value) <= INVALID_NUMBER) {
      throw new NumberFormatException(value + " isn't a number format.");
    }
  }

  public static void validateBooleanFormat(final String value) {
    if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
      throw new BooleanFormatException(value + " isn't a boolean format.");
    }
  }
}
