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
```

---

## 학습 도구
- Claude CLI 설치 완료 (`claude --version`: 2.0.22)
- IntelliJ IDEA 연동

---

## 통계
- 해시: 5문제 완료
- 스택/큐: 3문제 완료
