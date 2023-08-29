### 성능
- 메모리 : 271332KB
- 시간 : 1268ms

<br/>


### 풀이 과정 👀 
- map에 key값으로 keyword를 넣는다.
- M만큼 반복하며 map 안에 입력받은 keyword와 같은 값인 key가 존재할 경우 (containsKey) remove 해준다.
- remove하며 동시에 ans 값을 1씩 감소시킨다.
- ans 값을 출력한다.

<br/>

### 회고
- containsKey라는 메서드가 있는 지 몰라서 하나 씩 for문으로 확인한다고 map을 썼다.
- 그래서 for문을 쓰니까 시간초과가 났다.
- containsKey라는 메서드를 쓰니까 시간초과는 해결했지만, 굳이 이 문제에서 HashMap을 쓸 필요는 없는 것 같다 ...
- 다음에는 HashSet을 사용해서 풀겠습니다 ~~~ !!! 
