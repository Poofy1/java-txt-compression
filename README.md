# .TXT Compression

## Description

This program can encode and decode a txt file using a Huffman tree. Currently only supports .txt input file with UTF-8 charector codes between 0-128.

### Executing program

The Driver method accepts 3 arguments 
* args[0] accepts either "e" for encoding or "d" for decoding
* args[1] accepts an input file directory
* args[2] accepts an output file directory

For encoding, the input must be a .txt and output will be a binary file and vice versa for decoding



* Running encode examples
```
java Driver e input.txt decoded_file

java Driver d decoded_file output.txt
```


## License

This project is licensed under the MIT License - see the LICENSE.md file for details