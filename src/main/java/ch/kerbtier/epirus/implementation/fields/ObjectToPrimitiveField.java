package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.schema.Entity;
import ch.kerbtier.epirus.WrongFieldType;
import ch.kerbtier.epirus.implementation.parents.Parent;

public class ObjectToPrimitiveField implements ObjectField {

  private Entity entity;
  private Object pogoValue;
  private Object newValue;
  private boolean valueWritten = false;
  private boolean valueDeleted = false;
  private Parent parent;
  
  public ObjectToPrimitiveField(Entity entity, Parent parent, Object pogoValue) {
    this.entity = entity;
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
    if(value != null && !entity.is(value.getClass())) {
      throw new WrongFieldType("trying to set " + value.getClass() + " on field with type " + entity);
    }
    newValue = value;
    valueWritten = true;
    valueDeleted = false;
  }

  @Override
  public void write() {
    if(valueWritten) {
      parent.set(newValue);
      pogoValue = parent.get();
      valueWritten = false;
    } else if(valueDeleted) {
      parent.deletePogo();
      valueDeleted = false;
    }
  }

  @Override
  public void delete() {
    valueWritten = true;
    valueDeleted = true;
    newValue = null;
  }
}
