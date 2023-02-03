package P1256_사전;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 풀이 {

    static int N, M, K;
    static int[][] dp = new int[201][201];

    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1256_사전/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


    }

    public static void query(int n, int m, int k) {
        if (n + m == 0) {
            return;
        } else if (n == 0) {
            sb.append("z");
            query(n, m - 1, k);
        } else if (m == 0) {
            sb.append("a");
            query(n - 1, m, k);
        } else {
            int limit = combination(N + M - 1, M);
            if (limit >= K) {
                sb.append("a");
                query(n - 1, m, k);
            } else {
                sb.append("z");
                query(n, m - 1, k - limit);
            }
        }
    }

    public static int combination(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        } else if (dp[n][r] != 0) {
            return dp[n][r];
        } else {
            return dp[n][r] = (int) Math.min(1e9, combination(n - 1, r - 1) + combination(n - 1, r) );
        }
    }
}
