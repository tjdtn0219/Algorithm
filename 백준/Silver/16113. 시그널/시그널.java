import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] board;
    public static int[][][] nums = {
            {{1,1,1},{1,0,1},{1,0,1},{1,0,1},{1,1,1}},
            {{1},{1},{1},{1},{1}},
            {{1,1,1},{0,0,1},{1,1,1},{1,0,0},{1,1,1}},
            {{1,1,1},{0,0,1},{1,1,1},{0,0,1},{1,1,1}},
            {{1,0,1},{1,0,1},{1,1,1},{0,0,1},{0,0,1}},
            {{1,1,1},{1,0,0},{1,1,1},{0,0,1},{1,1,1}},
            {{1,1,1},{1,0,0},{1,1,1},{1,0,1},{1,1,1}},
            {{1,1,1},{0,0,1},{0,0,1},{0,0,1},{0,0,1}},
            {{1,1,1},{1,0,1},{1,1,1},{1,0,1},{1,1,1}},
            {{1,1,1},{1,0,1},{1,1,1},{0,0,1},{1,1,1}}
    };
    public static int rl;
    public static int cl;

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());
        String sig = bf.readLine();

        rl = 5;
        cl = n / 5;

        board = new int[rl][cl];
        for(int i=0; i<rl; i++) {
            for(int j=0; j<cl; j++) {
                if(sig.charAt(i*cl + j) == '#') board[i][j] = 1;
            }
        }

        for(int j=0; j<cl; j++) {
            if(board[0][j] == 1) {
                int num = compare(0,j);
                if(num == -1) {
                    if (compare1(0, j)) {
                        sb.append(1); j++;
                    }
                    else {
                        continue;
                    }
                } else {
                    sb.append(num); j+=2;
                }
            }
        }

        System.out.println(sb);

    }

    public static void print_board(int rl, int cl) {
        for(int i=0; i<rl; i++) {
            for(int j=0; j<cl; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }

    }

    public static void print_num(int[][] num) {
        for(int i=0; i<num.length; i++) {
            for(int j=0; j<num[0].length; j++) {
                System.out.print(num[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static int compare(int r, int c) {
        if(c+2 >= cl) return -1;
        
        for(int k=0; k<10; k++) {
            if(k==1) continue;
            boolean flag = false;

            for(int i=0; i<nums[k].length; i++) {
                for(int j=0; j<nums[k][0].length; j++) {
                    if(board[r+i][c+j] != nums[k][i][j]) {
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            }
            if(!flag) return k;
        }
        return -1;
    }

    public static boolean compare1(int r, int c) {
        int k=1;
        for(int j=0; j<nums[k][0].length; j++) {
            if(board[r][c+j] != nums[k][0][j]) {
                return false;
            }
        }

        return true;
    }

}
