class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = -1;
        int maxY = -1;
        
        int n = wallpaper.length;
        int m = wallpaper[0].length();
        
        int[][] board = new int[n][m];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(wallpaper[i].charAt(j)=='#') {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i+1);
                    maxY = Math.max(maxY, j+1);
                }
            }
        }
        
        answer[0] = minX;
        answer[1] = minY;
        answer[2] = maxX;
        answer[3] = maxY;
            
        return answer;
    }
}