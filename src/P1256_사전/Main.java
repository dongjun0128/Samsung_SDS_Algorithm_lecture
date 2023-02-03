package P1256_사전;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] pascalTriAngle;
    static final int MAX = 1000000000;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1256_사전/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        pascalTriAngle = new int[N + M + 2][N + M + 2];

        for (int n = 0; n <= N + M; n++) {
            for (int k = 0; k <= n; k++) {
                if (n < k) break;

                if (k == 0 || n == k) {
                    pascalTriAngle[n][k] = 1;
                } else {
                    pascalTriAngle[n][k] = pascalTriAngle[n - 1][k - 1] + pascalTriAngle[n - 1][k];
                    if (pascalTriAngle[n][k] > MAX) pascalTriAngle[n][k] = MAX;
                }
            }
        }

        if (pascalTriAngle[N + M][M] < K) {
            System.out.println(-1);
            return;
        }

        /*for (int n = 0; n <= N + M + 1; n++) {
            for (int k = 0; k <= N + M + 1; k++) {
                System.out.print(pascalTriAngle[n][k] + " ");
            }
            System.out.println();
        }*/

        while (true) {
            int total = N + M;
            if (total == 0) {
                System.out.println(sb);
                break;
            }
            if (K <= pascalTriAngle[total - 1][M]) {
                sb.append('a');
                N--;
            } else {
                sb.append('z');
                K -= pascalTriAngle[total - 1][M];
                M--;
            }
        }

    }
}
