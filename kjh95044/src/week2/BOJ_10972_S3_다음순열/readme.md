# [Silver III] 다음 순열 - 10972 

[문제 링크](https://www.acmicpc.net/problem/10972) 

### 성능 요약

메모리: 18116 KB, 시간: 236 ms

### 분류

조합론(combinatorics), 수학(math)

### 문제 설명

<p>1부터 N까지의 수로 이루어진 순열이 있다. 이때, 사전순으로 다음에 오는 순열을 구하는 프로그램을 작성하시오.</p>

<p>사전 순으로 가장 앞서는 순열은 오름차순으로 이루어진 순열이고, 가장 마지막에 오는 순열은 내림차순으로 이루어진 순열이다.</p>

<p>N = 3인 경우에 사전순으로 순열을 나열하면 다음과 같다.</p>

<ul>
	<li>1, 2, 3</li>
	<li>1, 3, 2</li>
	<li>2, 1, 3</li>
	<li>2, 3, 1</li>
	<li>3, 1, 2</li>
	<li>3, 2, 1</li>
</ul>

### 입력 

 <p>첫째 줄에 N(1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄에 순열이 주어진다.</p>

### 출력 

 <p>첫째 줄에 입력으로 주어진 순열의 다음에 오는 순열을 출력한다. 만약, 사전순으로 마지막에 오는 순열인 경우에는 -1을 출력한다.</p>

### 🐟풀이
  처음에 문제에 순열이라고 적혀있어서 순열로 풀었다가 시간 초과가 떴다. N이 최대 10000이라 최악의 경우 10000!만큼 시간이 든다.(다음에는 제한 사항을 꼭 확인하자!)
  
  그래서 만들어본 순열을 보면서 규칙을 열심히 찾았다. 뒤에서부터 확인하는데, 오름차순이 아닐때 해당 인덱스 값보다 큰 값중에 가장 작은 값으로 대체해주고 그 뒤 배열은 다시 정렬해주면 다음 순열이 나오게 된다.
  

### 👀어려웠던 점

규칙을 찾는게 어려웠고, 규칙을 찾고나서 이를 구현할때 너무 헷갈렸다.

### 🙂개선점

지금 코드에 for문과 if문이 너무 많은데 고쳐야 할 점이 한두가지가 아닌거 같다. (for문과 if문이 너무 많다) week2 문제를 다 풀고 한번 개선해봐야겠다.
