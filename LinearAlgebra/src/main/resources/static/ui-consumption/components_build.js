const DOMMatrixB = document.getElementById("B").cloneNode()
let DOMMatrixStepScheme = document.querySelector(".steps_list").cloneNode(true)

document.querySelector(".steps_list").remove()

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

        insertionElement.classList.add("matrix_form__value")
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

export function UnsetStepsList() {
    const stepsButtonContainer = document.querySelector(".log"), 
          stepsButtonContainerMessage = document.querySelector(".steps_status > .steps_status-text"),
          stepsStagesContainer = document.querySelector(".steps_container"),
          listSelection = document.querySelector(".option_list")

    stepsButtonContainer.classList.remove("steps_on")
    stepsButtonContainerMessage.innerHTML = `<i class="fa-solid fa-ban"></i> Procedimiento Inexistente`
    stepsStagesContainer.innerHTML = ""
    listSelection.innerHTML = `
    <div class="list__step close-btn">
        <button class="close_protocol_steps"><i class="fa-solid fa-circle-xmark"></i></button>
    </div>
    `
}

export function buildMatrixDataComponentWith(resultMatrix) {
    const matrixResults = cleanData(resultMatrix.data)
    let matrixField = []

    const rows_A = document.getElementById("rA"), columns_A = document.getElementById("cA")

    const matrixModuleForm_A = document.getElementById("A"), cellsInsertionFragment = new DocumentFragment()

    const matrixContainerB = document.getElementById("matrix-02")

    // if (matrixContainerB.style.display === "none" && document.getElementById("shot-operations").dataset.protocol_type === "binary") {
    //     return
    // }

    for(let i = 0; i < resultMatrix.rows;i++) {
        let valuesSlice = matrixResults.splice(0, resultMatrix.cols)

        matrixField.push(valuesSlice)
    }

    rows_A.value = `${resultMatrix.rows}`
    columns_A.value = `${resultMatrix.cols}`

    matrixModuleForm_A.innerHTML = ""
    matrixModuleForm_A.dataset.total_rows = `${resultMatrix.rows}`
    matrixModuleForm_A.dataset.total_columns = `${resultMatrix.cols}`
    matrixModuleForm_A.style.gridTemplateRows = `repeat(${resultMatrix.rows}, auto)`
    matrixModuleForm_A.style.gridTemplateColumns = `repeat(${resultMatrix.cols}, auto)`

    for(let i = 0; i < resultMatrix.rows;i++) {
        for(let j = 0; j < resultMatrix.cols;j++) {
            let matrixCell = document.createElement("input")
    
            matrixCell.setAttribute("type", "text")
            matrixCell.setAttribute("name", `A${i+1}${j+1}`)
            matrixCell.setAttribute("placeholder", `A${i+1}${j+1}`)
            matrixCell.classList.add("matrix_form__value")
            matrixCell.style.width = `calc(12rem / ${resultMatrix.cols})`
            matrixCell.style.height = `calc(12rem / ${resultMatrix.rows})`
            matrixCell.style.fontSize = "1rem"
            matrixCell.dataset.row = `${i+1}`
            matrixCell.dataset.column = `${j+1}`
            matrixCell.value = `${matrixField[i][j]}`

            cellsInsertionFragment.appendChild(matrixCell)
        }
    }
    
    matrixContainerB.style.display = "none"
    document.getElementById("matrix-01").style.display = "flex"

    return matrixModuleForm_A.appendChild(cellsInsertionFragment)
    
}

