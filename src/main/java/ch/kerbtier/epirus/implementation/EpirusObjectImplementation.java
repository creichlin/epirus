package ch.kerbtier.epirus.implementation;

import java.util.HashMap;
import java.util.Map;

import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.EpirusContainer;
import ch.kerbtier.epirus.EpirusObject;
import ch.kerbtier.epirus.implementation.fields.JointObject;
import ch.kerbtier.epirus.implementation.fields.ObjectJoint;
import ch.kerbtier.pogo.PogoObject;

public class EpirusObjectImplementation extends EpirusContainerImplementation<JointObject> implements EpirusObject {

  private MapEntity schema;
  private PogoObject backend;
  private Map<String, ObjectJoint> fields = new HashMap<>();
  
  public EpirusObjectImplementation(MapEntity schema, JointObject parent, PogoObject backend) {
    super(parent);
    this.schema = schema;
    this.backend = backend;
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
    getField(field).delete();
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

  public void setPogo(PogoObject backend) {
    this.backend = backend;
  }

  @Override
  public void writeCommit() {
    for(Map.Entry<String, ObjectJoint> entry: fields.entrySet()) {
      entry.getValue().writeCommit();
    }
  }

  public PogoObject getBackend() {
    return backend;
  }

  // Private parts

  private ObjectJoint getField(String fieldName) {
    ObjectJoint field = fields.get(fieldName);
    
    if(field == null) {
      field = ObjectJoint.create(this, schema.get(fieldName), backend);
      fields.put(fieldName, field);
    }
    return field;
  }

}
