import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Node {
    int idx;
    int d;
    public Node(int idx, int d) {
        this.idx = idx;
        this.d = d;
    }
}

public class Main {

    static final int[] DX = {0,0,-1,1};
    static final int[] DY = {1,-1,0,0};

    int n, k;
    int[][] board;
    HashMap<String, Stack<Node>> nodeMap;
    HashMap<Integer, Point> pointMap;
    int answer;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    public void solution() {
        input();
        answer = solve();
        System.out.println(answer);
    }

    private void printNodeMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(nodeMap.get(toString(i, j)).size()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public int solve() {
        // printNodeMap();
        for(int i=1; i<=1000; i++) {
            // System.out.println("idx : " + i);
            boolean flag = moveAll();
            if(!flag) {
                return i;
            }
            // printNodeMap();
        }

        return -1;
    }

    public boolean moveAll() {
        for(int i=1; i<=k; i++) {
            Point p = pointMap.get(i);
            Stack<Node> stk = nodeMap.get(toString(p.x, p.y));
            Stack<Node> tmp = new Stack<>();
            while(!stk.isEmpty()) {
                tmp.push(stk.peek());
                if(stk.pop().idx == i) {
                    break;
                }
            }

            // System.out.println("i : " + i + ", tmp.size : " + tmp.size());
            int d = tmp.peek().d;
            int nx = p.x + DX[d];
            int ny = p.y + DY[d];
            if(!isInner(nx, ny)) {
                d = getReverseDir(d);
            } else if(board[nx][ny] == 2) {
                d = getReverseDir(d);
            }
			tmp.peek().d = d;

            nx = p.x + DX[d];
            ny = p.y + DY[d];
            // if(i==7) {
            //  System.out.println("idx : " + i + " | x, y : " + p.x + ", " + p.y);
            //  System.out.println("nx, ny : " + nx + ", " + ny + ", d : " + d);
            // }
            if(isInner(nx, ny) && board[nx][ny] == 0) {
                while(!tmp.isEmpty()) {
                    int idx = tmp.peek().idx;
                    pointMap.get(idx).x = nx;
                    pointMap.get(idx).y = ny;

                    nodeMap.get(toString(nx, ny)).push(tmp.pop());
                }
                if(nodeMap.get(toString(nx, ny)).size() >= 4) {
                    return false;
                }
            } else if(isInner(nx, ny) && board[nx][ny] == 1) {
                for(Node node : tmp) {
                    pointMap.get(node.idx).x = nx;
                    pointMap.get(node.idx).y = ny;
                    nodeMap.get(toString(nx, ny)).push(node);
                }
                if(nodeMap.get(toString(nx, ny)).size() >= 4) {
                    return false;
                }
            } else {
                while(!tmp.isEmpty()) {
                    nodeMap.get(toString(p.x, p.y)).push(tmp.pop());
                }
            }
        }
        return true;
    }
    //0흰 1빨 2파

    public int getReverseDir(int d) {
        if(d == 0) return 1;
        if(d == 1) return 0;
        if(d == 2) return 3;
        if(d == 3) return 2;
        return -1;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_k = br.readLine().split(" ");
            n = Integer.parseInt(n_k[0]);
            k = Integer.parseInt(n_k[1]);
            board = new int[n][n];
            for(int i=0; i<n; i++) {
                board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            nodeMap = new HashMap<>();
            pointMap = new HashMap<>();
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    String s = toString(i, j);
                    nodeMap.put(s, new Stack<>());
                }
            }
            for(int i=0; i<k; i++) {
                String[] x_y_d = br.readLine().split(" ");
                int x = Integer.parseInt(x_y_d[0]) - 1;
                int y = Integer.parseInt(x_y_d[1]) - 1;
                int d = Integer.parseInt(x_y_d[2]) - 1;
                Node node = new Node(i+1, d);
                nodeMap.get(toString(x, y)).add(node);
                pointMap.put(i+1, new Point(x, y));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

    public String toString(int x, int y) {
        return x + " " + y;
    }

    public Point toPoint(String s) {
        String[] tmp = s.split(" ");
        return new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
    }
}
