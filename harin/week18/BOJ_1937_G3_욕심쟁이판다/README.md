### 성능
- 메모리 : 57920KB
- 시간 : 468ms

<br/>

### 풀이 방법 🐼 
- 판다는 이전 지역보다 대나무가 많은 지역으로 이동을 계속! 하므로 BFS가 아닌 DFS 방식을 선택해서 풀었다.
- 하지만 DFS 방식으로만 풀면 시간초과가 난다.
- 시간 초과를 해결하기 위해 메모이제이션 방식을 사용하여 중복된 곳을 방문하면 재탐색하는 것이 아닌 이전에 기록해 둔 값을 사용한다. 

<br/>

### 회고
- 처음에는 DFS만 사용해서 풀어서 시간초과가 났다. 메모이제이션으로 시간 초과 해결하는 게 생각이 안나서 블참을 했다 😥 
- 다음에는 블참 안하고 메모이제이션을 생각해 낼 수 있음 좋겠다 😄 
