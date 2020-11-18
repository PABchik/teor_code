import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		GaluaField.init();

		char gen = 3;

		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		int s = 5;
		
		Encoder e = new Encoder(input, s, gen);

		char[] c28 = e.encode();

		Decoder d = new Decoder(gen, input.length());
		
		
		char[] c2 = d.decode(c28);
		String s2 = new String(c2);
		System.out.println("Decoded value: " + s2);

		
	}
	public static HashSet<Integer> createSet(int[] a) {
		HashSet<Integer> h = new HashSet<Integer>();
		for(int a1 : a) h.add(a1);
		return h;
	}
}