export function buildUIMatrixStepsFormulary(resultMatrix) {
    let listIndex = 0,
    stepsListFragment = new DocumentFragment()

    resultMatrix.stepsWithMatrices.forEach(request_step => {
        let DOMStepAccessContainer = document.createElement("div"),
        DOMStepAccessButton = document.createElement("button")
        
        DOMStepAccessContainer.classList.add("list__step")
        DOMStepAccessContainer.classList.add("step-btn")
        DOMStepAccessButton.classList.add("protocol_step")
        DOMStepAccessButton.textContent = `${listIndex + 1}`
        DOMStepAccessButton.dataset.uiCoordinates = `-${listIndex}00vw`

        DOMStepAccessContainer.appendChild(DOMStepAccessButton)

        let copy = DOMMatrixStepScheme.cloneNode(true)

        copy.id = `step-tag-${listIndex}`
        copy.style.left  = `${listIndex}00vw`
        copy.querySelector(".step__tag").textContent = `${request_step.step}`

        const matrixResults = cleanData(request_step.matrix.data), cellsInsertionFragment = new DocumentFragment()
        let matrixField = []
    
        for(let i = 0; i < request_step.matrix.rows;i++) {
            let valuesSlice = matrixResults.splice(0, request_step.matrix.cols)
    
            matrixField.push(valuesSlice)
        }
    
        copy.querySelector(".step__scheme").style.gridTemplateRows = `repeat(${request_step.matrix.rows}, auto)`
        copy.querySelector(".step__scheme").style.gridTemplateColumns = `repeat(${request_step.matrix.cols}, auto)`
        copy.querySelector(".step__scheme").innerHTML = ""
    
    
        for(let i = 0; i < request_step.matrix.rows;i++) {
            for(let j = 0; j < request_step.matrix.cols;j++) {
                let schemeStepCell = document.createElement("div")
                schemeStepCell.classList.add("scheme_cell")
                schemeStepCell.textContent = `${matrixField[i][j]}`
                cellsInsertionFragment.appendChild(schemeStepCell)
            }
        }
    
        copy.querySelector(".step__scheme").appendChild(cellsInsertionFragment)
        copy.querySelector(".rows_tag").textContent = `Filas : ${request_step.matrix.rows}`
        copy.querySelector(".cols_tag").textContent = `Columnas: ${request_step.matrix.cols}`

        document.querySelector(".option_list").appendChild(DOMStepAccessContainer)
    
        listIndex++

        return stepsListFragment.appendChild(copy)
    })

    document.querySelector(".steps_container").appendChild(stepsListFragment)
}

