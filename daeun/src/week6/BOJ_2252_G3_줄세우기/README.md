# 2252 G3 줄 세우기
##### <p align="right"> 2023.03.07 📆 </p>

 
### 👩‍🏫 풀이
1. 두 학생의 키를 비교해 전체 학생을 줄 세우는 위상정렬 문제이다.
2. 학생 전체에 대한 비교가 아니라 인접리스트를 이용했다.
3. 방향 그래프를 입력받으면서 진입차수를 늘렸다.
4. 진입차수가 0이 되면 큐에 넣고, 큐가 빌 때까지 큐에서 뽑으면서 인접 노드를 처리한다.
5. 큐에서 뽑은 순서대로 ArrayList에 넣고 출력한다.


<br>

### ⏱️ 성능
<!-- 테이블 -->
성공 |메모리 | 시간 | 코드길이
---|---|---|---|
1|58058KB|484ms|1644B

<br>

#### PLUS 🔍
위상 정렬을 배운지 얼마 안 지을 때 풀어서 금방 푼 것 같다. 
근데 조금만 늦게 풀었어도 알고리즘 구현이 가물가물했을 것 같아서 유사한 문제를 더 풀어봐야겠다.
