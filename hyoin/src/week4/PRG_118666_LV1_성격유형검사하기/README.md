
## 접근 방법
단순 구현 문제였는데 어떻게 구현할 지 조금 고민했다  


입력별로 지표번호, 성격 유형, 점수는 계속 사용할 것 같아서 MBTI 클래스를 만들었다   
일단 1번지표부터 순서대로 출력해야하니까 list에 1번부터 차례로 MBTI객체를 넣었다 
점수 산정 방식은 최종 점수가 음수이거나 같으면 알파벳 순서가 더 빠른 유형(ex) R), 양수이면 알파벳 순서가 더 늦은 유형(ex) T)을 선택함  
입력에서 성격유형의 순서가 오름차순이 아닐 수도 있으니 점수를 매길 때 조건 처리를 해줌  

## 회고
단순 구현 문제라서 머리가 복잡하지는 않아서 좋았다!  
하지만 문제가 너무 길어서 읽는게 힘들다
