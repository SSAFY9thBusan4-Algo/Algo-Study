# Solution

** 구현 **
- 각 열을 순서데로 빗물이 고일 수 있는지 탐색한다. 첫째, 마지막 열은 빗물이 고일 수 없으므로 제외
- 현재 열의 왼쪽 오른쪽에서 각각 제일 높게 쌓인 블록이 높이를 구한다
- 두 높이 중 작은 것과 현재 열의 블록 높이의 차가 그 열에 고이는 빗물의 양이다
- 모들 열에서 위 과정으로 빗물의 양을 더해 고이는 빗물의 총량을 반환한다.


</br>

|메모리|시간|
|---|---|
|14608 KB|136 ms|
</br>

++ 복잡한 조건문을 만들어 풀다가 계속 틀렸다.. 풀이를 생각할때 더 단순하고 깔끔한 풀이과정을 생각해낼 수 있도록 노력해야겠다 </br>
++ 풀이시간 1시간 반