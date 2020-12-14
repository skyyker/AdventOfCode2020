import java.io.*;

//#1 07:50
//#2 1:07:50 Wrong way to compute at first...

public class day13 {
	static String file = "./inputs/day13.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
//		int result = 0;
//		int minWait = Integer.MAX_VALUE;
		int b = -1;
		long ppcm = 1;
		long init = 0;
		while ((line = br.readLine()) != null) {
			if(b==-1) {
				b = Integer.parseInt(line);
			}else {
				String[] s = line.split(",");
				long r = 0;
				for(String a : s) {
					if(!a.equals("x")) {
						long t = Integer.parseInt(a);
						long x = 0;
						while((init+(ppcm*x)+r)%t!=0) {
							x++;
						}
						init = init + ppcm*x;
						ppcm = PPCM(ppcm, t);
//						int w = t-b%t;
//						System.out.println(b+" "+t+" "+w);
//						if(w<minWait) {
//							minWait = w;
//							result = minWait * t;
//						}
					}
					r++;
				}
			}
		}
		System.out.println(init);
		br.close();
	}
	
	public static long PPCM (long nb1, long nb2) {
		long product, r, PPCM;

		product = nb1*nb2;
		r   = nb1%nb2;
		while(r != 0){
			nb1 = nb2;
			nb2 = r;
			r = nb1%nb2;
		}
		PPCM = product/nb2;
		return PPCM;		
	}
}
