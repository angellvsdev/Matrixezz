import { buildMatrixDataComponentWith, buildUIMatrixStepsFormulary } from "./components_build.js";
import { handleSizeRules, handleCellValueException } from "./exception_handling.js";

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
    constructor() {
        this.matrix_A = document.getElementById("A")
        this.matrix_B = document.getElementById("B")
        this.rows_A = this.matrix_A.dataset.total_rows
        this.cols_A = this.matrix_A.dataset.total_columns
        this.rows_B = this.matrix_B.dataset.total_rows
        this.cols_B = this.matrix_B.dataset.total_columns
        this.endpoint = document.getElementById("shot-operations").dataset.protocol
        this.protocolType = document.getElementById("shot-operations").dataset.protocol_type
    }
   
    solveMatrixesThrough() {
        const resolveTrigger = document.getElementById('shot-operations'),
              protocolType = resolveTrigger.dataset.protocol_type,
              protocolHasSteps = resolveTrigger.dataset.protocol_has_steps

        if (handleSizeRules(this.protocolType, this.endpoint)) {
            document.querySelector(".error_alert").style.top = "18vh"

            return setTimeout(() => {
                document.querySelector(".error_alert").style.top = "-18vh"
                document.querySelector(".error_alert").style.background = "#ec3737"
            }, 5000)
        }

        if (!resolveTrigger.dataset.protocol) {
            document.querySelector(".error_alert > .error_alert__message").innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Pista: Recuerda establecer los parametros para las operaciones antes. Prueba seleccionando tu método deseado y agregando tus matrices.`
            document.querySelector(".error_alert").style.top = "18vh"

            return setTimeout(() => {
                document.querySelector(".error_alert").style.top = "-18vh"
                document.querySelector(".error_alert").style.background = "#ec3737"
            }, 5000)
        }

        const UIMatrixInstanceA = new Matrix(parseInt(this.rows_A), parseInt(this.cols_A), setMatrixes("A")),
              UIMatrixInstanceB = new Matrix(parseInt(this.rows_B), parseInt(this.cols_B), setMatrixes("B"))

        if(this.endpoint === "add" || this.endpoint === "subtract" || this.endpoint === "multiply") {           
            const matrix1 = UIMatrixInstanceA.toJSON(),
                  matrix2 = UIMatrixInstanceB.toJSON()

            
            if (handleCellValueException(this.matrix_A)) {
                document.querySelector(".check_A").style.border = "1.5px dashed #e6b735"

                setTimeout(() => {
                    document.querySelector(".check_A").style.border = "initial"
                }, 3000)

                throw new Error("La matriz A presenta problemas.")
            }

            if (handleCellValueException(this.matrix_B)) {
                document.querySelector(".check_B").style.border = "1.5px dashed #e6b735"

                setTimeout(() => {
                    document.querySelector(".check_B").style.border = "initial"
                }, 3000)

                throw new Error("La matriz B presenta problemas.")
            }

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
            .then(response => {
                if (response.status >= 200 && response.status < 300) {
                    document.querySelector(".on_load_screen").style.transform = "translateY(0vh)"
                }
                
                return response.json()
            })
            .then(responseContent => {
                return buildMatrixDataComponentWith(responseContent)
            }).then(() => {
                document.querySelector(".on_load_screen").style.transform = "translateY(-100vh)"
            })
            .catch(error => {
                document.querySelector(".error_alert").style.top = "18vh"
                document.querySelector(".error_alert").style.background = "#701111"
                document.querySelector(".error_alert > .error_alert__message").innerHTML = `<i class="fa-solid fa-skull-crossbones"></i> Error de calculo. Se recomienda rectificar las reglas para este procedimiento.`

                return setTimeout(() => {
                    document.querySelector(".error_alert").style.top = "-18vh"
                    document.querySelector(".error_alert").style.background = "#ec3737"
                }, 5000)
            })
        }

        if (this.endpoint === "multiplyScalar") {
            const matrix = UIMatrixInstanceA.toJSON(),
                  scalarString = this.matrix_B.children[0].value,
                  scalarInteger = Number.parseFloat(scalarString)

            
            if (handleCellValueException(this.matrix_A)) {
                document.querySelector(".check_A").style.border = "1.5px dashed #e6b735"

                setTimeout(() => {
                    document.querySelector(".check_A").style.border = "initial"
                }, 3000)

                throw new Error("La matriz A presenta problemas.")
            }
    
            if (handleCellValueException(this.matrix_B)) {
                document.querySelector(".check_B").style.border = "1.5px dashed #e6b735"

                setTimeout(() => {
                    document.querySelector(".check_B").style.border = "initial"
                }, 3000)

                throw new Error("La matriz B presenta problemas.")
            }


            fetch(`http://localhost/matrix/${this.endpoint}?scalar=${scalarInteger.toFixed(2)}`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    ...matrix
                })
            })
            .then(response => {
                if (response.status >= 200 && response.status < 300) {
                    document.querySelector(".on_load_screen").style.transform = "translateY(0vh)"
                }
                
                return response.json()
            })
            .then(responseContent => {
                return buildMatrixDataComponentWith(responseContent)
            }).then(() => {
                document.querySelector(".on_load_screen").style.transform = "translateY(-100vh)"
            })
            .catch(error => {
                document.querySelector(".error_alert").style.top = "18vh"
                document.querySelector(".error_alert").style.background = "#701111"
                document.querySelector(".error_alert > .error_alert__message").innerHTML = `<i class="fa-solid fa-skull-crossbones"></i> Error de calculo. Se recomienda rectificar las reglas para este procedimiento.`

                return setTimeout(() => {
                    document.querySelector(".error_alert").style.top = "-18vh"
                    document.querySelector(".error_alert").style.background = "#ec3737"
                }, 5000)
            })
        }

        if (protocolType === "unary") {
            if (protocolHasSteps) {
                const inputMatrix = UIMatrixInstanceA.toJSON(),
                      DOMInstructionsTrigger = document.querySelector(".log")

                if (handleCellValueException(this.matrix_A)) {
                    document.querySelector(".check_A").style.border = "1.5px dashed #e6b735"

                    setTimeout(() => {
                        document.querySelector(".check_A").style.border = "initial"
                    }, 3000)

                    throw new Error("La matriz A presenta problemas.")
                }
            

                DOMInstructionsTrigger.classList.add("steps_on")
                DOMInstructionsTrigger.innerHTML = `<li class="steps_status"><p class="steps_status-text"><i class="fa-solid fa-circle-check"></i> Procedimiento</p></li>`

                fetch(`http://localhost/matrix/${this.endpoint}`, {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        ...inputMatrix
                    })
                })
                .then(response => {
                    if (response.status >= 200 && response.status < 300) {
                        document.querySelector(".on_load_screen").style.transform = "translateY(0vh)"
                    }
                    
                    return response.json()
                })
                .then(responseContent => {
                    buildUIMatrixStepsFormulary(responseContent)
                }).then(() => {
                    document.querySelector(".on_load_screen").style.transform = "translateY(-100vh)"
                })
                .catch(error => {
                    document.querySelector(".error_alert").style.top = "18vh"
                    document.querySelector(".error_alert").style.background = "#701111"
                    document.querySelector(".error_alert > .error_alert__message").innerHTML = `<i class="fa-solid fa-skull-crossbones"></i> Error de calculo. Se recomienda rectificar las reglas para este procedimiento.`

                    return setTimeout(() => {
                        document.querySelector(".error_alert").style.top = "-18vh"
                        document.querySelector(".error_alert").style.background = "#ec3737"
                    }, 5000)
                })
            } else {
                const inputMatrix = UIMatrixInstanceA.toJSON()
                
                if (handleCellValueException(this.matrix_A)) {
                    document.querySelector(".check_A").style.border = "1.5px dashed #e6b735"

                    setTimeout(() => {
                        document.querySelector(".check_A").style.border = "initial"
                    }, 3000)

                    throw new Error("La matriz A presenta problemas.")
                }

                fetch(`http://localhost/matrix/${this.endpoint}`, {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        ...inputMatrix
                    })
                })
                .then(response => {
                    if (response.status >= 200 && response.status < 300) {
                        document.querySelector(".on_load_screen").style.transform = "translateY(0vh)"
                    }
                    
                    return response.json()
                })
                .then(responseContent => {
                    if (this.endpoint === "calculateDeterminant") {
                        const determinantCellNode = document.querySelector('input[name="A11"').cloneNode()
                        determinantCellNode.value = `${responseContent}`
                        determinantCellNode.style.width = "6rem"
                        determinantCellNode.style.height = "6rem"
                        determinantCellNode.style.fontSize = "2rem"

                        document.getElementById("A").dataset.total_rows = 1
                        document.getElementById("A").dataset.total_columns = 1
                        document.getElementById("rA").value = 1
                        document.getElementById("cA").value = 1

                        this.matrix_A.innerHTML = ""

                        return this.matrix_A.appendChild(determinantCellNode)
                    } else {
                       return buildMatrixDataComponentWith(responseContent)
                    }
                }).then(() => {
                    document.querySelector(".on_load_screen").style.transform = "translateY(-100vh)"
                })
                .catch(error => {

                    document.querySelector(".error_alert").style.top = "18vh"
                    document.querySelector(".error_alert").style.background = "#701111"
                    document.querySelector(".error_alert > .error_alert__message").innerHTML = `<i class="fa-solid fa-skull-crossbones"></i> Error de calculo. Se recomienda rectificar las reglas para este procedimiento.`

                    return setTimeout(() => {
                        document.querySelector(".error_alert").style.top = "-18vh"
                        document.querySelector(".error_alert").style.background = "#ec3737"
                    }, 5000)
                })
            }
        }
    }
}
