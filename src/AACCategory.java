import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.KVPair;
import structures.NullKeyException;

/**
 * Represents the mappings for a single page of items that should be displayed
 * 
 * @author Alexander Maret
 */

public class AACCategory {
  
  String name;
  AssociativeArray<String, String> category;

  /**
   * Creates a new empty category with the given name
   */
  public AACCategory(String name) {
    this.name = name;
    this.category = new AssociativeArray<String, String>();
  }

  /**
   * Adds the mapping of the imageLoc to the text to the category.
   */
  public void addItem(String imageLoc, String text) {
    try {
      this.category.set(imageLoc, text);
    }
    catch(Exception e) {

    }
  }

  /**
   * Returns the name of the category
   */
  public String getCategory() {
    return this.name;
  }

  /**
   * Returns an array of all the images in the category
   */
  public String[] getImages(){
    int size = this.category.size();
    String[] images = new String[size];
    for(int i = 0; i < size; i++ ) {
      images[i] = (String) this.category.index(i).key();
    }
    return images;
  }

  /**
   * Returns the text associated with the given image loc in this category
   */
  public String getText(String imageLoc){
    try {
      return this.category.get(imageLoc);
    }
    catch(Exception e) {
      return null;
    }
  }

  /**
   * Determines if the provided images is stored in the category
   */
  public boolean hasImage(String imageLoc) {
    return this.category.hasKey(imageLoc);
  }

  /**
   * Returns the associative array for this AACCategory
   */
  public AssociativeArray<String, String> getAA() {
    return category;
  }
}
