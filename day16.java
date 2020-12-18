import java.io.*;
import java.util.*;

//#1 12:12
//#2 57:25

public class day16 {
	static String file = "./inputs/day16.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		boolean[] correct = new boolean[1000];
		Map<String, Boolean[]> maps = new HashMap<>();
		Map<Integer, Set<String>> mapping = null;
		int sum = 0;
		String myNumbers = null;
		boolean mine = false;
		while ((line = br.readLine()) != null) {
			if(mine) {
				myNumbers = line;
				System.out.println(myNumbers);
				mine = false;
			}
			
			if(line.contains(":") && line.contains("or")) {
				String alias = line.substring(0, line.indexOf(":"));
				String[] s = line.substring(line.indexOf(":")+2).split(" or ");
				Boolean[] bb = new Boolean[1000];
				Arrays.fill(bb, false);
				for(String ss : s) {
					int a = Integer.parseInt(ss.split("-")[0]);
					int b = Integer.parseInt(ss.split("-")[1]);
					for(; a<=b; a++) {
						correct[a] = true;
						bb[a] = true;
					}
				}
				maps.put(alias, bb);
			}else if(line.equals("your ticket:")) {
				mine = true;
				mapping = initMapping(maps);
			}
			else if(line.contains(",")) {
				String[] s = line.split(",");
				int idx = 0;
				for(String si : s) {
					int a = Integer.parseInt(si);
					if(!correct[a])
						sum+=a;
					else {
						Set<String> toRemove = new HashSet<>();
						for(String key : mapping.get(idx)) {
							if(!maps.get(key)[a]) {
								toRemove.add(key);
							}
						}
						mapping.get(idx).removeAll(toRemove);
						
						checkRemainingChoices(idx, mapping);
					}
					idx++;
				}
			}
		}
		System.out.println(sum);
		System.out.println(mapping);
		long multiply = 1;
		String[] s = myNumbers.split(",");
		for(int i = 0; i<mapping.size(); i++) {
			if(mapping.get(i).iterator().next().contains("departure")) {
				multiply *= Long.parseLong(s[i]);
			}
		}
		System.out.println(multiply);
		br.close();
	}
	
	private static Map<Integer, Set<String>> initMapping(Map<String, Boolean[]> maps){
		Map<Integer, Set<String>> mapping = new HashMap<>();
		Set<String> keys = maps.keySet();
		for(int i = 0; i<keys.size(); i++) {
			mapping.put(i, new HashSet<String>(keys));
		}
		return mapping;
	}
	
	private static void checkRemainingChoices(int idx, Map<Integer, Set<String>> mapping) {
		if(mapping.get(idx).size()==1) {
			String value = mapping.get(idx).iterator().next();
			//System.out.println(idx+" = " + value);

			for(int i = 0; i<mapping.size(); i++) {
				if(i==idx) continue;
				boolean r = mapping.get(i).remove(value);
				if(r)
					checkRemainingChoices(i, mapping);
			}
		}
	}
}
