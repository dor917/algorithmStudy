package org.example.stackqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Bridge {
    @Test
    public void test () {
        // given
        int bl = 2;
        int[] arr = {7, 4, 5, 6};
        int weight = 10;
        // when
        int result = solution(bl, weight, arr);
        // then
        assertEquals(8, result);
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        Queue<Integer> trucks = Arrays.stream(truck_weights)
                                        .boxed()
                                        .collect(Collectors.toCollection(LinkedList::new));

        int currentWeight = 0;
        while (!bridge.isEmpty() || !trucks.isEmpty()) {
            answer ++;
            int out = bridge.poll();
            currentWeight -= out;
            if (!trucks.isEmpty()) {
                if (currentWeight + trucks.peek() <= weight) {
                    int truck = trucks.poll();
                    bridge.offer(truck);
                    currentWeight += truck;
                } else {
                    bridge.offer(0);
                }
            }
        }
        return answer;
    }
}
