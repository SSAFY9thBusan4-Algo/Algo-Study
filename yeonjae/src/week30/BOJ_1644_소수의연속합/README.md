# 1644 G3 소수의연속합

### 📂 풀이
1. 투포인터를 이용하여 풀었다.
2. 우선 소수를 구하기 위해서 에라토스테네스 채를 이용하였다.
3. 그리고 소수들만 저장된 배열을 통해 구간의 합을 이용하였다.
4. from과 to라는 2개의 포인터를 기준으로 구간의 합이 작으면 to를 증가시키고 구간의 합이 크면 from을 증가시킨다.
5. 그 와중에 구간의 합이 N이면 정답인 ans의 카운트를 증가시켜주었다.