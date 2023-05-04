package practice;

import java.util.*;
import java.io.*;
//프로그래머스_ 게임 맵 최단거리

class Solution {
    int n,m,ans;
    boolean visited[][];
    Queue<Dot>queue = new LinkedList<>();
    int dx[] = {-1,1,0,0};   
    int dy[] = {0,0,-1,1};
    
    public class Dot{
        int x,y,cnt;
        Dot(int x, int y, int cnt){
            this.x=x;
            this.y=y;
            this.cnt=cnt;
        }
    }
    
    public void bfs(int maps[][]){
        queue.add(new Dot(0,0,1)); 
        while(!queue.isEmpty()){
            Dot dot = queue.poll();
            for(int i=0; i<4; i++){
                int nx = dot.x+dx[i];
                int ny = dot.y+dy[i];
                int ncnt=dot.cnt+1; 
                
                if(nx==n-1 && ny==m-1){
                    ans = ncnt;
                    return;
                }
                
                
                //인덱스 요휴성 검사 + 방문하지 않았고 + 값이 1이라면 
                if(nx>=0 && ny>=0 && nx<n && ny<m && !visited[nx][ny] && maps[nx][ny]==1){
                    queue.add(new Dot(nx,ny,ncnt));
                    visited[nx][ny]=true;
                    
                }
            }
            
        }
        
        ans=-1;
        
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length; //행
        m = maps[0].length; //열
        
        visited = new boolean[n][m];
        
        // System.out.println(n+" "+m);
        
        bfs(maps);
        
        answer=ans;
        
        return answer;
    }
}