const buildMatrixTemplateComponent = (matrixSign) => {
    let insertionSection = document.getElementById(`${matrixSign}`),
        cellsInsertionTemplate = new DocumentFragment()        


    insertionSection.innerHTML = ""

    let rows = document.getElementById(`r${matrixSign}`).value,
        columns = document.getElementById(`c${matrixSign}`).value,
        totalCellsIndex = rows * columns

    
    insertionSection.style.gridTemplateRows = `repeat(${rows}, auto)`
    insertionSection.style.gridTemplateColumns = `repeat(${columns}, auto)`

    for(i = 0; i < totalCellsIndex; i++) {
        let insertionElement = document.createElement("input")

        insertionElement.setAttribute("class", "matrix_form__value")
        insertionElement.setAttribute("type", "number")
        insertionElement.setAttribute("placeholder", "N")

        insertionElement.style.width = `calc(12rem / ${columns})`
        insertionElement.style.height = `calc(12rem / ${rows})`
        insertionElement.style.fontSize = '1rem'

        cellsInsertionTemplate.appendChild(insertionElement)
    }

    let indexRow = 1, indexColumn = 1

    cellsInsertionTemplate.childNodes.forEach(node => {
        node.id = `${indexRow}${indexColumn}`
        node.setAttribute("name", `${matrixSign}${indexRow}${indexColumn}`)

        indexColumn++

        if(indexColumn > columns) {
            indexRow++
            indexColumn = 1
        }
    })

    insertionSection.appendChild(cellsInsertionTemplate)
}

document.addEventListener("click", e => {
    if (e.target.matches("#set_A, #set_A > i")) {
        buildMatrixTemplateComponent("A")
    }

    if (e.target.matches("#set_B, #set_B > i")) {
        buildMatrixTemplateComponent("B")
    }
})

// La clase SetOperationType establece el parametro necesario para guiar el método por el que se operan una o más matrices.