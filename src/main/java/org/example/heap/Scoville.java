package org.example.heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Scoville {

    @Test
    public void test () {
        // given
        int[] arr = {7, 4, 5, 6};
        int weight = 7;
        // when
        int result = solution(arr, weight);
        // then
        assertEquals(2, result);
    }
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = Arrays.stream(scoville)
                .boxed()
                .collect(Collectors.toCollection(PriorityQueue::new));

        while (!pq.isEmpty()) {
            if (pq.peek() >= K) {
                return answer;
            }
            if (pq.size() < 2) {
                return -1;
            }

            int first = pq.poll();
            int second = pq.poll();
            int newAnswer = first + (second*2);
            pq.offer(newAnswer);
            answer ++;
        }
        return answer;
    }
}
