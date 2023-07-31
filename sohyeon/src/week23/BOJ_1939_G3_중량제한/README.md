# Solution

**bfs**
- 우선순위 큐에 시작 섬과 인접한 섬과 그 섬까지의 비용(minw)을 Node 객체에 담아 넣는다. minw는 우선순의 큐 안에서 내림차 순으로 정렬하기 위한 기준이된다.
- 큐에서 node를 poll 했을 때 minw가 옮길 수 있는 최대 충량인 result 보다 작으면 continue,
아니면 인접한 노드까지의 비용과 minw중 더 작은 값을 minw로하는 새로운 node를 큐에 offer. poll한 node의 vertex가 도착 섬(end)이면 poll.minw와 result를 비교에서 더 큰 값으로 result 갱신 
- 큐가 비면 result 반환 

</br>

|메모리|시간|
|---|---|
|59708 KB|616 ms|
