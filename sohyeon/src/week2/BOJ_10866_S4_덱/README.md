# Solution

ArrayList로 큐 구현
- size, ampty는 각각 ArrayList의 size(), ampty()를 사용
- front, back은 get()으로 pop_front, pop_back는 remove()을 사용하되 먼저 size()가 0인지 확인
- push_front, push_back는 add()를 사용

</br>
주어진 명령을 공백으로 구분해 뒤에 정수가 있다면 앞에 온 명령을 조건문에 넣어 위 내용 수행, 주어진 명령의 개수 만큼 반복.

</br>

|메모리|시간|
|---|---|
|20220 KB|252 ms|
