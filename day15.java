import java.io.*;
import java.util.*;

//#1 16:22
//#2 17:22

public class day15 {
	static String file = "./inputs/day15.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		Map<Integer, Integer> idxs = new HashMap<>();
		int last = 0;
		int idx = -1;
		while ((line = br.readLine()) != null) {
			String[] s = line.split(",");
			for(String a : s) {
				idx++;
				last = Integer.parseInt(a);
				idxs.put(last, idx);
			}
		}
		while(idx!=29999999) { //last is the next value, we must stop 1 index before the end
			int l = idxs.containsKey(last) ? idx - idxs.get(last) : 0;
			idxs.put(last, idx);
			last = l;
			idx++;
		}
		System.out.println(last);
		br.close();
	}
}
