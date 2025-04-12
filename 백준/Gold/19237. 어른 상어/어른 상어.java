import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Point {
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Shark {
	int idx;
	Point p;
	int dir;
	int[][] priorDir;
	boolean isAlive;

	public Shark() {
		this.isAlive = true;
	}
}

class Smell {
	Point p;
	int idx;
	int time;

	public Smell(Point p, int idx, int time) {
		this.p = p;
		this.idx = idx;
		this.time = time;
	}
}

public class Main {

	static final int[] DX = {-1,1,0,0};
	static final int[] DY = {0,0,-1,1};

	int n;
	int m;
	int k;
	List<Shark> sharkList;
	Shark[][] sharkMap;
	Smell[][] smellMap;
	int ans;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    public void solution() {
		input();
		solve();
    }

	private void printMap() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int smellIdx = 0;
				if(smellMap[i][j] != null) {
					smellIdx = smellMap[i][j].idx;
				}
				int sharkIdx = 0;
				if(sharkMap[i][j] != null) {
					sharkIdx = sharkMap[i][j].idx;
				}
				sb.append(smellIdx).append("/").append(sharkIdx).append("  ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public void solve() {
		boolean flag = false;
		for(int i=0; i<1000; i++) {
			remainSmells();
			moveAllSharks();
			reduceSmellTime();
			// System.out.println("turn : " + i);
			// printMap();
			if(checkOnlyOne()) {
				ans = i+1;
				flag = true;
				break;
			}
		}

		if(!flag) {
			ans = -1;
		}

		System.out.println(ans);
	}

	public boolean checkOnlyOne() {
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(sharkMap[i][j] != null) cnt++;
			}
		}
		if(cnt == 1) return true;
		else return false;
	}

	public void reduceSmellTime() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(smellMap[i][j] != null) {
					smellMap[i][j].time--;
					if(smellMap[i][j].time == 0) {
						smellMap[i][j] = null;
					}
				}
			}
		}
	}

	public void moveAllSharks() {
		for(int i=1; i<=m; i++) {
			Shark shark = sharkList.get(i);
			if(shark.isAlive == false) continue;
			Point p = shark.p;
			HashSet<Integer> dirSet = new HashSet<>();
			for(int dir=0; dir<4; dir++) {
				//인접한 칸 중 비어있는 칸
				int nx = p.x + DX[dir];
				int ny = p.y + DY[dir];
				if(!isInner(nx, ny)) continue;
				if(smellMap[nx][ny] == null) {
					dirSet.add(dir);
				}
			}
			if(dirSet.isEmpty()) {
				for(int dir=0; dir<4; dir++) {
					//자신의 냄새 칸
					int nx = p.x + DX[dir];
					int ny = p.y + DY[dir];
					if(!isInner(nx, ny)) continue;
					if(smellMap[nx][ny] != null && smellMap[nx][ny].idx == shark.idx) {
						dirSet.add(dir);
					}
				}
			}
			int nx = p.x;
			int ny = p.y;
			int nDir = shark.dir;
			for(int dir : shark.priorDir[shark.dir]) {
				// System.out.println("idx : " + shark.idx + " | shark.dir : " + shark.dir + ", dir : " + dir);
				if(dirSet.contains(dir)) {
					nx = p.x + DX[dir];
					ny = p.y + DY[dir];
					nDir = dir;
					break;
				}
			}
			if(nx == p.x && ny == p.y) continue;	//이동 X
			if(sharkMap[nx][ny] != null) {
				//잡아먹힘
				// System.out.println("idx : " + shark.idx + " | 잡아먹힘");
				shark.isAlive = false;
				sharkMap[p.x][p.y] = null;
			} else {
				// System.out.println("idx : " + shark.idx + " | " + p.x + ", " + p.y + " --> " + nx + ", " + ny);
				sharkMap[p.x][p.y] = null;
				sharkMap[nx][ny] = shark;
				shark.p.x = nx;
				shark.p.y = ny;
				shark.dir = nDir;
			}
		}
	}

	public void remainSmells() {
		for(int i=1; i<=m; i++) {
			Shark shark = sharkList.get(i);
			if(shark.isAlive == false) continue;
			Smell smell = new Smell(new Point(shark.p.x, shark.p.y), shark.idx, k);
			smellMap[shark.p.x][shark.p.y] = smell;
		}
	}

	public boolean isInner(int x, int y) {
		return 0<=x && 0<=y && x<n && y<n;
	}

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] n_m_k = br.readLine().split(" ");
			n = Integer.parseInt(n_m_k[0]);
			m = Integer.parseInt(n_m_k[1]);
			k = Integer.parseInt(n_m_k[2]);

			sharkList = new ArrayList<>();
			for(int i=0; i<=m; i++) {
				sharkList.add(new Shark());
			}
			int[][] arr = new int[n][n];
			for(int i=0; i<n; i++) {
				arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for(int j=0; j<n; j++) {
					int idx = arr[i][j];
					if(idx == 0) continue;
					Shark shark = sharkList.get(idx);
					shark.idx = idx;
					shark.p = new Point(i, j);
				}
			}
			int[] dir = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int i=0; i<m; i++) {
				sharkList.get(i+1).dir = dir[i] - 1;
			}

			for(int i=0; i<m; i++) {
				int[][] priorDir = new int[4][4];
				for(int j=0; j<4; j++) {
					priorDir[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
					for(int p=0; p<4; p++) {
						priorDir[j][p]--;
					}
				}
				sharkList.get(i+1).priorDir = priorDir;
			}

			sharkMap = new Shark[n][n];
			smellMap = new Smell[n][n];
			for(int i=1; i<=m; i++) {
				Shark shark = sharkList.get(i);
				sharkMap[shark.p.x][shark.p.y] = shark;
			}

        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

}
