import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ScoreSource {
	private static Vector<String> score = new Vector<String>();

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
					score.add(line);
				}
			}
			buffreader.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score;
	}
	
	
	public static int scorelistsize() {
		return score.size();
	}
	
	
	
	
	
	

	
	
	
}
