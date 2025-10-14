package org.example.hash;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
public class Clothes {
    @Test
    public void test () {
        // given
        String[][] arr1 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        // when
        int result = solution(arr1);
        // then
        assertEquals(5, result);
    }
    @Test
    public void test2 () {
        // given
        String[][] arr1 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
        // when
        int result = solution(arr1);
        // then
        assertEquals(3, result);
    }
    public int solution(String[][]  clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }

        for (int count : map.values()) {
            answer *= (count + 1);
        }
        return answer - 1;
    }
}
