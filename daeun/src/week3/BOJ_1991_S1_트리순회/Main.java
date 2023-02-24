package week3.BOJ_1991_S1_트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node{
	char me;
	Node left, right;
	public Node(char me) {
		this.me = me;
	}
	@Override
	public String toString() {
		return me+" ";
	}
	
}

public class Main {
	private static int N;
	private static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		Node[] tree = new Node[N];
		char temp = 'A'; //A부터 알파벳순
		
		//알파벳순으로 배열에 넣기
		for(int i=0;i<N;i++) {
			tree[i] = new Node(temp++);
		}
		
		for(int i=0;i<N;i++) {
			String split = br.readLine();
			char me = split.charAt(0);
			char left = split.charAt(2);
			char right = split.charAt(4);
			
			if(left != '.') {
				tree[me-'A'].left = tree[left-'A'];
			}
			if(right != '.') {
				tree[me-'A'].right = tree[right-'A'];
			}
		}
		
		preorder(tree[0]);
		sb.append("\n");
		inorder(tree[0]);
		sb.append("\n");
		postorder(tree[0]);
		
		System.out.println(sb);
	}
	
	private static void preorder(Node node) {
		sb.append(node.me);
		if(node.left != null) {
			preorder(node.left);
		}
		if(node.right != null) {
			preorder(node.right);
		}
	}
	
	private static void inorder(Node node) {
		if(node.left != null) {
			inorder(node.left);
		}
		sb.append(node.me);
		if(node.right != null) {
			inorder(node.right);
		}
	}
	
	private static void postorder(Node node) {
		if(node.left != null) {
			postorder(node.left);
		}
		if(node.right != null) {
			postorder(node.right);
		}
		sb.append(node.me);
		
	}
}









