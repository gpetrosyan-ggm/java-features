package general.lombok;

import java.io.*;
import java.util.ArrayList;

import lombok.Cleanup;
import lombok.val;

public class MixDemo {

    public String valExample() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        return foo.toLowerCase();
    }

    public String varExample() {
        var example = new ArrayList<String>();
        example.add("Hello, World!");
        var foo = example.get(0);
        return foo.toLowerCase();
    }

    public void cleanupDemo() throws IOException {
        @Cleanup InputStream in = new FileInputStream("file.txt");
        @Cleanup OutputStream out = new FileOutputStream("file.txt");
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) {
                break;
            }
            out.write(b, 0, r);
        }
    }

}
