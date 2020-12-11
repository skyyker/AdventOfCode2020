import java.io.BufferedReader;
import java.io.FileReader;

public class day2 {
	public static void main(String args[]) throws Exception {
		String file = "./inputs/day2.txt";
		int nbValid = 0;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String[] lsplit = line.split(" ");
			String[] mm = lsplit[0].split("-");
			int min = Integer.parseInt(mm[0]);
			int max = Integer.parseInt(mm[1]);
			char c = lsplit[1].charAt(0);
			String password = lsplit[2];
			//				long nbChar = password.chars().filter(ch -> ch == c).count();
			//System.out.println(min+" "+max+" "+c+" "+password+" "+nbChar);
			//				if(nbChar>=min && nbChar<=max) {
			//					nbValid++;
			//				}
			boolean c1 = password.length()>min-1 && password.charAt(min-1)==c;
			boolean c2 = password.length()>max-1 && password.charAt(max-1)==c;
			if(c1 ^ c2) {
				nbValid++;
			}
		}
		System.out.println(nbValid);
		br.close();
	}
}
