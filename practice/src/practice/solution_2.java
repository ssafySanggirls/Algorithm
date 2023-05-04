package practice;
//프로그래머스 _피로도
class Solution {
    int size,cnt,s,kk; //s는 k 백업용
    int a=-1;
    int ans[][];
    boolean visited[];
    public void recur(int cur,int [][] dungeons){
        //dungeons[i][0], dungeons[i][1] 
        if(cur==size){
            //계산하자
            s=kk;
            cnt=0;
            for(int i=0; i<size; i++){
                // System.out.println(ans[i][0]+" "+ans[i][1]);
                if(s>=ans[i][0] && s-ans[i][1]>=0){
                    cnt++;
                    s-=ans[i][1];
                }else{
                    break;  
                }
            }
            
            // System.out.println(cnt);
            if(cnt>a){
                a=cnt;
            }
            
            return;
        }
        
        for(int i=0; i<size; i++){
            if(visited[i]){continue;}
            
            visited[i]=true;
            
            ans[cur][0]=dungeons[i][0];
            ans[cur][1]=dungeons[i][1];
            
            recur(cur+1,dungeons);
            
            visited[i]=false;
            
        }
    }
    
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        size = dungeons.length;
        // System.out.print(size);
        ans = new int[size][2];
        visited = new boolean[size];
        kk=k;
        s=k;
        recur(0,dungeons);
        answer=a;
        
        return answer;
    }
}