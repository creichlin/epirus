package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.pogo.Pogo;

public interface Joint {

  /**
   *  returns currently active value
   */
  Object get();

  /**
   * returns pogo root of this tree 
   */
  Pogo getPogoRoot();

  /**
   * delegates container.delete() call to parent.delete(fieldName) 
   */
  void delete();

}
