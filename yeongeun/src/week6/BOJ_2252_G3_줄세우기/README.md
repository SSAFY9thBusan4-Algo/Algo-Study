# Week6 BOJ_2252_G3_줄세우기

## 문제

```
N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하겠지만, 
마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다. 
그나마도 모든 학생들을 다 비교해 본 것이 아니고, 일부 학생들의 키만을 비교해 보았다.

일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.
```
<br>

## 접근방법
이번에 배운 위상정렬 사용. 선행조건이 충족되면 result에 넣도록함.


<br>

## 실행 흐름
1. 입력받을때 인접리스트만들기, 선행노드 수 count.
2. 선행노드가 없는 노드 queue에 삽입.
3. queue를 빼낸다. result에 넣는다.
	
	3-1. 해당 노드에서 갈 수 있는 다음 노드 찾기
	
	3-2. 해당 노드의 선행노드가 하나 완료된 것이니까 count--

	3-3. 만약 count가 0이면 큐에 넣기.

	3-4. 반복 



<br>

## 구현 시 어려웠던 점
생각보다 위상정렬코드가 잘 안외워지기도 하고, 문제 보고 위상정렬 문제인게 파악이 잘 안돼서 문제를 더 많이 풀어봐야 할 것 같다.ㅜㅜ


<br>

## 결과

|메모리|시간|
|:---:|:---:|
|50440 KB|548 ms|

풀이시간: 얼마나 걸렸지..? 적어놓질 않아서.. 그리고 풀다가 모르겠어서 필기한 내용 봤어요..ㅎㅎ..