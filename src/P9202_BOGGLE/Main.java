package P9202_BOGGLE;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<char[][]> boards = new ArrayList<>();
    static char[][] board;
    static boolean[][] visited;
    static Trie S;

    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P9202_BOGGLE/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        S = new Trie(' ', new Trie[26], false);

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();

            Trie temp = S;

            for (int j = 0; j < input.length(); j++) {
                if (temp.child[input.charAt(j) - 'A'] != null) {
                    temp = temp.child[input.charAt(j) - 'A'];
                    continue;
                } else {
                    if (j == input.length() - 1) { //마지막 노드
                        temp.child[input.charAt(j) - 'A'] = new Trie(input.charAt(j), null, true);
                    } else {
                        temp.child[input.charAt(j) - 'A'] = new Trie(input.charAt(j), new Trie[26], false);
                        temp = temp.child[input.charAt(j) - 'A'];
                    }
                }
            }
        }

        System.out.println(S.toString());

        br.readLine();
        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());


        while (b > 0) {
            char[][] board = new char[4][4];

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                String input = st.nextToken();
                for (int j = 0; j < 4; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            boards.add(board);
            br.readLine();
            b--;
        }

        for (int i = 0; i < boards.size(); i++) {
            board = boards.get(i);
            visited = new boolean[4][4];

            dfs(0, 0, "");
        }

        /*for (int a = 0; a < boards.size(); a++) {
            int[][] board = boards.get(a);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print((char)board[i][j]);
                }
                System.out.println();
            }

            System.out.println();
        }*/

    }

    static void dfs(int x, int y, String str) {
        // 1. 체크인
        visited[x][y] = true;

        // 2. 목적지인가?
        for (char ch : str.toCharArray()) {
            Trie temp = S;

            for (int i = 0; i < str.length(); i++) {
                if (temp.isEnd) {
                    System.out.println(str);
                    return;
                }
                if (temp.child[ch - 'A'] == null) return;
                if (temp.child[ch - 'A'].ch != ch) return;
            }

        }

        if (str.length() == 8) {
            System.out.println(str);
            return;
        }

        // 3. 연결된 곳을 순회
        for (int i = 0; i < 8; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            // 4. 갈 수 있는가?
            if (nx >= 4 || nx < 0 || ny >= 4 || ny < 0) {
                continue;
            } else { // 5. 간다.
                dfs(nx, ny, str + board[nx][ny]);
            }


        }

        visited[x][y] = false;
        // 6. 체크아웃
    }
}

class Trie {
    char ch;
    Trie[] child;
    boolean isEnd;

    public Trie(char ch, Trie[] child, boolean isEnd) {
        this.ch = ch;
        this.child = child;
        this.isEnd = isEnd;
    }

    @Override
    public String toString() {
        return "Trie{" +
                "ch=" + ch +
                ", child=" + child +
                ", isEnd=" + isEnd +
                '}';
    }
}