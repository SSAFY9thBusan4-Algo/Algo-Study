
## 성능 요약
메모리: 19716 KB  
시간 : 232 ms  
소요시간 : 1시간 30분 


## 접근 방법
map에 치즈는 1, 밀폐된 공기는 0, 외부 공기와 접촉하는 공기는 -1로 저장
1. 처음에는 (0,0)부터 상하좌우 인접한 좌표들을 탐색하면서 공기 중 외부 공기와 접촉하는 값을 -1로 변경(가장자리엔 치즈가 올 수 없으므로)
2. 2변 이상이 외부공기(-1)와 접촉하는 치즈를 찾아서 공기(0)으로 변경
3. 2번에서 찾은 치즈가 공기(0)으로 변했으니 해당 치즈 좌표들부터 다시 1~2번 진행
4. 더이상 2번에서 찾은 치즈가 없으면 break


## 회고
처음에는 밀폐된 공기와 외부 공기와 접촉하는 공기를 어떻게 구분할 지 생각하는게 힘들었다  
다행히 가장자리에는 치즈가 없다는 조건 덕분에 (0,0)부터 공기끼리 연결된 부분까지만 외부 공기와 접촉하는 공기인 사실을 생각해낼 수 있었다    
일단 성공을 목표로 짜다보니 메모리와 시간이 많이 나갔었는데 불필요한 배열과 조건처리를 해주니 훨씬 좋은 성능이 나와 행복했다!  
