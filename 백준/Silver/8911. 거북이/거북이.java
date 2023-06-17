import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] ldx = {0,-1,0,1};
    public static int[] ldy = {1,0,-1,0};
    public static int[] rdx = {0,1,0,-1};
    public static int[] rdy = {1,0,-1,0};


    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());

        for(int i=0; i<n; i++) {
            String str = bf.readLine();
            sb.append(move(str) + "\n");
        }
        System.out.print(sb);
    }

    public static int move(String str) {
        int max_x = 0; int min_x = 0;
        int max_y = 0; int min_y = 0;
        int cx = 0; int cy = 0;
        int dx = 0; int dy = 1;

        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(c=='F') {
                cx += dx; cy += dy;
            } else if(c=='B') {
                int tdx = dx * (-1);
                int tdy = dy * (-1);
                cx += tdx; cy += tdy;
            } else if(c=='L') {
                int dir;
                for(dir=0; dir<4; dir++) {
                    if(ldx[dir]==dx && ldy[dir]==dy) break;
                }
                dir = (dir+1) % 4;
                dx = ldx[dir]; dy = ldy[dir];
            } else if(c=='R'){
                int dir;
                for(dir=0; dir<4; dir++) {
                    if(rdx[dir]==dx && rdy[dir]==dy) break;
                }
                dir = (dir+1) % 4;
                dx = rdx[dir]; dy = rdy[dir];
            }
            if(max_x < cx) max_x = cx;
            if(min_x > cx) min_x = cx;
            if(max_y < cy) max_y = cy;
            if(min_y > cy) min_y = cy;
        }
        int wid = max_x - min_x;
        int height = max_y - min_y;

        return wid*height;
    }

}
