//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//public class main {
//
//    public static void main(String[] args) throws FileNotFoundException {
//
//        String filename = "abacadb";
////        Scanner userInputFile = new Scanner(System.in);
////        System.out.println("Enter file name with path");
////        filename = userInputFile.next();
//        if (filename != null && filename.trim().length() != 0) {
//            Huffman en = new Huffman();
//            en.encode(filename);
//        }
//
//    }   }
////            System.out.println(a.length());
////            for (char c : ch) {
////                System.out.println(c);
//
//
// Huffman Coding in Java

import com.sun.source.tree.Tree;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class main{
public static void main(String[] args) throws IOException {
    //Display to user, for choosing particular command.
    System.out.println("User Input: ");
    System.out.println("1. Encode File: ");
    System.out.println("2. Decode File: ");
    System.out.println("3. Print CodeBook");
    System.out.println("4. quit");
    Scanner userInputFile = new Scanner(System.in);
    int choice = 0;
    huffmanNode root = null;
    do{
        choice = userInputFile.nextInt();

        Huffman ah = new Huffman();
        Adaptive_huffman AdH = new Adaptive_huffman();
        int n = choice;
        if(choice==1){
            // When user chooses to encode a file
            // Here code will take file name which needs to be encoded and the file where the encoded bits is to be stored
            System.out.println("File to encode Data: ");
            String filename = userInputFile.next();
            System.out.println("File to store encoded Data: ");
            String encodeOutput = userInputFile.next();

            if (filename != null && filename.trim().length() != 0) {
                ah.encode(filename, 1, false, encodeOutput);
                //AdH.encode(filename, 1, false, filename);
            }
        }
        else if (choice == 2){
            // When user chooses to decode the encoded file
            // Here code will take file name which needs to be decode and the file where the decoded data is to be stored
            System.out.println("Filename to decode Data: ");
            String encodeOutput1 = userInputFile.next();
            System.out.println("Filename to store Decoded Data: ");
            String decodedOutput = userInputFile.next();
            ah.decode(encodeOutput1,decodedOutput);
        }
        else if (choice == 3){
            //This option will be triggred when user wants to print the code book
            ah.printCodeBlock();
        }
    }while (choice !=4);
    userInputFile.close();
}
}