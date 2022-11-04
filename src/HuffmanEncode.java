import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class HuffmanEncode {
	public HuffmanEncode(String in, String out) {
		
		//read the file and count the frequencies
		int count = 0;
		int[] frequencies = new int[128];
		try {
			BufferedReader f = new BufferedReader(new FileReader(in));
			int value = f.read();
			while(value != -1) {
				try {
					frequencies[value]++;
				}
				catch(Exception e) {
					System.out.println("Incompatible character code: " + value + "\nOnly input UTF-8 chars between 0-128");
					System.exit(0);
				}
				value = f.read();
				count++;
			}
			f.close();
			
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
		//build the heap
		BinaryHeap heap = new BinaryHeap(128);
		for(int i = 0; i < frequencies.length; i++) {
			if(frequencies[i] != 0) {
				HuffmanTree tree = new HuffmanTree((char)i);
				heap.insert(frequencies[i], tree);
			}
		}
		
		//build the tree
		HuffmanTree tree = null;
		while(heap.getSize() > 1) {
			HuffmanTree tree1 = heap.getMinTree();
			int priority1 = heap.getMinPriority();
			heap.removeMin();
			HuffmanTree tree2 = heap.getMinTree();
			int priority2 = heap.getMinPriority();
			heap.removeMin();
			tree = new HuffmanTree(tree1, (char)128, tree2);
			heap.insert(priority1 + priority2, tree);
		}
		
		//Debug: show tree
		//System.out.println("Tree: "+ tree.toString());
		
		//get the paths to the leaves
		String[] paths = heap.getMinTree().pathsToLeaves();
		
		//encode input into string
		int j = 0;
		HuffmanOutputStream outStream = new HuffmanOutputStream(out, tree.toString(), count);
		try {
			BufferedReader f = new BufferedReader(new FileReader(in));
			int value = f.read();
			while(value != -1) {
				for(int i = 0; i < paths[value].length(); i++) {
					outStream.writeBit(paths[value].charAt(i));
					j++;
				}
				value = f.read();
			}
			f.close();
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		//write the remaining bits
		while(j % 8 != 0) {
			outStream.writeBit('0');
	        j++;
		}
		
		
		//close the streams
		outStream.close();
		
		System.out.println("\nFinished Encoding");
	}

	public static void main(String args[]) {
		//args[0] is the name of the source file
		//args[1] is the name of the output file
		new HuffmanEncode(args[0], args[1]);
	}
}