import java.io.*;

//#1 42:01
//#2 56:37

public class day18 {
	static String file = "./inputs/day18.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		long sum = 0;
		while ((line = br.readLine()) != null) {
			long v = evaluateV2(line);
			System.out.println(line+" = "+ v);
			sum += v;
		}
		System.out.println(sum);
		br.close();
	}
	
	private static long evaluate(String line) {
		int idx = line.lastIndexOf("(");
		if(idx==-1) {
			int j = line.indexOf(" ");
			j = j==-1 ? line.length() : j;
			long r = Long.parseLong(line.substring(0,j));
			for(int i = 0; i<line.length(); i++) {
				if(line.charAt(i)=='+') {
					int iEnd = line.substring(i+2).indexOf(" ");
					iEnd = iEnd==-1 ? line.length() : i+2+iEnd;
					r += Long.parseLong(line.substring(i+2, iEnd));
				}else if(line.charAt(i)=='*') {
					int iEnd = line.substring(i+2).indexOf(" ");
					iEnd = iEnd==-1 ? line.length() : i+2+iEnd;
					r *= Long.parseLong(line.substring(i+2, iEnd));
				}
			}
			return r;
		}else {
			int count = 1;
			int idx2 = idx+1;
			for(; count>0; idx2++) {
				if(line.charAt(idx2)=='(') {
					count++;
				}else if(line.charAt(idx2)==')') {
					count--;
				}
			}
			String l = line.substring(idx+1, idx2-1);
			long v = evaluate(l);
			line = line.substring(0,idx)+String.valueOf(v)+line.substring(idx2);
			return evaluate(line);
		}
	}
	
	private static long evaluateV2(String line) {
		int idx = line.lastIndexOf("(");
		if(idx==-1) {
			int idxP = line.indexOf("+");
			if(idxP!=-1) {
				int j = line.substring(0,idxP-1).lastIndexOf(" ");
				j = j==-1 ? 0 : j+1;
				long a = Long.parseLong(line.substring(j,idxP-1));
				
				int iEnd = line.substring(idxP+2).indexOf(" ");
				iEnd = iEnd==-1 ? line.length() : idxP+2+iEnd;
				a += Long.parseLong(line.substring(idxP+2, iEnd));
				
				line = line.substring(0,j)+String.valueOf(a)+line.substring(iEnd);
				return evaluateV2(line);
			}
			else {
				int j = line.indexOf(" ");
				j = j==-1 ? line.length() : j;
				long r = Long.parseLong(line.substring(0,j));
				for(int i = 0; i<line.length(); i++) {
					if(line.charAt(i)=='+') {
						int iEnd = line.substring(i+2).indexOf(" ");
						iEnd = iEnd==-1 ? line.length() : i+2+iEnd;
						r += Long.parseLong(line.substring(i+2, iEnd));
					}else if(line.charAt(i)=='*') {
						int iEnd = line.substring(i+2).indexOf(" ");
						iEnd = iEnd==-1 ? line.length() : i+2+iEnd;
						r *= Long.parseLong(line.substring(i+2, iEnd));
					}
				}
				return r;
			}
		}else {
			int count = 1;
			int idx2 = idx+1;
			for(; count>0; idx2++) {
				if(line.charAt(idx2)=='(') {
					count++;
				}else if(line.charAt(idx2)==')') {
					count--;
				}
			}
			String l = line.substring(idx+1, idx2-1);
			long v = evaluateV2(l);
			line = line.substring(0,idx)+String.valueOf(v)+line.substring(idx2);
			return evaluateV2(line);
		}
	}
}
