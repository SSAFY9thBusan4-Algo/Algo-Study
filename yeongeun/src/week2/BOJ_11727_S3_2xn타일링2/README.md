# Week2

## BOJ_11727_S3_2xn타일링2

### 문제

```
2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
```

<br>

### 아이디어
1. n번째 경우 : n-1번째경우에 | 하나 붙인거 + n-2번째 경우에 = 또는 ㅁ 붙인거
	- 그럼 n = (n-1) + (n-2)*2인가? ㄴㄴ 숫자 커지면 틀림. int값이 20억까지니까.
2. (a+b)mod n = ((a mod n) + (b mod n)) mod n 이거 써서 풀자.

<br>

### 결과

|메모리|시간|
|:---:|:---:|
|14312 KB|220 ms|