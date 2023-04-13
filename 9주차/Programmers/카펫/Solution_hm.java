class Solution {
    static int b, y;
        
    public static void find(int R, int C, int[][] map){
        b = 0;
        y = 0;// 비교할 결과인 brown수, yellow수 초기화
        for(int i = 0; i< R; i++){
            for(int j = 0; j< C ; j ++) {
                if(i==0||j==0||i==R-1||j==C-1) b++;
                else y++;
            }
        }
        // System.out.println("find 함수:"+b+" "+y);
    }
    
    public int[] solution(int brown, int yellow) {
        int rowLen = 0, colLen = 0; // 가로 >= 세로
        int sum = brown + yellow;
        int[][] map;
        
        for(int i = 3; i<sum; i++){
            if(sum%i == 0){
                int r = sum/i;
                int c = i;
                // System.out.println(i+":"+r+" "+c);
                map = new int[r][c];
                find(r, c, map);
                if(b==brown && y==yellow) {
                    // System.out.println(b+" "+y);
                    rowLen = r;
                    colLen = c;
                    break;
                }
            }
        }
        
        // int[] answer = {};
        int[] answer = {rowLen, colLen};
        return answer;
    }
}