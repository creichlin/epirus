package ch.kerbtier.epirus;

public interface EpirusList extends EpirusContainer {
  Object get(int index);
  
  int size();
  
  void delete(int index);
}
