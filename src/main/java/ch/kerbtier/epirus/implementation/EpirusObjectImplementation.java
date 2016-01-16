package ch.kerbtier.epirus.implementation;

import java.util.HashMap;
import java.util.Map;

import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.EpirusContainer;
import ch.kerbtier.epirus.EpirusObject;
import ch.kerbtier.epirus.implementation.fields.ObjectField;
import ch.kerbtier.epirus.implementation.parents.Parent;
import ch.kerbtier.pogo.PogoObject;
import ch.kerbtier.pogo.PogoTransaction;

public class EpirusObjectImplementation extends EpirusContainerImplementation implements EpirusObject {

  private MapEntity schema;
  private PogoObject subject;
  
  private Map<String, ObjectField> fields = new HashMap<>();
  
  public EpirusObjectImplementation(MapEntity schema, Parent parent) {
    super(parent);
    this.schema = schema;
  }

  public EpirusObjectImplementation(MapEntity schema, Parent parent, PogoObject subject) {
    this(schema, parent);
    this.subject = subject;
  }

  // EpirusObject interface 
  
  @Override
  public Object get(String field) {
    return getField(field).get();
  }

  @Override
  public void set(String field, Object value) {
    getField(field).set(value);
  }

  @Override
  public void delete(String field) {
    if(subject != null) {
      subject.delete(field);
    }
    fields.remove(field);
  }
  
  // EpirusContainer interface
  
  @Override
  public EpirusContainer commit() {
    PogoTransaction transaction = getPogo().start();
    writeFields();
    transaction.commit();
    return this;
  }

  @Override
  public EpirusContainer clear() {
    fields.clear();
    return this;
  }

  @Override
  public void delete() {
    getParent().delete();
  }
  
  // Implementation

  MapEntity getSchema() {
    return schema;
  }

  public void setPogo(PogoObject subject) {
    this.subject = subject;
  }

  public void writeFields() {
    for(Map.Entry<String, ObjectField> entry: fields.entrySet()) {
      entry.getValue().write();
    }
  }

  public PogoObject getSubject() {
    return subject;
  }

  // Private parts

  private ObjectField getField(String fieldName) {
    ObjectField field = fields.get(fieldName);
    
    if(field == null) {

      field = ObjectField.create(this, schema.get(fieldName), subject);
      fields.put(fieldName, field);
    }
    return field;
  }

}
