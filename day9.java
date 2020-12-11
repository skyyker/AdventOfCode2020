import java.io.*;
import java.util.*;

//#1 8:10
//#2 15:51

public class day9 {
	static String file = "./inputs/day9.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		List<Long> inputs = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			inputs.add(Long.parseLong(line));
		}
		int i = 25;
		for(; i<inputs.size(); i++) {
			boolean isValid = false;
			for(int j = i-25; j<i-1; j++) {
				for(int k = j+1; k<i; k++) {
					if(inputs.get(k)+inputs.get(j)==inputs.get(i)) {
						isValid = true;
						break;
					}
				}
			}
			if(!isValid) {
				System.out.println(inputs.get(i));
				break;
			}
		}
		
		for(int j = 0; j<i-1; j++) {
			for(int k = j+1; k<i; k++) {
				int sum = 0;
				for(int l = j; l<=k; l++) {
					sum += inputs.get(l);
					if(l>inputs.get(i))
						break;
				}
				if(sum==inputs.get(i)) {
					List<Long> sums = inputs.subList(j, k+1);
					Collections.sort(sums);
					
					System.out.println(inputs.subList(j, k+1));
					
					System.out.println(sums.get(0) +  sums.get(sums.size()-1));
					break;
				}
			}
		}
		
		br.close();
	}
}
