import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class StreamsIndiaWordCount {
	    public static void main(String[] args) {
		        try {
		        String fileName = "C:\\Users\\sudhe\\eclipse-workspace\\Nov_13th_Assignment\\India.txt"; 
		        System.out.println("The count for India is: ");
		        System.out.println(Files.lines(Paths.get(fileName)).flatMap(line -> Arrays.stream(line.split(" "))).map(String::toLowerCase)
		                    .filter(word -> word.equals("india"))
		                    .count()); 
		        } catch (IOException e) {
		            System.out.println( e.getMessage());
		        }
		    }
		

	}

