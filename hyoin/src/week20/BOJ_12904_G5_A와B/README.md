
## 성능 요약
메모리: 11724 KB  
시간 : 92 ms  
소요시간 : 40분


## 접근 방법
T를 기준으로 규칙을 역으로 처리  
현재 T의 맨끝이 A이면 A삭제  
현재 T의 맨끝이 B이면 B삭제 후 뒤집기  


## 회고
처음엔 S를 기준으로 규칙을 진행하려했는데 너무 복잡해서 역으로 T를 처리했다  
StringBuffer가 문자열 뒤집기를 제공하는 메소드를 가지고있는것을 처음 알았다..!  
코드는 안길지만 발상이 중요한 문제였다!  
