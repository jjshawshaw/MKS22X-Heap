import java.util.*;

public class HeapPrinter {

	// call like this: HeapPrinter.print(<array>);
	// warning! large heaps will be very big and hard to read!
	// also, try to stick to 1, maybe 2 digit numbers to make it easier to read

	public static String toString(int[] array, int s) {

		if (array.length == 0) return "";
		int largest = array[0];
		for (int j = 1; j < s; j++)
			if (Math.abs(largest) < Math.abs(array[j])) largest = array[j];

		int size = 1;
		while (size <= array.length) size <<= 1;
		int buffer = (size>>1)*(int)(Math.log(Math.abs(largest))/Math.log(10)+(largest > 0 ? 1 : 2));

		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (int row = 2; row <= size; row <<= 1) {

			for (; i < row-1 && i < s; i++) {
				int numlength = array[i] == 0 ? 1 : (int)(Math.log(Math.abs(array[i]))/Math.log(10))+(array[i] > 0 ? 1 : 2);
				for (int j = numlength; j < buffer; j++) {
					sb.append(" ");
				}
				sb.append(array[i]);
				for (int j = 0; j < buffer; j++) sb.append(" ");
			}
			if (row != size) sb.append("\n");
			buffer = buffer>>1;
		}

		return new String(sb);
	}

	public static void print(int[] array) {
		System.out.println(toString(array, array.length));
	}
}
