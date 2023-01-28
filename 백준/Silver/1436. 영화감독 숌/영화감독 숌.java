import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());
        int number = 666;

        while(N>0){
            if(IsContain666(number)) N--;
            number++;
        }
        System.out.println((number-1));
        
    }

    public static boolean IsContain666(int num){
        int[] arr = sanitizeNum(num);

        for(int i=0; i<arr.length-2; i++){
            if(arr[i]==6 && arr[i+1]==6 && arr[i+2]==6){
                return true;
            }
        }
        return false;
    }

    public static int[] sanitizeNum(int num){
        int tmp = num;

        int cnt = 0;
        while(tmp>0){
            tmp /= 10;
            cnt++;
        }

        int[] arr = new int[cnt];
        for(int i=cnt-1; i>=0; i--){
            arr[i] = num%10;
            num /= 10;
        }

        return arr;
    }
}
