
## 성능 요약
메모리: 11612 KB  
시간 : 80 ms  
소요시간 : 1시간 


## 접근 방법
1. 오름차순 정렬  
2. 음수와 양수 중 거리가 더 먼 곳을 가장 마지막에 방문(양수가 더 멀면 음수먼저 차례로 방문 후 양수 방문)  
3. 마지막쪽은 꼭 M개 채워서 가자(M으로 나누어 떨어지지 않으면 나머지만큼 가까운곳에 먼저 방문)  

예제 1) M = 2 & [-37 2 -6 -39 -29 11 -28]  
1. 정렬 후 -39, -37, -29, -28, -6, 2, 11  
2. 음수가 더 머니까 양수 먼저 방문  
=> 11까지 왕복, 6까지 왕복, 29까지 왕복, 39까지 편도  

예제 2) M = 3 & [-45, -26, -18, -9, -4, 22, 40, 50]  
=> 9까지 왕복, 45까지 왕복, 50까지 편도  

예제 3) M = 2 & [-1, 3, 4, 5, 6, 11] => 29  
=> -1까지 왕복, 3까지 왕복, 5까지 왕복, 11까지 편도  


## 회고
규칙을 찾는게 어려운 문제였다  
그리디이다 보니 규칙을 찾아도 해답인지 확신할 수 없어서 더 어려웠던 것 같다  
