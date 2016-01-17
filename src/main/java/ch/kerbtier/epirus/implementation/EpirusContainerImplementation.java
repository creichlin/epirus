package ch.kerbtier.epirus.implementation;

import ch.kerbtier.epirus.EpirusContainer;
import ch.kerbtier.epirus.implementation.parents.Parent;
import ch.kerbtier.pogo.Pogo;
import ch.kerbtier.pogo.PogoTransaction;

public abstract class EpirusContainerImplementation implements EpirusContainer {

  private Pogo pogo;
  private Parent parent;

  public EpirusContainerImplementation(Parent parent) {
    this.pogo = parent.getPogoRoot();
    this.parent = parent;
  }

  public Pogo getPogo() {
    return pogo;
  }
  
  public Parent getParent() {
    return parent;
  }
  
  abstract void writeFields();
  
  @Override
  public EpirusContainer commit() {
    PogoTransaction transaction = getPogo().start();
    writeFields();
    transaction.commit();
    return this;
  }
}
