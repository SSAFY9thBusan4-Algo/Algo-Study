# Week6 BOJ_10282_G4_해킹

## 문제

```
최흉최악의 해커 yum3이 네트워크 시설의 한 컴퓨터를 해킹했다! 이제 서로에 의존하는 컴퓨터들은 점차 하나둘 전염되기 시작한다. 어떤 컴퓨터 a가 다른 컴퓨터 b에 의존한다면, b가 감염되면 그로부터 일정 시간 뒤 a도 감염되고 만다. 이때 b가 a를 의존하지 않는다면, a가 감염되더라도 b는 안전하다.

최흉최악의 해커 yum3이 해킹한 컴퓨터 번호와 각 의존성이 주어질 때, 해킹당한 컴퓨터까지 포함하여 총 몇 대의 컴퓨터가 감염되며 그에 걸리는 시간이 얼마인지 구하는 프로그램을 작성하시오.
```
<br>

## 접근방법
b가 a의 선행조건. Node클래스를 정의. 

선행노드가 감염된 시간. + 감염되는데 걸리는 시간. = 감염된 시간. 기준으로 오름차순 정렬되도록 만듬. 감염되면 큐에서 뺌.

priority queue이용 다이스트라


<br>

## 실행 흐름
1. 현재시간 (0) : 첫번째 컴퓨터가 선행조건인 컴퓨터를 pq에 넣는다.
2. pq에서 감염된 컴퓨터를 꺼냄 + 방문확인, 방문처리
3. 현재시간 (현재 컴퓨터가 감염된 시간) : 현재 컴퓨터가 선행 조건인 컴퓨터 큐에 넣기.
4. 반복.


<br>

## 구현 시 어려웠던 점
맞왜틀시간이 있었는데,,

이게 큐에 넣었다고해서 바로 visite한게 아니라, 시간이 되었을때 visite 처리를 해줘야 해서(돌아가는게 더 빠른경우도 있으니까) visite 처리 때문에 애를 좀 먹었다.


<br>

## 결과

|메모리|시간|
|:---:|:---:|
|171792 KB|916 ms|

풀이시간: 기록을 안했는데.. 오전에 문제 정하고 풀고난담에 맞왜틀하고 이후에는 그냥 수업듣고 실습 열심히 하다가 퇴근하기전에 문제 풀었당 아마 고민하는 시간 다 합해서 2시간 되지 않을까 싶다
