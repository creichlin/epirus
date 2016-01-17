package ch.kerbtier.epirus.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.EpirusContainer;
import ch.kerbtier.epirus.EpirusObject;
import ch.kerbtier.epirus.implementation.fields.JointObject;
import ch.kerbtier.epirus.implementation.fields.ObjectJoint;
import ch.kerbtier.pogo.PogoObject;

public class EpirusObjectImplementation extends EpirusContainerImplementation<JointObject> implements EpirusObject {

  private MapEntity schema;
  private PogoObject subject;
  
  private Map<String, ObjectJoint> fields = new HashMap<>();
  
  public EpirusObjectImplementation(MapEntity schema, JointObject parent) {
    super(parent);
    this.schema = schema;
  }

  public EpirusObjectImplementation(MapEntity schema, JointObject parent, PogoObject subject) {
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
  public void delete(String fieldName) {
    ObjectJoint field = getField(fieldName);
    field.delete();
  }
  
  // EpirusContainer interface
  
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

  @Override
  public void writeFields() {
    for(Map.Entry<String, ObjectJoint> entry: fields.entrySet()) {
      entry.getValue().write();
    }
  }

  public PogoObject getSubject() {
    return subject;
  }

  // Private parts

  private ObjectJoint getField(String fieldName) {
    ObjectJoint field = fields.get(fieldName);
    
    if(field == null) {

      field = ObjectJoint.create(this, schema.get(fieldName), subject);
      fields.put(fieldName, field);
    }
    return field;
  }

}
