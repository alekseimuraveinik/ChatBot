package datasource;

import java.io.*;

public class FileReader extends BufferedReader {
    private static final String fileEncoding = "UTF-8";

    public FileReader(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        super(new InputStreamReader(new FileInputStream(new File(filename)), fileEncoding));
    }
}
