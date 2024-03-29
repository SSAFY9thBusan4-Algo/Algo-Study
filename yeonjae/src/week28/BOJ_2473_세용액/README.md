# 2473 G3 세 용액

### 📂 풀이
1. 투포인터를 이용하여 풀었다.
2. 용액을 세가지 선택해야 하므로, 그냥 반복문을 돌리게 되면 O(5000^3)의 시간이 걸려 시간초과가 발생한다.
3. 투포인터를 사용하면 하나의 용액을 고르고 두개의 용액에 대해서 투포인터를 돌리는 것이므로 O(5000 * 5000)으로 시간초과 문제가 발생하지 않는다.

### 📌 기타
1. 기본적인 투포인터는 2개를 선택하는 문제라서 정확히 투포인터 문제라고 인식이 가능했다.
2. 하지만, 이 문제의 경우 3가지를 선택해야 하는 상황에서 한 가지를 고정시켜 놓고 2가지에 대해서 투포인터를 적용해야 했었다.
3. 그래서 생각보다 투포인터라는 것을 떠올리기 쉽지 않았다.
4. 또한, 처음에는 가운데 용액을 기준으로 잡을려고 했는데 그럴 필요가 없었다.
5. 어짜피 하나의 용액만 기준으로 하면 되므로 제일 왼쪽 또는 제일 오른쪽 용액을 기준으로 하는게 훨씬 간단하게 풀 수 있었다.
6. 아주 괜찮은 투포인터 문제라고 생각한다!