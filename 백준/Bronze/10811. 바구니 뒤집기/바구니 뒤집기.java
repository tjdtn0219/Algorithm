import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int[] arr = new int[sc.nextInt()];
		for (int i = 0; i < arr.length; i++) { // 기본배열 생성
			arr[i] = i + 1;
		}

		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int order1 = sc.nextInt() - 1;
			int order2 = sc.nextInt() - 1;
			
			while (order1 < order2) {
				int temp = arr[order1];
				arr[order1++] = arr[order2];
				arr[order2--] = temp;
			}

		}
		String ret = "";
		for (int j = 0; j < arr.length; j++) {
			ret += arr[j] + " ";
		}
		System.out.print(ret.trim());
		sc.close();
	}
}