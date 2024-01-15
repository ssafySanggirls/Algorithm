import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int n, m;
    static int[] parents;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append('#').append(tc).append(' ');

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            makeSet();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int flag = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (flag == 0) { // 합집합
                    union(a, b);
                } else { // 같은 집합인지 확인
                    if (findSet(a) == findSet(b)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }

            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void makeSet() {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    static void union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA == rootB) {
            return;
        }

        parents[rootB] = rootA;
    }
}
