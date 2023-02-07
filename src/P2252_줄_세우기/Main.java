package P2252_줄_세우기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] DAG;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P2252_줄_세우기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        DAG = new int[N + 1];
        visited = new boolean[N + 1];
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);

            DAG[B] += 1;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(findPriority() + " ");
        }
    }

    static int findPriority() {
        for (int i = 1; i <= N; i++) {
            if (DAG[i] == 0 && visited[i] == false) { //진입 차수가 0 && 방문 안함
                visited[i] = true;
                for (int j = 0; j < graph.get(i).size(); j++) {
                    DAG[graph.get(i).get(j)]--;
                }
                return i;
            }
        }

        return -1;
    }
}
