package P2458_키_순서;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] graph;
    static final int MAX_VALUE = 10000;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P2458_키_순서/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int cnt = 0;

        // 어떤 점에서 다른 모든 점까지의 도달이 가능한가? -> 도달하는 점에 대해서는 키가 작음을 확인
        // 다른 점에서 현재 까지의 도달이 가능한가? -> 키가 큰 점이 있음을 확인

        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i],MAX_VALUE);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N ; j++) {
                if(i == j){
                    graph[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a =Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
        }

        for (int k = 1; k <= N ; k++) {
            for (int a = 1; a <= N ; a++) {
                for (int b = 1; b <= N ; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j <= N ; j++) {
                if(graph[i][j] != MAX_VALUE){
                    graph[j][i] = graph[i][j];
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(graph[i][j] == MAX_VALUE) break;
                if(j == N){// 한 줄에 MAX_VALUE 가 없을 때
                    for (int k = 1; k <= N ; k++) {
                        if(graph[k][i] == MAX_VALUE){
                            break;
                        }
                        if(k == N) cnt++;
                    }
                }
            }
        }

//        for (int i = 1; i <= N; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }

        System.out.println(cnt);
    }
}
