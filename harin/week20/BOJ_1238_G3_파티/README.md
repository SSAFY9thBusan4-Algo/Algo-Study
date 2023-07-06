### 성능
- 메모리 : 25892KB
- 시간 : 2216ms

<br/>

![image](https://github.com/Harinee68/algorithm_study/assets/62701446/2f3fad32-4125-4601-8284-edd9f085d6d2)

### 풀이 방법 👀 
- 문제에서 최단 시간 오고 가기를 원한다고 하였고, 마을이 정점 도로가 간선을 뜻한다고 생각되어 다익스트라로 풀었다.
- 다익스트라를 통해 해당 마을에서 다른 마을까지의 최단 거리를 구해서 distance 배열에 저장했다.
- 모든 마을끼리의 최단 거리를 구한 후 오고 가는데 가장 많은 시간을 소비하는 학생 소요시간을 `ans = Math.max(ans, distance[i][X] + distance[X][i]);` 로 구한다. 

<br/>
