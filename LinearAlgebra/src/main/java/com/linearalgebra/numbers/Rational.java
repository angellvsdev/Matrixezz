package com.linearalgebra.numbers;

class Rational {
    private int numerator;
    private int denominator;

    /**
     * Constructor de la clase Rational que toma un numerador y un denominador.
     *
     * @param numerator   El numerador de la fracción.
     * @param denominator El denominador de la fracción (debe ser diferente de 0).
     */
    public Rational(int numerator, int denominator) {
        try {
            if (denominator == 0) {
                throw new ArithmeticException("El denominador no puede ser 0");
            }

            if (!(numerator >= Integer.MIN_VALUE && numerator <= Integer.MAX_VALUE) || !(denominator >= Integer.MIN_VALUE && denominator <= Integer.MAX_VALUE)) {
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
    /**
     * Constructor de la clase Rational que toma una cadena en el formato "num/denom" y la convierte en una fracción.
     *
     * @param fractionString La cadena que representa la fracción (ejemplo: "2/3").
     * @throws NumberFormatException Si la cadena no está en el formato esperado.
     */
    public Rational(String fractionString) {
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
    public int getNumerator() {
        return numerator;
    }

    /**
     * Obtiene el denominador de la fracción.
     *
     * @return El denominador.
     */
    public int getDenominator() {
        return denominator;
    }
    /**
     * Calcula la inversa de esta fracción.
     *
     * @return La fracción inversa.
     */
    public Rational getInverse() {
        if (numerator == 0) {
            throw new ArithmeticException("No se puede calcular la inversa si el numerador es 0");
        }

        return new Rational(denominator, numerator);
    }
    /**
     * Simplifica la fracción actual.
     */
    public void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    /**
     * Realiza la suma de dos fracciones y devuelve una nueva fracción como resultado.
     *
     * @param other La otra fracción a sumar.
     * @return Una nueva fracción que representa la suma.
     */
    
    /**
     * Realiza la suma de dos fracciones y devuelve una nueva fracción como resultado.
     *
     * @param other La otra fracción a sumar.
     * @return Una nueva fracción que representa la suma.
     */
    public Rational add(Rational other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        Rational result = new Rational(newNumerator, newDenominator);
        result.simplify(); // Simplificar el resultado
        return result;
    }

    /**
     * Realiza la resta de dos fracciones y devuelve una nueva fracción como resultado.
     *
     * @param other La otra fracción a restar.
     * @return Una nueva fracción que representa la resta.
     */
    public Rational subtract(Rational other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        Rational result = new Rational(newNumerator, newDenominator);
        result.simplify(); // Simplificar el resultado
        return result;
    }

    /**
     * Realiza la multiplicación de dos fracciones y devuelve una nueva fracción como resultado.
     *
     * @param other La otra fracción a multiplicar.
     * @return Una nueva fracción que representa la multiplicación.
     */
    public Rational multiply(Rational other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        Rational result = new Rational(newNumerator, newDenominator);
        result.simplify(); // Simplificar el resultado
        return result;
    }

    /**
     * Realiza la división de dos fracciones y devuelve una nueva fracción como resultado.
     *
     * @param other La otra fracción como divisor.
     * @return Una nueva fracción que representa la división.
     */
    public Rational divide(Rational other) {
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;
        Rational result = new Rational(newNumerator, newDenominator);
        result.simplify(); // Simplificar el resultado
        return result;
    }

    /**
     * Calcula el máximo común divisor (GCD) de dos enteros.
     *
     * @param a El primer entero.
     * @param b El segundo entero.
     * @return El GCD de a y b.
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    @Override
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
