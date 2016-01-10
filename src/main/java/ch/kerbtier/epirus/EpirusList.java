package ch.kerbtier.epirus;

public interface EpirusList extends EpirusContainer, Iterable<Object> {
  Object get(int index);
}
