import java.util.*;
import java.io.*;

class Main
{
	
	public static void main(String args[]) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int k = Integer.parseInt(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			String[] strings = br.readLine().split(" ");
			for(int i=0; i<k; i++) {
				pq.add(Long.parseLong(strings[i]));
			}
			
			long ans = 0;
			while(pq.size() > 1) {
				long n1 = pq.poll();
				long n2 = pq.poll();
				ans += n1 + n2;
				pq.add(n1+n2);
			}
			
			System.out.println(ans);
			
		}
		
	}
   
}

