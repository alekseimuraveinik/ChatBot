package datasource;

import java.io.*;

public class FileReader extends BufferedReader {
    public FileReader(String filename, String fileEncoding) throws FileNotFoundException, UnsupportedEncodingException {
        super(new InputStreamReader(new FileInputStream(new File(filename)), fileEncoding));
    }
}
