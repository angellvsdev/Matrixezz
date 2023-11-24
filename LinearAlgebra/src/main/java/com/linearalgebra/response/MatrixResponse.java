package com.linearalgebra.response;

import java.util.Arrays;

import com.linearalgebra.entity.Matrix;
import com.linearalgebra.entity.Value;

public class MatrixResponse {
    private int rows;
    private int cols;
    private Value[][] data;

    public MatrixResponse(Matrix matrix) {
        this.rows = matrix.getRows();
        this.cols = matrix.getCols();
        this.data = matrix.getData();
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
