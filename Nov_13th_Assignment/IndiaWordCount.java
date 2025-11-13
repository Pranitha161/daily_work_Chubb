import java.util.*;
import java.io.*;
public class IndiaWordCount{
	public static void main(String args[]) {
		try {
			BufferedReader fr=new BufferedReader(new FileReader("C:\\Users\\sudhe\\eclipse-workspace\\Nov_13th\\India.txt"));
			String s="";
			int count=0;
			while((s=fr.readLine())!=null) {
				System.out.println(s);
				String words[]=s.split(" ");
				for(String word:words) {
				if(word.equalsIgnoreCase("India")) {
					count++;
				}
				}
			}
			System.out.println("The count of the word India is: "+count);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
