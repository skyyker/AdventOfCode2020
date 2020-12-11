import java.io.BufferedReader;
import java.io.FileReader;

public class day3 {
	static String file = "./inputs/day3.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int count = 0;
		int y = 0;
		int i = 0;
		while ((line = br.readLine()) != null) {
			if(line.charAt(y)=='#' && i%2==0) {
				count++;
			}
			if(i%2==0)
				y += 1;
			y %= line.length();
			i++;
		}
		System.out.println(count);
		br.close();
	}
}
