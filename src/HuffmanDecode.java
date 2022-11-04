import java.io.*;

public class HuffmanDecode {
	
	public HuffmanDecode(String in, String out) {
		//implements the Huffman Decode Algorithm
		HuffmanInputStream h = new HuffmanInputStream(in);
		String tree = h.getTree();
		int totalChars = h.getTotalChars();
		
		//build the tree
		HuffmanTree t = new HuffmanTree(tree, (char)128);

		//read the encoded file and write the decoded file
		try {
			DataOutputStream d = new DataOutputStream(new FileOutputStream(out));

			int j = 0;
			while(j < totalChars) {
				
				t.moveToRoot();
				
				while(!t.atLeaf()) {
					int value = h.readBit();
					if(value == 0) t.moveToLeft();
					else t.moveToRight();
				}
				
				d.writeByte(t.current());

				j++;
			}
				
			
			
			//close the streams
			h.close();
			d.close();
		}
		catch (IOException e) {
		
		}
		
		System.out.println("\nFinished Decoding");
	}
	
	public static void main(String args[]) {
		//args[0] is the name of a input file (a file created by Huffman Encode)
		//args[1] is the name of the output file for the uncompressed file
	
		new HuffmanDecode(args[0], args[1]);
	}
}