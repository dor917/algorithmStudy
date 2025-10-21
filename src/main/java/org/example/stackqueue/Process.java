package org.example.stackqueue;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class Process {
    @Test
    public void test () {
        // given
        int[] arr = {2, 1, 3, 2};
        int num = 2;
        // when
        int result = solution(arr,num);
        // then
        assertEquals(1, result);
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int k = 0;
        int[] prioritiesDesc = Arrays.stream(priorities)
                                        .boxed()
                                        .sorted(Comparator.reverseOrder())
                                        .mapToInt(Integer::intValue)
                                        .toArray();
        Queue<ProcessVo> queue = IntStream.range(0, priorities.length)
                                            .mapToObj(i -> new ProcessVo(priorities[i], i))
                                            .collect(Collectors.toCollection(LinkedList::new));
        while (!queue.isEmpty()) {
            ProcessVo processVo = queue.poll();
            if (processVo.priority == prioritiesDesc[k]) {
                k++;
                answer++;
                if (location == processVo.index) {
                    return answer;
                }
            } else {
                queue.offer(processVo);
            }
        }

        return answer;
    }
    class ProcessVo {
        int priority;
        int index;  // 원래 위치

        ProcessVo(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }
    }
}
