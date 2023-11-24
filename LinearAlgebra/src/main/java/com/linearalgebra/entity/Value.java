package com.linearalgebra.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Value {
    private Double doubleValue;
    private Rational rationalValue;
    /**
     * Constructor que acepta un valor Double.
     *
     * @param doubleValue El valor Double a almacenar.
     */
    public Value(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    /**
     * Constructor que acepta un valor Rational.
     *
     * @param rationalValue El valor Rational a almacenar.
     */
    public Value(Value otherValue) {
        if (otherValue != null) {
            if (otherValue.rationalValue != null) {
                this.rationalValue = new Rational(otherValue.rationalValue.getNumerator(), otherValue.rationalValue.getDenominator());
            } else if (otherValue.doubleValue != null) {
                this.doubleValue = otherValue.doubleValue;
            }
        } else {
            // Aquí puedes manejar el caso en el que otherValue sea nulo, si es necesario.
            // Por ejemplo, puedes asignar un valor predeterminado o lanzar una excepción.
        }
    }
    public Value(int intValue) {
        this.doubleValue = (double) intValue; // Convierte el entero a un valor Double
    }
    public Value(Rational rationalValue) {
        this.rationalValue = rationalValue;
    }
    @JsonCreator
    public Value(@JsonProperty("value")String fractionString) {
        if (fractionString.contains("/")) {
            // Si la cadena contiene un '/', verificamos que solo haya uno
            String[] parts = fractionString.split("/");
            if (parts.length == 2) {
                this.rationalValue = new Rational(fractionString);
            } else {
            	throw new IllegalArgumentException("La cadena debe contener un '/' separando los numeros ");
            }
        } else {
            // Si no contiene '/', asumimos que es un número decimal
            this.doubleValue = Double.parseDouble(fractionString);
        }
    }
    public Value(int numerator,int denominator) {
        this(new Rational(numerator, denominator));
    }
    /**
     * Obtiene el valor Double almacenado en esta instancia.
     *
     * @return El valor Double.
     */
    @JsonIgnore
    public Double getDoubleValue() {
        return doubleValue;
    }

    /**
     * Obtiene el valor Rational almacenado en esta instancia.
     *
     * @return El valor Rational.
     */
    @JsonIgnore
    public Rational getRationalValue() {
        return rationalValue;
    }
    /**
     * Obtiene el valor almacenado en esta instancia. El valor puede ser de tipo Double o Rational.
     *
     * @return El valor almacenado como un objeto de tipo Double o Rational, o null si el valor es inválido.
     */
    public Object getValue() {
        if (rationalValue != null) {
            return rationalValue; // Si el valor es de tipo Rational, lo devuelve.
        } else if (doubleValue != null) {
            return doubleValue; // Si el valor es de tipo Double, lo devuelve.
        } else {
            return null; // En caso de que ambos valores sean nulos o inválidos, devuelve null.
        }
    }
    /**
     * Devuelve una representación formateada del valor con redondeo si tiene más de 3 decimales.
     *
     * @return Una cadena que representa el valor formateado.
     */
    public String getFormattedValue() {
        if (doubleValue != null) {
            // Formatea el valor Double si tiene más de 3 decimales.
            if (Math.abs(doubleValue - Math.floor(doubleValue)) >= 0.001) {
                return String.format("%.3f", doubleValue);
            } else {
                return String.valueOf(doubleValue);
            }
        } else if (rationalValue != null) {
            double rationalDouble = (double) rationalValue.getNumerator() / rationalValue.getDenominator();
            // Formatea el valor Rational si tiene más de 3 decimales.
            if (Math.abs(rationalDouble - Math.floor(rationalDouble)) >= 0.001) {
                return String.format("%.3f", rationalDouble);
            } else {
                return String.valueOf(rationalDouble);
            }
        } else {
            return "Valor no válido";
        }
    }
    /**
     * Genera la inversa de este valor y retorna el resultado como un objeto Value.
     *
     * @return El valor inverso como un objeto Value.
     * @throws ArithmeticException Si el valor es cero y no se puede calcular la inversa.
     */
    public Value getInverse() {
        if (this.rationalValue != null) {
            if (rationalValue.getNumerator() == 0) {
                throw new ArithmeticException("No se puede calcular la inversa si el numerador es 0");
            }
            // La inversa de una fracción a/b es b/a
            Rational inverseRational = this.rationalValue.getInverse();
            return new Value(inverseRational);
        } else if (this.doubleValue != null) {
            if (doubleValue == 0.0) {
                throw new ArithmeticException("No se puede calcular la inversa de 0.0.");
            }
            // La inversa de un valor Double es 1 / valor
            double doubleValue = (double) this.getDoubleValue();
            Rational rationalValue = Value.fromDouble(doubleValue);
            rationalValue = rationalValue.getInverse();
            return new Value(rationalValue);
        } else {
            throw new IllegalArgumentException("El valor no es válido");
        }
    }
    public boolean isZero() {
		double epsilon = 1e-10;
    	if (doubleValue!=null) {
            // Verifica si el valor es cercano a cero con una pequeña tolerancia
            return Math.abs(this.getDoubleValue()) < epsilon;
    	} else if (rationalValue!= null) {
    		double Value = (double) com.linearalgebra.entity.Value.fromRational(rationalValue);
    		return Math.abs(Value) < epsilon;
    	}
		return false;
    }
    public boolean isOne(){
    	double epsilon = 1e-10;
        if (doubleValue!=null) {
            // Verifica si el valor es cercano a uno con una pequeña tolerancia
            return Math.abs(this.getDoubleValue() - 1.0) < epsilon;
        } else if (rationalValue!= null) {
            double Value = (double) com.linearalgebra.entity.Value.fromRational(rationalValue);
            return Math.abs(Value - 1.0) < epsilon;
        }
		return false;
    }
    public Value negate() {
        if (this.rationalValue != null) {
            // Si es un valor Rational, negar el numerador y mantener el denominador.
            Rational negatedRational = new Rational(-this.rationalValue.getNumerator(), this.rationalValue.getDenominator());
            return new Value(negatedRational);
        } else if (this.doubleValue != null) {
            // Si es un valor Double, negar directamente.
            return new Value(-this.doubleValue);
        } else {
            throw new IllegalArgumentException("El valor no es válido");
        }
    }
    public double asDouble() {
    	if (this.doubleValue!= null) {
    		return doubleValue;
    	}
    	else if (this.rationalValue != null) {
    		return Value.fromRational(this.getRationalValue());
    	}
    	else {
            return 0.0;
        }
    }
    /**
     * Comprueba si el valor actual es de tipo double.
     *
     * @return Verdadero si el valor es un número de tipo double, falso en caso contrario.
     */
    public boolean isDouble() {
        // Verifica si la instancia actual contiene un valor Double no nulo.
        return (this.doubleValue != null);
    }

    /**
     * Comprueba si el valor actual es de tipo racional.
     *
     * @return Verdadero si el valor es un número de tipo racional, falso en caso contrario.
     */
    public boolean isRational() {
        // Verifica si la instancia actual contiene un valor Rational no nulo.
        return (this.rationalValue != null);
    }
	/**
	 * Convierte un valor Rational a Double.
	 *
	 * @param value El valor Rational a convertir.
	 * @return El valor como Double.
	 */
	public static Double fromRational(Rational value) {
	    Double doubleValue = (double) value.getNumerator() / value.getDenominator();
	    return doubleValue;
	}
	/**
	 * Convierte un valor Double a Rational.
	 *
	 * @param value El valor Double a convertir.
	 * @return El valor como Rational.
	 */
	public static Rational fromDouble(double value) {
	    BigDecimal decimalNumber = BigDecimal.valueOf(value);
	    int scale = decimalNumber.scale();
	
	    int numerator = decimalNumber.unscaledValue().intValue();
	    int denominator = (int) Math.pow(10, scale);
	
	    int gcd = Rational.gcd(numerator, denominator);
	    Rational value1 = new Rational(numerator / gcd, denominator / gcd);
	    return value1;
	}
	/**
	 * Verifica si un valor Double es "fraccionable" (puede convertirse a una fracción Rational) basado en la cantidad de decimales.
	 *
	 * @param value El valor Double a verificar.
	 * @return true si el valor se puede convertir a una fracción, de lo contrario false.
	 */
	public static boolean fractionable(double value) {
	        String stringValue = Double.toString(value);
	        int decimalPointIndex = stringValue.indexOf('.');
	
	        if (decimalPointIndex != -1) {
	            int decimalPlaces = stringValue.length() - decimalPointIndex - 1;
	            return decimalPlaces < 5;
	        } else {
	            return true; // No hay punto decimal, consideramos que tiene menos de 5 decimales.
	        }
	}
	@Override
    @JsonValue
    public String toString() {
        if (isRational()) {
            Rational rational = getRationalValue();
            return rational.toString();
        } else if (isDouble()) {
            DecimalFormat df = new DecimalFormat("0.###"); // Formato para redondear a 3 decimales
            double numericValue = doubleValue; // Obtiene el valor numérico

            // Si el valor es muy cercano a cero, considéralo como cero
            if (Math.abs(numericValue) < 1e-10) {
                numericValue = 0.0;
            }

            return df.format(numericValue); // Aplica el formato y devuelve como una cadena
        } else {
            return "Valor no válido";
        }
    }
}
