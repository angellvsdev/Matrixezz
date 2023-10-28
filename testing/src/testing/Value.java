package testing;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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
    		double Value = (double) testing.Value.fromRational(rationalValue);
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
            double Value = (double) testing.Value.fromRational(rationalValue);
            return Math.abs(Value - 1.0) < epsilon;
        }
		return false;
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
     * Devuelve el negativo de este valor. Si el valor es Double, se negará directamente.
     * Si el valor es Rational, se negará el numerador manteniendo el denominador.
     *
     * @return El negativo del valor.
     * @throws IllegalArgumentException Si el valor no es válido.
     */
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

    /**
     * Realiza la suma de dos instancias de Value y devuelve una nueva instancia de Value con el resultado.
     *
     * @param other El otro valor a sumar.
     * @return Una nueva instancia de Value que representa la suma.
     */
    public Value add(Value other) {
        try {
            if (this.isDouble() && other.isDouble()) {
                // Suma de dos valores Double
                return new Value(doubleValue + other.doubleValue);
            } else if (this.isRational() && other.isRational()) {
                // Suma de dos valores Rational
                return new Value(rationalValue.add(other.rationalValue));
            } else if (this.isDouble() && other.isRational()) {
                if (fractionable(this)) {
                    Rational rational = fromDouble(doubleValue);
                    return new Value(rational.add(other.rationalValue));
                } else {
                    return new Value(doubleValue + fromRational(other.rationalValue));
                }
            } else if (this.isRational() && other.isDouble()) {
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
		return new Value(0.0);
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

    @Override
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


