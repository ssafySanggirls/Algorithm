package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _11725 {
    static int n, parent[], cnt;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 노드의 개수
        tree = new ArrayList[n+1];
        for(int i=0; i<=n; i++) {
            tree[i] = new ArrayList<>();
        }
        StringTokenizer st = null;
        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        parent = new int[n+1];
        parent[1] = 1; // 루트는 1
        cnt = 1;
        findParent(1);

        for(int i=2; i<=n; i++) {
            System.out.println(parent[i]);
        }
    }

    private static void findParent(int cur) {
        if(cnt == n) {
            return;
        }

        for(int i=0; i<tree[cur].size(); i++) {
            if(parent[tree[cur].get(i)] == 0) {
                parent[tree[cur].get(i)] = cur;
                cnt++;
                findParent(tree[cur].get(i));
            }
        }
    }
}
