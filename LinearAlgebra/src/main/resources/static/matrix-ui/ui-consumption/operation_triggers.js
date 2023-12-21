import { MatrixProcessBy } from "./consumption.js"

document.addEventListener("click", e => {
    //e.preventDefault()
    if (e.target.matches("#shot-operations")) {
        let operate = new MatrixProcessBy()

        operate.solveMatrixesThrough()
    }
})