# 알고리즘 공부 📚

## 해시(Hash)

### 푼 문제
- ✅ Lv.1 - 완주하지 못한 선수
- ✅ Lv.1 - 폰켓몬
- ✅ Lv.2 - 전화번호 목록
- ✅ Lv.2 - 의상
- ✅ Lv.3 - 베스트앨범

### 핵심 개념
**언제 해시를 사용할까?**
- 빈도수 계산이 필요할 때
- 중복 제거가 필요할 때
- 빠른 검색(O(1))이 필요할 때
- 키-값 쌍으로 데이터를 관리할 때
- 그룹핑/분류가 필요할 때

**자주 사용하는 패턴**
```java
// 빈도수 계산
map.put(key, map.getOrDefault(key, 0) + 1);

// 그룹핑
map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);

// 중복 제거
Set<Integer> set = Arrays.stream(arr).boxed().collect(Collectors.toSet());

// Map 정렬 (value 기준)
map.entrySet().stream()
   .sorted(Map.Entry.comparingByValue().reversed())
```

**주요 학습 내용**
- 조합 문제: 각 종류별 +1 후 곱셈, 마지막 -1
- 접두어 비교: 정렬 후 인접 요소만 확인
- 2차 정렬 기준 구현 (재생횟수 내림차순 → 인덱스 오름차순)
- `computeIfAbsent()`로 간결한 코드 작성

---

## 스택/큐(Stack/Queue)

### 푼 문제
- ✅ Lv.1 - 같은 숫자는 싫어
- ✅ Lv.2 - 기능개발
- ✅ Lv.2 - 올바른 괄호
- ✅ Lv.2 - 프로세스
- ✅ Lv.2 - 다리를 지나는 트럭
- ✅ Lv.2 - 주식가격

### 핵심 개념

**같은 숫자는 싫어**
- 스택의 LIFO 특성 활용하여 이전 값과 비교
- `stack.peek()`으로 가장 최근 값 확인
- 연속된 중복 제거: 현재 값이 top과 다를 때만 push
- Stack을 int[]로 변환: `stack.stream().mapToInt(Integer::intValue).toArray()`

**기능개발**
- 각 작업의 완료 일수 계산: `Math.ceil((100.0 - progress) / speed)`
- **중요**: 올림 처리 필수! 정수 나눗셈은 내림되므로 double로 계산
- 큐로 순서대로 처리하며 배포 그룹 카운팅
- 기준일을 초과하면 카운트를 결과에 추가하고 새로운 배포 시작
- 큐 대신 배열 인덱스로도 해결 가능 (큐는 교육 목적)

**올바른 괄호**
- 스택의 LIFO 특성 활용
- 여는 괄호 `(` → push, 닫는 괄호 `)` → pop
- 예외 처리: 스택이 비어있을 때 pop 시도, 최종 스택이 비지 않은 경우
- 최적화: 스택 대신 카운터(int) 사용 가능

**자주 사용하는 패턴**
```java
// 스택으로 이전 값 비교
if (stack.isEmpty() || num != stack.peek()) {
    stack.push(num);
}

// Stack을 int[]로 변환
int[] array = stack.stream().mapToInt(Integer::intValue).toArray();

// 올림 처리
int days = (int) Math.ceil((100.0 - progress) / (double) speed);

// 큐로 순차 처리
int deployDay = queue.poll();
int count = 1;
while (!queue.isEmpty()) {
    if (queue.peek() <= deployDay) {
        count++;
        queue.poll();
    } else {
        result.add(count);
        deployDay = queue.poll();
        count = 1;
    }
}

// 배열 내림차순 정렬
Arrays.sort(arr, Collections.reverseOrder());  // 객체 배열
int[] sorted = Arrays.stream(numbers)
    .boxed()
    .sorted(Comparator.reverseOrder())
    .mapToInt(Integer::intValue)
    .toArray();  // 기본형 배열

// 배열 → Queue 변환
Queue<Integer> queue = new LinkedList<>(Arrays.asList(arr));
Queue<Integer> queue = Arrays.stream(arr)
    .boxed()
    .collect(Collectors.toCollection(LinkedList::new));
```

**프로세스**
- Queue에 (우선순위, 원래 인덱스)를 함께 저장하는 VO 클래스 활용
- `stream().anyMatch()`로 더 높은 우선순위 존재 확인
- 있으면 Queue 뒤로 보내고, 없으면 실행 처리
- location과 일치하는 프로세스가 실행될 때 순서 반환

