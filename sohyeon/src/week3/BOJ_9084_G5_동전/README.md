## Solution

**다이나믹 프로그래밍**
- 주어진 동전들을 오름차순으로 하나씩 선택(for1)해 DP배열(size = M+1)을 동전과 같은 수의 idx 부터 순회하며 M원을 만드는 경우의 수를 갱신한다(for2).
- 점화식은 다음과 같다. (coin : 현재 선택된 동전, i: 동전들로 만들어지는 금액)
> dp[i] += dp[i-coin]  
(dp[0] = 1)
- dp[M+1]가 주어진 동전들로 금액 M을 만들수 있는 경우의 수

</br>

|메모리|시간|
|---|---|
|14140 KB|128 ms|