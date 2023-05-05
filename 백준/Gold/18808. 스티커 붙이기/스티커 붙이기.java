import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static List<Puzzle> puzzle_list = new ArrayList<>();
    public static int[][] board;
    public static int bn, bm;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int k = Integer.parseInt(strings[2]);

        board = new int[n][m];
        bn = n;
        bm = m;

        for(int t=0; t<k; t++) {
            strings = bf.readLine().split(" ");
            int rw = Integer.parseInt(strings[0]);
            int cl = Integer.parseInt(strings[1]);
            int[][] arr = new int[rw][cl];
            for(int i=0; i<rw; i++) {
                strings = bf.readLine().split(" ");
                for(int j=0; j<cl; j++) {
                    arr[i][j] = Integer.parseInt(strings[j]);
                }
            }
            puzzle_list.add(new Puzzle(arr));
        }

        for(Puzzle puzzleOb : puzzle_list) {
            int[][] puzzle = puzzleOb.getPuzzle();
            for(int rot=0; rot<4; rot++) {
                if(do_puzzle(puzzle)) break;
                if(rot<3) puzzle = rotate_puzzle(puzzle);
            }
        }

        System.out.println(get_cnt(board));


    }

    public static int get_cnt(int[][] arr){
        int cnt = 0;
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                if(arr[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }
    public static void print_arr(int[][] arr){
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static boolean do_puzzle(int[][] puzzle) {
        int n = puzzle.length;
        int m = puzzle[0].length;

        for(int i=0; i+n<bn+1; i++) {
            for(int j=0; j+m<bm+1; j++) {
                if(try_put(puzzle, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean try_put(int[][] puzzle, int si, int sj) {
        int n = puzzle.length;
        int m = puzzle[0].length;
        for(int i=si; i<si+n; i++) {
            for(int j=sj; j<sj+m; j++) {
                if(board[i][j]==1 && puzzle[i-si][j-sj]==1) return false;
            }
        }

        for(int i=si; i<si+n; i++) {
            for(int j=sj; j<sj+m; j++) {
                if(puzzle[i-si][j-sj]==1) board[i][j] = puzzle[i-si][j-sj];
            }
        }
        return true;
    }

    public static int[][] rotate_puzzle(int[][] origin) {
        int n = origin.length;
        int m = origin[0].length;
        int[][] rotated = new int[m][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                rotated[j][n-i-1] = origin[i][j];
            }
        }
        return rotated;
    }
}

class Puzzle{
     int[][] puzzle;

    public Puzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }
}