package P11404_플로이드;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static long[][] graph;
    static final int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P11404_플로이드/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        graph = new long[n+ 1][n + 1];

        for (int i = 0; i <= n ; i++) {
            Arrays.fill(graph[i],MAX_VALUE);
        }

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (a == b) graph[a][b] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(graph[a][b] != MAX_VALUE) graph[a][b] = Math.min(graph[a][b], c);
            else graph[a][b] = c;
        }

        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b] , graph[a][k] + graph[k][b]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(graph[i][j] == MAX_VALUE) System.out.print(0 + " ");
                else System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

    }
}
