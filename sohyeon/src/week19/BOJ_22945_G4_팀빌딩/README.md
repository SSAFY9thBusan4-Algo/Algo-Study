# Solution

**투포인터**
- 포인터 l,r를 사용한다. 가장 왼쪽 개발자의 위치를 l, 가장 오른쪽 개발자의 위치를 r로 설정한다
- 개발자의 능력치가 담긴 배열이 xarr일때 r-l>1 를 만족할 동안 아래 내용을 반복한다
- xarr[l]<xarr[r]이면 r가 왼족으로 이동 했을때 더 높은 능력치가 나올 수 없으므로 l이 오른쪽으로 이동한다. 반대로 xarr[l]>xarr[r]이면 l이 오른쪽으로 이동 했을때 더 높은 능력치가 나올 수 없으므로 r이 왼쪽으로 이동한다. 포인터가 이동하기전에 result와 능력치를 비교해 최대값을 갱신한다
- 반복문이 끝나면 result 반환
<br/>


</br>

|메모리|시간|
|---|---|
|26284 KB|324 ms|
