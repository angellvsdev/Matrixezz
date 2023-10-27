package testing;

import java.math.BigDecimal;

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
    public Value(Rational rationalValue) {
        this.rationalValue = rationalValue;
    }
    public Value(String fractionString) {
        this(new Rational(fractionString));
    }
    public Value(int numerator,int denominator) {
        this(new Rational(numerator, denominator));
    }

    /**
     * Obtiene el valor Double almacenado en esta instancia.
     *
     * @return El valor Double.
     */
    public Double getDoubleValue() {
        return doubleValue;
    }

    /**
     * Obtiene el valor Rational almacenado en esta instancia.
     *
     * @return El valor Rational.
     */
    public Rational getRationalValue() {
        return rationalValue;
    }

    /**
     * Imprime el valor como fracción si es un valor Rational, o como Double si es un valor Double.
     */
    public void print() {
        if (this.rationalValue != null) {
            System.out.println(this.rationalValue.getNumerator() + "/" + this.rationalValue.getDenominator());
        } else {
            System.out.println(this.doubleValue);
        }
    }

    /**
     * Realiza la suma de dos instancias de Value y devuelve una nueva instancia de Value con el resultado.
     *
     * @param other El otro valor a sumar.
     * @return Una nueva instancia de Value que representa la suma.
     */
    public Value add(Value other) {
        try {
            if (doubleValue != null && other.doubleValue != null) {
                // Suma de dos valores Double
                return new Value(doubleValue + other.doubleValue);
            } else if (rationalValue != null && other.rationalValue != null) {
                // Suma de dos valores Rational
                return new Value(rationalValue.add(other.rationalValue));
            } else if (doubleValue != null && other.rationalValue != null) {
                if (fractionable(this)) {
                    Rational rational = fromDouble(doubleValue);
                    return new Value(rational.add(other.rationalValue));
                } else {
                    return new Value(doubleValue + fromRational(other.rationalValue));
                }
            } else if (rationalValue != null && other.doubleValue != null) {
                if (fractionable(other)) {
                    Rational rational = fromDouble(other.doubleValue);
                    return new Value(rationalValue.add(rational));
                } else {
                    return new Value(fromRational(rationalValue) + other.doubleValue);
                }
            } else {
                // Manejar el caso en el que ambos valores son nulos o inválidos
                return new Value(0.0);
            }
        } catch (Exception e) {
            // Manejar la excepción
            System.out.println("El valor está vacío");
        }
		return other;
    }
    /**
     * Realiza la resta de dos instancias de Value y devuelve una nueva instancia de Value con el resultado.
     *
     * @param other El otro valor a restar.
     * @return Una nueva instancia de Value que representa la resta.
     */
    public Value subtract(Value other) {
        try {
            if (doubleValue != null && other.doubleValue != null) {
                // Resta de dos valores Double
                return new Value(doubleValue - other.doubleValue);
            } else if (rationalValue != null && other.rationalValue != null) {
                // Resta de dos valores Rational
                return new Value(rationalValue.subtract(other.rationalValue));
            } else if (doubleValue != null && other.rationalValue != null) {
                if (fractionable(this)) {
                    Rational rational = fromDouble(doubleValue);
                    return new Value(rational.subtract(other.rationalValue));
                } else {
                    return new Value(doubleValue - fromRational(other.rationalValue));
                }
            } else if (rationalValue != null && other.doubleValue != null) {
                if (fractionable(other)) {
                    Rational rational = fromDouble(other.doubleValue);
                    return new Value(rationalValue.subtract(rational));
                } else {
                    return new Value(fromRational(rationalValue) - other.doubleValue);
                }
            } else {
                // Manejar el caso en el que ambos valores son nulos o inválidos
                return new Value(0.0);
            }
        } catch (Exception e) {
            // Manejar la excepción
            System.out.println("El valor está vacío");
        }
        return other;
    }/**
     * Realiza la multiplicación de dos instancias de Value y devuelve una nueva instancia de Value con el resultado.
    *
    * @param other El otro valor a multiplicar.
    * @return Una nueva instancia de Value que representa la multiplicación.
    */
   public Value multiply(Value other) {
       try {
           if (doubleValue != null && other.doubleValue != null) {
               // Multiplicación de dos valores Double
               return new Value(doubleValue * other.doubleValue);
           } else if (rationalValue != null && other.rationalValue != null) {
               // Multiplicación de dos valores Rational
               return new Value(rationalValue.multiply(other.rationalValue));
           } else if (doubleValue != null && other.rationalValue != null) {
               if (fractionable(this)) {
                   Rational rational = fromDouble(doubleValue);
                   return new Value(rational.multiply(other.rationalValue));
               } else {
                   return new Value(doubleValue * fromRational(other.rationalValue));
               }
           } else if (rationalValue != null && other.doubleValue != null) {
               if (fractionable(other)) {
                   Rational rational = fromDouble(other.doubleValue);
                   return new Value(rationalValue.multiply(rational));
               } else {
                   return new Value(fromRational(rationalValue) * other.doubleValue);
               }
           } else {
               // Manejar el caso en el que ambos valores son nulos o inválidos
               return new Value(0.0);
           }
       } catch (Exception e) {
           // Manejar la excepción
           System.out.println("El valor está vacío");
       }
       return other;
   }
   /**
    * Realiza la división de dos instancias de Value y devuelve una nueva instancia de Value con el resultado.
    *
    * @param other El otro valor para la división.
    * @return Una nueva instancia de Value que representa la división.
    */
   public Value divide(Value other) {
       try {
           if (doubleValue != null && other.doubleValue != null) {
               if (other.doubleValue == 0.0) {
                   // Manejar división por cero
                   System.out.println("División por cero no está permitida");
                   return new Value(0.0);
               }
               // División de dos valores Double
               return new Value(doubleValue / other.doubleValue);
           } else if (rationalValue != null && other.rationalValue != null) {
               if (other.rationalValue.getNumerator() == 0) {
                   // Manejar división por cero
                   System.out.println("División por cero no está permitida");
                   return new Value(0.0);
               }
               // División de dos valores Rational
               return new Value(rationalValue.divide(other.rationalValue));
           } else if (doubleValue != null && other.rationalValue != null) {
               if (other.rationalValue.getNumerator() == 0) {
                   // Manejar división por cero
                   System.out.println("División por cero no está permitida");
                   return new Value(0.0);
               }
               if (fractionable(this)) {
                   Rational rational = fromDouble(doubleValue);
                   return new Value(rational.divide(other.rationalValue));
               } else {
                   return new Value(doubleValue / fromRational(other.rationalValue));
               }
           } else if (rationalValue != null && other.doubleValue != null) {
               if (other.doubleValue == 0.0) {
                   // Manejar división por cero
                   System.out.println("División por cero no está permitida");
                   return new Value(0.0);
               }
               if (fractionable(other)) {
                   Rational rational = fromDouble(other.doubleValue);
                   return new Value(rationalValue.divide(rational));
               } else {
                   return new Value(fromRational(rationalValue) / other.doubleValue);
               }
           } else {
               // Manejar el caso en el que ambos valores son nulos o inválidos
               System.out.println("División no válida");
               return new Value(0.0);
           }
       } catch (Exception e) {
           // Manejar la excepción
           System.out.println("El valor está vacío");
       }
       return other;
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
    private static boolean fractionable(Value value) {
        if (value.getDoubleValue() != null) {
            Double originalValue = value.getDoubleValue();
            String stringValue = Double.toString(originalValue);
            int decimalPointIndex = stringValue.indexOf('.');

            if (decimalPointIndex != -1) {
                int decimalPlaces = stringValue.length() - decimalPointIndex - 1;
                return decimalPlaces < 5;
            } else {
                return true; // No hay punto decimal, consideramos que tiene menos de 5 decimales.
            }
        }
        return false; // No es un valor Double, no se puede fraccionar
    }
    @Override
    public String toString() {
        if (rationalValue != null) {
            if (rationalValue.getNumerator() == rationalValue.getDenominator()) {
                return "1";
            } else if (rationalValue.getNumerator() == 0) {
                return "0";
            } else if (rationalValue.getDenominator() == 1) {
                return Integer.toString(rationalValue.getNumerator());
            } else {
                return rationalValue.getNumerator() + "/" + rationalValue.getDenominator();
            }
        } else if (doubleValue != null) {
            return doubleValue.toString();
        } else {
            return "Valor no válido"; // Puedes personalizar el mensaje para el caso en que ambos valores sean nulos o inválidos.
        }
    }

}

