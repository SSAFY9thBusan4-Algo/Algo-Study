### 문제 해석
1. 맥주 20개가 들어있는 한 박스를 가지고 출발
2. 50m 걸을때마다 한병씩 마신다
3. 거리가 1000넘으면 안된다.
4. 편의점에 들렸을 때 박스의 맥주를 새 맥주로 전부 교체 할수 있다.
5. -32768 ≤ x, y ≤ 32767

### 풀이
1. 편의점에 가기만 하면 20개의 맥주가 전부 충전 되기때문에 거리가 1000이하이면 편의점을 갈 수 있다.
2. 1000이하의 거리에 있는 편의점을 queue에 넣어주고, 하나씩 꺼내면서 목적지까지 1000 이하의 거리이면 happy를 출력한다.

### 성능
메모리 : 12844 시간 : 112

### 추가
처음에 왼쪽위에서 오른쪽 밑 순서로 정렬을 진행했었는데, 그럴 필요가 없었다. 오히려 정렬하는데 시간이 추가로 들어서 삭제했다.