package src.week5.SWEA_2383_역량_점심식사시간;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    private static class Point implements Comparable<Point>{
        int x, y, d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }


        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.d, o.d);
        }
    }

    private static List<Point> people;
    private static List<Point> stairs;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // input
            int N = Integer.parseInt(br.readLine());

            people = new ArrayList<>();
            stairs = new ArrayList<>();


            String[] input;
            for(int i = 0 ; i < N; i++) {
                input = br.readLine().split(" ");

                for(int j = 0 ; j < N; j++) {

                    int n = Integer.parseInt(input[j]);
                    if(n == 1) {    // 사람
                        people.add(new Point(i,j,0));
                    }
                    else if(n > 1) {      // 계단
                        stairs.add(new Point(i,j,n));
                    }
                }
            }

            // ==============================================
            result = Integer.MAX_VALUE;

            // 1. 사람 조합하기
            int peoplecnt = people.size();
            for(int i = 0; i <= peoplecnt/2 ; i++) {
                combi(0,0,i,new int[i], peoplecnt);
            }

            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }

    private static void combi(int start, int cnt, int M, int[] selected, int size) {
        if(cnt == M) {
            // 2. 시뮬레이션

            // 선택되지 않은 사람 구하기
            boolean[] select = new boolean[size];
            for(int i = 0 ; i < selected.length; i++) {
                select[selected[i]] = true;
            }

            int[] unselected = new int[size - M];
            for(int i = 0 , n = 0 ; i < size; i++) {
                if(!select[i]) unselected[n++] = i;
            }


            // 시뮬 돌리기
            simulation(new int[][]{selected, unselected});
            if(selected.length != unselected.length) {   // 절반 똑같을때는 하나만 해도 됨.
                simulation(new int[][]{unselected, selected});
            }

            return;
        }

        // 조합구하기
        for(int idx = start; idx < size; idx++) {
            selected[cnt] = idx;
            combi(idx+1, cnt+1, M, selected, size);
        }
    }

    private static void simulation(int[][] peoplebystair) {

        int max = 0;    // 더 오래걸리는 시간이 결과 시간.
        for(int s = 0 ; s < 2 ; s++) {  // 계단 2개
            Point curstair = stairs.get(s);
            int[] waitingpeople = peoplebystair[s];

            PriorityQueue<Point> peopleDistance = new PriorityQueue<>();

            // 계단과 사람 사이의 거리 큐에 넣기.
            for(int i = 0 ; i < waitingpeople.length; i++) {
                Point p = people.get(waitingpeople[i]);
                p.d = Math.abs(curstair.x - p.x)+Math.abs(curstair.y-p.y);
                peopleDistance.offer(p);
            }

            // 1초부터 센다. 현재 계단에 출발하는 사람을 list로 저장.(add)
            LinkedList<Integer> cntbytime = new LinkedList<>();
            cntbytime.add(0);   // 0초일때 0

            int timer = 0;
            while(true) {
                timer++;
                // 초가 오르면 일단. 사람수를 센다. (-1 ~ 계단길이)에있는 사람은 현재에도 계단에 있음.
                int curpeoplecnt = 0;
                for(int i = 0 ; i < curstair.d - 1 && i < timer; i++) {
                    curpeoplecnt += cntbytime.get(i);
                }


                // 모든 사람이 계단을 다 탔을 때!! 종료
                if(curpeoplecnt == 0 && peopleDistance.isEmpty()) {
                    break;
                }

                // 더 계단을 기다리는 사람이 없을 때. 현재 계단 사람 update
                if(peopleDistance.isEmpty()) {
                    cntbytime.add(0,0);
                    continue;
                }

                // 계단으로 가고있는사람, 기다리는사람
                int uppeoplecnt = 0;
                while(!peopleDistance.isEmpty()) {
                    // 큐에서 빼낸 사람의 거리가 현재 시간보다 작으면
                    Point minimumperson = peopleDistance.peek();
                    if (minimumperson.d < timer) {	// 여기서 시간 같을때 말고 이미 도착이 끝난사람만 넣으니까 해결!!!!

                        // 현재 계단에 사람 수가 3인가 -> 큐에서 뺄 수 없음. 그냥 반복문 나와.
                        if (curpeoplecnt + uppeoplecnt == 3) {                            
                            break;
                        } else { // 아니면 -> list현재초에 계단 출발한다고 적음.
                            peopleDistance.poll();
                            uppeoplecnt++;
                        }
                    } else {
                        break;
                    }
                }
                cntbytime.add(0, uppeoplecnt);// 현재 초에 계단 출발한사람 저장.

            }   // 사람이 다 내려가면 끝.

            //끝나면 걸린 시간 저장.
            if(timer > max) max = timer;
        }

        if(max < result) {
            result = max;
        }


    }
}
