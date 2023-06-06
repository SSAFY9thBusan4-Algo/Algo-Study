# Solution

**DP**
- 포도중 양 배열에 저장(list)
- dp배열을 만들어서 1번째 칸은  list[1], 2번째 칸은 list[1]+list[2] 저장
- 3번째 칸부터 dp[i-1], dp[i-3]+list[i-1]+list[i], dp[i-2]+list[i] 중 최대값 저장
- dp[n]이 최대로 마실 수 있는 포도주의 양

</br>

|메모리|시간|
|---|---|
|15308 KB|148 ms|
