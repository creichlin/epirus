package ch.kerbtier.epirus.implementation;

import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.epirus.EpirusList;
import ch.kerbtier.epirus.implementation.parents.Parent;
import ch.kerbtier.pogo.PogoList;

public abstract class EpirusListImplementation extends EpirusContainerImplementation implements EpirusList {

  private ListEntity schema;
  private PogoList subject;

  public EpirusListImplementation(Parent parent, ListEntity schema, PogoList subject) {
    super(parent);
    this.schema = schema;
    this.subject = subject;
  }

  
  // implementation

  public PogoList getSubject() {
    return subject;
  }

  public void setPogo(PogoList subject) {
    this.subject = subject;
  }
}
