import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Huffman {
    static huffmanNode root = null;
    private TreeSet<huffmanNode> tree;
    Character[] ch = null;
    String str = "";
    public static String EncodedString = "";
    String DecodedString = "";

    public boolean encode(String input_filename, int level, boolean reset, String output_filename) throws IOException {
        try {
            //This try catch block is to catch the file not found exception
            File f = new File(input_filename);
            Scanner sc = new Scanner(f);
            if (f.length() != 0) {
                str = sc.nextLine();
                while (sc.hasNextLine()) {
                    str = str + "\n" + sc.nextLine();        // reading each line from the file
                }
                Character[] ch = new Character[str.length()];
                Map<String, huffmanNode> map = new HashMap<String, huffmanNode>();
                for (int i = 0; i < str.length(); i++) { // This loop is store each character data in hashmap
                    ch[i] = str.charAt(i);
                    if (map.size() == 0) { // If Hashap is emtpy, then directly put the first character with freq 1
                        huffmanNode hufNode = new huffmanNode(String.valueOf(ch[i]), 1);
                        map.put(String.valueOf(ch[i]), hufNode);
                    } else if (map.containsKey(String.valueOf(ch[i]))) { // if Hashmap contains the particular character as key then just increase the freq.
                        map.get(String.valueOf(ch[i])).freq = map.get(String.valueOf(ch[i])).freq + 1;
                        map.put(String.valueOf(ch[i]), map.get(String.valueOf(ch[i])));
                    } else {// if hashmap does not contain that particular character data then make a new entry in hashmap
                        huffmanNode hufNode = new huffmanNode(String.valueOf(ch[i]), 1);
                        map.put(String.valueOf(ch[i]), hufNode);
                    }
                }
                TreeSet<huffmanNode> tree = new TreeSet<huffmanNode>(new sortMap()); // Treeset will call a comparator class which will sort the data for us
                while (map.size() != 0) {
                    // Here, we are entering each and every data from Hashmap to Treeset
                    getSmallest gs = new getSmallest(); // getting data which has smallest frequency from hashmap
                    String c1 = gs.getSmallest(map);
                    //.out.println("Data added to Tree: "+map.get(c1).c + " " + map.get(c1).freq);
                    tree.add(new huffmanNode(map.get(c1).c, map.get(c1).freq)); // adding the data in treeset, but it will sorted by sortMap class before entering it into tree
                    map.remove(c1);
                }

                while (tree.size() > 1) {
                    huffmanNode x = tree.first(); // Creating new left node
                    tree.pollFirst();
                    huffmanNode y = tree.first(); // Creating new right node
                    tree.pollFirst();
                    String a = x.c + "" + y.c;
                    huffmanNode h = new huffmanNode(a, x.freq + y.freq); // Creating new root node
                    h.left = x;
                    h.right = y;
                    root = h; // Assigning root variable to newly created node
                    tree.add(h);
//            System.out.println("------------------------------------------------");
//            for (huffmanNode hf : tree) {
//
//                System.out.println("Character "+ hf.c + "Frequency " + hf.freq);
//            }
                }
                getEncodedValues ge = new getEncodedValues();
                ge.printCode(root, "");
                EncodedString = ge.getEncodedString(str); // this will give us the full encoded string in 0 and 1
                fileWriter fw = new fileWriter();
                fw.writeEncoded(output_filename, EncodedString); //writing it into seperate file
                return true;
            }
        }catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return false;
    }

    boolean decode(String input_filename, String output_filename) throws IOException {
        String enstring="";
        File f = new File(input_filename);
        Scanner sc = new Scanner(f);
        //String str = sc.nextLine();
        if (f.length() != 0) {
            enstring = sc.nextLine();
            while (sc.hasNextLine()) {
                enstring = enstring + "\n" + sc.nextLine(); //Getting 0 and 1 encoded values in a string
            }
                huffmanNode d = root;
                //System.out.println(enstring);
                for (int i = 0; i < enstring.length(); i++) { // Decoding started by reading 0 and 1 in the given sequence
                    char c = enstring.charAt(i);
                    if (d.right == null && d.left == null) {
                        DecodedString = DecodedString + d.c;
                        d = root;
                    } else if (c == '0') {
                        d = d.left;
                        if (d.right == null && d.left == null) {
                            DecodedString = DecodedString + d.c;
                            d = root;
                        }
                    } else {
                        d = d.right;
                        if (d.right == null && d.left == null) {
                            DecodedString = DecodedString + d.c;
                            d = root;
                        }
                    }
                }
                //System.out.println(DecodedString);
                fileWriter fw = new fileWriter();
                fw.writeEncoded(output_filename, DecodedString);
                return true;
        }
        return false;
    }
    public void printCodeBlock(){ // This method will be called when user wants to print code book in the terminal
        System.out.println(" Char | Huffman code ");
        getEncodedValues g = new getEncodedValues();
        g.codeBlock(root,"");
}


    class sortMap implements Comparator<huffmanNode> {
        @Override
        public int compare(huffmanNode h1, huffmanNode h2) {
            String a = h1.c;
            String b = h2.c;
            int f1 = h1.freq;
            int f2 = h2.freq;
            if (f1 > f2) {
                return 1;
            } else if (f1 == f2) {
                if (a.charAt(0) > b.charAt(0)) {
                    return 1;
                }
                return -1;
            } else {
                return -1;
            }
        }
    }
}


