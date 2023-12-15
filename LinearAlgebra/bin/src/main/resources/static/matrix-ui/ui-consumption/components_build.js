const buildMatrixTemplateComponent = (matrixSign) => {
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

    for(i = 0; i < totalCellsIndex; i++) {
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
        resolveTrigger.classList.add("on-selection")
    }
})