package com.linearalgebra.service;

import org.springframework.stereotype.Service;

import com.linearalgebra.entity.Rational;

@Service
public class RationalService {
	
    public Rational add(Rational first, Rational second) {
        int newNumerator = first.getNumerator() * second.getDenominator() + second.getNumerator() * first.getDenominator();
        int newDenominator = first.getDenominator() * second.getDenominator();
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
    public Rational subtract(Rational first, Rational second) {
        int newNumerator = first.getNumerator() * second.getDenominator() - second.getNumerator() * first.getDenominator();
        int newDenominator = first.getDenominator() * second.getDenominator();
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
    public Rational multiply(Rational first, Rational second) {
        int newNumerator = first.getNumerator() * second.getNumerator();
        int newDenominator = first.getDenominator() * second.getDenominator();
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
    public Rational divide(Rational first, Rational second) {
        int newNumerator = first.getNumerator() * second.getDenominator();
        int newDenominator = first.getDenominator() * second.getNumerator();
        Rational result = new Rational(newNumerator, newDenominator);
        result.simplify(); // Simplificar el resultado
        return result;
    }
}
