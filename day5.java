import java.io.*;

//#1 11:16
//#2 19:30

public class day5 {
	static String file = "./inputs/day5.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		//int max = 0;
		boolean[] seats = new boolean[128*8];
		while ((line = br.readLine()) != null) {
			int f=0, b=127, l=0, r=7;
			for(int i = 0; i<line.length(); i++) {
				char c = line.charAt(i);
				if(c=='B') {
					f=(b+1-f)/2+f;
				}else if(c=='F') {
					b=(b-f)/2+f;
				}else if(c=='R') {
					l=(r+1-l)/2+l;
				}else if(c=='L') {
					r=(r-l)/2+l;
				}
			}
			//System.out.println(f+" "+b+" "+l+" "+r);
			int val = 8*f+l;
			seats[val] = true;
			//max = Math.max(max, val);
		}
		//System.out.println(max);
		for(int i = 0; i<seats.length; i++) {
			if(!seats[i]) {
				boolean isMySeat = false;
				int r = i/8, c = i%8;
				if(r!=0 && r!=127 && c!=0 && c!=7) {
					isMySeat = true;
					for(int j = -1; j<2; j++) {
						for(int k = -1; k<2; k++) {
							if(j==0 && k==0) continue;
							isMySeat &= seats[(r+j)*8+(c+k)];
						}
					}
				}
				if(isMySeat)
					System.out.println(i + " " + (i/8) + " " + (i%8));
			}
		}
		br.close();
	}
}
