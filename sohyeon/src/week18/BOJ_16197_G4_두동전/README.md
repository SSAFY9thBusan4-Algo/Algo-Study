# Solution

**BFS**
- 동전 두개의 위치를 방문처리해야 하므로 4차원 배열 사용
- bfs로 두 동전의 위치를 방문처리하며 4방향으로 for문으로 이동해보고 벽을 만나는 경우 제자리로 바꾼 뒤 두 동전 다 떨어진 경우, 하나만 떨어진 경우, 둘 다 떨어지지 않은 경우로 나눠서 결과를 반환하거나 bfs 재귀를 반복한다
<br/>
<br/>
...버튼을 누른 수가 10번을 넘은 경우에 result=-1을 하지 않고 return해서 시간을 많이 썼다.. 

</br>

|메모리|시간|
|---|---|
|14400 KB|128 ms|
