import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {

    static int M, A, answer;
    static int[] dirA, dirB;
    static BC[] bcList;
    static final int[] dr = {0, -1, 0, 1, 0};
    static final int[] dc = {0, 0, 1, 0, -1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 총 이동 시간
            A = Integer.parseInt(st.nextToken()); // BC 개수

            answer = 0;

            dirA = new int[M];
            dirB = new int[M];
            bcList = new BC[A];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                dirA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                dirB[i] = Integer.parseInt(st.nextToken());
            }

            // 충전기 정보
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                bcList[i] = new BC(new Point(st.nextToken(), st.nextToken()), st.nextToken(), st.nextToken());
            }

            Arrays.sort(bcList, (o1, o2) -> o2.performance - o1.performance);

            Point a = new Point("1", "1");
            Point b = new Point("10", "10");

            charge(a, b);
            for (int i = 0; i < M; i++) {
                a.row += dr[dirA[i]];
                a.col += dc[dirA[i]];
                b.row += dr[dirB[i]];
                b.col += dc[dirB[i]];
                charge(a, b);
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static void charge(Point a, Point b) {
        boolean[][] available = new boolean[A][2];

        // 충전기와 거리 확인
        for (int i = 0; i < A; i++) {
            int dis = a.getDistance(bcList[i].point);
            if (dis <= bcList[i].coverage) {
                available[i][0] = true ;
            }

            dis = b.getDistance(bcList[i].point);
            if (dis <= bcList[i].coverage) {
                available[i][1] = true;
            }
        }

        boolean flagA = false, flagB = false;
        int cnt = 0;
        for (int i = 0; i < A && cnt < 2; i++) {
            if (available[i][0] && available[i][1]) {
                answer += bcList[i].performance;
                cnt++;
            } else if (available[i][0] && !flagA) {
                answer += bcList[i].performance;
                flagA = true;
                cnt++;
            } else if (available[i][1] && !flagB) {
                answer += bcList[i].performance;
                flagB = true;
                cnt++;
            }
        }
    }

}

class Point {
    int row;
    int col;

    public Point(String col, String row) {
        this.row = Integer.parseInt(row);
        this.col = Integer.parseInt(col);;
    }

    int getDistance(Point p) {
        return Math.abs(this.row - p.row) + Math.abs(this.col - p.col);
    }
}

class BC {
    Point point;
    int coverage;
    int performance;

    public BC(Point point, String coverage, String performance) {
        this.point = point;
        this.coverage = Integer.parseInt(coverage);
        this.performance = Integer.parseInt(performance);
    }
}
