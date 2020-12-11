import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class day1 {
	static String file = "./inputs/day1.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		List<Integer> inputs = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			inputs.add(Integer.parseInt(line));
		}

		for(int a = 0; a<inputs.size(); a++){
			int c = inputs.get(a);
			for(int b = 0; b<inputs.size(); b++){
				int d = inputs.get(b);
				for(int e = 0; e<inputs.size(); e++){
					int f = inputs.get(e);
					if(c+d+f==2020){
						System.out.println(c*d*f);
					}
				}
			}
		}
		br.close();
	}
}
