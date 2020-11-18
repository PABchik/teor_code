import java.util.ArrayList;
import java.util.HashSet;

public class GaluaField {
	public static char[][] adds;
	public static char[][] mults;
	public static char[] inverse;
	public static boolean[][] addsDone;
	public static boolean[][] multsDone;
	public static boolean[] inverseDone;
	
	public static final char PX = (char)0x11B;
	public static void init() {
		int max = 1<<8;
		adds = new char[max][max];
		mults = new char[max][max];
		addsDone = new boolean[max][max];
		multsDone = new boolean[max][max];
		inverse = new char[max];
		inverseDone = new boolean[max];
		for(int i = 0; i < adds.length; i++) {
			inverse[i] = 0;
			inverseDone[i] = false;
			for(int j = 0; j < adds[0].length; j++) {
				adds[i][j] = 0;
				mults[i][j] = 0;
				addsDone[i][j] = false;
				multsDone[i][j] = false;
			}
		}
	}
	public static char add(char a, char b) {
		return (char)(a ^ b);
	}
	public static char mult(char a, char b) {
		int p = a;
		int r = 0;
		while(b != 0) {
			if((b & 1) == 1) r = r ^ p;
			b = (char)(b >>> 1);
			p = p << 1;
			if((p & 0x100) == 0x100) p = p ^ PX;
		}
		return (char)r;
	}
	
	
	char val;
	public GaluaField(char val) {
		this.val = val;
	}
	public GaluaField add(char b) {
		if(!addsDone[val][b]) {
			adds[val][b] = add(val,b);
			adds[b][val] = adds[val][b];
			addsDone[val][b] = true;
			addsDone[b][val] = true;
		}
		return new GaluaField(adds[val][b]);
	}
	
	public GaluaField add(GaluaField b) {
		return add(b.val);
	}
	
	public GaluaField mult(char b) {
		if(!multsDone[val][b]) {
			mults[val][b] = mult(val,b);
			mults[b][val] = mults[val][b];
			multsDone[val][b] = true;
			multsDone[b][val] = true;
		}
		return new GaluaField(mults[val][b]);
	}
	public GaluaField mult(GaluaField b) {
		return mult(b.val);
	}
	
	public GaluaField getInverse() {
		return inverse(val);
	}
	public static GaluaField inverse(char a) {
		if(!inverseDone[a]) {
			GaluaField ga = new GaluaField(a);
			for(char i = 0; i < (1<<8); i++) {
				if(ga.mult(i).val == 1) {
					inverseDone[a] = true;
					inverseDone[i] = true;
					inverse[a] = i;
					inverse[i] = a;
					break;
				}
			}
		}
		return new GaluaField(inverse[a]);
	}
	
	public static String getBinaryString(char c) {
		String s = "";
		for(int i = 0; i < 8; i++) {
			s = (c % 2) + s;
			c = (char)(c >> 1);
		}
		return "0b" + s;
	}
	
	public static ArrayList<Integer> findGenerators() {
		ArrayList<Integer> gs = new ArrayList<Integer>();
		for(int j = 1; j < 1 << 8; j++) {
			GaluaField gz = new GaluaField((char)j);
			HashSet<Character> hs = new HashSet<Character>();
			hs.add(gz.val);
			for(int i = 2; i <= 1 << 8; i++) {
				gz = gz.mult((char)j);
				hs.add(gz.val);
			}
			if(hs.size() == 255) gs.add(j);
		}
		return gs;
	}
}
