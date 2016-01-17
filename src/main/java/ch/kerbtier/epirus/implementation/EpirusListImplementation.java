package ch.kerbtier.epirus.implementation;

import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.epirus.EpirusList;
import ch.kerbtier.epirus.implementation.fields.JointList;
import ch.kerbtier.pogo.PogoList;

public abstract class EpirusListImplementation extends EpirusContainerImplementation<JointList> implements EpirusList {

  private ListEntity schema;
  private PogoList backend;

  public EpirusListImplementation(JointList parent, ListEntity schema, PogoList backend) {
    super(parent);
    this.schema = schema;
    this.backend = backend;
  }

  public PogoList getBackend() {
    return backend;
  }

  public void setBackend(PogoList backend) {
    this.backend = backend;
  }

  public ListEntity getSchema() {
    return schema;
  }
}
