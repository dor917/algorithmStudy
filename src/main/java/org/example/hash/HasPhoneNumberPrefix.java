package org.example.hash;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
public class HasPhoneNumberPrefix {
    @Test
    public void test () {
        // given
        String[] arr1 = {"119", "97674223", "1195524421"};
        // when
        boolean result = solution(arr1);
        // then
        assertEquals(false, result);
    }

    @Test
    public void test2 () {
        // given
        String[] arr1 = {"123","456","789"};
        // when
        boolean result = solution(arr1);
        // then
        assertEquals(true, result);
    }
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return answer;
    }
}
