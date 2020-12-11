import java.io.*;
import java.util.*;

//#1 28:59
//#2 40:32

public class day11 {
	static String file = "./inputs/day11.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		List<String> lines = new ArrayList<>();
		int size = 0;
		while ((line = br.readLine()) != null) {
			lines.add(line);
			size = line.length();
		}
		int [][] s = new int[lines.size()][size];
		for(int i = 0; i<lines.size(); i++) {
			for(int j = 0; j<size; j++) {
				char c = lines.get(i).charAt(j);
				s[i][j] = c=='.' ? 0 : c=='L' ? 1 : 2;
			}
		}
//		for(int i = 0; i<lines.size(); i++)
//			System.out.println(Arrays.toString(s[i]));
		
		int [][] sDup = new int[lines.size()][size];
		while(true) {
			boolean same = true;
//			for(int i = 0; i<lines.size(); i++)
//				System.out.println(Arrays.toString(s[i]));
			for(int i = 0; i<lines.size(); i++)
				same &= Arrays.equals(sDup[i], s[i]);
			if(same)
				break;
			
			sDup = s;
			s = computeLap(s);
//			System.out.println();
		}
		
		int count = 0;
		for(int i = 0; i<s.length; i++) {
			for(int j = 0; j<s[0].length; j++) {
				if(s[i][j]==2) count++;
			}
		}
		
		System.out.println(count);
		br.close();
	}
	private static int[][] computeLap(int[][] s) {
		int [][] sDup = new int[s.length][s[0].length];
		for(int i = 0; i<s.length; i++) {
			for(int j = 0; j<s[0].length; j++) {
				if(s[i][j]==0)
					continue;
				
				if(s[i][j]==1) {
					int count = 0;
					for(int k = -1; k<2; k++) {
						for(int l = -1; l<2; l++) {
							if(l==0 && k==0) continue;
							int val = getNextVisibleValue(s, i, j, k, l);
							if(val!=2)
								count++;
						}
					}
					sDup[i][j] = count==8 ? 2 : 1;
				}else {
					int count = 0;
					for(int k = -1; k<2; k++) {
						for(int l = -1; l<2; l++) {
							if(l==0 && k==0) continue;
							int val = getNextVisibleValue(s, i, j, k, l);
							if(val==2)
								count++;
						}
					}
					sDup[i][j] = count>=5 ? 1 : 2;
				}
			}
		}
		return sDup;
	}
	
	public static int getNextVisibleValue(int[][] s, int i, int j, int si, int sj) {
		while(true) {
			i+=si;
			j+=sj;
			if(i<0 || i==s.length || j<0 || j==s[0].length)
				break;
			if(s[i][j]!=0)
				return s[i][j]; 
		}
		return 0;
	}
	
//	private static int[][] computeLap(int[][] s) {
//		int [][] sDup = new int[s.length][s[0].length];
//		for(int i = 0; i<s.length; i++) {
//			for(int j = 0; j<s[0].length; j++) {
//				if(s[i][j]==0)
//					continue;
//				
//				if(s[i][j]==1) {
//					int count = 0;
//					for(int k = -1; k<2; k++) {
//						for(int l = -1; l<2; l++) {
//							if(l==0 && k==0) continue;
//							if(i+l<0 || i+l==s.length || j+k<0 || j+k==s[0].length
//									|| s[i+l][j+k]!=2)
//								count++;
//						}
//					}
//					sDup[i][j] = count==8 ? 2 : 1;
//				}else {
//					int count = 0;
//					for(int k = -1; k<2; k++) {
//						for(int l = -1; l<2; l++) {
//							if(l==0 && k==0) continue;
//							if(i+l<0 || i+l==s.length || j+k<0 || j+k==s[0].length) continue;
//							if(s[i+l][j+k]==2)
//								count++;
//						}
//					}
//					sDup[i][j] = count>=4 ? 1 : 2;
//				}
//			}
//		}
//		return sDup;
//	}
}
