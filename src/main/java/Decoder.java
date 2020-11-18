import java.util.*;

public class Decoder {
    char gen;
    int k;
    public Decoder(char gen, int k) {
        this.gen = gen;
        this.k = k;
    }

    public static void printMatrix(char[][] A) {
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[i].length; j++) {
                System.out.print((int)A[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static void printMatrix(int[][] A) {
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[i].length; j++) {
                System.out.print((int)A[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public char[] marixProcession(char[][] A, char[] c) {
        char[] m = new char[k];
        for(int i = 0; i < k; i++) {
            GaluaField a = new GaluaField(A[i][i]);
            GaluaField inv = a.getInverse();
            for(int j = i+1; j < k; j++) {
                GaluaField b = new GaluaField(A[j][i]);
                b = b.mult(inv);
                for(int k = 0; k < this.k; k++) {
                    GaluaField v = new GaluaField(A[i][k]);
                    A[j][k] = v.mult(b).add(A[j][k]).val;
                }
                GaluaField v = new GaluaField(c[i]);
                c[j] = v.mult(b).add(c[j]).val;
            }
        }


        for(int i = k-1; i >= 0; i--) {

            GaluaField sum = new GaluaField((char)c[i]);
            for(int j = k-1; j > i; j--) {
                GaluaField v = new GaluaField(A[i][j]);
                sum =  v.mult(m[j]).add(sum);
            }

            GaluaField a = new GaluaField(A[i][i]);
            GaluaField inv = a.getInverse();
            m[i] = sum.mult(inv).val;
        }


        return m;
    }

    public char[] decode(char[] allC) {
        GaluaField wn = new GaluaField((char)1);
        char[][] A = new char[k][k];
        char[] c = new char[k];
        char[] m = new char[k];
        int cnt = 0;

        for(int i = 0; i < allC.length; i++) {
            if(i != 0) wn = wn.mult(gen);

            GaluaField cur = new GaluaField((char)1);
            for(int j = 0; j < k; j++) {
                A[cnt][j] = cur.val;
                cur = cur.mult(wn);
            }
            c[cnt] = allC[i];

            cnt++;
            if(cnt >= k) break;
        }

//		printMatrix(A);
        m = marixProcession(A,c);
//		printMatrix(A);

        return m;
    }



}
