package testing;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		String test = "1,1,1;1,1,1;1,1,1";
		Matrix matrix = Matrix.parseMatrix(test);
		System.out.println(matrix);
		Value[][] Value =  matrix.getArrayData();
		System.out.println(Value);
        System.out.println(Arrays.deepToString(Value));

    }
}