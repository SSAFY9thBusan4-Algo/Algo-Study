# Week3

## BOJ_9084_G5_동전

### 문제

```
우리나라 화폐단위, 특히 동전에는 1원, 5원, 10원, 50원, 100원, 500원이 있다. 
이 동전들로는 정수의 금액을 만들 수 있으며 그 방법도 여러 가지가 있을 수 있다. 
예를 들어, 30원을 만들기 위해서는 1원짜리 30개 또는 10원짜리 2개와 5원짜리 2개 등의 방법이 가능하다.

동전의 종류가 주어질 때에 주어진 금액을 만드는 모든 방법을 세는 프로그램을 작성하시오.
```
<br>

### 아이디어
1. 조합으로 하면 N이 20까지 올 수 있으니까 시간이 너무 오래 걸림.
2. 그럼 DP로 풀어야하는데. 점화식을 어떻게 세워야 하는가.
	1. 1원을 가지고 1원~M원까지 만들 수 있는 방법은 모두 1가지씩 이다.
		
		- f1(1) = 1, f1(2) = 1, ... , f1(M) = 1.

	2. 5원을 가지고는. 5의 배수가 되는 돈을 만들 수 있다. (+1가지)

		- f2(5) = 1, f2(10) = 1, ...
	3. 1원과 5원을 가지고 6원을 만든다면 5원+1원.

		- f2(6) = f1(1), f2(7) = f1(2), ...
	
	- 이를 일반식으로 이렇게 적을 수 있다.
		- list(n) = N 일 때,
		- fn(N) = 1
		- fn(m) = fn-1(m-N) (단, m > n)
		- result(M) = f1(M) + f2(M) + ... + fn(M)
	

<br>

### 문제를 풀면서
- 처음 문제를 읽었을 때는 DP로 풀라고 했을때 방법이 바로 생각나지 않아서 문제를 미루고 미뤘다. 근데 놀고 와서 문제를 풀라고 하니까 방법이 떠올라서 문제를 후다닥 풀 수 있었다.! 역시 리프레쉬가 중요한 것 같다!!


<br>

### 결과

|메모리|시간|
|:---:|:---:|
|14288 KB|128 ms|