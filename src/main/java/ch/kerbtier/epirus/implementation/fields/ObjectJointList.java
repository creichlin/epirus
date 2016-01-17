package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.epirus.implementation.EpirusPrimitiveListImplementation;
import ch.kerbtier.pogo.Pogo;
import ch.kerbtier.pogo.PogoList;

public class ObjectJointList implements ObjectJoint, JointList {

  private ListEntity schema;
  private PogoList pogoValue;
  private PogoList toDelete = null;
  
  private EpirusObjectImplementation parent = null;
  private EpirusPrimitiveListImplementation epirusValue = null;
  
  public ObjectJointList(ListEntity schema, EpirusObjectImplementation parent, PogoList pogoValue) {
    this.schema = schema;
    this.parent = parent;
    this.pogoValue = pogoValue;
    
    if(pogoValue == null) {
      epirusValue = new EpirusPrimitiveListImplementation(schema, this, null);
    } else {
      epirusValue = new EpirusPrimitiveListImplementation(schema, this, pogoValue);
    }
  }

  @Override
  public Object get() {
    return epirusValue;
  }

  @Override
  public void set(Object value) {
    throw new RuntimeException("cannot set list");
  }

  @Override
  public void writeCommit() {
    if(toDelete != null) {
      toDelete.delete();
    }
    
    if(pogoValue == null) {
      parent.getBackend().set(schema.getName(), PogoList.class);
      pogoValue = parent.getBackend().get(schema.getName(), PogoList.class);
      epirusValue.setBackend(pogoValue);
    }
    epirusValue.writeCommit();
  }

  @Override
  public void delete() {
    // discard epirus node and create a new empty one
    epirusValue = new EpirusPrimitiveListImplementation(schema, this, null);
    
    if(pogoValue != null) {
      // store old pogo node so we can delete it on commit
      toDelete = pogoValue;
      pogoValue = null;
    }
  }

  @Override
  public Pogo getPogoRoot() {
    return parent.getPogo();
  }
}
