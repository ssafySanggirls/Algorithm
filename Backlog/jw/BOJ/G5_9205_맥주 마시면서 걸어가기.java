import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int t, n;
    static Point[] storeList;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());

            storeList = new Point[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Point start = new Point(x, y);

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                storeList[i] = new Point(x, y);
            }

            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            Point end = new Point(x, y);

            if (bfs(start, end)) {
                sb.append("happy").append('\n');
            } else {
                sb.append("sad").append('\n');
            }
        }

        System.out.println(sb);
    }

    static boolean bfs(Point start, Point end) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        q.offer(start);

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.checkDistance(end)) {
                return true;
            }

            for (int i = 0; i < n; i++) {
                Point next = storeList[i];
                if (!next.checkDistance(now)) {
                    continue;
                }

                if (visited[i]) {
                    continue;
                }

                visited[i] = true;
                q.offer(next);
            }
        }

        return false;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean checkDistance(Point p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y) <= 1000;
    }
}
