import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
 
        boolean[][] arr = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
 
            arr[x][y] = true;
        }
        
        // 플로이드 와샬 알고리즘
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][k] && arr[k][j]) {
                        arr[i][j] = true;
                    }
                }
            }
        }
        
        int S = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        while(S-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            if(x < 1 || y < 1 || x > N || y > N) { // 주어진 범위에서 벗어날 경우
                sb.append("0\n");
            }else {
                if(arr[x][y]) { // x에서 y로 갈 수 있다는 뜻은 사건이 먼저 일어났다는 뜻
                    sb.append("-1\n");
                }else {
                    if(arr[y][x]) { // y에서 x로 갈 수 있다는 뜻은 사건이 나중에 일어났다는 뜻
                        sb.append("1\n");
                    }else { // x에서 y, y에서 x로 모두 갈 수 없으면 상관 없는 사건 관계임
                        sb.append("0\n");
                    }
                }
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
}