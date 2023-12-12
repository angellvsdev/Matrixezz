
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
        this.output_A
        this.output_B
    }

    setMatrixes(matrix, rows, cols) {
        for(i = 0; i < rows; i++) {
            matrix.push(new Array(cols))
        }

        console.log(matrix)
    }

    sendMatrix() {


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

class Matrix {
    constructor(rows, cols, data) {
        this.rows = rows;
        this.cols = cols;
        this.data = data;
    }

    // Getter para el número de filas
    getRows() {
        return this.rows;
    }

    // Getter para el número de columnas
    getCols() {
        return this.cols;
    }

    // Getter para los datos de la matriz
    getData() {
        return this.data;
    }

    // Método para obtener el valor en una posición específica
    getCellValue(row, col) {
        return this.data[row][col];
    }
    static fromJSON(json) {
        const { rows, cols, data } = json;

        // Asegurarse de que se proporcionen las propiedades necesarias
        if (!rows || !cols || !data) {
            throw new Error('El objeto JSON debe contener propiedades "rows", "cols" y "data".');
        }

        // Validar que data es una matriz de las dimensiones correctas
        if (!Array.isArray(data) || data.length !== rows || !data.every(row => Array.isArray(row) && row.length === cols)) {
            throw new Error('La propiedad "data" debe ser una matriz de las dimensiones correctas.');
        }

        return new Matrix(rows, cols, data);
    }
    // Método para convertir la instancia de Matrix a un objeto JSON
    toJSON() {
        const json = {
            rows: this.rows,
            cols: this.cols,
            data: this.data.map(row =>
                row.map(cell => {
                    if (typeof cell === 'object' && cell !== null && 'value' in cell) {
                      // Si ya es un objeto con una propiedad "value"
                      return cell;
                    } else {
                      // Si es un valor simple, envuélvelo en un objeto
                      return {
                        "value": cell
                      };
                    }
                  })
                )
              };
        return json;
    }
}


const matrixInstance = new Matrix(3, 4, [
    ["1/2", "3/4", "5/6", "1"],
    ["2/3", "4/5", "6/7", "2"],
    ["3/4", "5/6", "7/8", "3"]
  ]);
  
const jsonOutput = matrixInstance.toJSON();
  
console.log(jsonOutput)
console.log(JSON.stringify(jsonOutput, null, 0));

function setMatrixes(entranceID, matrix, rows, cols) {
    let entranceContent = document.getElementById(`${entranceID}`)
    let entranceValues = entranceContent.children

    for(i = 0; i < rows; i++) {
        matrix.push(new Array(cols))
    }

    for (let childNode of entranceValues) {
        matrix[childNode.dataset.row - 1][childNode.dataset.column -1 ] = childNode.value
    }

    return matrix
}

console.log(setMatrixes("A", [], 3, 4));
