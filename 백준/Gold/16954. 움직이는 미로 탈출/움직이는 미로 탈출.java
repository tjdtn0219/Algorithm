import java.io.*;
import java.util.*;;

class Node{
	int x;
	int y;
	
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
}

public class Main {
	
    static char[][] map = new char[8][8];
    static List<Node> walls = new ArrayList<>();
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    
    
    public static boolean isValid(int x, int y) {
    	return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
    
    public static void moveWall() {
    	for(int i=6;i>=0;i--){
            for(int j=0;j<8;j++){
                map[i+1][j] = map[i][j];
            }
        }
    	
        //첫번째 행은 모두 '.'으로 변경
        for(int i=0;i<8;i++){
            map[0][i] = '.';
        }
    }
    
    public static int bfs(int x, int y) {
    	Queue<Node> q = new LinkedList<>();
    	q.offer(new Node(x, y));
    	
    	while(!q.isEmpty()) {
    		
    		int size = q.size();
    		
    		for(int s = 0; s < size; s++) {
    			Node tmp = q.poll();
        		x = tmp.getX();
        		y = tmp.getY();
    			
    			if(map[x][y] == '#') continue;
    			if(x == 0 && y == 7) return 1;
        		
        		for(int i = 0; i < 9; i++) {
        			int newX = x + dx[i];
        			int newY = y + dy[i];

    		    	if(isValid(newX, newY)) {
    		    		if(map[newX][newY] == '.')
    		    			q.offer(new Node(newX, newY));
    		    	}
    		    	
        		}
    		}
    		moveWall();
    	}

    	return 0;
    	
    }
    

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 8; i++) {
			String input = br.readLine();
			for(int j = 0; j < 8; j++) {
				map[i][j] = input.charAt(j);

			}
		}
		
		System.out.println(bfs(7, 0));
		
	}

}