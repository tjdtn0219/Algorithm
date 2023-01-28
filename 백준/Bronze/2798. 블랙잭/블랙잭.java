import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strings = bf.readLine().strip().split(" ");

        int N = Integer.parseInt(strings[0]);
        int M = Integer.parseInt(strings[1]);

        strings = bf.readLine().strip().split(" ");
        int[] arr = new int[strings.length];

        for(int i=0; i<strings.length; i++){
            arr[i] = Integer.parseInt(strings[i]);
        }

        int min_gap = 300000;
        int csum = 0;
        int sum = 0;
        for(int i=0 ; i<arr.length-2; i++){
            for(int j=i+1; j<arr.length-1; j++){
                for(int k=j+1; k<arr.length; k++){
                    sum = arr[i] + arr[j] + arr[k];
                    int gap = M - sum;
                    if(gap < min_gap && gap >= 0){
                        min_gap = gap;
                        csum = sum;
                    }
                }
            }
        }
        System.out.println(csum);
    }
}
