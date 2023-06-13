package Unifaccamp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String fileName = "C:\\temp\\quickSort\\100000NumerosAleatorios.txt";
        List<Integer> numbers = readNumbersFromFile(fileName);

        /*System.out.println("Array antes da ordenação:");
        System.out.println(numbers);*/

        QuickSort quickSort = new QuickSort();
        
        long startTime = System.currentTimeMillis();
        List<Integer> result = quickSort.Sequential(numbers);
        long endTime = System.currentTimeMillis();

        /*System.out.println("Array após a ordenação:");
        System.out.println(Arrays.asList(result));*/
        
        long duration = endTime - startTime;
        System.out.println("Tempo de execução Sequential: " + duration + " milissegundos");
        System.out.println("Comparações: " + quickSort.getComparisons());
        System.out.println("Movimentações: " + quickSort.getMovements());
        
        startTime = System.currentTimeMillis();
        List<Integer> result2 = quickSort.Parallelized(numbers);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução Parallelized: " + duration + " milissegundos");
        System.out.println("Comparações: " + quickSort.getComparisons());
        System.out.println("Movimentações: " + quickSort.getMovements());
	}
	
	private static List<Integer> readNumbersFromFile(String fileName) {
        List<Integer> numbers = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                int num = Integer.parseInt(scanner.nextLine());
                numbers.add(num);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}
