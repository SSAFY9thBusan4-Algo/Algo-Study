## Week12 BOJ 13549 G5 숨바꼭질3

### 접근방법
bfs 시간순으로, 방문처리

현재 위치에서 갈 수 있는 위치 (*2, +1, -1)을 큐에 넣는다.
이때, 큐는 PQ를 사용하여 시간순으로 정렬한다.
K에 도착했을 때 걸린 시간을 출력한다.

visited배열에 초를 저장하여서 가장 작을 경우에만 다음 위치로 방문한다.


<br>

### 결과

|메모리|시간|
|:---:|:---:|
|21752 KB|300 ms|

문제 조건을 잘 확인하자.