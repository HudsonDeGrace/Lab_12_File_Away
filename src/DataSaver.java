import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
/**
 *
 * @author wulft
 *
 * Demonstrates how to use Java NIO, a thread safe File IO library
 * to write a text file
 */

/** I assume that I'm allowed to use this example program since it's readily available
 * and I didn't see anything against using it. - Hudson DeGrace
 */

public class DataSaver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        boolean done = false;
        String fileName = "";

        // Test data the lines of the file we will write
        ArrayList <String>recs = new ArrayList<>();
        do{
            String firstName = SafeInput.getNonZeroLenString(in, "What is your first name?");
            String lastName = SafeInput.getNonZeroLenString(in, "What is your last name?");
            String id = SafeInput.getRegExString(in, "What is your ID number?", "(\\d{6})");
            String email = SafeInput.getNonZeroLenString(in, "What is your email?");
            String yearOfBirth = SafeInput.getRegExString(in, "What is your birth year?", "(\\d{4})");

            recs.add(firstName + ", " + lastName + ", " + id + ", " + email + ", " + yearOfBirth);

            if(!SafeInput.getYNConfirm(in, "Do you want to continue?")){
                fileName = SafeInput.getNonZeroLenString(in, "What is the name of this file?");
                done = true;
            }
        }while(!done);



        // uses a fixed known path:
        //  Path file = Paths.get("c:\\My Documents\\data.txt");

        // use the toolkit to get the current working directory of the IDE
        // will create the file within the Netbeans project src folder
        // (may need to adjust for other IDE)
        // Not sure if the toolkit is thread safe...
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "/src/" + fileName + ".csv");
        // You don't need to use backslashes when using mac/linux
        // You should probably include this in the videos



        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
