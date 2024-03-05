package P1753_최단거리;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_VALUE = Integer.MAX_VALUE;
    static int V, E, K;
    static ArrayList<ArrayList<Node>> graph;
    static int[] distance;

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
        pq.offer(new Node(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) { // 큐가 비어있지 않다면
            // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            Node node = pq.poll();

            int dist = node.getDistance(); // 현재 노드까지의 비용
            int now = node.getIndex(); // 현재 노드

            // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if (distance[now] < dist) continue;

            // 현재 노드와 연결된 다른 인접한 노드들을 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = distance[now] + graph.get(now).get(i).getDistance();
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < distance[graph.get(now).get(i).getIndex()]) {
                    distance[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1753_최단거리/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());

        distance = new int[V + 1];

        graph = new ArrayList<>(K + 1);

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < E; i++) { // u -> v cost :w
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        for (int i = 0; i <= V; i++) {
            System.out.println("graph = " + graph.get(i));
        }


        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(distance, MAX_VALUE);

        distance[K] = 0;

        pq.offer(new Node(K, 0));

        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            if (distance[i] == MAX_VALUE) System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }
}

class Node implements Comparable<Node> {
    private int index;
    private int distance;

    public int getIndex() {
        return this.index;
    }

    public int getDistance() {
        return this.distance;
    }

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return distance - o.distance;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", distance=" + distance +
                '}';
    }
}