package chapter5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommonUtils {
    public static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String readFileIntoString(String fileName) throws FileNotFoundException {
        // Delimiter '\Z' indicates the EOF.
        return new Scanner(new File("resources\\files\\" + fileName)).useDelimiter("\\Z").next();
    }
}
