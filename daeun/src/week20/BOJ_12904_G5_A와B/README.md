# 12904 G5 A와 B
##### <p align="right"> 2023.06.30 📆 </p> 

 
### 👩‍🏫 풀이
1. A로 B를 만들기에는 매번 2연산씩 하면서 계산이 많아질 것으로 생각했다.
2. B의 마지막 자리를 확인하고 그 문자에 따른 연산을 실시했다.
2-1. 마지막 문자를 저장한다.
2-2. 마지막 문자를 문자열에서 지운다.(deleteCharAt())
2-3. 마지막 문자가 B였다면 뒤집는다. (reverse())
3. 남은 문자열이 같은 경우에 1을 출력하고, 다르다면 0을 출력한다.

<br>

### ⏱️ 성능
<!-- 테이블 -->
성공 |메모리 | 시간 | 코드길이
---|---|---|---|
1|11788KB|96ms|631B

<br>

#### PLUS 🔍
뒤집기를 효율적으로 하기 위해 StringBuffer를 쓰게 되었다.
StringBuffer를 찾아보다가 deleteCharAt까지 찾게 되어서 빠르게 풀 수 있었던 것 같다.
