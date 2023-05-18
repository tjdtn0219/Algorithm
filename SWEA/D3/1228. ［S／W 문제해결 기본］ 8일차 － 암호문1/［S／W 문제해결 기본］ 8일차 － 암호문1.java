
import java.util.*;
import java.io.*;

public class Solution {

	public static final int T = 10;
	public static final int N = 8;
	public static final int Cycle = 5;
	
	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(bf.readLine());
		for(int t=0; t<T; t++) {
			StringBuilder sb = new StringBuilder();
			LinkedList<Integer> list = new LinkedList<>();
			
			int ori_len = Integer.parseInt(bf.readLine());
			String[] strings = bf.readLine().split(" ");
			for(int i=0; i<ori_len; i++) {
				list.add(Integer.parseInt(strings[i]));
			}
			
			int com_len = Integer.parseInt(bf.readLine());
			strings = bf.readLine().split("I");
			for(int i=1; i<=com_len; i++) {
//				System.out.println("I" + (i+1) + " : " + strings[i]);
				String[] cmds = strings[i].trim().split(" ");
				insert(cmds, list);
				
			}
			
			sb.append("#" + (t+1));
			for(int i=0; i<10; i++) {
				sb.append(" " + list.get(i));
			}
			System.out.println(sb.toString());
			
			
		}
	}
	
	public static void insert(String[] cmds, LinkedList<Integer> list) {
		int idx = Integer.parseInt(cmds[0]);
		int i_num = Integer.parseInt(cmds[1]);
		
//		for(int i=0; i<i_num; i++) {
//			list.add(idx+i, Integer.parseInt(cmds[2+i]));
//		}
//		
		ListIterator<Integer> iter = list.listIterator();
		for(int i=0; i<idx; i++) {
			iter.next();
		}
		for(int i=0; i<i_num; i++) {
			iter.add(Integer.parseInt(cmds[2+i]));
		}
	}
}