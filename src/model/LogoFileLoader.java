/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reads pre-defined .logo files
 * 
 * @author Hunter
 *
 */
public class LogoFileLoader extends FileLoader {
    private static final int BRACKET_COUNT = 2;
    
    private String myLogoFile;
    private List<String> myCustomCommands;
    
    /**
     * Default constructor that sets the path & filename
     * 
     * @param filename
     */
    public LogoFileLoader (String filename) {
        myLogoFile = filename;
        myCustomCommands = new ArrayList<String>();
    }
    
    /**
     * Converts the contents of the file to a big String object
     * so that it could be passed to the parser
     * 
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String createString () throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(myLogoFile))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                stringAdder(sb, line);
                line = br.readLine();
            }
            String everything = sb.toString();
            return everything;
        }
    }
    
    /**
     * Used in conjunction with createString
     * Adds each word to the entire string
     * 
     * @param sb
     * @param line
     */
    private void stringAdder (StringBuilder sb, String line) {
        List<String> words = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
        words.forEach(word -> sb.append(word + " "));        
    }
    
    /**
     * Detect custom commands that start with "to" and run them prior to
     * execution of the entire command list to avoid error
     * 
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<String> registerCustomCommands () 
                                throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(myLogoFile))) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            String line = br.readLine();
            while (line != null) {
                List<String> words = new ArrayList<String>(
                                                Arrays.asList(line.split("\\s+")));
                for (String word : words) {
                    if (word.equals("to")) {
                        sb.append(word + " ");
                        count = BRACKET_COUNT;
                    }
                    else if (count > 0) {
                        sb.append(word + " ");
                        if (word.equals("]")) {
                            count--;
                            if (count == 0) {
                                myCustomCommands.add(sb.toString());
                                // Clear the StringBuilder
                                sb.setLength(0);
                            }
                        }
                    }
                }
                line = br.readLine();
            }
            return myCustomCommands;
        }
    }

    /**
     * @return the myLogoFile
     */
    public String getLogoFile () {
        return myLogoFile;
    }

    /**
     * @return the myCustomCommands
     */
    public List<String> getCustomCommands () {
        return myCustomCommands;
    }
    
    /**
     * Returns a list of commands consisting of custom commands and
     * the entire String appended at the end
     * 
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public List<String> getAllCommands () throws FileNotFoundException, IOException {
        myCustomCommands = registerCustomCommands();
        getCustomCommands().add(createString());
        return getCustomCommands();
    }

    /**
     * For unit testing only
     * 
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        LogoFileLoader l = new LogoFileLoader("examples/procedures/doubledash.logo");
        for (String s : l.registerCustomCommands()) {
            System.out.println(s);
        }
    }
}
