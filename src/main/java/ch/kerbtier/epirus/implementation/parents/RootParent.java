package ch.kerbtier.epirus.implementation.parents;

import ch.kerbtier.pogo.Pogo;

public class RootParent implements Parent {

  private Pogo pogo;
  
  public RootParent(Pogo pogo) {
    this.pogo = pogo;
  }
  
  @Override
  public Pogo getPogoRoot() {
    return pogo;
  }

  @Override
  public void set(Object value) {
    throw new AssertionError();
  }

  @Override
  public <T> T get(Class<T> type) {
    throw new AssertionError();
  }

  @Override
  public Object get() {
    throw new AssertionError();
  }

  @Override
  public void delete() {
    throw new AssertionError();
  }

  @Override
  public void deletePogo() {
    throw new AssertionError();
  }
}
