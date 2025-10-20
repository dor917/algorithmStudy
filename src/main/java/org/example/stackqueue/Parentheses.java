package org.example.stackqueue;

import org.junit.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class Parentheses {
    @Test
    public void test () {
        // given
        String s = "()()";
        // when
        boolean result = solution(s);
        // then
        assertEquals(true, result);
    }
    @Test
    public void test2 () {
        // given
        String s = ")()(";
        // when
        boolean result = solution(s);
        // then
        assertEquals(false, result);
    }
    boolean solution(String s) {
        boolean answer = false;
        Stack<String> stack = new Stack<>();
        String[] arr = s.split("");
        try {
            for (String str : arr) {
                if (str.equals("(")) {
                    stack.push(str);
                } else if (str.equals(")")) {
                    stack.pop();
                }
            }
        } catch (EmptyStackException e) {
            return false;
        }
        if (stack.isEmpty()) {
            answer = true;
        }

        return answer;
    }
}
