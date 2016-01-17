package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.pogo.Pogo;

public class RootJointObject implements JointObject {

  private Pogo root;
  
  public RootJointObject(Pogo root) {
    this.root = root;
  }

  @Override
  public Object get() {
    throw new AssertionError();
  }

  @Override
  public Pogo getPogoRoot() {
    return root;
  }

  @Override
  public void delete() {
    throw new AssertionError();
  }

}
