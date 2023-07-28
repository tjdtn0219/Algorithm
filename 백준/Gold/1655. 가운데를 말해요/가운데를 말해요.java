import java.util.*;
import java.io.*;

class Main
{
	
	public static void main(String args[]) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1,o2)->o2-o1);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(maxHeap.size() <= minHeap.size()) maxHeap.add(num);
			else minHeap.add(num);
			
			if(minHeap.size()>0 && maxHeap.peek() > minHeap.peek()) {
				minHeap.add(maxHeap.poll());
				maxHeap.add(minHeap.poll());
			}
			
			sb.append(maxHeap.peek() + "\n");
		}
		
		System.out.print(sb.toString());
		
	}
   
}

