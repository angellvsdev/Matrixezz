let responseMatrix, responseMatrixWithInstructive

// La clase ProcessMatrixes se encarga de procesar operaciones simples que incluyan solo dos matrices.
class ProcessMatrixes {
    constructor(matrix_A, matrix_B, rows_A, cols_A, rows_B, cols_B, endpoint) {
        this.matrix_A = matrix_A
        this.matrix_B = matrix_B
        this.rows_A = rows_A
        this.cols_A = cols_A
        this.rows_B = rows_B
        this.cols_B = cols_B
        this.endpoint = endpoint
        this.output_A = []
        this.output_B = []
    }

    sendMatrix() {
        let template_matrix_vector = [] // Se almacenan los valores de la fila.

        for(i = 0; i < this.rows_A;i++) {
            for(j = 0; j < this.cols_A;j++) {
                template_matrix_vector.push(this.matrix_A[j]) // Por cada iteración, y mientras que se evalua la fila en concreto, se agregan valores del output this.matrix_A.
            }

            this.output_A.push(template_matrix_vector) // Al array del output, se le agrega un nuevo array que representa a la fila con esos valores.

            template_matrix_vector = [] // Se vacía el arreglo para repetir el proceso para la siguiente fila.
        }

        for(i = 0; i < this.rows_B;i++) {
            for(j = 0; j < this.cols_B;j++) {
                template_matrix_vector.push(this.matrix_B[j])
            }

            this.output_B.push(template_matrix_vector)

            template_matrix_vector = []
        }

        fetch(`http://localhost/matrix/${this.endpoint}`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                matrix: {
                    "rows": this.rows_A,
                    "cols": this.cols_A,
                    "data": this.output_A
                },
                matrix: {
                    "rows": this.rows_B,
                    "cols": this.cols_B,
                    "data": this.output_B
                }
            })
        }).then(response => response.json())
        .then(responseContent => {
            buildMatrixDataComponent(responseContent)
        })
    }
}

// La clase MatrixUIBuild se encarga de realizar operaciones que involucran a una sola matriz.
class MatrixUIBuild {
    constructor(matrix, rows, cols, endpoint) {
        this.matrix = matrix
        this.rows = rows
        this.cols = cols
        this.endpoint = endpoint
        this.output = []
    }

    sendMatrix() {
        let template_matrix_vector = []

        for(i = 0; i < this.rows;i++) {
            for(j = 0; j < this.cols;j++) {
                template_matrix_vector.push(this.matrix[j])
            }

            this.output.push(template_matrix_vector)

            template_matrix_vector = []
        }

        fetch(`http://localhost/matrix/${this.endpoint}`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                matrix: {
                    "rows": this.rows,
                    "cols": this.cols,
                    "data": this.output
                },
                matrix: {
                    "rows": this.rows,
                    "cols": this.cols,
                    "data": this.output
                }
            })
        }).then(response => response.json())
        .then(responseContent => {
            buildMatrixDataComponent(responseContent)
        })
    }
}