import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Block {
	int t;
	Point p;
	public Block(int t, Point p) {
		this.t = t;
		this.p = p;
	}
}

public class Main {

	static final int N = 10;

	int n;
	int[][] board;
	char[][] map;
	List<Block> blocks;
	int ans;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    public void solution() {
		input();
		solve();
    }

	public void printMap() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private void printBoard() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public int getBlockCnt() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(board[i][j] == 1) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public void solve() {
		for(Block block : blocks) {
			// System.out.println("t : " + block.t + " || x,y : " + block.p.x + ", " + block.p.y);
			setBlock(block);
			// printBoard();
			resetGreen();
			removeOverGreen();
			resetBlue();
			removeOverBlue();
			// System.out.println("========after=========");
			// printBoard();

			// System.out.println("========-----=========\n");
		}
		System.out.println(ans);
		System.out.println(getBlockCnt());
	}

	public void removeOverBlue() {
		int cnt = 0;
		for(int j=4; j<6; j++) {
			for(int i=0; i<4; i++) {
				if(board[i][j] == 1) {
					cnt++;
					break;
				}
			}
		}
		int k = 0;
		while(k++ < cnt) {
			for(int j=N-1; j>3; j--) {
				for(int i=0; i<4; i++) {
					board[i][j] = board[i][j-1];
				}
			}
		}

	}

	public void resetBlue() {
		for(int j=N-1; j>N-5; j--) {
			int sum = 0;
			for(int i=0; i<4; i++) {
				sum += board[i][j];
			}
			if(sum == 4) {
				for(int k=j-1; k>2; k--) {
					for(int i=0; i<4; i++) {
						board[i][k+1] = board[i][k];
					}
				}
				ans++;
				j++;
			}
		}
	}

	public void removeOverGreen() {
		int cnt = 0;
		for(int i=4; i<6; i++) {
			for(int j=0; j<4; j++) {
				if(board[i][j] == 1) {
					cnt++;
					break;
				}
			}
		}
		int k = 0;
		while(k++ < cnt) {
			for(int i=N-1; i>3; i--) {
				for(int j=0; j<4; j++) {
					board[i][j] = board[i-1][j];
				}
			}
		}

	}

	public void resetGreen() {
		for(int i=N-1; i>N-5; i--) {
			int sum = 0;
			for(int j=0; j<4; j++) {
				sum += board[i][j];
			}
			if(sum == 4) {
				for(int k=i-1; k>2; k--) {
					for(int j=0; j<4; j++) {
						board[k+1][j] = board[k][j];
					}
				}
				ans++;
				i++;
			}
		}
	}


	public void setBlock(Block block) {
		Point p = block.p;
		if(block.t == 1) {
			Point moved = moveOnePointToBlue(p.x, p.y);
			board[moved.x][moved.y] = 1;

			moved = moveOnePointToGreen(p.x, p.y);
			board[moved.x][moved.y] = 1;
		} else if(block.t == 2) {
			Point moved = moveOnePointToBlue(p.x, p.y+1);
			board[moved.x][moved.y-1] = 1;
			board[moved.x][moved.y] = 1;

			Point moved1 = moveOnePointToGreen(p.x, p.y);
			Point moved2 = moveOnePointToGreen(p.x, p.y+1);
			int nx = Math.min(moved1.x, moved2.x);
			board[nx][p.y] = 1;
			board[nx][p.y+1] = 1;
		} else {
			Point moved1 = moveOnePointToBlue(p.x, p.y);
			Point moved2 = moveOnePointToBlue(p.x+1, p.y);
			int ny = Math.min(moved1.y, moved2.y);
			board[p.x][ny] = 1;
			board[p.x+1][ny] = 1;

			Point moved = moveOnePointToGreen(p.x+1, p.y);
			board[moved.x-1][moved.y] = 1;
			board[moved.x][moved.y] = 1;
		}
	}

	public Point moveOnePointToBlue(int x, int y) {
		while(y+1 < N && board[x][y+1] == 0) {
			y++;
		}
		return new Point(x, y);
	}

	public Point moveOnePointToGreen(int x, int y) {
		while(x+1 < N && board[x+1][y] == 0) {
			x++;
		}
		return new Point(x, y);
	}

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
			blocks = new ArrayList<>();
			for(int i=0; i<n; i++) {
				String[] t_x_y = br.readLine().split(" ");
				int t = Integer.parseInt(t_x_y[0]);
				int x = Integer.parseInt(t_x_y[1]);
				int y = Integer.parseInt(t_x_y[2]);
				blocks.add(new Block(t, new Point(x, y)));
			}
			map = new char[N][N];
			board = new int[N][N];
			for(int i=4; i<N; i++) {
				for(int j=4; j<N; j++) {
					board[i][j] = 2;
				}
			}
			initMap();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

	public void initMap() {
		for(int i=0; i<N; i++) {
			Arrays.fill(map[i], '0');
		}
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				map[i][j] = 'R';
			}

			for(int j=4; j<6; j++) {
				map[i][j] = 'b';
			}

			for(int j=6; j<N; j++) {
				map[i][j] = 'B';
			}
		}

		for(int j=0; j<4; j++) {
			for(int i=4; i<6; i++) {
				map[i][j] = 'g';
			}

			for(int i=6; i<N; i++) {
				map[i][j] = 'G';
			}
		}

	}
}