```java
class ProcessVo {
    int priority;
    int index;
    ProcessVo(int priority, int index) {
        this.priority = priority;
        this.index = index;
    }
}

Queue<ProcessVo> queue = new LinkedList<>();
for (int i = 0; i < priorities.length; i++) {
    queue.offer(new ProcessVo(priorities[i], i));
}

while (!queue.isEmpty()) {
    ProcessVo current = queue.poll();
    boolean hasHigher = queue.stream()
        .anyMatch(p -> p.priority > current.priority);
    
    if (hasHigher) {
        queue.offer(current);
    } else {
        count++;
        if (current.index == location) return count;
    }
}
```

**다리를 지나는 트럭**
- 다리를 Queue로 표현 (길이만큼 0으로 초기화)
- 매 초마다 시뮬레이션: 다리에서 하나 빠짐 → 새 트럭 진입 시도
- `currentWeight` 변수로 다리 위 총 무게 관리
- 무게 제한 확인 후 진입 가능하면 트럭 추가, 불가능하면 0 추가

```java
Queue<Integer> bridge = new LinkedList<>();
for (int i = 0; i < bridge_length; i++) {
    bridge.offer(0);
}

int time = 0, currentWeight = 0;
while (조건) {
    time++;
    int out = bridge.poll();
    currentWeight -= out;
    
    if (!trucks.isEmpty() && currentWeight + trucks.peek() <= weight) {
        int truck = trucks.poll();
        bridge.offer(truck);
        currentWeight += truck;
    } else {
        bridge.offer(0);
    }
}
```

**주식가격**
- 각 시점마다 가격이 떨어지지 않은 기간 구하기
- **방법 1: 이중 반복문** - 직관적, O(n²)
  - i 시점 이후를 순회하며 가격이 떨어지는 순간 break
  - 떨어지지 않으면 끝까지 카운트

```java
for (int i = 0; i < n; i++) {
    int count = 0;
    for (int j = i + 1; j < n; j++) {
        count++;
        if (prices[i] > prices[j]) break;  // 떨어지면 종료
    }
    answer[i] = count;
}
```

- **방법 2: Stack 활용** - 효율적, O(n)
  - Stack에 "아직 답을 구하지 못한 시점의 인덱스" 저장
  - 가격이 떨어지는 순간 해당 시점의 답 계산: `i - idx`
  - 끝까지 Stack에 남은 것 = 끝까지 안 떨어진 것

```java
Stack<Integer> stack = new Stack<>();
for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
        int idx = stack.pop();
        answer[idx] = i - idx;  // 떨어진 시점까지의 기간
    }
    stack.push(i);
}

while (!stack.isEmpty()) {
    int idx = stack.pop();
    answer[idx] = n - 1 - idx;  // 끝까지 안 떨어진 경우
}
```

**Stack & Queue 주요 메서드**
- `push(value)` / `offer(value)`: 추가
- `pop()` / `poll()`: 꺼내서 제거 (반환 + 제거)
- `peek()`: 맨 위/앞 보기만 (제거 X)
- `isEmpty()`: 비어있는지 확인

---

## 힙(Heap)

### 푼 문제
- ✅ Lv.2 - 더 맵게
- ✅ Lv.3 - 디스크 컨트롤러

### 핵심 개념

**힙을 사용하는 이유**
- **시간 복잡도**: 반복적인 최솟값/최댓값 찾기가 필요할 때
  - 일반 배열 + 정렬: O(N² log N) ❌
  - 힙 사용: O(N log N) ✅
- **효율적인 우선순위 관리**: 삽입/삭제 후 자동으로 힙 구조 유지
- **대표적 사용 사례**: 
  - 반복적으로 최솟값/최댓값 꺼내기
  - 우선순위 큐 구현
  - 작업 스케줄링

**Java에서 힙 사용하기 - PriorityQueue**

```java
// 1. 최소 힙 (기본값 - 작은 숫자가 먼저)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// 2. 최대 힙 (큰 숫자가 먼저)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// 3. 커스텀 정렬 (절댓값 기준)
PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> 
    Math.abs(a) - Math.abs(b)
);
```

**배열을 힙으로 변환**

```java
// int[] 배열 → PriorityQueue
int[] scoville = {1, 2, 3, 9, 10, 12};

// 방법 1: boxed() 사용
PriorityQueue<Integer> heap = Arrays.stream(scoville)
                                    .boxed()
                                    .collect(Collectors.toCollection(PriorityQueue::new));

// 방법 2: forEach 사용
PriorityQueue<Integer> heap = new PriorityQueue<>();
Arrays.stream(scoville).forEach(heap::offer);
```

**2차원 배열을 힙으로 변환**

