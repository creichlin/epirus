package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.schema.Entity;
import ch.kerbtier.epirus.WrongFieldType;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.pogo.Pogo;

public class ObjectJointPrimitive implements ObjectJoint, JointPrimitive {

  private Entity schema;
  private EpirusObjectImplementation parent;
  private Object pogoValue;
  private Object newValue;
  private boolean valueWritten = false;
  private boolean valueDeleted = false;
  
  public ObjectJointPrimitive(Entity schema, EpirusObjectImplementation parent, Object pogoValue) {
    this.schema = schema;
    this.parent = parent;
    this.pogoValue = pogoValue;
  }

  @Override
  public Object get() {
    if(valueWritten) {
      return newValue;
    }
    return pogoValue;
  }

  @Override
  public void set(Object value) {
    if(value != null && !schema.is(value.getClass())) {
      throw new WrongFieldType("trying to set " + value.getClass() + " on field with type " + schema);
    }
    newValue = value;
    valueWritten = true;
    valueDeleted = false;
  }

  @Override
  public void write() {
    if(valueWritten) {
      parent.getSubject().set(schema.getName(), newValue);
      pogoValue = parent.getSubject().get(schema.getName());
      valueWritten = false;
    } else if(valueDeleted) {
      parent.getSubject().delete(schema.getName());
      valueDeleted = false;
    }
  }

  @Override
  public void delete() {
    valueWritten = true;
    valueDeleted = true;
    newValue = null;
  }

  @Override
  public Pogo getPogoRoot() {
    return parent.getPogo();
  }
}
