import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	

    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String[] strings = br.readLine().split(" ");
    	
    	int n = Integer.parseInt(strings[0]);
    	int k = Integer.parseInt(strings[1]);
    	
    	int[] arr = new int[n+1];
    	int idx = 1;
    	
    	for(int i=1; i<n+1; i++) {
    		if((n%i)==0) arr[idx++] = i;
    	}
    	
    	System.out.println(arr[k]);
    	
    }
}