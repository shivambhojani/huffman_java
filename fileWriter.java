import java.io.FileWriter;
import java.io.IOException;

public class fileWriter {
    public void writeEncoded(String fileName, String s) throws IOException {
        // Writes the encoded string of 0s and 1s in a file with given name.

        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(s);
        myWriter.close();
    }

    public void writeDecoded(String fileName, String s) throws IOException {
        // Writes the decoded string in a file with given name.
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(s);
        myWriter.close();
    }
}
