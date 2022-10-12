//algorytm najlepiej uzywac, gdy tekst i szukany pattern maja duzo wspolnych znakow
public class KMP {
	private final String template;
	private final String text;
	private final int m;
	private final int n;

	//najdluzszy prefix poprzedzajacy sufix
	private final int[] lpsTable;

	public KMP(final String template, final String text) {
		this.template = template;
		this.text = text;
		this.m = template.length();
		this.n = text.length();
		this.lpsTable = new int[this.m];
		setLpsTable();
		algorithm();
	}

	//zlozonosc obliczeniowa: O(m)
	private void setLpsTable() {
		final String[] T = this.template.split("");

		//ustawiamy pierwsza wartosc na 0
		this.lpsTable[0] = 0;
		//pointer dla najdluzszego prefixu
		int k = 0;
		//wyliczamy najdluzszy prefix, ktory jest jednoczesnie suffixem
		for (int q = 1; q < this.m; q++) {
			while (k > 0 && !T[k].equalsIgnoreCase(T[q])) {
				k = this.lpsTable[k - 1];
			}
			if (T[k].equalsIgnoreCase(T[q])) {
				k++;
			}
			this.lpsTable[q] = k;
		}
	}

	//zlozonosc obliczeniowa: O(m+n), ale n >> m, wiec O(n)
	private void algorithm() {
		final String[] splitTemplate = this.template.split("");
		final String[] splitText = this.text.split("");
		int q = 0;
		for (int i = 0; i < this.n; i++) {
			while (q > 0 && !splitTemplate[q].equalsIgnoreCase(splitText[i])) {
				q = this.lpsTable[q - 1];
			}
			if (splitTemplate[q].equalsIgnoreCase(splitText[i])) {
				q++;
			}
			if (q == this.m) {
				System.out.println("Wzorzec wystepuje z przesunieciem " + (i - this.m + 1));
				q = this.lpsTable[q - 1];
			}
		}
	}
}
