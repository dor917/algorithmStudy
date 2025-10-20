package org.example.stackqueue;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SameNumber {
    @Test
    public void test () {
        // given
        int[] arr1 = {1,1,3,3,0,1,1};
        int[] expected = {1,3,0,1};
        // when
        int[] result = solution(arr1);
        // then
        assertArrayEquals(expected, result);
    }
    /*
      스택을 사용하는 이유
      1. 이전 값과의 비교가 필요: 현재 원소가 바로 직전 원소와 같은지 확인
      2. LIFO 특성 활용: 스택의 top이 항상 "가장 최근에 추가된 값"이므로 peek()으로 쉽게 비교
      3. 효율적인 구현: O(n) 시간복잡도, O(n) 공간복잡도

     */
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for (int num: arr) {
            if (stack.isEmpty() || num != stack.peek()) {
                stack.push(num);
            }
        }
        int[] answer = stack.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        return answer;
    }
}
