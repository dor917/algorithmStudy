package org.example.stackqueue;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;

public class Function {
    @Test
    public void test () {
        // given
        int[] arr1 = {93, 30, 55};
        int[] arr2 = {1, 30, 5};
        int[] expected = {2, 1};
        // when
        int[] result = solution(arr1,arr2);
        // then
        assertArrayEquals(expected, result);
    }
    /*
    아무리 생각해도 굳이 큐를? 사용해야할까
    FIFO(First In First Out) 특성

    작업이 순서대로 처리되는 것을 명확하게 표현
    poll()로 앞에서부터 하나씩 꺼내는 것이 직관적
     */
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            queue.add((int) Math.ceil((100.0 - progresses[i]) / (double) speeds[i]));
        }
        int day = queue.poll();
        int cnt = 1;
        while (!queue.isEmpty()) {
            if (day >= queue.peek()){
                cnt++;
                queue.poll();
            } else {
                list.add(cnt);
                day = queue.poll();
                cnt=1;
            }
        }
        list.add(cnt);
        return list.stream().mapToInt(i->i).toArray();
    }
}
