import java.util.*;

public class FA {

	private final String template;
	private final String text;
	private final int m;
	private final Set<String> alphabet = new HashSet<>();

	//mapa wszystkich przejsc; Pair<Stan, input>, targetStan
	private final Map<Pair<Integer, String>, Integer> sigma = new HashMap<>();

	public FA(final String template, final String text) {
		this.template = template;
		this.text = text;
		this.m = template.length();
		//nie chcemy powtorzen w alfabecie
		this.alphabet.addAll(Arrays.asList(template.split("")));
		this.calculateSigma();
		this.algorithm();
	}

	private void calculateSigma() {
		//rozpatrujemy wszystkie stany q i symbole a (z alfabetu)
		for (int q = 0; q < this.m + 1; q++) {
			for (final String a : this.alphabet) {
				int k = Math.min(this.m + 1, q + 2);
				do {
					k -= 1;
				} while (!(this.template.substring(0, q) + a).endsWith(this.template.substring(0, k)));
				this.sigma.put(new Pair<>(q, a), k);
			}
		}
	}

	private void algorithm() {
		final String[] T = this.text.split("");
		final int n = this.text.length();

		//aktualny stan
		int q = 0;

		for (int i = 0; i < n; i++) {
			if (this.alphabet.contains(T[i])) {
				q = this.sigma.get(new Pair<>(q, T[i]));
				//doszlismy do stanu akceptujacego, wiec mozemy wypisac wzorzec
				if (q == this.m) {
					final int s = i - this.m;
					System.out.println("Wzorzec występuje z przesunięciem " + (s + 1));
				}
			} else {
				q = 0;
			}
		}
	}
}
