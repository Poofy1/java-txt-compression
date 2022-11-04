import java.io.*;

public class HuffmanOutputStream {


	private DataOutputStream d;
	public HuffmanOutputStream(String filename, String tree, int totalChars) {
		
		try {
		d = new DataOutputStream(new FileOutputStream(filename));
		d.writeUTF(tree);
		d.writeInt(totalChars);
		
		}
		catch (IOException e) {
		}
	}

	int j = 0;
	int b = 0;
	public void writeBit(char bit) {
		//Converts 8 bits into a byte and writes the byte to the file
		try {
			b = (b << 1) | (bit - '0'); 
            j++;
            
			if(j == 8) {
				d.writeByte(b);
				j = 0;
				b = 0;
			}
		
		}
		catch (IOException e) {
		
		}
	
	}
	
	public void close() {
		//write final byte (if needed);
		//close the DataOutputStream
		try {
			d.close();
		}
		catch (IOException e) {
		
		}
	
	} 
	
}