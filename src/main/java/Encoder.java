public class Encoder {
	int k, n, s;
	String input;
	char gen;
	public Encoder(String m, int s, char gen) {
		input = m;
		k = m.length();
		this.s = s;
		n = 2*s + k;
		this.gen = gen;
	}
	public char[] encode() {
		char[] cin = input.toCharArray();
		char[] m = new char[n];
		for(int i = 0; i < n; i++) {
			m[i] = 0;
			if(i < cin.length) m[i] = cin[i];
		}
		char[] c = new char[n];
		
		GaluaField wn = new GaluaField((char)1);
		char[][] A = new char[n][n];
		for(int i = 0; i < n; i++) {
			
			GaluaField cur = new GaluaField((char)1);
			
			GaluaField tot = new GaluaField((char)0);
			
			for(int j = 0; j < n; j++) {
				A[i][j] = cur.val;
				tot = cur.mult(m[j]).add(tot);
				cur = cur.mult(wn);
			}
			wn = wn.mult(gen);
			c[i] = tot.val;
		}
//		Decoder.printMatrix(A);
		System.out.println("Encoded word: " + new String(c));
		return c;
	}

}
