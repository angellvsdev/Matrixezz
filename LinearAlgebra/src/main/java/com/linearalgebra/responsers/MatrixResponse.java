package com.linearalgebra.responsers;

import java.util.Arrays;

import com.linearalgebra.matrix.Matrix;
import com.linearalgebra.numbers.Value;

public class MatrixResponse {
    private int rows;
    private int cols;
    private Value[][] data;

    public MatrixResponse(Matrix matrix) {
        this.rows = matrix.getRows();
        this.cols = matrix.getCols();
        this.data = matrix.getArrayData();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public String getData() {
        return Arrays.deepToString(data);
    }

    public void setData(Value[][] data) {
        this.data = data;
    }
}
