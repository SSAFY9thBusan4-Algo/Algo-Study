# 7576 G5 토마토
##### <p align="right"> 2023.02.11 📆 </p>

 
### 👩‍🏫 풀이
1. 익은 토마토의 좌표를 큐에 넣고 꺼낸다.
2. 꺼낸 토마토에 인접한 칸에 안 익은 토마토가 있으면 익게하고 그 토마토의 좌표를 큐에 넣는다.
3. 그 과정을 큐가 빌 때까지 반복한다.
4. 끝나고 전체를 탐색하며 안 익은 토마토가 있으면 -1을 출력하고, 
창고 안의 토마토가 다 익은 토마토라면 day에 저장해 둔 값 중 최댓값을 출력한다.


<br>

### ⏱️ 성능
<!-- 테이블 -->
성공 |메모리 | 시간 | 코드길이
---|---|---|---|
1|126352KB|772ms|1889B

<br>

#### PLUS 🔍
큐를 배우자마자 문제를 풀어서 골드지만 쉽게 푼 것 같다.
<br>
큐를 생각 못 했으면 이중 for문으로 계속 사방탐색하고 있었을테니...
