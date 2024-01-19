function isSizeSettersUnload(matrix, rowSetter, columnSetter, messageModule) {
    const rowsSizeData = matrix.dataset.total_rows,
          columnsSizeData = matrix.dataset.total_columns

    if (rowsSizeData !== rowSetter.value) {
        return messageModule.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Los valores cargados para el tamaño de la <b>matriz ${matrix.id}</b> no han sido cargados correctamente. Carga las dimensiones previamente antes de ejecutar su operacion.`
    }
    
    if (columnsSizeData !== columnSetter.value) {
        return messageModule.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Los valores cargados para el tamaño de la <b>matriz ${matrix.id}</b> no han sido cargados correctamente. Carga las dimensiones previamente antes de ejecutar su operacion.`
    }

}

export function handleSizeRules(protocolType, solutionProtocol) {
    const handleParameter_rowsA = document.getElementById("rA"),
          handleParameter_colsA = document.getElementById("cA"),
          handleParameter_rowsB = document.getElementById("rB"),
          handleParameter_colsB = document.getElementById("cB"),
          errorMessageDOMAlert = document.querySelector(".error_alert > .error_alert__message")

    switch (protocolType) {
        case "binary":
            if (isSizeSettersUnload(document.getElementById("A"), handleParameter_rowsA, handleParameter_colsA, errorMessageDOMAlert)) {
                return document.querySelector(".error_alert").style.top = "18vh"
            }

            if (isSizeSettersUnload(document.getElementById("B"), handleParameter_rowsB, handleParameter_colsB, errorMessageDOMAlert)) {
                return document.querySelector(".error_alert").style.top = "18vh"
            }

            switch (solutionProtocol) {
                case "add":
                    if (handleParameter_rowsA.value !== handleParameter_rowsB.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Recuerda, en casos de suma o resta de matrices, ambas matrices deben poseer el mismo número de filas.`
                    }

                    if (handleParameter_colsA.value !== handleParameter_colsB.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Recuerda, en el caso de suma o resta de matrices, ambas matrices deben poseer el mismo número de columnas.`
                    }

                    break;
                case "subtract":
                    if (handleParameter_rowsA.value !== handleParameter_rowsB.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Recuerda, en casos de suma o resta de matrices, ambas matrices deben poseer el mismo número de filas.`
                    }

                    if (handleParameter_colsA.value !== handleParameter_colsB.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Recuerda, en el caso de suma o resta de matrices, ambas matrices deben poseer el mismo número de columnas.`
                    }

                    break;
                case "multiply":
                    if (handleParameter_rowsA.value !== handleParameter_colsB.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Recuerda, en el caso de multiplicación de matrices, el número de filas de la matriz 'A' deben ser iguales al número de columnas de la matriz 'B'.`
                    }

                    break;
                case "multiplyScalar":
                    if ((handleParameter_rowsB.value && handleParameter_colsB.value) !== "1") {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Al multiplicar una matriz por su escalar, es obligatorio que el escalar sea una matriz de 1×1.`
                    }

                    break;
            }

        case "unary":
            if (isSizeSettersUnload(document.getElementById("A"), handleParameter_rowsA, handleParameter_colsA, errorMessageDOMAlert)) {
                return document.querySelector(".error_alert").style.top = "18vh"
            }

            switch (solutionProtocol) {
                case "gaussianElimination":
                    if (handleParameter_rowsA.value >= handleParameter_colsA.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: La matriz evaluada debe poseer una columna más que fila, esto debido a que la ultima columna corresponde al valor de las variables dadas (X, Y, Z, etc...).`
                    }

                    break;
                case "gaussianEliminationWithSteps":
                    if (handleParameter_rowsA.value >= handleParameter_colsA.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: La matriz evaluada debe poseer una columna más que fila, esto debido a que la ultima columna corresponde al valor de las variables dadas (X, Y, Z, etc...).`
                    }

                    break;
                case  "gaussJordanElimination":
                    if (handleParameter_rowsA.value >= handleParameter_colsA.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: La matriz evaluada debe poseer una columna más que fila, esto debido a que la ultima columna corresponde al valor de las variables dadas (X, Y, Z, etc...).`
                    }

                    break;
                case "gaussJordanEliminationWithSteps":
                    if (handleParameter_rowsA.value >= handleParameter_colsA.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: La matriz evaluada debe poseer una columna más que fila, esto debido a que la ultima columna corresponde al valor de las variables dadas (X, Y, Z, etc...).`
                    }

                    break;
                case "inverseMatrix":
                    if (handleParameter_rowsA.value !== handleParameter_colsA.value) {
                        return errorMessageDOMAlert.innerHTML  = `<i class="fa-solid fa-circle-exclamation"></i> Error: Para obtener la matriz inversa de una matriz, es necesario que se trate de una matriz cuadrada, a partir de matrices de 2×2.`
                    }

                    break;
                case "inverseMatrixWithSteps":
                    if (handleParameter_rowsA.value !== handleParameter_colsA.value) {
                        return errorMessageDOMAlert.innerHTML  = `<i class="fa-solid fa-circle-exclamation"></i> Error: Para obtener la matriz inversa de una matriz, es necesario que se trate de una matriz cuadrada, a partir de matrices de 2×2.`
                    }

                    break;
                case "calculateDeterminant":
                    if (handleParameter_rowsA.value !== handleParameter_colsA.value) {
                        return errorMessageDOMAlert.innerHTML = `<i class="fa-solid fa-circle-exclamation"></i> Error: Para obtener el determinante de una matriz, la matriz debe ser cuadrada, es decir, mismas filas que columnas.`
                    }

                    break;
            }
    }
}

