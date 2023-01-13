
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(bf.readLine());

        List<Integer> pnumList = new ArrayList<>();

        int len = N/2 + 1;

        for(int i=2; i<=N; i++){
            while(N%i==0){
                pnumList.add(i);
                N /= i;
            }
            if(N==1){
                break;
            }
        }
        
        for(int pnum : pnumList){
            System.out.println(pnum);
        }
    }
}
