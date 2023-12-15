import { buildMatrixDataComponentWith } from "./components_build.js";



// La clase Matrix se encarga de generar una matriz bidimensional a partir de filas y columnas y una matriz de datos dada, la instancia de esta es lo que se envía para el API.
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



// La clase setMatrixes toma la ID de un formulario HTML con el fin de transformar sus valores a un arreglo entendible para el API.
function setMatrixes(entranceID) {
    const field = []

    let entranceContent = document.getElementById(`${entranceID}`),
        entranceValues = entranceContent.children,
        x_rows = entranceContent.dataset.total_rows,
        y_columns = entranceContent.dataset.total_columns

    for(let i = 0; i < x_rows; i++) {
        field.push(new Array(y_columns))
    }

    for (let childNode of entranceValues) {
        field[childNode.dataset.row - 1][childNode.dataset.column -1 ] = childNode.value
    }

    return field
}



// La clase ProcessMatrixes se encarga de procesar operaciones simples que incluyan solo dos matrices.
export class MatrixProcessBy {
    constructor(endpoint) {
        this.matrix_A = document.getElementById("A")
        this.matrix_B = document.getElementById("B")
        this.rows_A = this.matrix_A.dataset.total_rows
        this.cols_A = this.matrix_A.dataset.total_columns
        this.rows_B = this.matrix_B.dataset.total_rows
        this.cols_B = this.matrix_B.dataset.total_columns
        this.endpoint = endpoint
    }
   
    solveMatrixesThrough() {
        const resolveTrigger = document.getElementById('shot-operations'),
              protocolType = resolveTrigger.dataset.protocol_type,
              protocolHasSteps = resolveTrigger.dataset.protocol_has_steps

        const UIMatrixInstanceA = new Matrix(parseInt(this.rows_A), parseInt(this.cols_A), setMatrixes("A")),
              UIMatrixInstanceB = new Matrix(parseInt(this.rows_B), parseInt(this.cols_B), setMatrixes("B"))

        const matrix1 = UIMatrixInstanceA.toJSON(),
              matrix2 = UIMatrixInstanceB.toJSON()

            fetch(`http://localhost/matrix/${this.endpoint}`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    matrix1,
                    matrix2,
                })
            })
            .then(response => response.json())
            .then(responseContent => {
                return buildMatrixDataComponentWith(responseContent)
            })
            .catch(error => {})
        

        if (protocolType === "unary") {
            if (protocolHasSteps) {
                // Maqueta los pasos
            }

            fetch(`http://localhost/matrix/${this.endpoint}`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    matrix1
                })
            })
            .then(response => response.json())
            .then(responseContent => {
                console.log(responseContent)
            })
            .catch(error => console.log(error.status))
        }
    }
}

// const matrixInstanceA = new Matrix(2, 2, [["1", "4"],["9", "6"]]),
//       matrixInstanceB = new Matrix(2, 2, [["9", "19"],["1", "4"]])
                
// const matrix1 = matrixInstanceA.toJSON();
// const matrix2 = matrixInstanceB.toJSON();

// fetch("http://localhost/matrix/multiply", {
//         method: "POST",
//     	headers: {
//     		'Content-Type': 'application/json'
//     	},
//     	body: JSON.stringify({
//     		matrix1,
//     		matrix2
//     	})
// 	})
// 	.then(response => response.json())
//     .then(responseContent => {
//     	console.log(responseContent)
//     })
//     .catch(error => console.log(error.status))