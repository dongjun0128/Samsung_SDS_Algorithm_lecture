package P3830_교수님은_기다리지_않는다;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P3830_교수님은_기다리지_않는다/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        adjList = new ArrayList<>();

        // 지금까지 유니온 파인드로는 집합 확인만 가능
        // 거리는 잴 수가 없기 때문에 거리를 재는 arr 을 만들어 준다.

        do {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= N; i++) {
                adjList.add(new ArrayList<>());
            }

            while (M > 0) {
                st = new StringTokenizer(br.readLine());

                char command = st.nextToken().charAt(0);
                if(command == '!'){ // b가 a보다 w그램 무겁다

                } else{ // command = ? b가 a보다 얼마나 더 무겁냐?

                }

                M--;
            }

        } while (N == 0 && M == 0);
    }
}
