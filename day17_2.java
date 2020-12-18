import java.io.*;

//#2 45:09

public class day17_2 {
	static String file = "./inputs/day17.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int SIZE = 30;
		boolean[][][][] map = new boolean[SIZE][SIZE][SIZE][SIZE];
		final int OFFSET = 10;
		int j = 0;
		int maxx = 0;
		while ((line = br.readLine()) != null) {
			maxx = line.length();
			for(int i = 0; i<line.length(); i++) {
				map[i+OFFSET][j+OFFSET][0+OFFSET][0+OFFSET] = line.charAt(i)=='#';
				System.out.print(map[i+OFFSET][j+OFFSET][0+OFFSET][0+OFFSET]?'#':'.');
			}
			System.out.println();
			j++;
		}
		int[] min = {OFFSET, OFFSET, OFFSET, OFFSET};
		int[] max = {OFFSET+maxx, OFFSET+j, OFFSET, OFFSET};
		for(int a = 0; a<6; a++) {
			for(int i = 0; i<4; i++) {
				min[i]--;
				max[i]++;
			}
			
			boolean[][][][] nmap = new boolean[SIZE][SIZE][SIZE][SIZE];
			for(int w = min[2]; w<=max[2]; w++) {
				for(int z = min[2]; z<=max[2]; z++) {
					System.out.println("\nz="+(z-OFFSET)+" w="+(w-OFFSET));
					for(int y = min[1]; y<=max[1]; y++) {
						for(int x = min[0]; x<=max[0]; x++) {
							int neighbours = activeNeighbours(x, y, z, w, map);
							if(map[x][y][z][w]) {
								nmap[x][y][z][w] = neighbours==2 || neighbours==3;
							}else {
								nmap[x][y][z][w] = neighbours==3;
							}
							System.out.print(nmap[x][y][z][w]?'#':'.');
						}
						System.out.println();
					}
				}
			}
			map = nmap;
		}
		int count = 0;
		for(int w = min[3]; w<=max[3]; w++) {
			for(int z = min[2]; z<=max[2]; z++) {
				for(int y = min[1]; y<=max[1]; y++) {
					for(int x = min[0]; x<=max[0]; x++) {
						if(map[x][y][z][w])
							count++;
					}
				}
			}
		}
		
		System.out.println(count);
		br.close();
	}
	
	private static int activeNeighbours(int x, int y, int z, int w, boolean[][][][] map) {
		int count = 0;
		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				for(int k = -1; k<2; k++) {
					for(int l = -1; l<2; l++) {
						if(i==0 && j==0 && k==0 && l==0) continue;
						if(map[i+x][j+y][k+z][l+w]) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}
}
