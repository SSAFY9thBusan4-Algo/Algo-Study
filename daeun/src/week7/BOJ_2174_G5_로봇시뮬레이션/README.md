# 2174 G5 로봇 시뮬레이션
##### <p align="right"> 2023.03.16 📆 </p>

 
### 👩‍🏫 풀이
1. 입력 받을 때 좌표를 0, 0 부터 시작하기 위해 1을 뺀 좌표로 입력받았다.
2. 로롯 배열에 입력받은 값을 넣고, map 좌표에 해당 로봇 번호를 넣었다.
3. 명령어에 따라서 입력받은 로봇의 위치를 찾고 명령어에 따라 동작한다.
4. 명령어가 끝날 때 변경된 정보로 업데이트한다.

<br>

### ⚒️ 추가한 점
1. 틀린 부분을 찾으면서 map 입력 받을 때와 방향을 깔끔하게 바꿨다.

<br>

### ⏱️ 성능
<!-- 테이블 -->
성공 |메모리 | 시간 | 코드길이
---|---|---|---|
1|11568KB|80ms|2406B

<br>

#### PLUS 🔍
처음 주어진 좌표의 방향을 제대로 이해하는 게 제일 어려웠다.
틀린 부분 찾으려다 제대로 짰던 코드만 계속 리팩토링 했던.... 
2시간 10분 정도 걸렸다.
