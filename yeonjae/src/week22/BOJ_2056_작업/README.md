# 2056 G4 작업

### 📂 풀이
1. 위상 정렬을 이용하였다.
2. 위상 정렬을 하고 방문 순서를 파악하는 것이 아니라 최소 시간을 구하는 문제이다.
3. 그래서 간단하게 DP를 이용하여 이전 노드의 최소 시간에서 현재 노드의 작업에 걸리는 시간을 더하는 형식으로 정답을 도출했다.