import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TextSource {
	private Vector<String> v = new Vector<String>();
	// private static Vector<String> words = new Vector<String>();
	private static HashMap<String, String> words = new HashMap<>();

	public TextSource() {

	}

	public String get() {
		int index = (int) (Math.random() * v.size());
		return v.get(index);
	}

	public static HashMap<String, String> loadfile(String filename) throws IOException {

		FileReader reader;
		try {
			reader = new FileReader(filename);
			BufferedReader buffreader = new BufferedReader(reader);

			while (true) {
				String line = buffreader.readLine();
				if (line == null) {
					break;
				} else {
					// words.add(line);
					String[] split = line.split(":", 2);
					if (split.length >= 2) {
						String key = split[0];
						String value = split[1];
						words.put(key, value);
					} else {
						System.out.println("ignoring line: " + line);
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

	public static HashMap<String, String> loadWord(String filename) throws IOException {

		FileReader reader;
		try {
			reader = new FileReader(filename);
			BufferedReader buffreader = new BufferedReader(reader);
			if (words != null) {
				words.clear();
			}

			while (true) {
				String line = buffreader.readLine();
				if (line == null) {
					break;
				} else {
					String[] split = line.split(":", 2);
					if (split.length >= 2) {
						String key = split[1];
						String value = split[0];
						words.put(key, value);
					} else {
						System.out.println("ignoring line: " + line);
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
