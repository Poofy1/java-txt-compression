
public class Driver {

	public static void main(String[] args) {
		//args[0] e = encoding, d = decoding
		//args[1] is the name of a input file (txt for encoding, binary for decoding)
		//args[2] is the name of the output file (outputs binary for encoding, outputs txt for decoding)
	
		if(args[0].equals("e")) {
			new HuffmanEncode(args[1], args[2]);
		}
		else if(args[0].equals("d")) {
			new HuffmanDecode(args[1], args[2]);
		}
		
		
	}

}
