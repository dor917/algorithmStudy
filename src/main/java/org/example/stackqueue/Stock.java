package org.example.stackqueue;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertArrayEquals;

public class Stock {
    @Test
    public void test () {
        // given
        int[] arr1 = {1, 2, 3, 2, 3};
        int[] expected = {4, 3, 1, 1, 0};
        // when
        int[] result = solution(arr1);
        // then
        assertArrayEquals(expected, result);
    }
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 현재 가격이 Stack에 있는 가격보다 낮으면
            // = 그 시점의 가격이 드디어 떨어진 것!
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }

            stack.push(i);
        }

        // Stack에 남은 것들 = 끝까지 안 떨어진 것들
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = n - 1 - idx;
        }

        return answer;
    }
}
