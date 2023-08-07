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
    	
    	strings = br.readLine().split(" ");
    	int[] arr = new int[k];
    	for(int i=0; i<k; i++) arr[i] = Integer.parseInt(strings[i]);
    	
    	HashSet<Integer> using = new HashSet<>();
    	
    	int ans = 0;
    	
    	for(int i=0; i<k; i++) {
    		if(using.size() < n) {
    			using.add(arr[i]);
    			continue;
    		}
    		if(using.contains(arr[i])) continue;
    		else {	//멀티탭 중 하나를 빼야하는 상황
//    			HashMap<Integer, Integer> hmap = new HashMap<>();	//<val,idx>
    			PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o2.idx - o1.idx);
    			boolean[] is_dup = new boolean[k+1];
    			for(int j=i+1; j<k; j++) {
    				if(using.contains(arr[j]) && !is_dup[arr[j]]) {
    					is_dup[arr[j]] = true;
    					pq.add(new Pair(arr[j], j));
    				}
    			}
    			if(pq.size() < n) {
    				//앞으로 안나올 전자기기 빼면됨
    				for(Integer num : using) {
        				boolean flag = false;
    					for(Pair willUse : pq) {
    						if(num==willUse.val) flag = true;
    					}
    					if(!flag) {
    						using.remove(num);
    						break;
    					}
    				}
    			} else {
    				//앞으로 나올 전자기기 중 가장 늦게 나올 기기 빼면 됨
    				int out_val = pq.poll().val;
    				using.remove(out_val);
    			}
    			using.add(arr[i]);
    			ans++;
    		}
    	}
    	
    	System.out.println(ans);
    	
    }
}

class Pair {
	int val;
	int idx;
	
	public Pair(int val, int idx) {
		this.val = val;
		this.idx = idx;
	}
}