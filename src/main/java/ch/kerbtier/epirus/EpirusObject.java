package ch.kerbtier.epirus;

import ch.kerbtier.pogo.PogoObject;

public interface EpirusObject extends EpirusContainer {
  
  Object get(String field);
  
  void set(String field, Object value);
  
  /**
   * deletes given field, if it contains a container it deletes it recursively
   */
  void delete(String field);
}
