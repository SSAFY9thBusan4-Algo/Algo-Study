# Week5 SWEA_4014_역량_활주로건설

## 문제

```
경사로의 길이 X 와 절벽지대의 높이 정보가 주어질 때,
활주로를 건설할 수 있는 경우의 수를 계산하는 프로그램을 작성하라.
```
<br>

## 접근방법
2중포문 가로방향, 세로방향으로 확인한다.
- 오르막길 : 이전값, 이전값이 중복해서 나온 갯수를 저장하고, 값이 바뀔 때 이전값이 X개를 충족하는지, 높이가 1인지를 확인한다.
- 내리막길 : 내려왔다면 -> 높이가 1인지 확인. 다음것도 x개만큼 같은 값인지 확인.

두가지를 만족해야한다.


<br>

## 실행 흐름
가로방향
1. 행 반복문
	
	이전값, 이전값이 반복 된 횟수 초기화 (0번째 값으로)

	2. 열 반복문

		- 만약 현재값이 이전값이랑 같다면 반복횟수++ 다음칸으로
		- 만약 1 올랐다면
			
			이전 반복 횟수가 가로길이 X 를 만족하는가.

			만족하면 이전값, 반복횟수 초기화하고 next, 아니면 break.
		- 만약 1 내렸다면

			다음에 X개만큼 같은 번호가 나와야 한다.

			만족하면 이전값, 반복횟수 초기화하고 next, 아니면 break.
		- 1 차이가 아니면

			만족하지 못하니까 break.
	
		현재 열에 활주로 설치 가능하면 result++




<br>

## 구현 시 어려웠던 점
복붙 했다가 i,j 안바꾼 부분이 있었다. 끝까지 꼼꼼하게 확인하기


<br>

## 결과

|메모리|시간|
|:---:|:---:|
|28,768 KB|204 ms|