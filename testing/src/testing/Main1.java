package testing;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa la primera expresión racional (por ejemplo, 2/3):");
        String input1 = scanner.next();
        String[] parts1 = input1.split("/");
        Rational rational1 = new Rational(Integer.parseInt(parts1[0]), Integer.parseInt(parts1[1]));

        System.out.println("Ingresa la segunda expresión racional (por ejemplo, 1/4):");
        String input2 = scanner.next();
        String[] parts2 = input2.split("/");
        Rational rational2 = new Rational(Integer.parseInt(parts2[0]), Integer.parseInt(parts2[1]));

        System.out.println("Operación a realizar (+, -, *, /):");
        String operation = scanner.next();

        Rational result = null;
        switch (operation) {
            case "+":
                result = rational1.add(rational2);
                break;
            case "-":
                result = rational1.subtract(rational2);
                break;
            case "*":
                result = rational1.multiply(rational2);
                break;
            case "/":
                result = rational1.divide(rational2);
                break;
            default:
                System.out.println("Operación no válida.");
        }

        if (result != null) {
            System.out.println("Resultado: " + result);
        }
    }
}
