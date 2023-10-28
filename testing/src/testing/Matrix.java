package testing;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * La clase Matrix representa una matriz de valores numéricos. Puede realizar operaciones
 * matriciales básicas, como suma, resta, multiplicación y cálculo del determinante.
 */
public class Matrix {
    private int rows; // Número de filas de la matriz.
    private int cols; // Número de columnas de la matriz.
    private Value[][] data; // Los datos de la matriz representados como objetos de tipo Value.

    /**
     * Constructor de la clase Matrix que crea una matriz con el número especificado de filas y columnas.
     *
     * @param rows El número de filas de la matriz.
     * @param cols El número de columnas de la matriz.
     */
    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones de la matriz deben ser positivas.");
        }
        this.setRows(rows);
        this.setCols(cols);
        this.data = new Value[rows][cols];
    }

    /**
     * Obtiene el número de filas de la matriz.
     *
     * @return El número de filas.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Establece el número de filas de la matriz.
     *
     * @param rows El número de filas a establecer.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Obtiene el número de columnas de la matriz.
     *
     * @return El número de columnas.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Establece el número de columnas de la matriz.
     *
     * @param cols El número de columnas a establecer.
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Obtiene el valor en una ubicación específica de la matriz.
     *
     * @param row La fila de la ubicación.
     * @param col La columna de la ubicación.
     * @return El valor en la ubicación especificada.
     * @throws IllegalArgumentException Si los índices de fila o columna están fuera de rango.
     */
    public Value getData(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Índices fuera de rango");
        }
        return data[row][col];
    }

    /**
     * Obtiene una copia de los datos de la matriz como un arreglo bidimensional de objetos Value.
     *
     * @return Una copia de los datos de la matriz.
     */
    public Value[][] getArrayData() {
        Value[][] matrixData = new Value[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixData[i][j] = getData(i, j);
            }
        }
        return matrixData;
    }

    /**
     * Llena la matriz con nuevos datos representados como un arreglo bidimensional de objetos Value.
     *
     * @param newData Los nuevos datos para llenar la matriz.
     * @throws IllegalArgumentException Si el arreglo de datos de entrada es nulo o no tiene las mismas dimensiones que la matriz actual.
     */
    public void fillMatrix(Value[][] newData) {
        if (newData == null) {
            throw new IllegalArgumentException("La matriz de entrada no puede ser nula");
        }

        if (newData.length != rows || newData[0].length != cols) {
            throw new IllegalArgumentException("Las dimensiones de la matriz de entrada no coinciden con la matriz actual");
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.setData(row, col, newData[row][col]);
            }
        }
    }

    /**
     * Establece el valor en una ubicación específica de la matriz.
     *
     * @param row   La fila de la ubicación.
     * @param col   La columna de la ubicación.
     * @param value El valor a establecer en la ubicación especificada.
     * @throws IllegalArgumentException Si los índices de fila o columna están fuera de rango.
     */
    public void setData(int row, int col, Value value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Índices fuera de rango");
        }
        data[row][col] = value;
    }

    /**
     * Imprime la matriz en la consola, mostrando sus valores en filas y columnas con redondeo a tres decimales.
     */
    /**
     * Imprime la matriz en la consola, mostrando sus valores en filas y columnas con redondeo a tres decimales.
     */
    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Obtiene el valor de la matriz en la posición actual.
                Value value = data[i][j];
                System.out.print(value + "\t");
            }
            // Agregamos un salto de línea al final de cada fila.
            System.out.println();
        }
    }



    /**
     * Copia la matriz actual a una nueva instancia.
     *
     * @return Una nueva instancia de Matrix que es una copia de la matriz actual.
     */
    public Matrix copyMatrix() {
        Value[][] originalData = this.getArrayData();
        Matrix copiedMatrix = new Matrix(this.getRows(), this.getCols());
        copiedMatrix.fillMatrix(originalData);
        return copiedMatrix;
    }
    /**
     * Calcula la matriz transpuesta de la matriz actual.
     *
     * @return Una nueva instancia de Matrix que representa la matriz transpuesta.
     */
    public Matrix transpose() {
        int transposedRows = this.getCols();
        int transposedCols = this.getRows();
        Value[][] transposedData = new Value[transposedRows][transposedCols];

        for (int row = 0; row < transposedRows; row++) {
            for (int col = 0; col < transposedCols; col++) {
                transposedData[row][col] = this.getData(col, row);
            }
        }

        Matrix transposedMatrix = new Matrix(transposedRows, transposedCols);
        transposedMatrix.fillMatrix(transposedData);
        
        return transposedMatrix;
    }
    public Matrix add(Matrix otherMatrix) {
        if (this.rows != otherMatrix.rows || this.cols != otherMatrix.cols) {
            throw new IllegalArgumentException("Las matrices no tienen las mismas dimensiones");
        }

        Matrix result = this.copyMatrix();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Value add = result.getData(i, j).add(otherMatrix.getData(i, j));
                result.setData(i, j, add);
            }
        }

        return result;
    }
    public Matrix subtract(Matrix otherMatrix) {
        if (this.rows!= otherMatrix.rows || this.cols!= otherMatrix.cols) {
            throw new IllegalArgumentException("Las matrices no tienen las mismas dimensiones");
        }
        Matrix result = this.copyMatrix();
        for (int i = 0; i < this.rows; i++) {
        	for (int j = 0; j < this.cols; j++) {
                Value subtract = result.getData(i, j).subtract(otherMatrix.getData(i, j));
                result.setData(i, j, subtract);
            }
        }
        return result;
        }
    public Matrix multiply(Matrix otherMatrix) {
    	if (this.cols!= otherMatrix.rows) {
            throw new IllegalArgumentException("Las matrices no tienen las mismas dimensiones");
        }
        Matrix result = new Matrix(this.rows, otherMatrix.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < otherMatrix.cols; j++) {
                Value multiply = new Value(0);
                for (int k = 0; k < this.cols; k++) {
                    multiply = multiply.add(this.getData(i, k).multiply(otherMatrix.getData(k, j)));
                }
                result.setData(i, j, multiply);
            }
        }
        return result;
    }

    /**
     * Genera una matriz con dimensiones especificadas y la llena con números enteros aleatorios.
     *
     * @param rows El número de filas de la matriz.
     * @param cols El número de columnas de la matriz.
     * @return Una nueva instancia de Matrix con números enteros aleatorios.
     */
    public static Matrix generateRandomMatrix(int rows, int cols) {
        return generateRandomMatrix(rows, cols, -100, 100); // Valores mínimos y máximos predefinidos (-100 a 100)
    }

    public static Matrix generateRandomMatrix(int rows, int cols, int minValue, int maxValue) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones de la matriz deben ser positivas.");
        }

        Matrix randomMatrix = new Matrix(rows, cols);

        Random random = new Random();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int randomValue = random.nextInt(maxValue - minValue + 1) + minValue;
                randomMatrix.setData(row, col, new Value(randomValue));
            }
        }

        return randomMatrix;
    }

    // Otras operaciones con matrices (suma, resta, multiplicación, determinante) se pueden encontrar a continuación.

    /**
     * Calcula el determinante de la matriz.
     *
     * @return El determinante de la matriz.
     * @throws IllegalArgumentException Si la matriz no es cuadrada.
     */
    public Value calculateDeterminant() {
        if (this.getRows() != this.getCols()) {
            throw new IllegalArgumentException("La matriz no es cuadrada. Debe tener el mismo número de filas y columnas.");
        }

        int n = getRows();

        if (n == 1) {
            return getData(0, 0); // Caso base para matriz de 1x1
        } else if (n == 2) {
            // Caso base para matriz de 2x2
            Value a = getData(0, 0);
            Value b = getData(0, 1);
            Value c = getData(1, 0);
            Value d = getData(1, 1);
            return a.multiply(d).subtract(b.multiply(c));
        } else {
            Value determinant = new Value(0.0);

            for (int col = 0; col < n; col++) {
                Value term = getData(0, col).multiply(cofactor(0, col));
                if (col % 2 == 1) {
                    term = term.negate(); // Cambia el signo de términos en columnas impares
                }
                Matrix subMatrix = createSubMatrix(0, col);
                Value subMatrixDeterminant = term.multiply(subMatrix.calculateDeterminant());
                determinant = determinant.add(subMatrixDeterminant);
            }

            return determinant;
        }
    }

    // Otras operaciones matriciales (como cofactor, Gauss, etc.) pueden estar presentes a continuación.

    /**
     * Devuelve el cofactor de la matriz en la ubicación especificada.
     *
     * @param row La fila de la ubicación.
     * @param col La columna de la ubicación.
     * @return El cofactor de la matriz en la ubicación especificada.
     */
    public Value cofactor(int row, int col) {
        Matrix subMatrix = createSubMatrix(row, col);
        return subMatrix.calculateDeterminant();
    }

    /**
     * Crea y devuelve una submatriz excluyendo la fila y la columna especificadas.
     *
     * @param excludingRow Fila a excluir.
     * @param excludingCol Columna a excluir.
     * @return La submatriz creada.
     */
    public Matrix createSubMatrix(int excludingRow, int excludingCol) {
        int subMatrixSize = getRows() - 1;
        Matrix subMatrix = new Matrix(subMatrixSize, subMatrixSize);

        int newRow = 0;
        for (int row = 0; row < getRows(); row++) {
            if (row == excludingRow) {
                continue;
            }

            int newCol = 0;
            for (int col = 0; col < getCols(); col++) {
                if (col == excludingCol) {
                    continue;
                }
                subMatrix.setData(newRow, newCol, getData(row, col));
                newCol++;
            }

            newRow++;
        }
        return subMatrix;
    }


    /**
     * Obtiene una nueva matriz que contiene solo los coeficientes de la matriz original.
     *
     * @return Una nueva matriz que contiene los coeficientes.
     */
    public Matrix getCoefficients() {
        int coefCols = this.getCols() - 1;
        int coefRows = this.getRows();
        Matrix coefficientsMatrix = new Matrix(coefRows, coefCols);

        // Copia los valores de la matriz original a la matriz de coeficientes
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < coefCols; col++) {
                coefficientsMatrix.setData(row, col, this.getData(row, col));
            }
        }
        return coefficientsMatrix;
    }
    /**
     * Realiza la eliminación gaussiana en una copia de la matriz actual para resolver un sistema de ecuaciones lineales.
     *
     * @return Una nueva matriz que representa la matriz resultante después de aplicar la eliminación gaussiana.
     * @throws ArithmeticException Si se intenta calcular la inversa de un número que es igual a 0.
     */
    public Matrix gaussianEliminationWihSteps() {
        Matrix copyMatrix = this.copyMatrix(); // Crea una copia de la matriz original
        int n = copyMatrix.getRows();
        int steps = 0; // Contador de pasos

        for (int col = 0; col < n; col++) {
            System.out.println("Paso " + (steps + 1) + ":");
            copyMatrix.printMatrix();

            int pivotRow = findNonZeroPivot(copyMatrix, col);

            if (pivotRow == -1) {
                System.out.println("No se encontró un pivote válido en esta columna, se salta este paso.");
            } else {
                if (pivotRow != col) {
                    steps++;   
                    System.out.println("Paso " + (steps + 1) + ":");                    System.out.println("Operación realizada: F" + (col + 1) + " <-> F" + (pivotRow + 1));
                    copyMatrix.swapRows(col, pivotRow);
                    copyMatrix.printMatrix(); // Imprime la matriz después de intercambiar filas

                }

                Value pivotValue = copyMatrix.getData(col, col);

                if (pivotValue.isZero()) {
                    System.out.println("Se encontró un pivote igual a cero, intentando intercambiar filas para encontrar un pivote distinto de cero.");
                    boolean pivotFound = false;
                    for (int rowToCheck = col + 1; rowToCheck < n; rowToCheck++) {
                        if (!copyMatrix.getData(rowToCheck, col).isZero()) {
                            System.out.println("Se encontró un pivote no nulo en la fila " + (rowToCheck + 1) + ". Se intercambian las filas.");
                            copyMatrix.swapRows(col, rowToCheck);
                            copyMatrix.printMatrix(); // Imprime la matriz después de intercambiar filas
                            pivotFound = true;
                            break;
                        }
                    }
                    if (!pivotFound) {
                        System.out.println("No se encontró un pivote no nulo, se salta este paso.");
                    } else {
                        // Se vuelve a calcular el valor del pivote después de intercambiar filas
                        pivotValue = copyMatrix.getData(col, col);
                    }
                }

                // Transforma el pivote en 1 tomando su inversa
                Value pivotInverse = pivotValue.getInverse();
                for (int i = col; i < n + 1; i++) {
                    Value newValue = copyMatrix.getData(col, i).multiply(pivotInverse);
                    copyMatrix.setData(col, i, newValue);
                }

                for (int row = col + 1; row < n; row++) {
                    Value factor = copyMatrix.getData(row, col);
                    if (factor.isZero()||factor.isOne()) {
                        System.out.println("Operación realizada: F" + (row + 1) + " - F" + (col + 1));
                    } else {
                        System.out.println("Operación realizada: F" + (row + 1) + " - " + factor + " * F" + (col + 1));
                    }

                    for (int i = col; i < n + 1; i++) {
                        // Redondea los valores cercanos a cero a cero
                        if (factor.isZero()) {
                            factor = new Value(0.0);
                        }
                        Value newValue = copyMatrix.getData(row, i).subtract(factor.multiply(copyMatrix.getData(col, i)));
                        copyMatrix.setData(row, i, newValue);
                    }
                }
                steps++;
            }
        }

        System.out.println("Matriz final:");
        copyMatrix.printMatrix();

        System.out.println("Número total de pasos: " + steps);

        return copyMatrix;
    }

    /**
     * Realiza la eliminación gaussiana en una copia de la matriz actual para resolver un sistema de ecuaciones lineales.
     *
     * @return Una nueva matriz que representa la matriz resultante después de aplicar la eliminación gaussiana.
     * @throws ArithmeticException Si se intenta calcular la inversa de un número que es igual a 0.
     */
    public Matrix gaussianElimination() {
        Matrix copyMatrix = this.copyMatrix(); // Crea una copia de la matriz original
        int n = copyMatrix.getRows();

        for (int col = 0; col < n; col++) {
            int pivotRow = findNonZeroPivot(copyMatrix, col);

            if (pivotRow == -1) {
                continue; // No se encontró un pivote válido en esta columna, se salta este paso.
            } else {
                if (pivotRow != col) {
                    copyMatrix.swapRows(col, pivotRow);
                }

                Value pivotValue = copyMatrix.getData(col, col);

                if (pivotValue.isZero()) {
                    continue; // Se encontró un pivote igual a cero, se salta este paso.
                }

                Value pivotInverse = pivotValue.getInverse();

                for (int i = col; i < n + 1; i++) {
                    Value newValue = copyMatrix.getData(col, i).multiply(pivotInverse);
                    copyMatrix.setData(col, i, newValue);
                }

                for (int row = col + 1; row < n; row++) {
                    Value factor = copyMatrix.getData(row, col);

                    for (int i = col; i < n + 1; i++) {
                        Value newValue = copyMatrix.getData(row, i).subtract(factor.multiply(copyMatrix.getData(col, i)));
                        copyMatrix.setData(row, i, newValue);
                    }
                }
            }
        }

        return copyMatrix;
    }

    /**
     * Realiza la eliminación de Gauss-Jordan en una copia de la matriz actual para resolver un sistema de ecuaciones lineales
     * y obtener la matriz en su forma escalonada reducida por filas.
     *
     * @return Una nueva matriz que representa la matriz resultante en su forma escalonada reducida por filas.
     * @throws ArithmeticException Si se intenta calcular la inversa de un número que es igual a 0.
     */
    public Matrix gaussJordanElimination() {
        Matrix copyMatrix = this.copyMatrix(); // Crea una copia de la matriz original
        int n = copyMatrix.getRows();

        for (int col = 0; col < n; col++) {
            int pivotRow = findNonZeroPivot(copyMatrix, col);

            if (pivotRow == -1) {
                continue; // No se encontró un pivote válido en esta columna, se salta este paso.
            } else {
                if (pivotRow != col) {
                    copyMatrix.swapRows(col, pivotRow);
                }

                Value pivotValue = copyMatrix.getData(col, col);

                if (pivotValue.isZero()) {
                    continue; // Se encontró un pivote igual a cero, se salta este paso.
                }

                Value pivotInverse = pivotValue.getInverse();

                for (int i = col; i < n + 1; i++) {
                    Value newValue = copyMatrix.getData(col, i).multiply(pivotInverse);
                    copyMatrix.setData(col, i, newValue);
                }

                for (int row = 0; row < n; row++) {
                    if (row != col) {
                        Value factor = copyMatrix.getData(row, col);

                        for (int i = col; i < n + 1; i++) {
                            Value newValue = copyMatrix.getData(row, i).subtract(factor.multiply(copyMatrix.getData(col, i)));
                            copyMatrix.setData(row, i, newValue);
                        }
                    }
                }
            }
        }

        return copyMatrix;
    }      


    /**
     * Realiza la eliminación de Gauss-Jordan en una copia de la matriz actual para resolver un sistema de ecuaciones lineales
     * y obtener la matriz en su forma escalonada reducida por filas.
     *
     * @param matrix La matriz a la que se aplicará la eliminación de Gauss-Jordan.
     * @return Una nueva matriz que representa la matriz resultante en su forma escalonada reducida por filas.
     * @throws ArithmeticException Si se intenta calcular la inversa de un número que es igual a 0.
     */
    public Matrix gaussJordanEliminationWithSteps() {
        Matrix copyMatrix = this.copyMatrix(); // Crea una copia de la matriz original
        int n = copyMatrix.getRows();
        int steps = 0; // Contador de pasos

        for (int col = 0; col < n; col++) {
            int pivotRow = findNonZeroPivot(copyMatrix, col);

            if (pivotRow == -1) {
                System.out.println("Paso " + (steps + 1) + ":");
                copyMatrix.printMatrix();
                System.out.println("No se encontró un pivote válido en esta columna, se salta este paso.");
            } else {
                if (pivotRow != col) {
                    System.out.println("Paso " + (steps + 1) + ":");
                    copyMatrix.printMatrix();
                    System.out.println("Operación realizada: F" + (col + 1) + " <-> F" + (pivotRow + 1));
                    copyMatrix.swapRows(col, pivotRow);
                    copyMatrix.printMatrix(); // Imprime la matriz después de intercambiar filas
                    steps++;
                }

                Value pivotValue = copyMatrix.getData(col, col);

                if (pivotValue.isZero()) {
                    System.out.println("Paso " + (steps + 1) + ":");
                    copyMatrix.printMatrix();
                    System.out.println("Se encontró un pivote igual a cero, se salta este paso.");
                } else {
                    if (!pivotValue.isOne()) {
                        Value pivotInverse = pivotValue.getInverse();
                        System.out.println("Paso " + (steps + 1) + ":");
                        copyMatrix.printMatrix();
                        System.out.println("Operación realizada: F" + (col + 1) + " * " + pivotInverse);
                        for (int i = col; i < n + 1; i++) {
                            Value newValue = copyMatrix.getData(col, i).multiply(pivotInverse);
                            copyMatrix.setData(col, i, newValue);
                        }
                        copyMatrix.printMatrix(); // Imprime la matriz después de volver el pivote igual a 1
                        steps++;
                    }

                    for (int row = 0; row < n; row++) {
                        if (row != col) {
                            Value factor = copyMatrix.getData(row, col);
                            System.out.println("Paso " + (steps + 1) + ":");
                            copyMatrix.printMatrix();
                            System.out.println("Operación realizada: F" + (row + 1) + " - " + factor + "F" + (col + 1));
                            for (int i = col; i < n + 1; i++) {
                                Value newValue = copyMatrix.getData(row, i).subtract(factor.multiply(copyMatrix.getData(col, i)));
                                copyMatrix.setData(row, i, newValue);
                            }
                            steps++;
                        }
                    }
                }
            }
        }

        System.out.println("Matriz final:");
        copyMatrix.printMatrix();
        System.out.println("Número total de pasos: " + steps);

        return copyMatrix;
    }

    /**
     * Encuentra el índice de la primera fila con un pivote no nulo en una columna específica.
     *
     * @param matrix La matriz en la que se busca el pivote.
     * @param col    La columna en la que se busca el pivote.
     * @return El índice de la fila con un pivote no nulo, o -1 si no se encuentra.
     */
    private int findNonZeroPivot(Matrix matrix, int col) {
        int n = matrix.getRows();
        for (int row = col; row < n; row++) {
            if (!matrix.getData(row, col).isZero()) {
                return row; // Devuelve el índice de la primera fila con un pivote no nulo
            }
        }
        return -1; // Devuelve -1 si no se encuentra un pivote no nulo
    }

    /**
     * Intercambia dos columnas de la matriz.
     *
     * @param col1 El índice de la primera columna a intercambiar.
     * @param col2 El índice de la segunda columna a intercambiar.
     */
    @SuppressWarnings("unused")
	private void swapCols(int col1, int col2) {
        // Itera a través de todas las filas de la matriz
        for (int row = 0; row < this.getRows(); row++) {
            // Guarda temporalmente el valor en la columna 1 en una variable temporal
            Value temp = this.getData(row, col1);

            // Reemplaza el valor en la columna 1 con el valor en la columna 2
            this.setData(row, col1, this.getData(row, col2));

            // Reemplaza el valor en la columna 2 con el valor guardado en la variable temporal
            this.setData(row, col2, temp);
        }
    }

    /**
     * Intercambia dos filas en la matriz.
     *
     * @param row1 El índice de la primera fila a intercambiar.
     * @param row2 El índice de la segunda fila a intercambiar.
     */
    public void swapRows(int row1, int row2) {
        if (row1 < 0 || row1 >= rows || row2 < 0 || row2 >= rows) {
            throw new IllegalArgumentException("Índices de fila fuera de rango");
        }

        Value[] temp = data[row1];
        data[row1] = data[row2];
        data[row2] = temp;
    }

    /**
     * Devuelve una representación en forma de cadena de la matriz con los valores formateados y redondeados a tres decimales.
     *
     * @return Una representación en forma de cadena de la matriz.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.##"); // Utiliza un formato para redondear a 3 decimales

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Value value = data[i][j];
                double numericValue = (double) value.getValue(); // Usa el método doubleValue() para obtener el valor numérico

                // Si el valor es muy cercano a cero, considéralo como cero
                if (Math.abs(numericValue) < 1e-10) {
                    numericValue = 0.0;
                }

                String formattedValue = df.format(numericValue); // Aplica el formato
                stringBuilder.append(formattedValue);
                stringBuilder.append("\t"); // Agrega un tabulador para separar las columnas
            }
            stringBuilder.append("\n"); // Agrega un salto de línea al final de cada fila
        }
        return stringBuilder.toString();
    }
}
