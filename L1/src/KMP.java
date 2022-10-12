public class KMP {
	private final String template;
	private final String text;
	private final int m;
	private final int n;

	//najdluzszy prefix poprzedzajacy sufix
	private final int[] pi;

	public KMP(final String template, final String text) {
		this.template = template;
		this.text = text;
		this.m = template.length();
		this.n = text.length();
		this.pi = new int[this.m];
		setPrefix();
		algorithm();
	}

	private void setPrefix() {
		final String[] T = this.template.split("");
		this.pi[0] = 0;
		int k = 0;
		for (int q = 1; q < this.m; q++) {
			while (k > 0 && !T[k].equalsIgnoreCase(T[q])) {
				k = this.pi[k - 1];
			}
			if (T[k].equalsIgnoreCase(T[q])) {
				k++;
			}
			this.pi[q] = k;
		}
	}

	private void algorithm() {
		final String[] splitTemplate = this.template.split("");
		final String[] splitText = this.text.split("");
		int q = 0;
		for (int i = 0; i < this.n; i++) {
			while (q > 0 && !splitTemplate[q].equalsIgnoreCase(splitText[i])) {
				q = this.pi[q - 1];
			}
			if (splitTemplate[q].equalsIgnoreCase(splitText[i])) {
				q++;
			}
			if (q == this.m) {
				System.out.println("Wzorzec wystepuje z przesunieciem " + (i - this.m + 1));
				q = this.pi[q - 1];
			}
		}
	}
}
