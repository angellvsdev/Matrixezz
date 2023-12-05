let resultMatrix, resultMatrixSteps

// Se crea una "clase maestra" para orquestar todos los tipos de operaciones con las matrices.
class ProcessMatrix {
    constructor(matrix_A, matrix_B, rows_A, cols_A, rows_B, cols_B, queryRoute) {
        this.A = matrix_A
        this.B = matrix_B
        this.ROWS_A = rows_A
        this.COLS_A = cols_A
        this.ROWS_B = rows_B
        this.COLS_B = cols_B
        this.ENDPOINT = queryRoute
    }

    sendMatrix() {}
}

// La clase ProcessMatrixWithSteps se encarga de realizar la matriz paso por paso hasta la solución final.
class ProcessMatrixWithSteps extends ProcessMatrix {
    constructor(steps) {
        this.RESOLVE_PROCESS = steps
    }

    sendMatrix() {}
}