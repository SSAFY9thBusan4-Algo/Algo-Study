# Solution

**재귀, 큐**
- 익은 토마토의 위치를 큐에 저장, 익지 않은 토마토 개수 count
- 재귀문에서 익은 토마토 큐의 크기가 0이 아니면 큐의 크기만큼 poll()해서 익은 토마토들의 위치에서 다음날 익을 수 있는 토마토들의 위치를 큐에 담고 익지 않은 토마토의 개수를 -1
- 위와 반대로 익은 토마토 큐의 크기가 0이면 전날 익은 토마토가 없으므로 재귀가 끝나며 익지 않은 토마토의 개수가 0보다 크면 -1, 아니면 재귀문의 반복수(cnt)를 출력

</br>

|메모리|시간|
|---|---|
|124732	KB|652 ms|