package week7.BOJ_10775_G2_공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	private static int G; // 게이트 수 
	private static int[] parents;
	
	private static void makeSet() {
		parents = new int[G+1];
		
		for (int i=0; i<=G; i++) {
			parents[i] = i;
		}
	}
	
	private static int findSet(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	
	private static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		parents[bRoot] = aRoot;
	}
    
    public static void main(String[] args) throws NumberFormatException, IOException {
		
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	int result = 0;
    	
    	G = Integer.parseInt(in.readLine());
    	int P = Integer.parseInt(in.readLine());
    	int[] planes = new int[P];
    	for (int i=0; i<P; i++) planes[i] = Integer.parseInt(in.readLine());
    	
    	makeSet();
    	
    	for (int p : planes) {
    		
    		int parent = findSet(p);
    		if (findSet(p) == 0) {
    			break;
    		}else {
    			union(parent-1, parent);
    			result++;
    		}
    	}
    	System.out.println(result);
	}
	
}
