import java.io.*;

public class HuffmanInputStream {
	
	private String tree;
	private int totalChars;
	private DataInputStream d;
	
	public HuffmanInputStream(String filename) {
	
		try {
			d = new DataInputStream(new FileInputStream(filename));
			tree = d.readUTF();
			totalChars = d.readInt();
		}
		catch (IOException e) {
		
		}
	}
	
	int i = 8;
	int[] bits = new int[8];
	public int readBit() {
		//returns the next bit is the file

		try {
			//Read new byte
	        if (i == 8) {
	        	i = 0;
	        	int b = d.readUnsignedByte();
	            for (int j = 7; j>=0; j--) {
	                bits[j] = b&1;
	                b = b>>1;
	            }
	        }
			
	        int k = bits[i];
	        i++;
			
			return k; 
			
		
		}
		catch (IOException e) {
			return -1;
		}

	}
	
	public String getTree() {
		return tree;
	}
	
	public int getTotalChars() {
		//return the character count read from the file
		return totalChars;
	}
	
	public void close() {
		//close the DataInputStream
		try {
			d.close();
		}
		catch (IOException e) {
		
		}
	}

}