package P1722_순열의_순서;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dp = new int[21];
    static ArrayList<Integer> numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1722_순열의_순서/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        List<Integer> input = new ArrayList<>();

        numbers = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            numbers.add(i);
        }

        for (int i = 0; i <= N; i++) {
            if (i == 0) dp[i] = 1;
            else {
                dp[i] = dp[i - 1] * i;
            }
        }

        st = new StringTokenizer(br.readLine());
        if (Integer.parseInt(st.nextToken()) == 1) {
            int k = Integer.parseInt(st.nextToken());

            query(k);

        } else {
            boolean[] visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                input.add(Integer.parseInt(st.nextToken()));
            }
            int cnt = 0;
            for (int i = 0; i < input.size(); i++) {
                int num = input.get(i);

                visited[num - 1] = true;

                int cntNum = 0;
                for (int j = 0; j < num; j++) {
                    if (visited[j] == false) cntNum++;
                }

                if (N == 1) cnt += 1;
                else {
                    cnt += cntNum * dp[N - 1];
                }
                N--;
            }
            System.out.println(cnt);
        }
    }

    static void query(int k) {
        if (N == 0) {
            System.out.println(sb);
            return;
        } else {
            int time = findNumberTimes(k);
            k -= dp[N - 1] * time;

            sb.append(numbers.get(time));
            numbers.remove(time);

            N--;
            query(k);
        }
    }

    static int findNumberTimes(int k) {
        int number = 0;
        int time = 0;

        while (true) {
            number += dp[N - 1];
            time++;

            if (number >= k) break;
        }

        return --time;
    }

}
