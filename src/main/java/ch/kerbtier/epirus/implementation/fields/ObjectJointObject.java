package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.pogo.Pogo;
import ch.kerbtier.pogo.PogoObject;

public class ObjectJointObject implements ObjectJoint, JointObject {

  private EpirusObjectImplementation parent;
  private MapEntity schema;
  private PogoObject pogoValue;
  private PogoObject toDelete;
  private EpirusObjectImplementation epirusValue = null;
  
  public ObjectJointObject(MapEntity schema, EpirusObjectImplementation parent,  PogoObject pogoValue) {
    this.schema = schema;
    this.parent = parent;
    this.pogoValue = pogoValue;
    
    epirusValue = new EpirusObjectImplementation(schema, this, pogoValue);
  }

  @Override
  public Object get() {
    return epirusValue;
  }

  @Override
  public void set(Object value) {
    throw new RuntimeException("cannot set object");
  }

  @Override
  public void writeCommit() {
    
    if(toDelete != null) {
      toDelete.delete();
    }
    
    if(pogoValue == null) {
      parent.getBackend().set(schema.getName(), PogoObject.class);
      pogoValue = parent.getBackend().get(schema.getName(), PogoObject.class);
      epirusValue.setPogo(pogoValue);
    }
    epirusValue.writeCommit();
  }

  @Override
  public void delete() {
    // discard epirus node and create a new empty one
    epirusValue = new EpirusObjectImplementation(schema, this, null);
    
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
