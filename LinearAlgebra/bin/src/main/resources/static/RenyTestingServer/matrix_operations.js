// Función para generar campos de entrada de texto para una matriz 2x2
function generateMatrixFields(matrixId) {
    const matrix = document.getElementById(matrixId);
    for (let i = 0; i < 2; i++) {
        for (let j = 0; j < 2; j++) {
            const input = document.createElement("input");
            input.type = "text";
            input.placeholder = `Value ${i + 1},${j + 1}`;
            matrix.appendChild(input);
        }
    }
}

// Función para capturar los valores de una matriz
function getMatrixValues(matrixId) {
    const matrix = document.getElementById(matrixId);
    const inputs = matrix.querySelectorAll("input");
    return Array.from(inputs).map(input => parseFloat(input.value));
}

// Función para realizar la multiplicación de dos matrices 2x2
function multiplyMatrices(matrixA, matrixB) {
    const result = [
        matrixA[0] * matrixB[0] + matrixA[1] * matrixB[2],
        matrixA[0] * matrixB[1] + matrixA[1] * matrixB[3],
        matrixA[2] * matrixB[0] + matrixA[3] * matrixB[2],
        matrixA[2] * matrixB[1] + matrixA[3] * matrixB[3]
    ];
    return result;
}

// Función para formatear una matriz como texto
function formatMatrix(matrix) {
    return matrix.map(row => row.join("  ")).join("\n");
}

// Función para realizar la operación y mostrar el resultado como matriz
function performOperation(operation) {
    const matrixAValues = getMatrixValues("matrixA");
    const matrixBValues = getMatrixValues("matrixB");

    // Realiza la operación correspondiente
    let result;
    if (operation === "sum") {
        result = matrixAValues.map((val, index) => val + matrixBValues[index]);
    } else if (operation === "subtract") {
        result = matrixAValues.map((val, index) => val - matrixBValues[index]);
    } else if (operation === "multiply") {
        result = multiplyMatrices(matrixAValues, matrixBValues);
    }

    // Formatea el resultado como matriz
    const formattedResult = [
        [result[0], result[1]],
        [result[2], result[3]]
    ];

    // Muestra el resultado en el elemento "result"
    document.getElementById("result").textContent = `Result:\n${formatMatrix(formattedResult)}`;
}

// Genera campos de entrada para Matrix A y Matrix B
generateMatrixFields("matrixA");
generateMatrixFields("matrixB");

// Configura los eventos de los botones
document.getElementById("sumButton").addEventListener("click", () => performOperation("sum"));
document.getElementById("subtractButton").addEventListener("click", () => performOperation("subtract"));
document.getElementById("multiplyButton").addEventListener("click", () => performOperation("multiply"));
