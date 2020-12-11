import java.io.*;
import java.util.*;

public class day4 {
	static String file = "./inputs/day4.txt";
	public static void main(String args[]) throws Exception {
		int nbValid = 0;
		Set<String> mandatoryData = new HashSet<>();
		mandatoryData.addAll(Arrays.asList("hcl", "iyr", "eyr", "hgt", "byr", "ecl", "pid"));
		Set<String> currentPerson = new HashSet<>();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			if(line.equals("")) {
				System.out.println(currentPerson);
				if(currentPerson.containsAll(mandatoryData)) {
					nbValid++;
				}
				currentPerson.clear();
				continue;
			}
			String[] l = line.split(" ");
			for(int i =0; i<l.length; i++) {
				String[] kl = l[i].split(":");
				if(validateData(kl[0], kl[1])){
					currentPerson.add(kl[0]);
				}
			}
		}
		if(currentPerson.containsAll(mandatoryData)) {
			nbValid++;
		}
		System.out.println(nbValid);
		br.close();
	}
	
	private static boolean validateData(String key, String value) {
		boolean isValid = false;
		switch(key) {
		case "byr":
			if(value.length()==4) {
				int y = Integer.parseInt(value);
				if(y>=1920 && y <=2002) {
					isValid = true;
				}
			}
			break;
			
		case "iyr":
			if(value.length()==4) {
				int y = Integer.parseInt(value);
				if(y>=2010 && y <=2020) {
					isValid = true;
				}
			}
			break;
		case "eyr":
			if(value.length()==4) {
				int y = Integer.parseInt(value);
				if(y>=2020 && y <=2030) {
					isValid = true;
				}
			}
			break;
		case "hgt":
			if(value.contains("cm")) {
				int y = Integer.parseInt(value.substring(0, value.length()-2));
				if(y>=150 && y <=193) {
					isValid = true;
				}
			}else if(value.contains("in")) {
				int y = Integer.parseInt(value.substring(0, value.length()-2));
				if(y>=59 && y <=76) {
					isValid = true;
				}
			}
			break;
		case "hcl":
			if(value.matches("#[0-9a-f]{6}")) {
				System.out.println("hcl valid "+value);
				isValid = true;
			}
			break;
		case "ecl":
			Set<String> enumValues = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
			if(enumValues.contains(value)) {
				isValid = true;
			}
			break;
		case "pid":
			if(value.matches("\\d{9}")) {
				//System.out.println("pid valid "+value);
				isValid = true;
			}
			break;
		}
		return isValid;
	}
}
