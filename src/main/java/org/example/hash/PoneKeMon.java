package org.example.hash;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PoneKeMon {
    @Test
    public void test() {
        // given
        int[] nums = {3, 1, 2, 3};
        // when
        int result = solution(nums);
        // then
        assertEquals(2, result);
    }

    private int solution(int[] nums) {
        Set<Integer> set = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());
        return Math.min(set.size(), nums.length / 2);
    }
}

