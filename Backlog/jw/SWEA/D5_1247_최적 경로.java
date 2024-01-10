import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int N, answer;
    static Point company, home;
    static Point[] customer;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            customer = new Point[N];
            answer = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());

            company = new Point(st.nextToken(), st.nextToken());
            home = new Point(st.nextToken(), st.nextToken());

            for (int i = 0; i < N; i++) {
                customer[i] = new Point(st.nextToken(), st.nextToken());
            }

            dfs(0, company, 0, 0);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int idx, Point prev, int route, int flag) {
        if (route >= answer) {
            return;
        }

        if (idx == N) {
            answer = Math.min(answer, route + home.getDistance(prev));
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((flag & 1 << i) != 0) {
                continue;
            }
            dfs(idx + 1, customer[i], route + customer[i].getDistance(prev), flag | 1 << i);
        }
    }
}

class Point {
    int row;
    int col;

    public Point(String row, String col) {
        this.row = Integer.parseInt(row);
        this.col = Integer.parseInt(col);;
    }

    int getDistance(Point prev) {
        return Math.abs(this.row - prev.row) + Math.abs(this.col - prev.col);
    }
}
