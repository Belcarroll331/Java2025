import java.io.*;
import java.util.*;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (args.length == 2) {
            File file1 = new File(args[0]);
            File file2 = new File(args[1]);
            if (file1.exists() && file2.exists()) {
                int[][] matrix1 = readMatrixFromFile(args[0]);
                int[][] matrix2 = readMatrixFromFile(args[1]);
                int[][] result = multiplyMatrices(matrix1, matrix2);
                writeMatrixToFile(result, "matrix3.txt");
                System.out.println("Multiplication result saved to matrix3.txt");
                return;
            }
        }

        System.out.print("Enter two file names or an integer: ");
        if (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            int[][] matrix1 = generateRandomMatrix(size);
            int[][] matrix2 = generateRandomMatrix(size);
            writeMatrixToFile(matrix1, "matrix1.txt");
            writeMatrixToFile(matrix2, "matrix2.txt");
            int[][] result = multiplyMatrices(matrix1, matrix2);
            writeMatrixToFile(result, "matrix3.txt");
            System.out.println("Random matrices generated and saved. Result saved to matrix3.txt");
        } else {
            String file1 = scanner.next();
            String file2 = scanner.next();
            int[][] matrix1 = readMatrixFromFile(file1);
            int[][] matrix2 = readMatrixFromFile(file2);
            int[][] result = multiplyMatrices(matrix1, matrix2);
            writeMatrixToFile(result, "matrix3.txt");
            System.out.println("Multiplication result saved to matrix3.txt");
        }
    }

    private static int[][] readMatrixFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            List<int[]> rows = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().trim().split(" ");
                int[] row = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
                rows.add(row);
            }
            return rows.toArray(new int[0][]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            System.exit(1);
        }
        return null;
    }

    private static void writeMatrixToFile(int[][] matrix, String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (int[] row : matrix) {
                for (int num : row) {
                    writer.print(num + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix2[0].length;
        int common = matrix1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < common; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    private static int[][] generateRandomMatrix(int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(10); // Random number between 0-9
            }
        }
        return matrix;
    }
}