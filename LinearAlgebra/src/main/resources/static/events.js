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

        return fetch(this.PATH)
            .then(resp => resp.text())
            .then(finalVal => {
                console.log(finalVal);
            })
            .catch(error => {
                console.log(error);
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
