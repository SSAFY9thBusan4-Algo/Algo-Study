## Week16 BOJ 14620 S2 꽃길

### 접근방법 포인트

- 조합을 이용하여 죽지 않는 경우의 꽃 3개 선택.
- 죽는다 아니다 -> 2차원 배열 방문처리로 확인
- 화단 5개 비용 -> 합을 미리 저장. -> sum 작은 순으로 정렬 -> sum이 min보다 크면 멈추도록 백트래킹.


<br>

### 결과

|메모리|시간|
|:---:|:---:|
|19072 KB|228 ms|


처음에 방문처리를 안하고 식으로 계산하려고 했는데
```
if((Math.abs(a.px - b.py) + Math.abs(a.py - b.py)) <= 2) 
    return true;
```
이 식으로 하면 꽃잎이 겹쳐지는지 확인 되는거 아닌가..? 왜 안될까?