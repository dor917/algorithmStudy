package org.example.hash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertArrayEquals;

public class Best {
    @Test
    public void 기본_테스트_장르별_최대_2곡_선택() {
        // given
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] expected = {4, 1, 3, 0};
        
        // when
        int[] result = solution(genres, plays);
        
        // then
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void 장르에_노래가_1개만_있는_경우() {
        // given
        String[] genres = {"pop", "classic"};
        int[] plays = {500, 600};
        int[] expected = {1, 0};
        
        // when
        int[] result = solution(genres, plays);
        
        // then
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void 재생횟수가_같을_때_인덱스_우선순위() {
        // given
        String[] genres = {"pop", "pop", "pop"};
        int[] plays = {100, 100, 100};
        int[] expected = {0, 1};
        
        // when
        int[] result = solution(genres, plays);
        
        // then
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void 여러_장르_복합_테스트() {
        // given
        String[] genres = {"jazz", "pop", "jazz", "classic", "pop", "classic"};
        int[] plays = {500, 600, 800, 900, 2500, 1000};
        int[] expected = {4, 1, 5, 3, 2, 0};
        
        // when
        int[] result = solution(genres, plays);
        
        // then
        assertArrayEquals(expected, result);
    }
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        HashMap<String, List<int[]>> map2 = new HashMap<>();

        // 1. 장르별 총 재생 횟수 & 노래 리스트 저장
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            map.put(genre, map.getOrDefault(genre, 0) + play);
            map2.computeIfAbsent(genre, k -> new ArrayList<>()).add(new int[]{i, play});
        }

        // 2. 장르를 총 재생 횟수 기준 내림차순 정렬
        List<String> sortedGenres = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 3. 각 장르별로 노래 정렬 후 최대 2곡 선택
        for (String genre : sortedGenres) {
            List<int[]> songs = map2.get(genre);
            
            // 재생횟수 내림차순 → 인덱스 오름차순 정렬
            songs.sort((a, b) -> {
                if (b[1] != a[1]) {  // 재생횟수가 다르면
                    return b[1] - a[1];  // 재생횟수 내림차순
                }
                return a[0] - b[0];  // 재생횟수가 같으면 인덱스 오름차순
            });
            
            // 최대 2곡 선택
            int count = Math.min(songs.size(), 2);
            for (int i = 0; i < count; i++) {
                answer.add(songs.get(i)[0]);  // 인덱스만 추가
            }
        }
        
        // 4. List를 int[]로 변환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
