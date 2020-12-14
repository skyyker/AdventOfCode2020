import java.io.*;

//#1 11:23
//#2 21:45

public class day12 {
	static String file = "./inputs/day12.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int dx = 10, dy = 1;
		int x = 0, y = 0;
		while ((line = br.readLine()) != null) {
			char c = line.charAt(0);
			int v = Integer.parseInt(line.substring(1));
			if(c=='F') {
				x += dx*v;
				y += dy*v;
			} else if(c=='E') {
				dx+=v;
			}else if(c=='W') {
				dx-=v;
			}else if(c=='N') {
				dy+=v;
			}else if(c=='S') {
				dy-=v;
			}else if(c=='R' || c=='L') {
				v /= 90;
				if(c=='L') v = 4-v;
				
				if(v==1) {
					int t = dy;
					dy = -dx;
					dx = t;
				}else if(v==2) {
					dx = -dx;
					dy = -dy;
				}else if(v==3) {
					int t = dy;
					dy = dx;
					dx = -t;
				}
			}
		}
		System.out.println(x+ " " + y);
		System.out.println(Math.abs(x)+ Math.abs(y));
		br.close();
	}
	
	//Part 1
//	String line;
//	int dirIdx = 0;
//	char[] dirs = {'E', 'S', 'W', 'N'};
//	int x = 0, y = 0;
//	while ((line = br.readLine()) != null) {
//		char c = line.charAt(0);
//		int v = Integer.parseInt(line.substring(1));
//		if(c=='F') {
//			c=dirs[dirIdx];
//		}
//		
//		if(c=='E') {
//			x+=v;
//		}else if(c=='W') {
//			x-=v;
//		}else if(c=='N') {
//			y+=v;
//		}else if(c=='S') {
//			y-=v;
//		}else if(c=='R') {
//			v /= 90;
//			dirIdx += v;
//			dirIdx %= 4;
//		}else if(c=='L') {
//			v /= 90;
//			dirIdx -= v;
//			if(dirIdx<0) dirIdx+=4;
//		}
//	}
//	System.out.println(x+ " " + y);
//	System.out.println(Math.abs(x)+ Math.abs(y));
}
