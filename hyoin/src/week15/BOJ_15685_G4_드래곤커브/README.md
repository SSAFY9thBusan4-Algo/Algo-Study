
## 성능 요약
메모리: 11912 KB  
시간 : 84 ms  
소요시간 : 2시간  


## 접근 방법
1. 방향 구하기  
	1-1. 다음 방향은 이전 방향의 반시계 방향으로 90도 회전한 것  
	1-2. 최신 방향부터 과거 방향까지 역순으로 방향을 구해야함  
2. 꼭짓점 구하기  
	2-1. 1번에서 구한 방향으로 꼭짓점 구하고 map에 체크  
3. 정사각형 구하기  
	3-1. 완전탐색으로 인접한 4개의 꼭짓점 비교  


## 회고
아무리 봐도 문제를 이해하지 못해서 블참했다ㅜㅜ  
예제가 길어서 못봤는데 맨 밑에 힌트가 있었다..!  
다음엔 꼭 마지막 예제까지 훑어봐야겠다  
