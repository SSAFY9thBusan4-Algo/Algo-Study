# Week7 BOJ_2174_G5_로봇시뮬레이션

## 접근방법
초기 로봇 위치 받기.

int[][]로 각 index 번호의 x,y좌표 저장

NWSE

L : +1

R : -1

F: move


<br>

## 실행 흐름
1. 입력받으면서 map 그리기, 각 robot 정보 저장
2. 명령실행
3. L,R : 반복횟수 mod4 하고 연산

	F: 이동. 하면서 로복충돌, 좌표 벗어나는 경우 err

	로봇 정보 다시 저장.

<br>

## 결과

|메모리|시간|
|:---:|:---:|
|16044 KB|148 ms|

풀이시간 : 40분

처음에는 출력을 이상하게해서 틀렸습니다 인줄 알았는데 알고보니 방향도 잘못했었다~~
한 3번 더 틀렸습니다 하고나서는
테케 직접 만들어서 검증해보고 맞는거 확인하고 넣었더니 맞더라
처음부터 테케가 작으면 만들어서 확인하자!