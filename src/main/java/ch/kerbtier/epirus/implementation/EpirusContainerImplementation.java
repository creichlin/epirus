package ch.kerbtier.epirus.implementation;

import ch.kerbtier.epirus.EpirusContainer;
import ch.kerbtier.epirus.implementation.fields.Joint;
import ch.kerbtier.pogo.Pogo;
import ch.kerbtier.pogo.PogoTransaction;

public abstract class EpirusContainerImplementation<JOINT_TYPE extends Joint> implements EpirusContainer {

  private Pogo pogo;
  private JOINT_TYPE parent;

  public EpirusContainerImplementation(JOINT_TYPE parent) {
    this.pogo = parent.getPogoRoot();
    this.parent = parent;
  }

  public Pogo getPogo() {
    return pogo;
  }
  
  public JOINT_TYPE getParent() {
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
