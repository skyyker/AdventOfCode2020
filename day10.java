import java.io.*;
import java.util.*;

//#1 9:10
//#2 49:17

public class day10 {
	static String file = "./inputs/day10.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		List<Integer> inputs = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			inputs.add(Integer.parseInt(line));
		}
		Collections.sort(inputs);
		inputs.add(0, 0);
		inputs.add(inputs.get(inputs.size()-1)+3);
//		int[] diff = {0, 0, 0};
//		for(int i = 1; i<inputs.size(); i++) {
//			int d = inputs.get(i) - inputs.get(i-1);
//			diff[d-1]++;
//		}
//		System.out.println(Arrays.toString(diff));
		//System.out.println(diff[0]*diff[2]);
		
		long[] value = new long[inputs.size()];
		value[inputs.size()-1] = 1;
		for(int i = inputs.size()-2; i>=0; i--) {
			int v = inputs.get(i);
			for(int a = 1; a<=3; a++) {
				if(i+a<inputs.size()) {
					int b = inputs.get(i+a);
					if(b-v<=3) {
						value[i]+=value[i+a];
					}
				}
			}
		}
		
		System.out.println(Arrays.toString(value));
		br.close();
	}
}
