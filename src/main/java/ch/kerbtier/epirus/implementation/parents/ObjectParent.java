package ch.kerbtier.epirus.implementation.parents;

import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.pogo.Pogo;

public class ObjectParent implements Parent {

  private EpirusObjectImplementation parent;
  private String field;
  
  public ObjectParent(EpirusObjectImplementation parent, String field) {
    this.parent = parent;
    this.field = field;
  }

  @Override
  public Pogo getPogoRoot() {
    return parent.getPogo();
  }

  @Override
  public void set(Object value) {
    parent.getSubject().set(field, value);
  }

  @Override
  public <T> T get(Class<T> type) {
    return parent.getSubject().get(field, type);
  }

  @Override
  public Object get() {
    return parent.getSubject().get(field);
  }

  @Override
  public void delete() {
    parent.delete(field);
  }

}
