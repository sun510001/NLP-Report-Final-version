package NLPAnalasis;

import java.io.IOException;


public class DiffWord {
	public boolean DiffWord(String Fir, String Sec) throws IOException {//Whether the Japanese words are the same or different.

		if (Sec.equals(Fir)) {
			return true;
			//System.out.println("True");
		} else {
			//System.out.println("False");
			return false;
		}
	}
}
