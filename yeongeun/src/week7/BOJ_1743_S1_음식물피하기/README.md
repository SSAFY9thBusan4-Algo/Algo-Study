# Week7 BOJ_1743_S1_음식물피하기

## 문제

```
코레스코 콘도미니엄 8층은 학생들이 3끼의 식사를 해결하는 공간이다. 그러나 몇몇 비양심적인 학생들의 만행으로 음식물이 통로 중간 중간에 떨어져 있다. 이러한 음식물들은 근처에 있는 것끼리 뭉치게 돼서 큰 음식물 쓰레기가 된다. 

이 문제를 출제한 선생님은 개인적으로 이러한 음식물을 실내화에 묻히는 것을 정말 진정으로 싫어한다. 참고로 우리가 구해야 할 답은 이 문제를 낸 조교를 맞추는 것이 아니다. 

통로에 떨어진 음식물을 피해가기란 쉬운 일이 아니다. 따라서 선생님은 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다. 

선생님을 도와 제일 큰 음식물의 크기를 구해서 “10ra"를 외치지 않게 도와주자.
```
<br>

## 접근방법
dfs탐색.
map크기를 [N+2][M+2] 로해서 범위 탈출 신경안쓰게 했음.


<br>

## 실행 흐름
1. 입력받으면서 map 그리기
2. map탐색. 만약 음식물이 있으면 count0설정하고 방문처리 visite()

	- 자기자신좌표 방문(count++)
	- 상하좌우 탐색. 있으면 방문.
3. 방문 다 했을때 count값 확인. 가장 큰 값 설정.

<br>

## 결과

|메모리|시간|
|:---:|:---:|
|16900 KB|188 ms|

풀이시간 : 20분