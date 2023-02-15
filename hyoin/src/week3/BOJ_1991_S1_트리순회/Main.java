package week3.BOJ_1991_S1_트리순회;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	// 결과를 한번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		/*
		 * 1. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		/*
		 * 2. 입력파일 객체화
		 */
		int N = Integer.parseInt(st.nextToken());

		Node root = new Node('A'); // root노드 생성
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			char node = st.nextToken().charAt(0); // 현재 노드
			char left = st.nextToken().charAt(0); // 왼쪽 자식 노드
			char right = st.nextToken().charAt(0); // 오른쪽 자식 노드

			makeNode(root, node, left, right);

		}

		/*
		 * 3. 알고리즘 풀기
		 */
		preOrder(root);
		sb.append("\n");
		inOrder(root);
		sb.append("\n");
		postOrder(root);

		/*
		 * 4. 정답 출력
		 */
		System.out.println(sb);
	}

	// 이진트리에 Node를 연결
	private static void makeNode(Node root, char node, char left, char right) {
		// 현재 노드의 왼쪽 자식과 오른쪽 자식 연결
		if (root.data == node) {
			root.leftNode = (left == '.') ? null : new Node(left);
			root.rightNode = (right == '.') ? null : new Node(right);
		} 
		// 현재 트리의 root노드가 입력의 노드일 때를 찾아감
		else {
			if (root.leftNode != null) {
				makeNode(root.leftNode, node, left, right);
			}
			if (root.rightNode != null) {
				makeNode(root.rightNode, node, left, right);
			}
		}

	}

	// 전위순회 : 루트(현재노드) -> 왼쪽 서브트리 -> 오른쪽 서브트리
	private static void preOrder(Node node) {
		if (node != null) {
			sb.append(node.data);
			preOrder(node.leftNode);
			preOrder(node.rightNode);
		}
	}

	// 중위순회 : 왼쪽 서브트리 -> 루트(현재노드) -> 오른쪽 서브트리
	private static void inOrder(Node node) {
		if (node != null) {
			inOrder(node.leftNode);
			sb.append(node.data);
			inOrder(node.rightNode);
		}
	}

	// 후위순회 : 왼쪽 서브트리 -> 오른쪽 서브트리 -> 루트(현재노드)
	private static void postOrder(Node node) {
		if (node != null) {
			postOrder(node.leftNode);
			postOrder(node.rightNode);
			sb.append(node.data);
		}
	}

	public static class Node {
		public char data;
		public Node leftNode;
		public Node rightNode;

		public Node(char data) {
			super();
			this.data = data;
		}

	}
}
