package ch.kerbtier.epirus.implementation.parents;

import ch.kerbtier.pogo.Pogo;

public interface Parent {

  Pogo getPogoRoot();

  void set(Object value);

  <T> T get(Class<T> type);

  Object get();

  void delete();

  void deletePogo();

}
