import { MatrixProcessBy } from "./consumption.js"
import { UnsetStepsList } from "./components_build.js"

document.addEventListener("click", e => {
    //e.preventDefault()
    if (e.target.matches("#shot-operations, #shot-operations *")) {        
        let operate = new MatrixProcessBy()

        UnsetStepsList()
        operate.solveMatrixesThrough()
    }

    if (e.target.matches(".steps_on, .steps_on *")) {
        document.querySelector(".steps_container").style.top = "0vh"
        document.querySelector(".option_list").style.bottom = "10vh"
    }

    if (e.target.matches(".protocol_step")) { 
        document.querySelector(".steps_container").style.left = `${e.target.dataset.uiCoordinates}`
    }
})