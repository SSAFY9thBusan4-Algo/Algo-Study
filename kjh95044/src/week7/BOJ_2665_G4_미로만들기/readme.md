### 풀이
- 처음 생각은 방문 체크를 하면서 만나는 흰방의 갯수를 세면 되나 생각했다.
- 그렇게 하면 최단 경로를 구할수는 있지만, 색을 바꾸는 최소값은 구할수 없었다.
- 그래서 방문 체크를 할때 여기까지 색을 바꿔온 값중에 최소값을 저장해줬다.
- visited의 값을 비교해가면서 최소값을 갱신하고, 모든 경우를 다 돌고나면 목적지 방의 visited 값을 출력했다.

### 성능
메모리: 11988 KB, 시간: 92 ms

### 추가
- 한시간 반정도 걸렸다.
- 다크모드로 헀더니 처음에 흰색이랑 검은색이 뒤바껴서 헷갈렸다..ㅋㅋ