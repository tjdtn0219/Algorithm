import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Shark {
	int v;
	int d;
	int size;
	public Shark(int v, int d, int size) {
		this.v = v;
		this.d = d;
		this.size = size;
	}
}

public class Main {
	
	static final int[] DX = {0,-1,1,0,0};
	static final int[] DY = {0,0,0,1,-1};

	int n, m, k;
	HashMap<Integer, List<Shark>> sharkMap;
	int answer;

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}

	public void solution() {
		input();
		solve();
	}

	public void solve() {
		// System.out.println("=====init=====");
		// printMap();
		// System.out.println("=========================");
		for(int j=1; j<=m; j++) {
			catchShark(j);
			moveSharks();
			cleanSharks();
			// System.out.println("j : " + j);
			// printMap();
			// System.out.println("=========================");
		}
		System.out.println(answer);
	}

	public void printMap() {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int num = toInt(i, j);
				List<Shark> list = sharkMap.get(num);
				sb.append(list.size()).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	public void cleanSharks() {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int num = toInt(i, j);
				List<Shark> list = sharkMap.get(num);
				if(list.size() > 1) {
					Shark maxShark = list.get(0);
					for(Shark shark : list) {
						// System.out.println("sharkSize : " + shark.size + ", v : " + shark.v);
						if(maxShark.size < shark.size) {
							maxShark = shark;
						}
					}
					list.clear();
					list.add(maxShark);
					// System.out.println("After : " + list.size());
				}
			}
		}
	}

	public void moveSharks() {
		HashMap<Integer, List<Shark>> newMap = new HashMap<>();
		initSharkMap(newMap);

		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int num = toInt(i, j);
				List<Shark> list = sharkMap.get(num);
				if(list.isEmpty()) continue;
				Shark shark = list.get(0);
				int nx = i + DX[shark.d] * shark.v;
				int ny = j + DY[shark.d] * shark.v;
				if(isInner(nx, ny)) {
					int newNum = toInt(nx, ny);
					newMap.get(newNum).add(shark);
					// System.out.println("v, d : " + shark.v + ", " + shark.d + " || x, y : " + i + ", " + j + " --> " + nx + ", " + ny);

				} else {
					int[] nxt = getNxt(i, j, shark.d, shark.v);
					nx = nxt[0];
					ny = nxt[1];
					int nd = nxt[2];
					int newNum = toInt(nx, ny);
					// System.out.println("v, d : " + shark.v + ", " + shark.d + " || x, y : " + i + ", " + j + " --> " + nx + ", " + ny + ", newNum : " + newNum);
					// System.out.println("newNum : " + newNum);
					newMap.get(newNum).add(new Shark(shark.v, nd, shark.size));
				}
			}
		}
		sharkMap = newMap;
	}

	public int[] getNxt(int x, int y, int d, int v) {
		int nx, ny;
		if(d == 1) {
			//위
			v -= (x - 1);
			nx = 1;
			ny = y;
			
			int term = n-1;
			int mod = v / term;
			int rest = v % term;
			if(mod % 2 == 0) {
				nx += rest;
				d = getReverseDir(d);
			} else {
				nx = n;
				nx -= rest;
			}
			
		} else if(d == 2) {
			//아래
			v -= (n - x);
			nx = n;
			ny = y;
			
			int term = n-1;
			int mod = v / term;
			int rest = v % term;
			if(mod % 2 == 0) {
				nx -= rest;
				d = getReverseDir(d);
			} else {
				nx = 1;
				nx += rest;
			}
		} else if(d == 3) {
			//오
			v -= (m - y);
			nx = x;
			ny = m;
			
			int term = m-1;
			int mod = v / term;
			int rest = v % term;
			if(mod % 2 == 0) {
				ny -= rest;
				d = getReverseDir(d);
			} else {
				ny = 1;
				ny += rest;
			}
		} else {
			//왼
			v -= (y - 1);
			nx = x;
			ny = 1;
			
			int term = m-1;
			int mod = v / term;
			int rest = v % term;
			if(mod % 2 == 0) {
				ny += rest;
				d = getReverseDir(d);
			} else {
				ny = m;
				ny -= rest;
			}
		}

		int[] res = {nx, ny, d};
		return res;
	}

	public int getReverseDir(int dir) {
		if(dir == 1) {
			return 2;
		} else if(dir == 2) {
			return 1;
		} else if(dir == 3) {
			return 4;
		} else {
			return 3;
		}
	}

	public boolean isInner(int x, int y) {
		return 1<=x && 1<=y && x<=n && y<=m;
	}

	public void catchShark(int j) {
		for(int i=1; i<=n; i++) {
			//상어 잡기
			int num = toInt(i, j);
			List<Shark> list = sharkMap.get(num);
			if(list.isEmpty()) continue;
			Shark shark = list.remove(0);
			// System.out.println("catch : " + i + ", " + j + ", num : " + num + ", size : " + shark.size);
			answer += shark.size;
			break;
		}
	}

	public void input() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] R_C_M = br.readLine().split(" ");
			n = Integer.parseInt(R_C_M[0]);
			m = Integer.parseInt(R_C_M[1]);
			k = Integer.parseInt(R_C_M[2]);
			sharkMap = new HashMap<>();
			initSharkMap(sharkMap);

			for(int i=0; i<k; i++) {
				String[] r_c_s_d_z = br.readLine().split(" ");
				int x = Integer.parseInt(r_c_s_d_z[0]);
				int y = Integer.parseInt(r_c_s_d_z[1]);
				int v = Integer.parseInt(r_c_s_d_z[2]);
				int d = Integer.parseInt(r_c_s_d_z[3]);
				int size = Integer.parseInt(r_c_s_d_z[4]);
				int num = toInt(x, y);
				List<Shark> list = sharkMap.get(num);
				list.add(new Shark(v, d, size));
			}
		} catch (Exception e) {
			System.out.println("INPUT ERROR!");
			throw new RuntimeException(e);
		}
	}

	public void initSharkMap(HashMap<Integer, List<Shark>> map) {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int num = toInt(i, j);
				map.put(num, new ArrayList<>());
			}
		}
	}

	public int[] toPoint(int num) {
		int x = num / m;
		if(num % m == 0) {
			x--;
		}
		int y = num - x*m;
		int[] res = {x+1, y};
		return res;
	}

	public int toInt(int x, int y) {
		return (x-1)*m + y;
	}

	public void printNewMap(HashMap<Integer, List<Shark>> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("printNewMap\n");
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int num = toInt(i, j);
				List<Shark> list = map.get(num);
				sb.append(list.size()).append(" ");
			}
			sb.append("\n");
		}
		sb.append("============\n");
		System.out.print(sb);
	}
}
// 1,1 1,2 1,3 1,4 1,5 1,6
// 2,1 2,2 2,3 2,4

// 1 2 3 4 5 6
// 7 8 9 10 11 12