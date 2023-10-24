class OperationRequest {
    constructor(opProtocol) {
        this.BASE_PATH = `/api`;
        this.OPERATION_PROTOCOL = opProtocol;
        this.CONST_FIRST_VALUE
        this.CONST_SECOND_VALUE
        this.PATH
    }

    execOperation() {
        this.CONST_FIRST_VALUE = document.getElementById('data-01').value;
        this.CONST_SECOND_VALUE = document.getElementById('data-02').value;

        this.PATH = `${this.BASE_PATH}${this.OPERATION_PROTOCOL}?num1=${this.CONST_FIRST_VALUE}&num2=${this.CONST_SECOND_VALUE}`;
        let operation_dom_result = document.getElementById('result')

        return fetch(this.PATH)
            .then(resp => {
                if (typeof this.CONST_FIRST_VALUE === null) {
                    document.getElementById('data-01').textContent = '¡Error! Debe ingresar un número válido.'
                    return document.getElementById('data-01').style.background = '#8d2828'
                }
                
                if (typeof this.CONST_SECOND_VALUE === null) {
                    document.getElementById('data-02').textContent = '¡Error! Debe ingresar un número válido.'
                    return document.getElementById('data-02').style.background = '#8d2828'
                }

                resp.text()
            })
            .then(finalVal => {
                operation_dom_result.style.display = "block"
                operation_dom_result.textContent = `Resultado Operacional: ${finalVal}`;
            })
            .catch(error => {
                if (error.response.status >= 400) {
                    operation_dom_result.style.display = "block"
                    operation_dom_result.style.background = "#8d2828"
                    operation_dom_result.textContent = `Error de servidor: Error ${error.response.status}.`
                }
            });
    }
}


const addProtocol = new OperationRequest('/sum')
const substractProtocol = new OperationRequest('/substract')
const divideProtocol = new OperationRequest('/divide')
const multiplyProtocol = new OperationRequest('/multiply')

document.addEventListener("submit", e => {
    if(e.target.matches('#calculator')) {
        e.preventDefault()
    }
})

document.addEventListener("click", e => {
    if(e.target.matches('#calc-add')) {
        addProtocol.execOperation()
    }

    if(e.target.matches('#calc-substract')) {
        substractProtocol.execOperation()
    }

    if(e.target.matches('#calc-divide')) {
        divideProtocol.execOperation()
    }

    if(e.target.matches('#calc-multiply')) {
        multiplyProtocol.execOperation()
    }
})
