package org.afazlieva.task.view.serialization.impl;

import java.util.Objects;

public class LexicalEncoder {
    public final static String CHARS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Encodes a positive integer number to base26 alphabetic format
     *
     * @param number a positive integer
     * @return base 26 alphabetic representation
     */
    public String encode(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Can't encode negative numbers");
        } else if (number == 0) {
            return String.valueOf(CHARS.charAt(number));
        } else {
            StringBuilder sb = new StringBuilder();
            while (number > 0) {
                int remainder = number % CHARS.length();
                sb.append(CHARS.charAt(remainder));
                number = number / CHARS.length();
            }
            return sb.reverse().toString();
        }
    }

    /**
     * Decodes a positive integer number from base26 alphabetic format
     *
     * @param string base 26 alphabetic representation of a positive integer
     * @return positive integer
     */
    public int decode(String string) {
        Objects.requireNonNull(string);
        if (string.isEmpty()) throw new IllegalArgumentException();
        string = string.toLowerCase();
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(string.length() - i - 1);
            int charValue = CHARS.indexOf(c);
            if (charValue < 0) {
                throw new IllegalArgumentException("Unknown character at index " + i);
            }
            if (i > 0) charValue = charValue * (CHARS.length() * i);
            result += charValue;

        }
        return result;
    }
}
