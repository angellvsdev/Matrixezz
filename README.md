# 🧮 Matrixezz | A totally complete matrices theorem calculator
Matrixezz is a project coming from the collaboration between two programmers; a specialized Frontend (myself), and a colleague experienced in Backend (Brizhel), emerged as a contribution idea for our linear algebra class, which we discussed and carried out, solving flawlessly the need to solve the university theorems concerning the module of matrices in linear algebra. This work was possible thanks to the flawless and well-founded combination of HTML, CSS + SCSS and Vanilla JavaScript technologies for the client side, and a well implemented and polished Backend developed by (Brizhel) using Java + Springboot and Maven.  This project took about 2 months to complete and deploy, which was done by packaging the application in a Java executable. We sincerely hope it will be of great use and pleasure for you.

# 🛠 Approach & system architecture
The system was realized with a Frontend to Backend classic architecture, using MVC model for the API's, where we decided to use the Backend server for all the calculus modules of matrices, where this modules are actioned by the user inputs from the client which sends a request to the server with the matrices involded in the operations, returning the result matrix and laying it on the user-interface. Of course, it has security protocols for all the exceptions, both in the Frontend and Backend as well, securing the quality and guarantee the most accurate results at the calculus using this theorem.


# 📖 Add and substract
To perform and add or a substract using the app, is very simple, is necessary to take in count the basic rules of the binary operation of the matrices; to perform this, take in mind that the size in rows and columns of both matrices should be equal, this app posses mainly two matrices, where **Matrix A** is the matrix who receives and render the results of an operation, and **Matrix B** is the auxiliar matrix that goes hand in hand with the **Matrix A** to perform all his operations (except for the cases where we want to make operations that only requires a single matrix), **note: both matrices requires to set its size first, of course**. Let's try out with the following example:
- **Matrix A: [[2, 4], [8, 12]] (2x2)**
<img src="https://imgur.com/U4TtUDQ.jpg" width="600px">

- **Matrix B: [[4, 2] [10, 22]] (2x2)**
<img src="https://imgur.com/iYicEL3.jpg" width="600px">

- **New Matrix A: [6, 6, 18, 32] ✔**
<img src="https://imgur.com/dsRZjql.jpg" width="900px">

---

And now we can try making the same operation but with a substract, and the expected output would be:
- **New Matrix A: [-2, 2, -2, -8] ✔**
<img src="https://imgur.com/DckW2l1.jpg" width="900px">


# 📖 Multiplication and scalar multiplications
To make an multiplication with matrices or multiply a single matrix by an scalar, the app is completely trustable. We just got to choose the option and adjust the parameters of each matrix, taking in count the rules of the matrices calculus in linear algebra, this means that in the case of multiplication, the rows size of **Matrix A** should be equal to the columns size in **Matrix B** to perform a correct operation. Let's try out an example of multiplication with the following matrices:
- **Matrix A: [[3, 6, 9], [2, 4, 8], [7, 14, 21]] (3x3)**
<img src="https://imgur.com/rGf3W1k.jpg" width="600px">

- **Matrix B: [[1/2, 4/2, 6], [7/2, 4, -8], [10, 2, 4]] (3x3)**
<img src="https://imgur.com/HFTI44f.jpg" width="600px">
 
- **New Matrix A: [[225/2, 48, 6], [95, 36, 12], [525/2, 112, 14]] ✔**
<img src="https://imgur.com/u8zM4m6.jpg" width="900px">

---

In the case of multiply a whole matrix by a single number or scalar, we just got to set the dimensions of our **Matrix A** and stablish the scalar that multiplies in **Matrix B** *(Matrix B is always locked in this case, because the scalar never should be more than a single number, and the app threats it in the UI as a 1x1 matrix). Let's try out and example with the following excercise:
- **Matrix A: [[5, 25, 4/5], [-10, 2/4, 15/3], [-2, -3, -7]] (3x3)**
<img src="https://imgur.com/jEV2w1K.jpg" width="600px">

- **Matrix B: [[2]] (1x1)**
<img src="https://imgur.com/hlynKEo.jpg" width="600px">

- **New Matrix A: [[10, 50, 8/5], [-20, 1, 10], [-4, -6, -14]] ✔**
<img src="https://imgur.com/1K8heKU.jpg" width="900px">

# ⚙ Compound methods
In the case you want to make more complex operation, the app got you perfectly covered! because we implemented a module to resolve inverse matrices through Gauss, Gauss Jordan and the classic inverse matrix resolution method, and that's not the only good stuff here, you can also get all the steps to solve a matrix through this methods choosing the "Add steps" option at a side of the required type of solution. Let's see some examples:
##  📖 Gauss + steps
For the case of the Gaussian method, we got to take in count that normally it is used to solve equations systems, so, what the app is expecting is to represent that system as a matrix, that's why is a mandatory rule that when you use Gauss (and also, Gauss Jordan) you should respect always that the columns size of the matrix is above the rows size by one. Let's see an example with the following excercise.
- **Matrix A: [[1, 1, 1, 1], [2, 3, -4, 9], [1, -1, 1, -1]] (3x4)**

- **New Matrix A: [[1, 1, 1, 1], [0, 1, -6, 7], [0, 0, 1, -1]]**
<img src="https://imgur.com/hSRsgEs.jpg" width="900px">

---
Now, we don't have to settle just with the result, if we need a detailed list of steps, you can add the steps flags in the solution, and the result will render in the steps module, you can open it, and check all the required steps to complete the solution. Let's see an example using the same **Matrix A** as in the past excercise:
/* Video. */

## 📖 Gauss Jordan + steps
In this case, we just follow the rules the same way as with the Gauss method, let's give it a try using the following excercise:
- **Matrix A: [[3, 2, 1, 4], [5, 3, 4, 2], [1, 1, -1, 1]] (3x4)**

- **New Matrix A: [[1, 2/3, 1/3, 4/3], [0, 1, -7, 14], [0, 0, 1, -5]]**
<img src="https://imgur.com/WWvoZxn.jpg" width="900px">

The same way, we can use this method including the solution steps:
- **New Matrix A + steps:**
/* Video */

## 📖 Classic Inverse Matrix
Matrixezz also includes the classical solution protocol to find the inverse matrix in an excercise, in this case, the matrix to solve got to be a squared matrix, in example, the matrix got to have the same size both for his rows and his columns. Let's give it a try with the following excercise:
- **Matrix A: [[1, 0, 3], [0, 5, -1], [0, 0, 3]] (3x3)**

So, this way, we obtain the following result for a squared matrix:
- **New Matrix A: [[1, 0, -1], [0, 1/5, 1/15], [0, 0, 1/3]]**
<img src="https://imgur.com/YnWl1CS.jpg" width="900px">

---
We can also get the steps from this option:
/* Video */