document.addEventListener("click", e => {
    if (e.target.matches("#set_A, #set_A > i")) {
        buildMatrixTemplateComponent("A")
    }

    if (e.target.matches("#set_B, #set_B > i")) {
        buildMatrixTemplateComponent("B")
    }

    if (e.target.matches(".menu__opt-button, .compounds__op-button")) {
        if (e.target.dataset.operation_type === "unary") {
            document.querySelector(".check_B").style.display = "none"
            document.querySelector("#matrix-02").style.display = "none"
            document.querySelector("#matrix-01").style.display = "flex"

        } else {
            document.querySelector(".check_B").style.display = "flex"
        }

        UnsetStepsList()

        let resolveTrigger = document.getElementById("shot-operations"),
            menuOptionNodes = document.querySelectorAll(".menu__opt-button, .compounds__op-button, .menu__opt_compounds_action")

        menuOptionNodes.forEach(node => node.classList.remove("on-selection"))
        e.target.classList.add("on-selection")
        
        resolveTrigger.dataset.protocol = `${e.target.dataset.operation}`
        resolveTrigger.dataset.protocol_type = `${e.target.dataset.operation_type}`

        const DOMMatrixStepsFlag = document.querySelector(".show_steps")

        if (e.target.dataset.operation !== "gaussianEliminationWithSteps" && document.querySelector(".matrix_parameters").contains(DOMMatrixStepsFlag)) {
            document.querySelector(".matrix_parameters > .show_steps").remove()
        }

        
        if (e.target.dataset.operation !== "gaussJordanEliminationWithSteps" && document.querySelector(".matrix_parameters").contains(DOMMatrixStepsFlag)) {
            document.querySelector(".matrix_parameters > .show_steps").remove()
        }

        
        if (e.target.dataset.operation !== "inverseMatrixWithSteps" && document.querySelector(".matrix_parameters").contains(DOMMatrixStepsFlag)) {
            document.querySelector(".matrix_parameters > .show_steps").remove()
        }

        const DOMMatrixLockFlag = document.querySelector(".size_locker")

        if (e.target.dataset.operation !== "multiplyScalar" && document.querySelector(".matrix_parameters").contains(DOMMatrixLockFlag)) {
            document.querySelector(".matrix_parameters > .size_locker").remove()

            document.getElementById("rB").disabled = false
            document.getElementById("cB").disabled = false
            document.getElementById("set_B").disabled = false
        }

        if (e.target.dataset.operation_steps) {
            resolveTrigger.dataset.protocol_has_steps = `${e.target.dataset.operation_steps}`
        } else {
            resolveTrigger.removeAttribute("data-protocol_has_steps")
        }

        resolveTrigger.classList.add("on-selection")
    }

    if (e.target.matches('button[data-operation="multiplyScalar"')) {
        if (document.querySelector(".matrix_parameters > .size_locker")) {
            document.querySelector(".matrix_parameters > .size_locker").remove()
        }

        const DOMMatrixSizeLockerFlagContainer = document.createElement("div"),
              DOMMatrixSizeLockerFlagIndicator = document.createElement("div"),
              DOMMatrixSizeLockerFlagIcon = document.createElement("i")

        DOMMatrixSizeLockerFlagContainer.classList.add("matrix_parameters")
        DOMMatrixSizeLockerFlagIndicator.classList.add("matrix_parameters__modifier")
        DOMMatrixSizeLockerFlagIndicator.classList.add("size_locker")
        DOMMatrixSizeLockerFlagIcon.classList.add("fa-solid")
        DOMMatrixSizeLockerFlagIcon.classList.add("fa-lock")

        document.getElementById("B").remove()

        DOMMatrixB.innerHTML = '<input class="matrix_form__value type="text" name="B11" data-row="1" data-column="1" placeholder="B11">'

        document.getElementById("matrix-02").appendChild(DOMMatrixB)

        DOMMatrixSizeLockerFlagContainer.appendChild(DOMMatrixSizeLockerFlagIndicator)
        DOMMatrixSizeLockerFlagIndicator.appendChild(DOMMatrixSizeLockerFlagIcon)

        document.querySelector(".matrix_parameters").appendChild(DOMMatrixSizeLockerFlagIndicator)

        document.getElementById("rB").disabled = true
        document.getElementById("rB").value = 1
        document.getElementById("cB").disabled = true
        document.getElementById("cB").value = 1
        document.getElementById("set_B").disabled = true
    }

    if (e.target.matches(".add_matrix, .add_matrix > i")) {
        document.getElementById("matrix-02").style.display = "flex"
    }

    if (e.target.matches(".check_A, .check_A > p")) {
        document.getElementById("matrix-01").style.display = "flex"
        document.getElementById("matrix-02").style.display = "none"
    }

    if (e.target.matches(".check_B, .check_B > p")) {
        document.getElementById("matrix-02").style.display = "flex"
        document.getElementById("matrix-01").style.display = "none"
    }

    if (e.target.matches('button[data-operation="gaussianEliminationWithSteps"], button[data-operation="gaussJordanEliminationWithSteps"], button[data-operation="inverseMatrixWithSteps"]')) {
        if (document.querySelector(".matrix_parameters > .show_steps")) {
            document.querySelector(".matrix_parameters > .show_steps").remove()
        }

        const protocolHasStepsFlag = document.createElement("button")
        protocolHasStepsFlag.classList.add("matrix_parameters__modifier")
        protocolHasStepsFlag.classList.add("show_steps")
        protocolHasStepsFlag.setAttribute("type", "button")

        protocolHasStepsFlag.innerHTML = '<i class="fa-solid fa-circle-info"></i>'
        
        document.querySelector(".matrix_parameters").appendChild(protocolHasStepsFlag)
    }

    if (e.target.matches(".list__step .close_protocol_steps, .close_protocol_steps *")) {
        document.querySelector(".steps_container").style.top = "-100vh"
        document.querySelector(".option_list").style.bottom = "-10vh"
    }

    if (e.target.matches(".error_alert, .error_alert *")) {
        document.querySelector(".error_alert").style.top = "-18vh"
    }
})

window.addEventListener("load", e => {
    document.querySelector(".on_load_screen").style.transform = "translateY(-100vh)"
})