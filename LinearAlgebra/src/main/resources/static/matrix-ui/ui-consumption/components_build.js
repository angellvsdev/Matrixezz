let DOMMatrixNodeCopy_A = document.getElementById("A").cloneNode(true),
    DOMMatrixNodeCopy_B = document.getElementById("B").cloneNode(true)

export function buildMatrixTemplateComponent(matrixSign) {
    let insertionSection = document.getElementById(`${matrixSign}`),
        cellsInsertionTemplate = new DocumentFragment()        

    insertionSection.innerHTML = ""

    let rows = document.getElementById(`r${matrixSign}`).value,
        columns = document.getElementById(`c${matrixSign}`).value,
        totalCellsIndex = rows * columns

    
    insertionSection.dataset.total_rows = `${rows}`
    insertionSection.dataset.total_columns = `${columns}`

    insertionSection.style.gridTemplateRows = `repeat(${rows}, auto)`
    insertionSection.style.gridTemplateColumns = `repeat(${columns}, auto)`


    for(let i = 0 ; i < totalCellsIndex; i++) {
        let insertionElement = document.createElement("input")

        insertionElement.setAttribute("class", "matrix_form__value")
        insertionElement.setAttribute("type", "text")

        insertionElement.style.width = `calc(12rem / ${columns})`
        insertionElement.style.height = `calc(12rem / ${rows})`
        insertionElement.style.fontSize = '1rem'

        cellsInsertionTemplate.appendChild(insertionElement)
    }

    let indexRow = 1, indexColumn = 1

    cellsInsertionTemplate.childNodes.forEach(node => {
        node.dataset.row = `${indexRow}`
        node.dataset.column = `${indexColumn}`
        node.setAttribute("placeholder", `${matrixSign}${indexRow}${indexColumn}`)
        node.setAttribute("name", `${matrixSign}${indexRow}${indexColumn}`)

        indexColumn++

        if(indexColumn > columns) {
            indexRow++
            indexColumn = 1
        }
    })

    insertionSection.appendChild(cellsInsertionTemplate)
}

export function cleanData(string) {
    let purgePatterns = [/\[+/ig, new RegExp("]", "ig"), new RegExp(" ", "ig")]

    purgePatterns.forEach(pattern => {
        string = string.replaceAll(pattern, "")
    })

    string = string.split(",")

    return Array.from(string)
}

export function buildMatrixDataComponentWith(resultMatrix) {
    const matrixResults = cleanData(resultMatrix.data)
    let matrixField = []

    const rows_A = document.getElementById("rA"), columns_A = document.getElementById("cA")

    const matrixModuleForm_A = document.getElementById("A"), cellsInsertionFragment = new DocumentFragment()

    const matrixContainerB = document.getElementById("matrix-02")

    for(let i = 0; i < resultMatrix.rows;i++) {
        matrixField.push([])
        for (let j = 0; j < resultMatrix.cols; j++) {
            matrixField[i][j] = matrixResults[j]
        }
    }

    matrixContainerB.remove()

    rows_A.value = `${resultMatrix.rows}`
    columns_A.value = `${resultMatrix.cols}`

    matrixModuleForm_A.innerHTML = ""
    matrixModuleForm_A.dataset.total_rows = `${resultMatrix.rows}`
    matrixModuleForm_A.dataset.total_columns = `${resultMatrix.cols}`
    matrixModuleForm_A.style.gridTemplateRows = `repeat(${resultMatrix.rows}, auto)`
    matrixModuleForm_A.style.gridTemplateColumns = `repeat(${resultMatrix.columns}, auto)`

    for(let i = 1; i <= resultMatrix.rows;i++) {
        for(let j = 1; j <= resultMatrix.cols;j++) {
            let matrixCell = document.createElement("input")
    
            matrixCell.setAttribute("type", "text")
            matrixCell.setAttribute("name", `A${i}${j}`)
            matrixCell.setAttribute("placeholder", `A${i}${j}`)
            matrixCell.classList.add("matrix_form__value")
            matrixCell.style.width = `calc(12rem / ${resultMatrix.cols})`
            matrixCell.style.height = `calc(12 / ${resultMatrix.rows})`
            matrixCell.style.fontSize = "1rem"
            matrixCell.dataset.row = `${i}`
            matrixCell.dataset.column = `${j}`
            console.log(matrixCell)

            cellsInsertionFragment.appendChild(matrixCell)
        }
    }

    console.log(resultMatrix.data)
    return matrixModuleForm_A.appendChild(cellsInsertionFragment)
    
}

document.addEventListener("click", e => {
    if (e.target.matches("#set_A, #set_A > i")) {
        buildMatrixTemplateComponent("A")
    }

    if (e.target.matches("#set_B, #set_B > i")) {
        buildMatrixTemplateComponent("B")
    }

    if (e.target.matches(".menu__opt-button, .compounds__op-button")) {
        let resolveTrigger = document.getElementById("shot-operations"),
            menuOptionNodes = document.querySelectorAll(".menu__opt-button, .compounds__op-button, .menu__opt_compounds_action")

        menuOptionNodes.forEach(node => node.classList.remove("on-selection"))
        e.target.classList.add("on-selection")
        
        resolveTrigger.dataset.protocol = `${e.target.dataset.operation}`
        resolveTrigger.dataset.protocol_type = `${e.target.dataset.operation_type}`

        if (e.target.dataset.operation_steps) {
            resolveTrigger.dataset.protocol_has_steps = `${e.target.dataset.operation_steps}`
        } else {
            resolveTrigger.removeAttribute("data-protocol_has_steps")
        }

        resolveTrigger.classList.add("on-selection")
    }
})