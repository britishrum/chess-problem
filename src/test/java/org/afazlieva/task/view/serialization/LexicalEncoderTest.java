package org.afazlieva.task.view.serialization;

import org.afazlieva.task.view.serialization.impl.LexicalEncoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LexicalEncoderTest {

    private LexicalEncoder lexicalEncoder = new LexicalEncoder();

    @Test
    void testEncodeNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> lexicalEncoder.encode(-1));
    }

    @Test
    void testEncodeFirstOrderNumbers() {
        for (int i = 0; i < LexicalEncoder.CHARS.length(); i++) {
            assertEquals(String.valueOf(LexicalEncoder.CHARS.charAt(i)), lexicalEncoder.encode(i));
        }
    }

    @Test
    void testEncodeSecondOrderNumbers() {
        for (int i = 0; i < LexicalEncoder.CHARS.length(); i++) {
            int number = i + LexicalEncoder.CHARS.length();
            String expected = LexicalEncoder.CHARS.charAt(1) + "" + LexicalEncoder.CHARS.charAt(i);
            assertEquals(expected, lexicalEncoder.encode(number));
        }
    }

    @Test
    void testDecodeNull() {
        assertThrows(NullPointerException.class, () -> lexicalEncoder.decode(null));
    }

    @Test
    void testDecodeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> lexicalEncoder.decode(""));
    }

    @Test
    void testDecodeFirstOrderNumber() {
        for (int i = 0; i < LexicalEncoder.CHARS.length(); i++) {
            String string = LexicalEncoder.CHARS.substring(i, i + 1);
            assertEquals(i, lexicalEncoder.decode(string));
        }
    }

    @Test
    void testDecodeSecondOrderNumber() {
        for (int i = 0; i < LexicalEncoder.CHARS.length(); i++) {
            String string = LexicalEncoder.CHARS.charAt(1) + LexicalEncoder.CHARS.substring(i, i + 1);
            assertEquals(i + LexicalEncoder.CHARS.length(), lexicalEncoder.decode(string));
        }
    }

    @Test
    void testDecodeNonAlphabetic() {
        assertThrows(IllegalArgumentException.class, () -> lexicalEncoder.decode("123"));
    }
}
