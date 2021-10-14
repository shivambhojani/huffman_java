import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class getEncodedValues {

    public static HashMap<String, String> encodedvalues = new HashMap<String, String>();
    public static HashMap<String, String> adaptiveCodeBeforeRebuild = new HashMap<String, String>();

    public String printCode(huffmanNode root, String s) {
        if (root.left == null && root.right == null) {
            //System.out.println(root.c + "     |  " + s);
            encodedvalues.put(root.c, s);
            return s;
        }
        printCode(root.left, s + "0" );
        printCode(root.right,s + "1");
        return null;
    }

    public String codeBlock(huffmanNode root, String s) {
        if (root.left == null && root.right == null) {
            System.out.println(root.c + "     |  " + s);
            //encodedvalues.put(root.c, s);
            return s;
        }
        codeBlock(root.left, s + "0" );
        codeBlock(root.right,s + "1");
        return null;
    }


    public String storeCode(huffmanNode root, String s) {
        if (root.left == null && root.right == null) {
            System.out.println(root.c + "     |  " + s);
            //System.out.println("Map Size: " + adaptiveCodeBeforeRebuild.size());
            adaptiveCodeBeforeRebuild.put(root.c, s);
            //System.out.println(adaptiveCodeBeforeRebuild.values());
            return s;
        }
        storeCode(root.left, s + "0" );
        storeCode(root.right,s + "1");
        return null;
    }

    public String returnCode(String s){
        return adaptiveCodeBeforeRebuild.get(s);
    }

    public String getEncodedString(String s){
        encodedvalues.get("");
        String st = "";
        for(int i=0; i < s.length(); i++){
            Character c = s.charAt(i);
        st = st + encodedvalues.get(String.valueOf(c));
        }
        return st;
    }
}
