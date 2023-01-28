import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strings = bf.readLine().strip().split(" ");
        int N = Integer.parseInt(strings[0]);
        int M = Integer.parseInt(strings[1]);

        char[][] chessBoard = new char[N][M];

        for(int i=0; i<N; i++){
            String line = bf.readLine();
            for(int j=0; j<M; j++){
                chessBoard[i][j] = line.charAt(j);
            }
        }

        int min = 8*8;
        for(int i=0; i<N-7; i++){
            for(int j=0; j<M-7; j++){
                int changed = compareBoard(chessBoard, i, j);
                if(changed < min) min = changed;
            }
        }

        System.out.println(min);

    }

    public static int compareBoard(char[][] board, int p, int q){
        char[][] Wboard = originBoard('W');
        char[][] Bboard = originBoard('B');
        int Wcnt = 0;
        int Bcnt = 0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(board[p+i][q+j] != Wboard[i][j]) Wcnt++;
                if(board[p+i][q+j] != Bboard[i][j]) Bcnt++;
            }
        }
        if(Wcnt < Bcnt) return Wcnt;
        else return Bcnt;
    }

    public static char[][] originBoard(char c){
        char[][] board = new char[8][8];

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if((i+j) % 2 == 0){
                    if(c == 'W') board[i][j] = 'W';
                    else board[i][j] = 'B';
                } else{
                    if(c == 'W') board[i][j] = 'B';
                    else board[i][j] = 'W';
                }
            }
        }
        return board;
    }

    public static void printChessBoard(char[][] board, int N, int M){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
