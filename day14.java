import java.io.*;
import java.util.*;

//#1 38:20 (a 1L instead of 1 caused me some trouble...)
//#2 54:25

public class day14 {
	static String file = "./inputs/day14.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		String mask = null;
		List<Integer> v1 = new ArrayList<>();
		List<Integer> vX = new ArrayList<>();
		Map<Long, Long> values = new HashMap<>();
		while ((line = br.readLine()) != null) {
			if(line.startsWith("mask")) {
				mask = line.substring(7);
				System.out.println(mask);
				v1.clear();vX.clear();
				for(int i = 0; i<mask.length(); i++) {
					if(mask.charAt(i)=='1') {
						v1.add(mask.length()-1-i);
					}else if(mask.charAt(i)=='X') {
						vX.add(mask.length()-1-i);
					}
				}
//				System.out.println(v1);
//				System.out.println(v0);
			}else {
				String[] split = line.substring(4).split("] = ");
				long idx = Integer.parseInt(split[0]);
				long value = Integer.parseInt(split[1]);
				for(int a : v1) {
					idx |= (1L << a);
				}
				Set<Long> idxs = new HashSet<>();
				idxs.add(idx);
				for(int a : vX) {
					Set<Long> newIdxs = new HashSet<>();
					for(long id : idxs) {
						long idx1 = id | (1L << a);//with 1
						long idx2 = id & (~(1L << a));//with 0
						newIdxs.add(idx1);
						newIdxs.add(idx2);
					}
					idxs = newIdxs;
				}
				for(long id : idxs) {
					//System.out.println(Long.toBinaryString(id)+" " +id + " "+value);
					values.put(id, value);
				}
			}
		}
		
		long sum = 0;
		for(long a : values.values()) {
			sum += a;
		}
		System.out.println(sum);
		br.close();
	}
	
//	{ PART 1
//		String mask = null;
//		List<Integer> v1 = new ArrayList<>();
//		List<Integer> v0 = new ArrayList<>();
//		long[] values = new long[100000];
//		while ((line = br.readLine()) != null) {
//			if(line.startsWith("mask")) {
//				mask = line.substring(7);
//				System.out.println(mask);
//				v1.clear();v0.clear();
//				for(int i = 0; i<mask.length(); i++) {
//					if(mask.charAt(i)=='1') {
//						v1.add(mask.length()-1-i);
//					}else if(mask.charAt(i)=='0') {
//						v0.add(mask.length()-1-i);
//					}
//				}
////				System.out.println(v1);
////				System.out.println(v0);
//			}else {
//				String[] split = line.substring(4).split("] = ");
//				int idx = Integer.parseInt(split[0]);
//				long value = Integer.parseInt(split[1]);
////				System.out.println(idx +" " +value);
////				System.out.println(Long.toBinaryString(value));
//				for(int a : v1) {
//					value |= (1L << a);
//				}
//				for(int a : v0) {
//					//System.out.println(Long.toBinaryString(~(1L<<a)));
//					value &= (~(1L << a));
//				}
//				System.out.println(Long.toBinaryString(value));
//				values[idx] = value;
//			}
//		}
//	}
}
