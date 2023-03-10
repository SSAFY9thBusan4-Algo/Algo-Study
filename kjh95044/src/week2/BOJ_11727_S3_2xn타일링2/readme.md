# [Silver III] 2×n 타일링 2 - 11727 

[문제 링크](https://www.acmicpc.net/problem/11727) 

### 성능 요약

메모리: 14412 KB, 시간: 124 ms

### 분류

다이나믹 프로그래밍(dp)

### 문제 설명

<p>2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.</p>

<p>아래 그림은 2×17 직사각형을 채운 한가지 예이다.</p>

<p style="text-align: center;"><img alt="" src="https://www.acmicpc.net/upload/images/t2n2122.gif" style="height:59px; width:380px"></p>

### 입력 

 <p>첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)</p>

### 출력 

 <p>첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.</p>

### 🐟풀이

처음에 순조부로 풀어야 하나 고민을 하다가 n이 1000인 것을보고 다른 방법을 찾았다. 도형을 직접 그려보고 규칙을 찾았다. n-1 타일에 2x1 타일이 추가 되고, n-2 타일에 
2x2와 1x2 타일이 추가되는 규칙이었다.  

### 👀어려웠던 점
규칙을 찾는게 어려웠고, 코드 자체는 간단했다.

