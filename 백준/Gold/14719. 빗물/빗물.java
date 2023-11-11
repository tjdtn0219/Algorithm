import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String[] strings = br.readLine().split(" ");
    	
    	int h = Integer.parseInt(strings[0]);
    	int w = Integer.parseInt(strings[1]);
    	
    	strings = br.readLine().split(" ");
    	int[] heights = new int[w];
    	for(int i=0; i<w; i++) {
    		heights[i] = Integer.parseInt(strings[i]);
    	}
    	
    	int[] left_max_height = new int[w];		//idx 기준 왼쪽에서 가장 큰 높이
    	int max = heights[0];
    	for(int i=0; i<w; i++) {
    		if(max < heights[i]) max = heights[i];
    		left_max_height[i] = max;
    	}
    	
    	int[] right_max_height = new int[w];	//idx기준 오른쪽에서 가장 큰 높이
    	max = heights[w-1];
    	for(int i=w-1; i>=0; i--) {
    		if(max < heights[i]) max = heights[i];
    		right_max_height[i] = max;
    	}

    	int[] flood_heights = new int[w];		//빗물 높이
    	for(int i=0; i<w; i++) {
    		flood_heights[i] = Math.min(left_max_height[i], right_max_height[i]);
    	}
    	
    	int ans = 0;
    	for(int i=0; i<w; i++) {
    		ans += flood_heights[i] - heights[i];
    	}
    	
    	System.out.println(ans);
    	
    }
    
}