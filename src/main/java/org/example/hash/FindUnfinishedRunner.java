package org.example.hash;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FindUnfinishedRunner {

    @Test
    public void test () {
        // given
        String[] arr1 = {"leo", "kiki", "eden"};
        String[] arr2 = {"eden", "kiki"};
        // when
        String result = solution(arr1, arr2);
        // then
        assertEquals("leo", result);
    }
    @Test
    public void test2 () {
        // given
        String[] arr1 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] arr2 = {"josipa", "filipa", "marina", "nikola"};
        // when
        String result = solution(arr1, arr2);
        // then
        assertEquals("vinko", result);
    }
    public String solution(String[] participant, String[] completion) {
        int result = 0;

        // arr1의 모든 문자열 hashCode XOR
        for (String str : participant) {
            result ^= str.hashCode();
        }

        // arr2의 모든 문자열 hashCode XOR
        for (String str : completion) {
            result ^= str.hashCode();
        }

        // xor 결과와 일치하는 문자열 찾기
        for (String str : participant) {
            if (str.hashCode() == result) {
                return str;
            }
        }

        return "";
    }
}
