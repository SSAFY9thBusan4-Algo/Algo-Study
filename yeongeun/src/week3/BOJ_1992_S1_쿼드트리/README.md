# Week3

## BOJ_1992_S1_쿼드트리

### 문제

```
흑백 영상을 압축하여 표현하는 데이터 구조로 쿼드 트리(Quad Tree)라는 방법이 있다. 
흰 점을 나타내는 0과 검은 점을 나타내는 1로만 이루어진 영상(2차원 배열)에서 같은 숫자의 점들이 한 곳에 많이 몰려있으면, 
쿼드 트리에서는 이를 압축하여 간단히 표현할 수 있다.

주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 모두 1로만 되어 있으면 압축 결과는 "1"이 된다. 
만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며, 
이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다
N ×N 크기의 영상이 주어질 때, 이 영상을 압축한 결과를 출력하는 프로그램을 작성하시오.
```
<br>

### 아이디어
1. 4개의 부분으로 나누어지고, 2x2일때 4개가 다 같은지 확인해서 리턴하면 되겠다 해서 재귀로 결정.
2. 결과값을 리턴해서 재귀안에서 계속 같은지 확인하도록 함.

<br>

### 어려웠던 점
1. 풀다가 출력을 어떻게해야할지 좀 의문이 생겨서 질문게시판의 반례를 찾아보았다.
	- (0100)이렇게 4군데가 모두 같아도 압축이 되는줄 알았는데 
	알고보니 4군데가 모두 1또는0일 경우에만 압축이 되는것이었다.

2. 재귀호출에서 length가 1인 경우를 처리안해줘서 무한반복 돌아서 제출했을때 계속 메모리 초과가 났다. 스택 오버플로우가 발생한 것이었다..!!ㅜㅜ 최대값인 64만 계속 쳐다보다가 이거 찾는다고 시간이 좀 걸렸다.
	- 앞으로는 최대값 뿐만아니라 최소값도 신경을 써야겠다.

3. 2번 이유를 찾기전에 String 때문인가 싶어서 StringBuilder로 써봤었다. 2번 문제를 해결하고 나서 반환값을 String으로 했을때와 StringBuilder로 했을 때 차이를 비교해 보았는데 시간이 거의 2배가량 차이났다...!! 
	- 재귀 반환 값도 String 사용을 지양해야 겠다.



<br>

### 결과

|반환값|메모리|시간|
|:---:|:---:|:---:|
StringBuilder|14692 KB|128 ms|
String|17136 KB|212 ms|