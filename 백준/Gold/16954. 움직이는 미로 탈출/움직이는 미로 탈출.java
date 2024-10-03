import java.io.*;
import java.util.*;;

public class Main {
	
    static final int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static final int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static final int N = 8;
    
    char[][] map = new char[8][8];

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            map = new char[8][8];
            for(int i=0; i<8; i++) {
                String s = br.readLine();
                for(int j=0; j<8; j++) {
                    map[i][j] = s.charAt(j);
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        // System.out.println(bfs(7, 0));
        System.out.println(bfs());
    }
    
    public boolean isInner(int x, int y) {
    	return 0<=x && 0<=y && x<8 && y<8;
    }
    
    public void moveWall() {
    	for(int i=6;i>=0;i--){
            for(int j=0;j<8;j++){
                map[i+1][j] = map[i][j];
            }
        }
    	
        for(int i=0;i<8;i++){
            map[0][i] = '.';
        }
    }

    public int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(N-1, 0));

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                if(cur.x == 0 && cur.y == N-1) return 1;
                if(isHitByWall(cur.x, cur.y)) continue;
                for(int dir=0; dir<9; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if(!isInner(nx, ny)) continue;
                    if(map[nx][ny] == '#') continue;
                    q.add(new Point(nx, ny));
                }
            }
            moveWall();
        }
        return 0;
    }

    public boolean isHitByWall(int x, int y) {
        return map[x][y] == '#';
    }
    
    public int bfs(int x, int y) {
    	Queue<Point> q = new LinkedList<>();
    	q.offer(new Point(x, y));
    	
    	while(!q.isEmpty()) {
    		
    		int size = q.size();
    		
    		for(int s = 0; s < size; s++) {
    			Point tmp = q.poll();
        		x = tmp.x;
        		y = tmp.y;
    			
    			if(map[x][y] == '#') continue;
    			if(x == 0 && y == 7) return 1;
        		
        		for(int i = 0; i < 9; i++) {
        			int newX = x + dx[i];
        			int newY = y + dy[i];

    		    	if(isInner(newX, newY)) {
    		    		if(map[newX][newY] == '.')
    		    			q.offer(new Point(newX, newY));
    		    	}
    		    	
        		}
    		}
    		moveWall();
    	}

    	return 0;
    	
    }
    
}

class Point{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}	
}