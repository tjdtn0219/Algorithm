import java.io.*;
import java.util.*;

public class Main {
    //파이어볼 정보 클래스
    static class meteor{
        int r, c, m, s, d;
        public meteor(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};	//방향 r값 변경값
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};	//방향 c값 변경값
    static ArrayList<meteor>[][] map;	//파이어볼 이동했을 때 정보
    static ArrayList<meteor> meteors = new ArrayList<>();	//모든 파이어볼 정보

    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                map[i][j] = new ArrayList<>();
        }
        //입력되는 파이어볼 정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            meteors.add(new meteor(r, c, m, s, d));
        }
        //K번 이동명령 진행
        for (int i = 0; i < K; i++) {
            meteorMove(N);
            meteorFire(N);
        }
        bw.write(meteorCal() + "");	//메테오 질량의 합 BufferedWriter 저장
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //파이어볼 이동시키는 함수
    static void meteorMove(int N) {
        for (meteor cur : meteors) {
            //r, c값 변경
            // +N을 하는 이유는 이동하였을 때 음수가 나올 수 있기 때문입니다.
            int tempR = (cur.r + N + dr[cur.d] * (cur.s%N)) % N;
            int tempC = (cur.c + N + dc[cur.d] * (cur.s%N)) % N;
            // System.out.println("x, y : " + cur.r + ", " + cur.c + " --> " + "nx, ny : " + tempR +", " + tempC);
            cur.r = tempR;
            cur.c = tempC;
            //이동한 파이어볼 저장
            map[cur.r][cur.c].add(cur);
        }
    }
    //파이어볼 분열 진행
    static void meteorFire(int N){
        for(int r = 0; r<N;r++){
            for(int c = 0; c<N;c++) {
                //파이어볼 개수가 2개 미만일 때
                if(map[r][c].size() < 2){
                    map[r][c].clear();
                    continue;
                }
                //파이어볼 2개 이상일 때
                int mSum = 0, sSum = 0, oddCount = 0, evenCount = 0;
                int size = map[r][c].size();
                //분열 진행!
                for(meteor cur : map[r][c]){
                    mSum += cur.m;	//질량 더하기
                    sSum += cur.s;	//속도 더하기
                    if(cur.d % 2 == 1)	//방향 홀수일 때
                        oddCount++;
                    else		//방향 짝수일 때
                        evenCount++;
                    meteors.remove(cur);	//분열될 파이어볼 제거!
                }
                map[r][c].clear();
                mSum /= 5;	//분열될 질량 구하기
                if(mSum == 0)	//분열될 질량이 0이면 분열 실패!
                    continue;
                sSum /= size;	//분열될 속도 구하기
                //모든 파이어볼 방향이 짝수이거나 홀수일 때
                if(oddCount == size || evenCount == size){
                    for(int i=0;i<8;i+=2)	//{0, 2, 4, 6} 방향 분열
                        meteors.add(new meteor(r,c,mSum, sSum, i));
                }else{
                    for(int i=1;i<8;i+=2)	//{1, 3, 5, 7} 방향 분열
                        meteors.add(new meteor(r,c,mSum, sSum, i));
                }
            }
        }
    }
    //파이어볼 질량의 합 구하는 함수
    static int meteorCal(){
        int result = 0;
        //모든 질량 더하기!
        for(meteor cur : meteors)
            result += cur.m;
        return result;
    }
}