import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static char[][] init_map;
    public static char[][] map;
    public static int n;
    public static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = bf.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        init_map = new char[n][m];
        map = new char[n][m];

        List<CCTV> cctv_list = new ArrayList<>();

        for(int i=0; i<n; i++) {
            String[] str = bf.readLine().split(" ");
            for(int j=0; j<m; j++) {
                char c = str[j].charAt(0);
                init_map[i][j] = c;
                map[i][j] = c;
                if('1'<=c && c<='5') cctv_list.add(new CCTV(new Point(i,j), c));
            }
        }


        int cctv_cnt = cctv_list.size();

        int Qnum = 1;
        for(int i=0; i<cctv_cnt; i++) {
            Qnum *= 4;
        }

        List<String> AllCase = IntToAllCase(Qnum, cctv_cnt);

        int min = Integer.MAX_VALUE;
        for(String s : AllCase) {
            for(int i=0; i<s.length(); i++) {
                func_cctv(cctv_list.get(i), s.charAt(i)-'0');
            }
//            printMap();
            int cnt = reset();
            if(cnt < min) min = cnt;
        }

        System.out.println(min);

    }

    public static List<String> IntToAllCase(int num, int cnt) {

        List<String> list = new ArrayList<>();

        for(int i=0; i<num; i++) {
            int temp = i;
            String str = "";
            for(int j=0; j<cnt; j++) {
                str = (temp%4) + str;
                temp /= 4;
            }
            list.add(str);
        }

        return list;
    }

    public static void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        return ;
    }
    public static int reset() {
        int cnt = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j]=='0') cnt++;
                map[i][j] = init_map[i][j];
            }
        }
        return cnt;
    }

    public static void cctv1(Point p, int dir) {
        int di[] = {1, 0, -1, 0};
        int dj[] = {0, 1, 0, -1};

        sketch(p, di[dir], dj[dir]);
    }

    public static void cctv2(Point p, int dir) {
        int di1[] = {1, 0, -1, 0};
        int dj1[] = {0, 1, 0, -1};
        int di2[] = {-1, 0, 1, 0};
        int dj2[] = {0, -1, 0, 1};


        sketch(p, di1[dir], dj1[dir]);
        sketch(p, di2[dir], dj2[dir]);
    }

    public static void cctv3(Point p, int dir) {
        int di1[] = {1, 1, -1, -1};
        int dj1[] = {0, 0, 0, 0};
        int di2[] = {0, 0, 0, 0};
        int dj2[] = {-1, 1, -1, 1};

        sketch(p, di1[dir], dj1[dir]);
        sketch(p, di2[dir], dj2[dir]);
    }

    public static void cctv4(Point p, int dir) {
        int di1[] = {1, 1, -1, -1};
        int dj1[] = {0, 0, 0, 0};
        int di2[] = {0, 0, 0, 0};
        int dj2[] = {-1, 1, -1, 1};
        int di3[] = {-1, 0, 0, 1};
        int dj3[] = {0, -1, 1, 0};

        sketch(p, di1[dir], dj1[dir]);
        sketch(p, di2[dir], dj2[dir]);
        sketch(p, di3[dir], dj3[dir]);
    }

    public static void cctv5(Point p, int dir) {
        int di1[] = {1, 1, -1, -1};
        int dj1[] = {0, 0, 0, 0};
        int di2[] = {0, 0, 0, 0};
        int dj2[] = {-1, 1, -1, 1};
        int di3[] = {-1, 0, 0, 1};
        int dj3[] = {0, -1, 1, 0};
        int di4[] = {0, -1, 1, 0};
        int dj4[] = {1, 0, 0, -1};

        sketch(p, di1[dir], dj1[dir]);
        sketch(p, di2[dir], dj2[dir]);
        sketch(p, di3[dir], dj3[dir]);
        sketch(p, di4[dir], dj4[dir]);
    }

    public static void func_cctv(CCTV cctv, int dir) {
        char type = cctv.getType();
        Point p = cctv.getPoint();
        switch(type) {
            case '1':
                cctv1(p, dir);
                break;
            case '2':
                cctv2(p, dir);
                break;
            case '3' :
                cctv3(p, dir);
                break;
            case '4' :
                cctv4(p, dir);
                break;
            case '5' :
                cctv5(p, dir);
                break;
        }
    }

    public static void sketch(Point p, int di, int dj) {
        int nx = p.getX(); int ny = p.getY();
        while(true) {
            nx += di;
            ny += dj;
            if(nx >= n || ny >= m || nx <0 || ny < 0) break;
            if(map[nx][ny] == '6') break;
            map[nx][ny] = '#';

        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class CCTV{
    Point p;
    char type;

    public CCTV(Point p, char type) {
        this.p = p;
        this.type = type;
    }

    public Point getPoint() {
        return p;
    }
    public char getType() {
        return type;
    }
}
