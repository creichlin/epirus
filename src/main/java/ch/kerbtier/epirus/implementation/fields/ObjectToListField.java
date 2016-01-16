package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.epirus.implementation.EpirusPrimitiveListImplementation;
import ch.kerbtier.epirus.implementation.parents.Parent;
import ch.kerbtier.pogo.PogoList;
import ch.kerbtier.pogo.PogoObject;

public class ObjectToListField implements ObjectField {

  private ListEntity schema;
  private PogoList pogoValue;
  private EpirusPrimitiveListImplementation epirusValue = null;
  private Parent parent;
  
  public ObjectToListField(ListEntity schema, Parent parent, PogoList pogoValue) {
    this.schema = schema;
    this.parent = parent;
    this.pogoValue = pogoValue;
    
    if(pogoValue == null) {
      epirusValue = new EpirusPrimitiveListImplementation(schema, parent);
    } else {
      epirusValue = new EpirusPrimitiveListImplementation(schema, parent, pogoValue);
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
  public void write() {
    if(pogoValue == null) {
      parent.set(PogoList.class);
      pogoValue = parent.get(PogoList.class);
      epirusValue.setPogo(pogoValue);
    }
    epirusValue.writeFields();
  }
}
