import java.util.*;

class Solution {
	
    // 1
	private static class Point { 
		
		public final long x, y; 
		
		private Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
    // 2
	public static Point intersection(long a1, long b1, long c1, long a2, long b2, long c2) {
		double x = (double)(b1*c2 - b2*c1) / (a1*b2 - a2*b1);
		double y = (double)(a2*c1 - a1*c2) / (a1*b2 - a2*b1);
		
		if((x % 1 != 0) || (y % 1 != 0)) {
			return null;
		}
		return new Point((long)x, (long)y);
	}
	
    public String[] solution(int[][] line) {
    	
    	List<Point> points = new ArrayList<>();
    	
    	// 3
    	for(int i = 0; i < line.length; i++) {
    		for(int j = i+1; j <line.length; j++) {
    			Point intersection = intersection(line[i][0], line[i][1], line[i][2], line[j][0], line[j][1], line[j][2]);
    			
    			
    			if(intersection != null) {
    				points.add(intersection);
    			}
    		}
    	}
    	
    	
    	// 4
    	long maxX = Long.MIN_VALUE;
    	long maxY = Long.MIN_VALUE;
    	long minX = Long.MAX_VALUE;
    	long minY = Long.MAX_VALUE;
    	for(int i = 0; i <points.size(); i++) {
    		maxX = Math.max(maxX, points.get(i).x);
    		minX = Math.min(minX, points.get(i).x);
    		maxY = Math.max(maxY, points.get(i).y);
    		minY = Math.min(minY, points.get(i).y);
    	}
    	
    	// 5
    	int xLength = (int)(maxX - minX + 1);
    	int yLength = (int)(maxY - minY + 1);
    	
    	char[][] map = new char[yLength][xLength];
    	
    	// 6
    	for(int i = 0; i < yLength; i++) {
    		for(int j = 0; j < xLength; j++) {
    			map[i][j] = '.';
    		}
    	}
    	
    	for(int i = 0; i <points.size(); i++) {
            int x = (int)(points.get(i).x - minX);
            int y = (int)(maxY-points.get(i).y);
    		map[y][x] = '*';
    	}
    	
    	
    	
        String[] answer = new String[map.length];
        for(int i = 0; i < answer.length; i++) {
        	answer[i] = new String(map[i]);
        }
        
        return answer;
    }
}