# 16197 G4 두 동전
##### <p align="right"> 2023.06.14 📆 </p> 

 
### 👩‍🏫 풀이
1. 주어진 보드를 입력받으면서 동전을 따로 저장한다.
2. BFS 탐색을 이용하여 최소 횟수를 구한다.
3. 현재 동전 2개를 큐에 넣고, 해시값으로 방문처리를 진행했다.
4. 큐에서 꺼낸 동전들을 각각 사방탐색을 진행한다.
4-1. 1번 동전만 떨어진 경우와 2번 동전만 떨어진 경우 종료해야 하기 대문에 현재 횟수를 리턴한다.
4-2. 두 동전이 다 떨어진 경우나, 둘 다 벽으로 간다면 넘어간다.
4-3. 한 동전의 이동 후 위치가 벽이라면, 이동 전 위치가 다른 동전의 이동 후 위치와 겹친다면 넘어가고 벽이면 이동 전 위치로 바꿔준다.
4-4. 현재 위치에 방문했었는지 확인하고, 방문하지 않았다면 큐에 넣고 방문처리를 진행한다.

<br>

### ⏱️ 성능
<!-- 테이블 -->
성공 |메모리 | 시간 | 코드길이
---|---|---|---|
1|11620KB|76ms|2404B

<br>

#### PLUS 🔍
조건을 잘 처리한다면 어렵지는 않은 문제였던 것 같다.
하지만 생각보다는 생각할 요소가 많은..?ㅋㅋ
그래도 1시간 안 걸려서 푼 것 같고,
지난번에 다른 스터디원 코드로 배운 해시값을 이용한 방문처리를 해봤다!
