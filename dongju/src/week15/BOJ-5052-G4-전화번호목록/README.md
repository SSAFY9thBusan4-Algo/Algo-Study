## 자료구조

처음에 문자열이랑 문자열 개수를 이용해서 완전 탐색을 해야하나 고민했는데 그냥 문자열로 정렬하면 의외로 쉽게 풀리는 문제였다.
문자열 길이에 관계없이 숫자를 문자열로 받으면 오름차순 정렬된다는 것을 알았고,
문자열 2개를 완전탐색으로 접두사 부븐을 검색했는데 찾아보니 String.startsWith(prefix) 함수를 쓰면 쉽게 가능하더라