```java
int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};

// 첫 번째 원소 기준 정렬
PriorityQueue<int[]> heap = Arrays.stream(jobs)
    .collect(Collectors.toCollection(() -> 
        new PriorityQueue<>((a, b) -> a[0] - b[0])
    ));

// 다중 조건 정렬 (1순위: a[1], 2순위: a[0])
PriorityQueue<int[]> heap = Arrays.stream(jobs)
    .collect(Collectors.toCollection(() -> 
        new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];  // 1순위
            return a[0] - b[0];  // 2순위
        })
    ));

// Comparator.comparing 사용 (더 명확)
PriorityQueue<int[]> heap = new PriorityQueue<>(
    Comparator.comparingInt((int[] a) -> a[1])  // 소요시간
              .thenComparingInt(a -> a[0])       // 요청시각
);
```

**더 맵게 문제**
```java
public int solution(int[] scoville, int K) {
    PriorityQueue<Integer> heap = Arrays.stream(scoville)
                                        .boxed()
                                        .collect(Collectors.toCollection(PriorityQueue::new));
    
    int count = 0;
    
    while (heap.peek() < K) {
        if (heap.size() < 2) {
            return -1;
        }
        
        int first = heap.poll();
        int second = heap.poll();
        int newScoville = first + (second * 2);
        
        heap.offer(newScoville);
        count++;
    }
    
    return count;
}
```

**디스크 컨트롤러 문제 - 핵심 포인트**

❌ **흔한 실수**
```java
// 처음부터 모든 작업을 힙에 넣으면 안 됨!
PriorityQueue<int[]> heap = Arrays.stream(jobs)
    .collect(Collectors.toCollection(() -> 
        new PriorityQueue<>((a, b) -> a[1] - b[1])
    ));
// 문제: 아직 도착하지 않은 작업을 먼저 처리하게 됨
```

✅ **올바른 접근**
```java
public int solution(int[][] jobs) {
    // 1. 요청 시각 기준으로 정렬
    Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
    
    // 2. 소요시간 기준 힙 (처리할 작업들)
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    
    int currentTime = 0;  // 현재 시간
    int totalTime = 0;    // 총 대기+처리 시간
    int jobIndex = 0;     // 다음에 확인할 작업 인덱스
    int count = 0;        // 처리한 작업 수
    
    while (count < jobs.length) {
        // 현재 시간까지 도착한 모든 작업을 힙에 추가
        while (jobIndex < jobs.length && jobs[jobIndex][0] <= currentTime) {
            heap.offer(jobs[jobIndex]);
            jobIndex++;
        }
        
        if (heap.isEmpty()) {
            // 처리할 작업이 없으면 다음 작업의 시작 시간으로 이동
            currentTime = jobs[jobIndex][0];
        } else {
            // 소요시간이 가장 짧은 작업 처리
            int[] job = heap.poll();
            currentTime += job[1];  // 작업 완료 시간
            totalTime += currentTime - job[0];  // (완료 시간 - 요청 시간)
            count++;
        }
    }
    
    return totalTime / jobs.length;
}
```

**디스크 컨트롤러 - 핵심 차이점**

| 항목 | 잘못된 방법 | 올바른 방법 |
|------|------------|------------|
| 작업 추가 시점 | 처음에 전부 | 현재 시간까지 도착한 것만 |
| 초기 정렬 | 없음 | 요청 시각 순 정렬 필수 |
| 시간 관리 | 불명확 | currentTime으로 명확히 관리 |
| 대기시간 계산 | 복잡한 식 | `currentTime - job[0]` |
| 공백 시간 처리 | 없음 | 다음 작업까지 시간 점프 |

**PriorityQueue 주요 메서드**
- `offer(element)` / `add(element)`: 힙에 추가
- `poll()`: 최솟값/최댓값 꺼내기 (제거)
- `peek()`: 최솟값/최댓값 확인만 (제거 X)
- `size()`: 힙의 크기
- `isEmpty()`: 비어있는지 확인

**학습 포인트**
- 힙은 **반복적인 최솟값/최댓값 처리**에 최적화
- 작업 스케줄링 문제는 **도착 시간 고려**가 핵심
- 도착하지 않은 작업을 미리 처리하면 안 됨
- 변수 이름을 명확히 (`currentTime`, `totalTime`)
- 공백 시간(처리할 작업 없음) 처리 필수

---

## 학습 도구
- Claude CLI 설치 완료 (`claude --version`: 2.0.22)
- IntelliJ IDEA 연동

---

## 통계
- 해시: 5문제 완료
- 스택/큐: 6문제 완료
- 힙: 2문제 완료
---