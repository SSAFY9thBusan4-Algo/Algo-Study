## Week12 BOJ 13549 G5 숨바꼭질3

### 접근방법
1. 엔터를 누르는 횟수는 문자열의 길이와 같다.
2. 현재 알파벳에서 방향키로 이동하는 횟수는 가장 큰 index 값과 가장 작은 index 값의 차이이다.
3. 각 알파벳에서 현재 커서가 오른쪽에있거나, 왼쪽에 있을 수 있다. 각각을 DP로 저장했다.
4. 각각의 커서에 올 수 있는 경우의 수는 다음과 같다.
    - 현재 커서가 오른쪽에 오는 경우
        - 이전 알파벳의 오른쪽 -> 현재 알파벳 왼쪽 -> 현재 알파벳 오른쪽
        - 이전 알파벳의 왼쪽 -> 현재 알파벳 왼쪽 -> 현재 알파벳 오른쪽
    - 현재 커서가 왼쪽에 오는 경우
        - 이전 알파벳의 오른쪽 -> 현재 알파벳 오른쪽 -> 현재 알파벳 왼쪽
        - 이전 알파벳의 왼쪽 -> 현재 알파벳 오른쪽 -> 현재 알파벳 왼쪽
5. 각각의 경우에서 최소값을 구하면서 26개의 알파벳에서 구한다.

<br>

### 결과

|메모리|시간|
|:---:|:---:|
|11556 KB|84 ms|

거의 3일만에 풀었다. 인터넷을 봤는데 dist식이 파라미터가 4개라서 이해가 잘 안갔는데, 생각해보니 4개일 필요가 있나 해서 3개짜리로 내 생각하는대로 만들었더니 다행이 돌아갔다..!! 진짜 너무너무 개운하다