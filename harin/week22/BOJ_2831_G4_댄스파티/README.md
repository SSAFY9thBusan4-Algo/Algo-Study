### 성능
- 메모리 : 47524KB
- 시간 : 648ms

<br/>


### 풀이 과정 👀 
- 키큰 여자를 선호하는 남자, 키 작은 여자를 선호하는 남자, 키 큰 남자를 선호하는 여자, 키 작은 남자를 선호하는 여자를 리스트로 따로 나눠서 받는다.
- 각각의 리스트를 정렬한다.
- 춤을 출 수 있는 경우는 키 큰 여자 ❤️ 키 작은 남자와 키 큰 남자 ❤️ 키 작은 여자가 될 수 있다.
- 두 커플로 나누어서 키가 큰 사람 > 키가 작은 사람이라면 짝을 지었기 때문에 b++ g++ 해주고 키가 큰 사람 <= 키가 작은 사람일 경우는 키가 큰 사람의 인덱스++ 를 해준다.
