package week3.BOJ_1991_S1_트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		// map에 트리 저장
		Map<Character, Node> nodes = new HashMap<Character, Node>();		
		for (int i = 0; i < N; i++) {
			String[] nlf = in.readLine().split(" ");
			if (!nodes.containsKey(nlf[0].charAt(0))) {
				Node n = new Node(nlf[0].charAt(0));
				nodes.put(nlf[0].charAt(0), n);		
				if (!nlf[1].equals(".")) {
					n.left = new Node(nlf[1].charAt(0));
					nodes.put(nlf[1].charAt(0), n.left);
				}
				if (!nlf[2].equals(".")) {
					n.right = new Node(nlf[2].charAt(0));
					nodes.put(nlf[2].charAt(0), n.right);
				}											
			}
			else {
				Node n = nodes.get(nlf[0].charAt(0));
				if (!nlf[1].equals(".")) {
					n.left = new Node(nlf[1].charAt(0));
					nodes.put(nlf[1].charAt(0), n.left);
				}
				if (!nlf[2].equals(".")) {
					n.right = new Node(nlf[2].charAt(0));
					nodes.put(nlf[2].charAt(0), n.right);
				}
								
			}
		}
		
		preorder(nodes.get('A'));
		System.out.println(sb);
		sb = new StringBuilder();
		inorder(nodes.get('A'));
		System.out.println(sb);
		sb = new StringBuilder();
		postorder(nodes.get('A'));
		System.out.println(sb);
		
	}
	
	private static void preorder(Node n) {
		sb.append(n.name);
		if (n.left != null) preorder(n.left);
		if (n.right != null) preorder(n.right);
	}
	
	private static void inorder(Node n) {
		if (n.left != null) inorder(n.left);
		sb.append(n.name);
		if (n.right != null) inorder(n.right);
	}
	
	private static void postorder(Node n) {
		if (n.left != null) postorder(n.left);
		if (n.right != null) postorder(n.right);
		sb.append(n.name);
	}
	
}

class Node {
	char name;
	Node left;
	Node right;
	
	public Node(char n) {
		super();
		this.name = n;
	}	
	
}
