package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.pogo.Pogo;
import ch.kerbtier.pogo.PogoObject;

public class ObjectToObjectField implements ObjectJoint, JointObject {

  private EpirusObjectImplementation parent;
  private MapEntity schema;
  private PogoObject pogoValue;
  private PogoObject toDelete;
  private EpirusObjectImplementation epirusValue = null;
  
  public ObjectToObjectField(MapEntity schema, EpirusObjectImplementation parent,  PogoObject pogoValue) {
    this.schema = schema;
    this.parent = parent;
    this.pogoValue = pogoValue;
    
    if(pogoValue == null) {
      epirusValue = new EpirusObjectImplementation(schema, this);
    } else {
      epirusValue = new EpirusObjectImplementation(schema, this, pogoValue);
    }
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
  public void write() {
    
    if(toDelete != null) {
      toDelete.delete();
    }
    
    if(pogoValue == null) {
      parent.getSubject().set(schema.getName(), PogoObject.class);
      pogoValue = parent.getSubject().get(schema.getName(), PogoObject.class);
      epirusValue.setPogo(pogoValue);
    }
    epirusValue.writeFields();
  }

  @Override
  public void delete() {
    // discard epirus node and create a new empty one
    epirusValue = new EpirusObjectImplementation(schema, this);
    
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
