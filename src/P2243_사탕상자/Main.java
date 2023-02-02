package P2243_사탕상자;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;

    static int[] tree;

    static int S;

    static int N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P2243_사탕상자/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        N = 1000000;
        nums = new int[N];
        S = 1;
        while (S < N) {
            S *= 2;
        }

        tree = new int[S * 2];

        for (int i = 0; i < n; i++) {
            int A, B, C;
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if (A == 1) { //B 순위 사탕 꺼내기
                int answer = query(1, S, 1, B);
                System.out.println(tree[1048576]);
                System.out.println(answer);
                update(1, S, 1, answer, -1);
            } else { // B맛을 C개 넣기
                C = Integer.parseInt(st.nextToken());
                int diff = C - tree[S + B - 1];
                update(1, S, 1, B, diff);
            }
        }
    }

    static int query(int left, int right, int node, int target) {
        // leaf노드 -> 사탕 찾음
        if (left == right) {
            return left;
        }
        // 내부노드 인 경우
        else {
            int mid = (left + right) / 2;
            // 1. 왼쪽에 있음
            if (tree[node * 2] >= target) {
                return query(left, mid, node * 2, target);
            }
            // 2. 오른쪽에 있음
            else {
                return query(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
            }
        }
    }

    static void update(int left, int right, int node, int target, int diff) {
        // 1. 연관 없음
        if (target < left || right < target) return;
            // 2. 연관 있음
        else {
            tree[node] += diff;
            if (left != right) { // 외부노드(leaf)가 아닌 경우 자식노드에게도 연산 수행
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}
