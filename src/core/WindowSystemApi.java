package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neeraj
 */
public class WindowSystemApi
{
    /**
     * This method requires to be executed Under administrator Privileges, Else it wil returns always false
     * even if the System is using NTFS fileSystem.<br>
     * Method to check is the underlying FileSystem is NTFS or not, this method can only be useful for
     * Microsoft Windows x86,x64 bit Systems (Ver. 7 , 8.1, 10 all variants), using on another
     * Operating Systems may not work.
     *
     * @return True only if the underlying core fileSystem of the main drive is NTFS otherwise false.
     */
    public static boolean isNTFS()
    {
        StringBuilder brBuilder = new StringBuilder();
        try
        {
            String operation = "fsutil fsinfo volumeinfo C:";
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(operation);
            pr.waitFor();
            if(pr.exitValue() != 0)
            {
                System.out.println("Program Requires Administrator Privileges");
            }
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while((line = input.readLine()) != null)
            {
                brBuilder.append(line);
            }
            String bkSpace = "\n";/*
//            brBuilder.insert(14, bk);
//            brBuilder.insert(48, bk);
//            brBuilder.insert(75, bk);
//            brBuilder.delete(99, brBuilder.length());*/
        }
        catch(IOException | InterruptedException ex)
        {
            Logger.getLogger(WindowSystemApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  String isNTFS = brBuilder.delete(100, brBuilder.length()).toString().toUpperCase();
        String arr[] = brBuilder.toString().split("\\s+");
        // return isNTFS.contains("NTFS");
        return Arrays.toString(arr).toUpperCase().contains("NTFS");
    }
}
