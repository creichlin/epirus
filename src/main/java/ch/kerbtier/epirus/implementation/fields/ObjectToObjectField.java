package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.epirus.implementation.parents.Parent;
import ch.kerbtier.pogo.PogoObject;

public class ObjectToObjectField implements ObjectField {

  private MapEntity schema;
  private PogoObject pogoValue;
  private EpirusObjectImplementation epirusValue = null;
  private Parent parent;
  
  public ObjectToObjectField(MapEntity schema, Parent parent, PogoObject pogoValue) {
    this.schema = schema;
    this.parent = parent;
    this.pogoValue = pogoValue;
    
    if(pogoValue == null) {
      epirusValue = new EpirusObjectImplementation(schema, parent);
    } else {
      epirusValue = new EpirusObjectImplementation(schema, parent, pogoValue);
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
    if(pogoValue == null) {
      parent.set(PogoObject.class);
      pogoValue = parent.get(PogoObject.class);
      epirusValue.setPogo(pogoValue);
    }
    epirusValue.writeFields();
  }
}
