# 1005 G3 ACM Craft
##### <p align="right"> 2023.03.31 📆 </p> 

 
### 👩‍🏫 풀이
1. 건설 순서가 우선되는 것이 있어서 위상정렬을 했다.
2. 위상 정렬을 하면서 큐에서 꺼낼 때 dp로 했다.
3. dp에 있는 값과 지금 온 값 중 더 큰 시간을 저장하면 문제에서 원하는 결과를 얻을 수 있다.

<br>

### ⏱️ 성능
<!-- 테이블 -->
성공 |메모리 | 시간 | 코드길이
---|---|---|---|
1|298596KB|852ms|1757B

<br>

#### PLUS 🔍
 아니 처음에는 배웠던 위상정렬 그대로 풀어보겠다고 list로 반환했다가 연결되어 있는 걸 제대로 보기 어려워서 한참 헤맸다..
하루 1시간 45분.. 하고 실패..
하고 다음날 1시간 정도? 바꿨더니 정답이 나왔다!
위상정렬 문제 잘 안 풀었어서 어려웠다ㅠ