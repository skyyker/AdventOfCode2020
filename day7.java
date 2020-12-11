import java.io.*;
import java.util.*;
import java.util.Map.Entry;

//#1 24min
//#2 36min

public class day7 {
	static String file = "./inputs/day7.txt";
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		Map<String, Node> nodes = new HashMap<>();
		while ((line = br.readLine()) != null) {
			String[]s = line.split(" bags contain");
			String color = s[0];
			Node n = nodes.containsKey(color) ? nodes.get(color) : new Node(color);
			nodes.put(color, n);
			String[] sons = s[1].split(",");
			for(String son : sons) {
				if(son.equals(" no other bags."))
					continue;
				int sonNumber = Integer.parseInt(son.substring(1,2));
				//System.out.println(sonNumber);
				String sonColor = son.substring(3, son.lastIndexOf(" "));
				Node nSon = nodes.containsKey(sonColor) ? nodes.get(sonColor) : new Node(sonColor);
				nodes.put(sonColor, nSon);
				nSon.fathers.add(n);
				n.sons.add(nSon);
				n.sonsNum.put(nSon, sonNumber);
			}
		}
		Node shinyGold = nodes.get("shiny gold");
//		int nbParents = 0, prevNbParents = -1;
//		Set<Node> parents = new HashSet<>();
//		Set<Node> toCompute = new HashSet<>();
//		toCompute.add(shinyGold);
//		while(!toCompute.isEmpty()) {
//			Set<Node> newToCompute = new HashSet<>();
//			for(Node s : toCompute) {
//				parents.addAll(s.fathers);
//				newToCompute.addAll(s.fathers);
//			}
//			prevNbParents = nbParents;
//			nbParents = parents.size();
//			toCompute = newToCompute;
//			if(prevNbParents==nbParents) {
//				System.out.println("Break, remains "+toCompute);
//				break;
//			}
//		}
		int bagNumber = computeBagNumber(shinyGold, 1);
		
		//ShinyGold not counted
		bagNumber--;
		System.out.println(bagNumber);
		br.close();
	}
	
	public static int computeBagNumber(Node bag, int number) {
		if(bag.sons.isEmpty())
			return number;
		int sum = number;
		for(Entry<Node, Integer> e : bag.sonsNum.entrySet()) {
			sum += computeBagNumber(e.getKey(), e.getValue()*number);
		}
		return sum;
	}
	
	public static class Node{
		public Set<Node> fathers = new HashSet<>();
		public Set<Node> sons = new HashSet<>();
		public Map<Node, Integer> sonsNum = new HashMap<>();
		String color;
		public Node(String color) {
			this.color = color;
		}
		@Override
		public String toString() {
			return color;
		}
	}
}
