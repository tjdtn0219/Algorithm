import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(bf.readLine());
            int[] twoAdds = getTwoAddsOfPnum(N);
            System.out.println(twoAdds[0] + " " + twoAdds[1]);
        }

    }

    public static int[] getTwoAddsOfPnum(int num){
        int[] twoAdds = new int[2];
        for(int i=num/2; i>=2; i--){
            if(checkPnum(i) && checkPnum(num-i)){
                twoAdds[0] = i;
                twoAdds[1] = num - i;
                return twoAdds;
            }
        }
        return null;
    }

    public static boolean checkPnum(int num){

        if(num==1){
            return false;
        }
        for(int i=2; i*i<=num; i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}