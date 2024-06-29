import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0,1,-1,1,-1};
    static final int[] DY = {0,1,0,-1,1,-1,-1,1};

    int N;					// 전체 땅의 행과 열
	int M;					// 처음에 주어진 나무의 개수
	int K;					// 년수
	int[][] foods;			// 땅마다 추가되는 양분
	int[][] lands;			// 전체 땅
	ArrayList<Tree> trees = new ArrayList<>();		// 나무들		
	Deque<Integer> deadTrees = new LinkedList<>();

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
            String[] N_M_K = br.readLine().split(" ");
            N = Integer.parseInt(N_M_K[0]);
            M = Integer.parseInt(N_M_K[1]);
            K = Integer.parseInt(N_M_K[2]);
            foods = new int[N][N];
            lands = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] food = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    foods[i][j] = Integer.parseInt(food[j]);
                    lands[i][j] = 5;
                }
            }

            for (int i = 0; i < M; i++) {
                String[] tree = br.readLine().split(" ");
                trees.add(new Tree(tree));
            }

            Collections.sort(trees, (t1, t2) -> t1.age - t2.age);
            
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        while (K != 0) {
            spring();		// 봄
            summer();		// 여름
            fall();			// 가을
            winter();		// 겨울
            K--;			// 년수 감소
        }

        System.out.println(trees.size());
    }

	public void spring() {
		for (int i = 0; i < trees.size(); i++) {
			Tree tree = trees.get(i);
			if (lands[tree.x][tree.y] < tree.age) {
				deadTrees.add(i);
				continue;
			}
			lands[tree.x][tree.y] -= tree.age;
			tree.age++;
		}
	}

	public void summer() {
		while (!deadTrees.isEmpty()) {
			int deadTreeIndex = deadTrees.pollLast();
			Tree deadTree = trees.get(deadTreeIndex);
			int food = deadTree.age / 2;
			lands[deadTree.x][deadTree.y] += food;
			deadTree.dead = true;
		}
	}

	public void fall() {
		ArrayList<Tree> newTrees = new ArrayList<>();
		for (int p = 0; p < trees.size(); p++) {
			Tree tree = trees.get(p);
			if (tree.dead) {
				continue;
			}
			if (tree.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nr = tree.x + DX[i];
					int nc = tree.y + DY[i];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					newTrees.add(new Tree(nr, nc, 1));
				}
			}
		}
		for (Tree tree : trees) {
			if (!tree.dead) {
				newTrees.add(tree);
			}
		}
		trees = newTrees;
	}

	public void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				lands[i][j] += foods[i][j];
			}
		}
	}

}

class Tree {
    int x;
    int y;		
    int age;		
    boolean dead;	

    public Tree(String[] tree) {
        this.x = Integer.parseInt(tree[0]) - 1;
        this.y = Integer.parseInt(tree[1]) - 1;
        this.age = Integer.parseInt(tree[2]);
    }

    public Tree(int row, int col, int age) {
        this.x = row;
        this.y = col;
        this.age = age;
    }
}
