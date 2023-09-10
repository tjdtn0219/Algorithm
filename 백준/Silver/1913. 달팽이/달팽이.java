import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] arr = new int[n+1][n+1];

        int cnt = 1;
        int num = 1;

        int x = n/2+1;
        int y = n/2+1;
        arr[x][y] = num;
        x--;
        cnt++;

        while(num<n*n) {

            for(int i=0; i<cnt; i++) {          //오른쪽
//                System.out.println("x = " + x + " y = " + y + " num : " + (num+1));
                arr[x][y++] = ++num;
//                System.out.println("arr[" + (x) + "][" + (y-1) + "] : " + arr[x][y-1]
//                        + " cur : " + (x) + " , " + (y-1));
            }
            x++; y--;

            for(int i=0; i<cnt; i++) {  //아래
//                System.out.println("x = " + x + " y = " + y + " num : " + (num+1));
                arr[x++][y] = ++num;
//                System.out.println("arr[" + (x-1) + "][" + y + "] : " + arr[x-1][y]
//                        + " cur : " + (x-1) + " , " + y);
            }
            x--; y--;

            for(int i=0; i<cnt; i++) {  //왼쪽
//                System.out.println("x = " + x + " y = " + y + " num : " + (num+1));;
                arr[x][y--] = ++num;
//                System.out.println("arr[" + (x) + "][" + (y+1) + "] : " + arr[x][y+1]
//                        + " cur : " + (x-1) + " , " + y);
            }
            x--; y++;

            for(int i=0; i<cnt; i++) {  //위쪽
//                System.out.println("x = " + x + " y = " + y + " num : " + (num+1));
                arr[x--][y] = ++num;
//                System.out.println("arr[" + (x+1) + "][" + y + "] : " + arr[x+1][y]
//                        + " cur : " + (x-1) + " , " + y);
            }

            cnt += 2;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
//                System.out.print(arr[i][j] + " ");
                if(arr[i][j]==m){
                    x=i; y=j;
                }
                sb.append(arr[i][j]).append(" ");
            }
//            System.out.println();
            sb.append("\n");
        }

        System.out.print(sb.toString());
        System.out.println(x + " " + y);
    }
}

