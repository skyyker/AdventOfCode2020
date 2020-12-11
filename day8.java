import java.io.*;
import java.util.*;

//#1 10:25
//#2 17:45

public class day8 {
	static String file = "./inputs/day8.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		List<String> lines = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		
		boolean modifFound = false;
		int acc = 0;
		int idxModified = 0;
		while(!modifFound) {
			while(lines.get(idxModified).startsWith("acc"))
				idxModified++;
				
			
			
			boolean[] rLines = new boolean[lines.size()];
			int idx = 0;
			acc = 0;
			while(!rLines[idx]) {
				line = lines.get(idx);
				rLines[idx] = true;
				if(idx==idxModified) {
					line = line.startsWith("jmp") ? "nop" : "jmp"+line.substring(3);
				}
				
				if(line.startsWith("acc")) {
					int a = Integer.parseInt(line.substring(5));
					if(line.charAt(4)=='-') {
						acc -= a;
					}else {
						acc += a;
					}
					idx++;
				}else if(line.startsWith("jmp")){
					int a = Integer.parseInt(line.substring(5));
					if(line.charAt(4)=='-') {
						idx -= a;
					}else {
						idx += a;
					}
				}else if(line.startsWith("nop")) {
					idx++;
				}
				
				if(idx==lines.size()) {
					modifFound = true;
					break;
				}
			}
			idxModified++;
		}
		
		System.out.println(acc);
		br.close();
	}
}
