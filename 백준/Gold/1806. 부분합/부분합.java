import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    	String[] strings = br.readLine().split(" ");
    	int n = Integer.parseInt(strings[0]);
    	int s = Integer.parseInt(strings[1]);

    	int[] arr = new int[n+1];
    	strings = br.readLine().split(" ");
    	for(int i=0; i<n; i++) arr[i] = Integer.parseInt(strings[i]);
    	
    	int l = 0;
    	int r = 0;
    	int sum = arr[0];
    	int len = n+1;
    	
    	while(l<=r && r<n) {
    		if(sum < s) {
    			sum += arr[++r];
    		} else {
    			len = Math.min(len, r-l+1);
    			sum -= arr[l++];
    		}
    		
    	}
    	if(len == n+1) len = 0;
    	System.out.println(len);
    }
}