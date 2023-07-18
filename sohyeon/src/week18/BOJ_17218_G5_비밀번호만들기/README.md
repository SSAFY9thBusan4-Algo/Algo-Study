# Solution

**LCS(최장 공통 부분수열)**
- 두 문자열의 길이를 행,열로 갖는 dp배열을 만든다
- 아래와 같은 조건으로 dp 배열에 값을 채운다
<br/>
```
if (s1[i-1]==s2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
```
<br/>
- 마지멱 행,열위치에서 부터 해당 r,c위치의 문자(s1[r], s2[c])가 같으면  result에 문자를 더하고(r-1,c-1)로 이동, 다르면 dp[r][c-1]<dp[r-1][c]비교후 true면 (r-1,c)로 false면 (r,c-1)로 이동
- result 출력

</br>

|메모리|시간|
|---|---|
|16228 KB|152 ms|
