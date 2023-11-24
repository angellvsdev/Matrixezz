package com.linearalgebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linearalgebra.entity.Rational;
import com.linearalgebra.entity.Value;

@Service
public class ValueService {
	@Autowired
	private RationalService rationalService;
    public Value add(Value first, Value second) {
        try {
            if (first.isDouble() && second.isDouble()) {
                // Suma de dos valores Double
                return new Value(first.getDoubleValue() + second.getDoubleValue());
            } else if (first.isRational() && second.isRational()) {
                // Suma de dos valores Rational
                return new Value(rationalService.add(first.getRationalValue(), second.getRationalValue()));
            } else if (first.isDouble() && second.isRational()) {
                if (Value.fractionable(first.getDoubleValue())) {
                    Rational rational = Value.fromDouble(first.getDoubleValue());
                    return new Value(rationalService.add(rational, second.getRationalValue()));
                } else {
                    return new Value(first.getDoubleValue() + Value.fromRational(second.getRationalValue()));
                }
            } else if (first.isRational() && second.isDouble()) {
                if (Value.fractionable(second.getDoubleValue())) {
                    Rational rational = Value.fromDouble(second.getDoubleValue());
                    return new Value(rationalService.add(rational, second.getRationalValue()));
                } else {
                    return new Value(Value.fromRational(first.getRationalValue()) + second.getDoubleValue());
                }
            } else {
                // Manejar el caso en el que ambos valores son nulos o inválidos
                return new Value(0.0);
            }
        } catch (Exception e) {
            // Manejar la excepción
            System.out.println("El valor está vacío");
        }
		return new Value(0.0);
    }
    /**
     * Realiza la resta de dos instancias de Value y devuelve una nueva instancia de Value con el resultado.
     *
     * @param other El otro valor a restar.
     * @return Una nueva instancia de Value que representa la resta.
     */
    public Value subtract(Value first, Value second) {
        try {
            if (first.isDouble() && second.isDouble()) {
                // Resta de dos valores Double
                return new Value(first.getDoubleValue() - second.getDoubleValue());
            } else if (first.isRational() && second.isRational()) {
                // Resta de dos valores Rational
                return new Value(rationalService.subtract(first.getRationalValue(), second.getRationalValue()));
            } else if (first.isDouble() && second.isRational()) {
                if (Value.fractionable(first.getDoubleValue())) {
                    Rational rational = Value.fromDouble(first.getDoubleValue());
                    return new Value(rationalService.subtract(rational, second.getRationalValue()));
                } else {
                    return new Value(first.getDoubleValue() - Value.fromRational(second.getRationalValue()));
                }
            } else if (first.isRational() && second.isDouble()) {
                if (Value.fractionable(second.getDoubleValue())) {
                    Rational rational = Value.fromDouble(second.getDoubleValue());
                    return new Value(rationalService.subtract(first.getRationalValue(), rational));
                } else {
                    return new Value(Value.fromRational(first.getRationalValue()) - second.getDoubleValue());
                }
            } else {
                // Manejar el caso en el que ambos valores son nulos o inválidos
                return new Value(0.0);
            }
        } catch (Exception e) {
            // Manejar la excepción
            System.out.println("El valor está vacío");
        }
        return new Value(0.0);
    }/**
     * Realiza la multiplicación de dos instancias de Value y devuelve una nueva instancia de Value con el resultado.
    *
    * @param other El otro valor a multiplicar.
    * @return Una nueva instancia de Value que representa la multiplicación.
    */
    public Value multiply(Value first, Value second) {
        try {
            if (first.isDouble() && second.isDouble()) {
                // Multiplicación de dos valores Double
                return new Value(first.getDoubleValue() * second.getDoubleValue());
            } else if (first.isRational() && second.isRational()) {
                // Multiplicación de dos valores Rational
                return new Value(rationalService.multiply(first.getRationalValue(), second.getRationalValue()));
            } else if (first.isDouble() && second.isRational()) {
                if (Value.fractionable(first.getDoubleValue())) {
                    Rational rational = Value.fromDouble(first.getDoubleValue());
                    return new Value(rationalService.multiply(rational, second.getRationalValue()));
                } else {
                    return new Value(first.getDoubleValue() * Value.fromRational(second.getRationalValue()));
                }
            } else if (first.isRational() && second.isDouble()) {
                if (Value.fractionable(second.getDoubleValue())) {
                    Rational rational = Value.fromDouble(second.getDoubleValue());
                    return new Value(rationalService.multiply(first.getRationalValue(), rational));
                } else {
                    return new Value(Value.fromRational(first.getRationalValue()) * second.getDoubleValue());
                }
            } else {
                // Manejar el caso en el que ambos valores son nulos o inválidos
                return new Value(0.0);
            }
        } catch (Exception e) {
            // Manejar la excepción
            System.out.println("El valor está vacío");
        }
        return new Value(0.0);
    }
   /**
    * Realiza la división de dos instancias de Value y devuelve una nueva instancia de Value con el resultado.
    *
    * @param other El otro valor para la división.
    * @return Una nueva instancia de Value que representa la división.
    */
    public Value divide(Value first, Value second) {
        try {
            if (first.isDouble() && second.isDouble()) {
                // División de dos valores Double
                return new Value(first.getDoubleValue() / second.getDoubleValue());
            } else if (first.isRational() && second.isRational()) {
                // División de dos valores Rational
                return new Value(rationalService.divide(first.getRationalValue(), second.getRationalValue()));
            } else if (first.isDouble() && second.isRational()) {
                if (Value.fractionable(first.getDoubleValue())) {
                    Rational rational = Value.fromDouble(first.getDoubleValue());
                    return new Value(rationalService.divide(rational, second.getRationalValue()));
                } else {
                    return new Value(first.getDoubleValue() / Value.fromRational(second.getRationalValue()));
                }
            } else if (first.isRational() && second.isDouble()) {
                if (Value.fractionable(second.getDoubleValue())) {
                    Rational rational = Value.fromDouble(second.getDoubleValue());
                    return new Value(rationalService.divide(first.getRationalValue(), rational));
                } else {
                    return new Value(Value.fromRational(first.getRationalValue()) / second.getDoubleValue());
                }
            } else {
                // Manejar el caso en el que ambos valores son nulos o inválidos
                return new Value(0.0);
            }
        } catch (Exception e) {
            // Manejar la excepción
            System.out.println("El valor está vacío");
        }
        return new Value(0.0);
    }
}
