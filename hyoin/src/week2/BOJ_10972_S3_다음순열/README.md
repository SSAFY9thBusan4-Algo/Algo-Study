
### 성능 요약
메모리: 18340 KB	
시간 : 192 ms

## 접근 방법
처음에 실습했던 것처럼 작성 했는데 메모리 초과가 나왔다..! 생각도 못했는데 아마 ArrayList로 순열을 다 저장해서 그런 것 같아 이번엔 재귀를 돌면서 결과를 String변수에 저장했다.<br>
덕분에 메모리 초과는 해결됐는데 시간초과 에러가 나왔다... 그래서 결과값을 찾으면 바로 return하게 만들었는데도 시간초과가 나왔다 ㅠ<br>
곰곰이 생각해보니 현재 순열의 시간복잡도는 nPn=n!인데 n이 100000까지이므로 택도 없는 접근방법이었다..^^<br>


그래서 주어진 순열에서 규칙을 찾으려고 했고 아래와 같은 규칙을 발견했다.<br>
기본으로 역순(오른쪽에서 왼쪽)으로 탐색
1. 현재 숫자 > 다음숫자(왼쪽숫자)
2. 현재 숫자 기준으로 오른쪽(현재 숫자 포함)과 왼쪽으로 구역을 나눔
3. 위치교환<br>
3-1. 역순으로 탐색하면서 오른쪽 구역에서 왼쪽 숫자보다 큰 숫자 탐색(현재 숫자 제외)<br>
3-2. 오른쪽에서 탐색된 숫자와 왼쪽의 마지막 숫자와 교환 
4. 오른쪽 구역을 오름차순으로 정렬

### 회고
재귀로 순열을 짜는 것을 주입식으로 교육받았더니 당연히 그래야하는 줄 알아서 문제를 푸는데 오래걸렸다.<br>
앞으로는 주입식 생각에서 벗어나자!<br>
시간복잡도 계산도 미리 해두자<br>
