# Solution

**투포인터**
- A와B C와D 모든 짝을 곱한 값을 preSum배열에 담고 preSum[0], preSum[1]을 정렬한다
- preSum[0]에는 p1, preSum[1]에는 p2를 포인터로 사용해 두 포인터를 인덱스로 하는 두 원소의 합이 0인 모든 경우의 수를 구한다. p1는 0에서 부터 +방향으로 p2는 n*n-1에서 부터 -방향으로 이동한다
- while문안에서 p1<n*n && p2>=0일 동안 반복
- preSum[0][p1]+preSum[1][p2]==0 이면 p1을 +방향으로, p2를 -방향으로 움직이며 같은 값의 수를 세서 count한 두 값을 곱해서 result에 더함
- preSum[0][p1]+preSum[1][p2]<0 이면 p1++;
- preSum[0][p1]+preSum[1][p2]>0 이면 p2--;
- while문 끝나면 result 출력

</br>

|메모리|시간|
|---|---|
|166112 KB|4100 ms|
