package com.linearalgebra.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Rational {
	private int numerator;
	private int denominator;
	@JsonIgnore
	public Rational(int numerator, int denominator) {
		try {
			if (denominator == 0) {
				throw new ArithmeticException("El denominador no puede ser 0");
			}

			if (!(numerator >= Integer.MIN_VALUE && numerator <= Integer.MAX_VALUE)
					|| !(denominator >= Integer.MIN_VALUE && denominator <= Integer.MAX_VALUE)) {
				throw new IllegalArgumentException("Los numeradores y denominadores deben ser enteros.");
			}

			// Manejar números negativos
			if (denominator < 0) {
				numerator = -numerator;
				denominator = -denominator;
			}

			// Simplificar la fracción
			int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
			this.numerator = numerator / gcd;
			this.denominator = denominator / gcd;
		} catch (ArithmeticException e) {
			System.out.println("Se ha producido una excepción: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Se ha producido una excepción: " + e.getMessage());
		}
	}
	@JsonCreator
    public Rational(@JsonProperty("rational")String fractionString) {
        try {
            String[] parts = fractionString.split("/");
            if (parts.length != 2) {
                throw new NumberFormatException("Formato de fracción no válido");
            }

            int num = Integer.parseInt(parts[0]);
            int denom = Integer.parseInt(parts[1]);

            if (denom == 0) {
                throw new ArithmeticException("El denominador no puede ser 0");
            }

            if (denom < 0) {
                num = -num;
                denom = -denom;
            }

            int gcd = gcd(Math.abs(num), Math.abs(denom));
            this.numerator = num / gcd;
            this.denominator = denom / gcd;
        } catch (NumberFormatException e) {
            // Manejar la excepción de formato
           System.out.println("Error de formato");
        } catch (ArithmeticException e) {
            // Manejar la excepción de división por cero
           System.out.println();
        }
    }
	/**
	 * Obtiene el numerador de la fracción.
	 *
	 * @return El numerador.
	 */
	@JsonIgnore
	public int getNumerator() {
		return numerator;
	}

	/**
	 * Obtiene el denominador de la fracción.
	 *
	 * @return El denominador.
	 */
	@JsonIgnore
	public int getDenominator() {
		return denominator;
	}
	@JsonIgnore
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
    /**
     * Calcula la inversa de esta fracción.
     *
     * @return La fracción inversa.
     */
	@JsonIgnore
    public Rational getInverse() {
        if (numerator == 0) {
            throw new ArithmeticException("No se puede calcular la inversa si el numerador es 0");
        }

        return new Rational(denominator, numerator);
    }
	@JsonIgnore
    public void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }
    @Override
    @JsonValue
    public String toString() {
        if (denominator == 1) {
            return Integer.toString(numerator); // Si el denominador es 1, imprime solo el numerador.
        } else if (numerator == denominator) {
            return "1"; // Si el numerador y el denominador son iguales, imprime "1".
        } else {
            return numerator + "/" + denominator; // En otros casos, imprime la fracción normalmente.
        }
    }
}
