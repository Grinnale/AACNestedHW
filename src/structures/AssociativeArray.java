package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  /**
   * Create a new array with given pairs, size.
   * @param pairs
   * @param size
   */
  public AssociativeArray(KVPair<K, V> pairs[], int size) {
    this.pairs = pairs;
    this.size = size;
  } // AssociativeArray

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    KVPair<K, V> cloned[] = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
    DEFAULT_CAPACITY);
    for(int i = 0; i < pairs.length; i++) {
      cloned[i] = pairs[i];
    }
    return new AssociativeArray(cloned, this.size);
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    if(size == 0) {
      return "{}";
    }
    else{
      String result = "{ " + pairs[0].key.toString() + ": " + pairs[0].value.toString();
      for(int i = 1; i < size; i++) {
        result += ", " + pairs[i].key.toString() + ": " + pairs[i].value.toString();
      }
      result += " }";
      return result;
    }
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) throws NullKeyException {
    if(key.equals(null)) {
      throw new NullKeyException();
    }
    else {
      KVPair<K, V> temp = new KVPair(key, value);
      try {
        int index = find(key);
        pairs[index] = temp;
      }
      catch (Exception e) {
        pairs[size] = temp;
        size++;
        if(size == pairs.length) {
          expand();
        }
      }
    }
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key is null or does not 
   *                              appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    try {
      int index = find(key);
      return pairs[index].value;
    }
    catch (Exception e) {
      throw new KeyNotFoundException();
    }
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */
  public boolean hasKey(K key) {
    try {
      find(key);
      return true;
    }
    catch (Exception e) {
      return false;
    }
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    try {
      int index = find(key);
      if(index != size - 1) {
        pairs[index] = pairs[size-1];
        size--;
      }
      else{
        pairs[index] = null;
        size--;
      }
    }
    catch (Exception e) {

    }
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  /**
   * Return the KVPair object in pairs at index i
   */
  public KVPair index(int i){
    return pairs[i];
  }

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K k) throws KeyNotFoundException {
    for(int i = 0; i < size; i++) {
      if(pairs[i].key.equals(k)) {
        return i;
      }
    }
    throw new KeyNotFoundException();
  } // find(K)

} // class AssociativeArray
