package week7.BOJ_10775_G2_공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int G, P, gate[];
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int result = 0;
       G = Integer.parseInt(br.readLine());
       P = Integer.parseInt(br.readLine());
       gate = new int[G+1];
       
       for(int i=1;i<G+1;i++) {
    	   gate[i] = i;
       }
       
       for(int i=0;i<P;i++) {
    	  int now = Integer.parseInt(br.readLine());
    	  int where = find(now);
    	  if(where==0) {
    		  break;
    	  }
    	  result+=1;
    	  union(where, where-1);
       }
       System.out.println(result);
	}
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a!=b) {
			gate[a] = b;
		}
	}
	private static int find(int now) {
		if(now == gate[now]) return now;
		return gate[now] = find(gate[now]);
	}
}

/*
public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int result = 0;
       int G = Integer.parseInt(br.readLine());
       int P = Integer.parseInt(br.readLine());
       int[] gate = new int[G+1];
       int now = 0;
       for(int i=0;i<P;i++) {
    	   now = Integer.parseInt(br.readLine());
    	   for(int j=now;j>=0;j--) {
    		   if(gate[j]!=1) {
    			   gate[j]++;
    			   break;
    		   }
    	   }
    	   if(gate[0]==1) {
    		   break;
    	   }
    	   result++;
       }       
       System.out.println(result);
	}
}*/