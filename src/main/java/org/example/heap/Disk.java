package org.example.heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Disk {
    @Test
    public void test () {
        // given
        int[][] arr = {{0, 3}, {1, 9}, {3, 5}};
        // when
        int result = solution(arr);
        // then
        assertEquals(8, result);
    }

    public int solution(int[][] jobs) {
        int answer = 0;
        int currentTime = 0;
        int count = 0;
        int jobIndex = 0;
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // 대기시간 기준으로 정렬
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        while (count < jobs.length) {
            // 현재 시간까지 도착한 모든 작업을 힙에 추가
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= currentTime) {
                heap.offer(jobs[jobIndex]);
                jobIndex++;
            }
            if (heap.isEmpty()) {
                // 처리할 작업이 없으면 다음 작업의 시작 시간으로 이동
                currentTime = jobs[jobIndex][0];
            } else {// 소요시간이 가장 짧은 작업 처리
                int[] job = heap.poll();
                currentTime += job[1];  // 작업 완료 시간
                answer += currentTime - job[0];  // (완료 시간 - 요청 시간)
                count++;
            }
        }
        return answer / jobs.length;
    }
}
