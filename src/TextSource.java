import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TextSource {
	private Vector<String> v = new Vector<String>();
	private static Vector<String> words = new Vector<String>();
	
	public TextSource() { // ���Ͽ��� �б�

	}
	
	public String get() {
		int index = (int)(Math.random()*v.size());
		return v.get(index);
	}
	
	public static Vector<String> loadfile(String filename) throws IOException{
		
		FileReader reader;
		try {
			reader = new FileReader(filename);
			BufferedReader buffreader = new BufferedReader(reader);
			
			while(true) {
				String line = buffreader.readLine();
				if(line == null) {
					break;
				}else {
					words.add(line);
				}
			}
			buffreader.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return words;
	}

	
public static Vector<String> loadWord(String filename) throws IOException{
		
		FileReader reader;
		try {
			reader = new FileReader(filename);
			BufferedReader buffreader = new BufferedReader(reader);
			if(words !=null) {
				words.removeAllElements();
			}
			
			while(true) {
				String line = buffreader.readLine();
				if(line == null) {
					break;
				}else {
					String[] split = line.split(":");
					for (String wo : split) {
						words.add(wo);
					}
					
				}
			
			}
			buffreader.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return words;
	}
	
	public static int wordlistsize() {
		return words.size();
	}
	
	
	
}
