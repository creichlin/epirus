package ch.kerbtier.epirus;

public interface EpirusContainer {

  /**
   * commits all changes made to this node as well as changes to all children
   */

  EpirusContainer commit();
  
  /**
   * clears all data in memory on this object and below
   * 
   * any made and not commited changes are discarded
   * 
   * if accessed again all data must be fetched from database again
   */
  EpirusContainer clear();
  
  
  /**
   * deletes this node and all children
   * unlinks it from parent container
   */
  void delete();
}
