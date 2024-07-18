package exercise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReversedSequenceTest {
    @Test
    void testReversedSequence() {
        CharSequence seq = new ReversedSequence("abcdef");

        // Проверяем длину
        assertEquals(6, seq.length());

        // Проверяем символы на соответствующих позициях
        assertEquals('f', seq.charAt(0));
        assertEquals('e', seq.charAt(1));
        assertEquals('d', seq.charAt(2));
        assertEquals('c', seq.charAt(3));
        assertEquals('b', seq.charAt(4));
        assertEquals('a', seq.charAt(5));

        // Проверяем подстроку
        assertEquals("edc", seq.subSequence(1, 4).toString());

        // Проверяем строковое представление
        assertEquals("fedcba", seq.toString());
    }
}
