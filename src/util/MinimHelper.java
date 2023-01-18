/* CLASS COMMENT:
 * This is a helper file class for loading mp3 audio by Minim library. 
 * This part of code was taken from the week 10 lab.
 */

package util;

import java.io.FileInputStream;
import java.io.InputStream;

public class MinimHelper {

    public String sketchPath( String fileName ) {
        return "assets/"+fileName;
    }

    public InputStream createInput(String fileName) {
        InputStream is = null;
        try{
            is = new FileInputStream(sketchPath(fileName));
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return is;
    }
}