export function handleCellValueException(matrix) {
    let wrongFormat = new RegExp("[^0-9/-]", "i"),
        divisionSigns = new RegExp("/", "g"),
        incorrectSignPut = new RegExp("[0-9]-", "gi"),
        isAWrongFormat = false,
        DOMAlertContainer = document.querySelector(".error_alert"),
        DOMAlertMessage = document.querySelector(".error_alert > .error_alert__message")

    for(let i = 0; i < matrix.children.length;i++) {
        let cellCharacters = matrix.children.item(i).value,
            allDivisionSigns = cellCharacters.match(divisionSigns)

        if (allDivisionSigns !== null) {
            if (allDivisionSigns.length > 1) {
                DOMAlertMessage.innerHTML = `<i class="fa-regular fa-square-full"></i> Error de formato: Recuerde que las matrices solo admiten como valores números reales y fracciones de números enteros.`
                DOMAlertContainer.style.top = "18vh"
                DOMAlertContainer.style.background = "#e6b735"
                //matrix.children.item(i).style.background = "#e6b735"
                //matrix.children.item(i).style.color = "#212529"
                matrix.children.item(i).style.border = "dashed 2px #e6b735"


                setTimeout(() => {
                    DOMAlertContainer.style.top = "-18vh"
                    //matrix.children.item(i).style.background = "#212529"
                    //matrix.children.item(i).style.color = "#f8f9fa"
                    matrix.children.item(i).style.border = "none"

                }, 3000);

                isAWrongFormat = true
            }
        }

        if (wrongFormat.test(cellCharacters) || cellCharacters.startsWith("/") || cellCharacters.endsWith("/") || cellCharacters.endsWith("-") || incorrectSignPut.test(cellCharacters) || !cellCharacters) {
            DOMAlertMessage.innerHTML = `<i class="fa-regular fa-square-full"></i> Error de formato: Recuerde que las matrices solo admiten como valores números reales y fracciones de números enteros.`
            DOMAlertContainer.style.top = "18vh"
            DOMAlertContainer.style.background = "#e6b735"
            //matrix.children.item(i).style.background = "#e6b735"
            //matrix.children.item(i).style.color = "#212529"
            matrix.children.item(i).style.border = "dashed 2px #e6b735"
            
            setTimeout(() => {
                DOMAlertContainer.style.top = "-18vh"
                //matrix.children.item(i).style.background = "#212529"
                //matrix.children.item(i).style.color = "#f8f9fa"
                matrix.children.item(i).style.border = "none"

            }, 3000);

            isAWrongFormat = true
        }

    }

    return isAWrongFormat
}
