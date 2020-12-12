import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class CircleController {
	public static Vector<Circle> v;
	private TextSource textSource = new TextSource();
	private Circle circle;
	public static HashMap<String, String> wordlist;
	
	
	
	
	
	public Vector<Circle> makevector() {
		// circle.startflag = true;
		
		try {

			wordlist = TextSource.loadWord("words.txt");
			textSource.wordsize = wordlist.size();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		v = new Vector<Circle>(textSource.wordsize);

		for (int i = 0; i < textSource.wordlistsize(); i++) {
			//System.out.println("Asdf");
			circle =new Circle();
			circle.setX((int) ((Math.random() * 1200)+1));
			circle.setY(0);
			v.add(circle);
		}
		return v;
	}
}
