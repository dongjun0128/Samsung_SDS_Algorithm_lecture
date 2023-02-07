package P11438_LCA2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr = new int[18][100001];
    static ArrayList<ArrayList<Integer>> adjList;
    static boolean[] visited;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P11438_LCA2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        depth = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        dfs(1,1);

        for (int i = 1; i < 18; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = arr[i-1][arr[i-1][j]];
            }
        }

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int depthA = depth[a];
            int depthB = depth[b];

            //swap(a,b)
            if (depthA < depthB) {
                int temp = depthA;
                depthA = depthB;
                depthB = temp;

                temp = a;
                a = b;
                b = temp;
            }

            for (int j = 17; j >= 0 ; j--) {

                if(depthA - (int) Math.pow(2,j) >= depthB){
                    depthA = depthA - (int) Math.pow(2,j);
                    a = arr[j][a];
                }

                if(depthA == depthB) break;
            }

            if(a == b) {
                System.out.println(a);
                continue;
            }

            int ans = a;

            for (int j = 17; j >= 0 ; j--) {
                if(arr[j][a] != arr[j][b]){
                    a = arr[j][a];
                    b = arr[j][b];
                }

                ans = arr[j][a];
            }
            System.out.println(ans);

        }

    }

    static void dfs(int current, int dep) {
        // 1. 체크인
        visited[current] = true;
        depth[current] = dep;

        // 2. 목적지인가?

        // 3. 연결된 곳 순회
        ArrayList<Integer> temp = adjList.get(current);

        for (int i = 0; i < temp.size(); i++) {
            int next = temp.get(i);
            // 4. 갈 수 있는가?
            if(visited[next] == false) {
                // 5. 간다
                dfs(next, dep + 1);
                arr[0][next] = current;
            }
        }
        // 6. 체크아웃
    }
}
