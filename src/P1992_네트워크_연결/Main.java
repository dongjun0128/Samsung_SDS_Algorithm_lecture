package P1992_네트워크_연결;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1992_네트워크_연결/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        while (M > 0) {
            int a,b,c;

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a,b,c));

            M--;
        }

        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        int totalCost = 0;

        while(!pq.isEmpty()){
            Edge temp = pq.poll();

            if(find(temp.start) != find(temp.end)){
                union(temp.start, temp.end);
                totalCost += temp.cost;
            } else{
                continue;
            }

        }

        System.out.println(totalCost);

    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB){
            arr[rootA] = rootB;
        }
    }

    static int find(int a){
        if(arr[a] == a) return a;
        else return arr[a] = find(arr[a]);
    }
}

class Edge implements Comparable<Edge> {

    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }
}
