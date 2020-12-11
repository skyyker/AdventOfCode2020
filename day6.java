import java.io.*;
import java.util.*;

//#1 7:14
//#2 15:00

public class day6 {
	static String file = "./inputs/day6.txt";
	static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int sum = 0;
		//Set<Character> cs = new HashSet<>();
		boolean[] cs = new boolean[26];
		Arrays.fill(cs, Boolean.TRUE);
		while ((line = br.readLine()) != null) {
//			if(line.isEmpty()) {
//				sum += cs.size();
//				cs.clear();
//			}else {
//				for(int i = 0; i<line.length(); i++) {
//					cs.add(line.charAt(i));
//				}
//			}
			if(line.isEmpty()) {
				int common = 0;
				for(int i = 0; i<26; i++) {
					if(cs[i]) common++;
				}
				sum += common;
				Arrays.fill(cs, Boolean.TRUE);
			}else {
				for(int i = 0; i<26; i++) {
					cs[i] &= line.contains(alphabet.substring(i, i+1));
				}
			}
		}
		System.out.println(sum);
		br.close();
	}
}
