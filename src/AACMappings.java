import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.KVPair;
import structures.NullKeyException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Holds all AACCategory objects.
 * @author Alexander Maret
 */

public class AACMappings {
  
  AACCategory current;
  AACCategory generic;

  /**
   * Creates a new mapping with categories as specified in filename
   */
  public AACMappings(String filename) {
    this.generic = new AACCategory("");
    this.current= this.generic;
    /*
    try{
      BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(filename));
      String line = br.readLine();
      while(line != null) {
        int space = line.indexOf(" ");
        if(line.charAt(0) == '>') {
          this.current.addItem(line.substring(1, space), line.substring(space + 1));
        }
        else{
          this.current = new AACCategory(line.substring(space + 1));
          this.generic.addItem(line.substring(0, space), line.substring(space + 1));
        }
        line = br.readLine();
      }
      this.current = generic;
    }
    catch(Exception e){

    }
    */
    File f = new File(/*~/csc207/labs/MP5/AACNestedHW/AACMappings.txt" */ filename);
    try{
      Scanner scan = new Scanner(f);
      String line = scan.nextLine();
      while(line != null) {
        System.out.println(line);
        int space = line.indexOf(" ");
        if(line.charAt(0) == '>') {
          this.current.addItem(line.substring(1, space), line.substring(space + 1));
        }
        else{
          this.current = new AACCategory(line.substring(space + 1));
          this.generic.addItem(line.substring(0, space), line.substring(space + 1));
        }
        line = scan.nextLine();
      }
      this.current = generic;
      System.out.println("test");
      scan.close();
    }
    catch(FileNotFoundException e){
      System.out.println(e.getMessage());
    }

    //System.out.println(this.getImageLocs()[0]);
  }

  /**
   * Adds the mapping to the current category (or the default category if that is the current category)
   */
  public void add(String imageLoc, String text) {
    this.current.addItem(imageLoc, text);
  }

  /**
   * Gets the current category
   */
  public String getCurrentCategory() {
    return this.current.getCategory();
  }

  /**
   * Provides an array of all the images in the current category
   */
  public String[] getImageLocs() {
    return this.current.getImages();
  }

  /**
   * Given the image location selected, it determines the associated text with the image.
   */
  public String getText(String imageLoc) {
    return this.current.getText(imageLoc);
  }

  /**
   * Determines if the image represents a category or text to speak
   */
  public boolean isCategory(String imageLoc) {
    return this.current.hasImage(imageLoc);
  }

  /**
   * Resets the current category of the AAC back to the default category
   */
  public void reset() {
    this.current = this.generic;
  }

  /**
   * Writes the ACC mappings stored to a file.
   */
  public void writeToFile(String filename) {
    //STUB
  }

}
