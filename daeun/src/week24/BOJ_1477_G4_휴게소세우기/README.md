# 1477 G4 휴게소 세우기
##### <p align="right"> 2023.07.28 📆 </p> 

 
### 👩‍🏫 풀이
1. 이분탐색을 이용하여 풀었다.
2. 주어진 고속도로의 위치들과 시작과 끝을 배열에 저장하고 정렬한다.
3. 이분탐색을 이용하여 휴게소 사이에 mid 길이로 세울 경우 몇개를 세울 수 있는지 확인한다.
4. 해당 개수가 반드시 지어야 하는 개수가 넘을 경우 시작 지점을 뒤로,
	 해당 개수가 모자라는 경우 끝 지점을 앞으로 당겨 이분탐색을 진행한다.
5. 이분탐색이 끝나면, 휴게소가 없는 구간의 최댓값이 최소가 되는 값을 찾을 수 있다.

<br>

### ⏱️ 성능

성공 |메모리 | 시간 | 코드길이
---|---|---|---|
1|11540KB|84ms|1156B

<br>

#### PLUS 🔍
이분탐색.. 다른 요소가 들어가니까 생각보다는 어렵다

