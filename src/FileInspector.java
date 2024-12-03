
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Tom Wulf Tom.Wulf@uc.edu
 */

/** I assume that I'm allowed to use this example program since it's readily available
 * and I didn't see anything against using it. - Hudson DeGrace
 */
public class FileInspector
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        int lines = 0;
        int lineChar = 0;
        int totalChar = 0;
        String[] words;
        int numWords = 0;
        int totalWords = 0;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        // set the chooser to the project src directory
        chooser.setCurrentDirectory(target.toFile());

        try  // Code that might trigger the exception goes here
        {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                inFile = new Scanner(target);

                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();
                    words = line.split(" ");
                    numWords = words.length;
                    lineChar = line.length();
                    System.out.println(line);
                    totalWords = totalWords + numWords;
                    totalChar = lineChar + totalChar;
                    lines++;
                }

                System.out.println(target);
                System.out.println(lines);
                System.out.println(totalWords);
                System.out.println(totalChar);

                inFile.close();
            }
            else   // User did not pick a file, closed the chooser
            {
                System.out.println("Sorry, you must select a file! Termininating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) // code to handle this exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }

}
