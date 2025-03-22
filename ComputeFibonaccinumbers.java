import java.util.Scanner;

public class Fibonacci {
    
    // Recursive method to calculate Fibonacci numbers
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    
    // Iterative method to calculate Fibonacci numbers
    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to compute Fibonacci: ");
        int n = scanner.nextInt();
        
        System.out.println("Fibonacci (Recursive) of " + n + " is: " + fibonacciRecursive(n));
        System.out.println("Fibonacci (Iterative) of " + n + " is: " + fibonacciIterative(n));
        
        scanner.close();
    }
}
