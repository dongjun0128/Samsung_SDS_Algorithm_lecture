package P1516_게임_개발;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] buildTime;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] DAG;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1516_게임_개발/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        buildTime = new int[N + 1];
        DAG = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int ancestor = Integer.parseInt(st.nextToken());

            while (ancestor != -1) {
                graph.get(i).add(ancestor);
                ancestor = Integer.parseInt(st.nextToken());
                DAG[i]++;
            }

            buildTime[i] = time;
        }

        System.out.println(Arrays.toString(DAG));

        /*System.out.println(Arrays.toString(buildTime));

        for (int i = 1; i <= N; i++) {
            System.out.println(graph.get(i));
        }*/

        for (int i = 1; i <= N; i++) {
            int totalTime = 0;

            totalTime += buildTime[i];
            visited[i] = true;

            for (int j = 0; j < graph.get(i).size(); j++) {
                totalTime += buildTime[graph.get(i).get(j)];
            }

            //buildTime[i] = totalTime;

            System.out.println(totalTime);
        }
    }

}
