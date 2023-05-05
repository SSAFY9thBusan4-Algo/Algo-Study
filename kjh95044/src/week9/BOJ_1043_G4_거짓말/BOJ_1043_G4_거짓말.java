import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int N;
    static int M;
    static int result;

    static void makeSet() {
        parents = new int[N+1];
        for(int i=0; i<=N; i++){
            parents[i] = i;
        }
    }

    static int findSet(int a){
        if(parents[a] == a){
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot){
            return false;
        }
        parents[bRoot] = aRoot;
        return true;
    }
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());

        if(cnt == 0){
            System.out.println(M);
            return;
        }
        makeSet();
        int[] whoKnowsTheTruth = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            whoKnowsTheTruth[i] = Integer.parseInt(st.nextToken());
        }

        int past = 0;
        union(past,past);
        for (int i = 0; i < cnt; i++) {
            union(whoKnowsTheTruth[past], whoKnowsTheTruth[i]);
            past = i;
        }

        List<List<Integer>> parties = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            List<Integer> party = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            past = Integer.parseInt(st.nextToken());
            party.add(past);
            for (int j= 1; j < cnt; j++) {
                int now = Integer.parseInt(st.nextToken());
                party.add(now);
                union(past, now);
            }
            parties.add(party);
        }
        int truthParent = findSet(whoKnowsTheTruth[0]);
        for (int i = 0; i < M; i++) {
            boolean isUnion = false;
            for (int person: parties.get(i)
                 ) {
                if(findSet(person) == truthParent){
                    isUnion = true;
                    break;
                }
            }
            if(!isUnion){
                result++;
            }

        }

        System.out.println(result);
    }
}
