import java.io.*;
import java.util.*;

public class BubbleSort {
    
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); // Random number between 0 and 100
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1 - Generate random array and save to file");
            System.out.println("2 - Read array from file, sort it, and save to a new file");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter array length: ");
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    int[] randomArray = createRandomArray(length);
                    System.out.print("Enter filename to save the array: ");
                    String file1 = scanner.nextLine();
                    writeArrayToFile(randomArray, file1);
                    System.out.println("Array saved to " + file1);
                    break;
                case 2:
                    System.out.print("Enter filename to read the array: ");
                    String file2 = scanner.nextLine();
                    int[] array = readFileToArray(file2);
                    if (array.length == 0) {
                        System.out.println("File is empty or invalid.");
                        break;
                    }
                    bubbleSort(array);
                    System.out.print("Enter filename to save sorted array: ");
                    String file3 = scanner.nextLine();
                    writeArrayToFile(array, file3);
                    System.out.println("Sorted array saved to " + file3);
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
