package P2042_구간_합_구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;

    static long[] nums;

    static long[] tree;

    static int S;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P2042_구간_합_구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Long.parseLong(st.nextToken());
        }

        S = 1;
        // 외부노트(leaf) 개수를 찾는 방법
        while (S < N) {
            S *= 2;
        }

        tree = new long[S * 2];
        init();

        // System.out.println(Arrays.toString(tree));

        for (int i = 0; i < M + K; i++) {
            int a, b;
            long c;

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());

            if (a == 1) { // b번째 수를 c로 바꾸기
                long diff = c - tree[S + b - 1];
                update(1, S, 1, b, diff);
                //System.out.println(Arrays.toString(tree));
            } else { // b ~ c 구간 합 출력
                System.out.println(query(1,S,1,b, (int) c));
            }
        }
    }

    static void init() {
        //leaf는 data로
        for (int i = 0; i < N; i++) {
            tree[S + i] = nums[i];
        }

        // 내부노드는 자식의 합
        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        // 1. 연관 없음
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        // 2. 판단 가능 (쏙 들어감)
        else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        // 3. 판단 불가 (걸쳐 있음)
        else {
            int mid = (left + right) / 2;
            long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
            long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
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
