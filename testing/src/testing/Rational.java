package testing;

class Rational {
	private int numerator;
	private int denominator;
	
	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("El denominado no puede ser 0");
		}
		int gcd = gcd(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}
	public Rational add(Rational other) {
		int newNumerator = this.numerator * other.numerator + other.numerator *this.numerator;
		int newDenominator = this.numerator * other.denominator;
		return new Rational(newNumerator,newDenominator);
	}
    public Rational subtract(Rational other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Rational(newNumerator, newDenominator);
    }
    public Rational multiply(Rational other) {
    	int newNumerator = this.numerator * other.numerator;
    	int newDenominator = this.denominator * other.denominator;
    	return new Rational(newNumerator, newDenominator);
    }
    public Rational divide(Rational other) {
    	int newNumerator = this.numerator * other.denominator;
    	int newDenominator = this.denominator * other.numerator;
    	return new Rational(newNumerator, newDenominator);
    }
    public int gcd(int a, int b) {
    	if (b==0) {
    		return a;
    	}
    	return gcd(b,a%b);
    }
    @Override
    public String toString() {
    	return numerator + "/" + denominator;
    }
}
