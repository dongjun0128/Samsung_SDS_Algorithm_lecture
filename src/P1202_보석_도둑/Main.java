package P1202_보석_도둑;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1202_보석_도둑/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        int[] bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()) , Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 가방 오름 차순 정렬
        Arrays.sort(bags);

        // 보석 무게순 정렬
        Arrays.sort(jewels, Comparator.comparingInt(Jewel::getWeight));

        // 보석 높은 값 기준 힙
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getValue).reversed());

        int jIndex = 0;
        long result = 0;
        // 1. 남은 가방 중 제일 작은 가방을 선택. <- 정렬
        for (int i = 0; i < K; i++) {
            int currentBag = bags[i];

            // 2. 선택된 가벙에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택. <- 힙 사용
            while(jIndex < N && jewels[jIndex].weight <= currentBag){
                pq.add(jewels[jIndex++]);
            }

            if (!pq.isEmpty()){
                result += pq.poll().value;
            }
        }

        System.out.println(result);
    }
}

class Jewel implements Comparable<Jewel> {
    int weight;
    int value;

    public Jewel(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "jewel{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Jewel o) {
        return o.value - value;
    }
}
