# Solution

**BFS**

- 2차원 배열에 음식물이 떨어진 위치를 표시한다
- 배열의 행과 열을 이동하며 bfs탐색을 한다.(이미 방문 처리된 위치에서는 탐색하지 않는다)
- bfs로 인접한 음식물들을 찾을 때 방문처리를 하고 queue에 좌표 배열을 넣을 때 마다 cnt값을 +1한다. 큐가 비면 더이상 인접한 음식물이 없으므로 result와 비교해 값이 더 크면 result 갱신

</br>

|메모리|시간|
|---|---|
|17896 KB|216 ms|
</br>

++ 방문처리 배열 위치를 잘못 해서 메모리 초과가 떴다. 푸는데 걸린 시간은 30분 정도