import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.KVPair;
import structures.NullKeyException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;


/**
 * Holds all AACCategory objects.
 * @author Alexander Maret
 */

public class AACMappings {
  
  AACCategory current;
  AACCategory generic;
  AssociativeArray<String, AACCategory> categories; //associates the imageLoc with the category

  /**
   * Creates a new mapping with categories as specified in filename
   */
  public AACMappings(String filename) {
    this.generic = new AACCategory("");
    this.current = this.generic;
    this.categories = new AssociativeArray<String, AACCategory>();
    
    File f = new File(/*~/csc207/labs/MP5/AACNestedHW/AACMappings.txt" */ filename);
    try{
      Scanner scan = new Scanner(f);

      while(scan.hasNextLine()) {
        String line = scan.nextLine();
        System.out.println(line);
        int space = line.indexOf(" ");
        if(line.charAt(0) == '>') {
          this.current.addItem(line.substring(1, space), line.substring(space + 1));
        }
        else{
          this.current = new AACCategory(line.substring(space + 1));
          this.categories.set(line.substring(0, space), this.current);
          this.generic.addItem(line.substring(0, space), line.substring(space + 1));
        }
      }

      this.current = generic;
      scan.close();
    }
    catch(Exception e){
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
   * Given the image location selected, it determines the associated text with the image. If the image provided is a category, it also updates the AAC's current category to be the category associated with that image
   */
  public String getText(String imageLoc) {
    if(isCategory(imageLoc)) {
      try{
        this.current = this.categories.get(imageLoc);
      }
      catch(Exception e){}
    }
    return this.current.getText(imageLoc);
  }

  /**
   * Determines if the image represents a category or text to speak
   */
  public boolean isCategory(String imageLoc) {
    return this.generic.hasImage(imageLoc);
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
    try{
      File nFile = new File(filename);
      nFile.createNewFile();
      FileWriter fw = new FileWriter(nFile);

      for(int i = 0; i < this.categories.size(); i++) {
        String imgLoc = (String) this.categories.index(i).key();
        fw.write(imgLoc + " " + this.generic.getText(imgLoc));
        System.out.println(imgLoc + " " + this.generic.getText(imgLoc));
      }
    }
    catch(Exception e){}
  }
}
