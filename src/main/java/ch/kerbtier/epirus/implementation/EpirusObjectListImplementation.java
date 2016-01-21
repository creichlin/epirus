package ch.kerbtier.epirus.implementation;

import java.util.ArrayList;
import java.util.List;

import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.EpirusObject;
import ch.kerbtier.epirus.EpirusObjectList;
import ch.kerbtier.epirus.implementation.fields.JointList;
import ch.kerbtier.epirus.implementation.fields.ListJointObject;
import ch.kerbtier.pogo.PogoList;
import ch.kerbtier.pogo.PogoObject;

public class EpirusObjectListImplementation extends EpirusListImplementation implements EpirusObjectList {

  private List<ListJointObject> elements = new ArrayList<>();
  
  public EpirusObjectListImplementation(ListEntity schema, JointList parent, PogoList subject) {
    super(parent, schema, subject);
    
    if(getBackend() != null) {
      loadFromBackend();
    }
  }

  @Override
  public Object get(int index) {
    return elements.get(index).get();
  }

  @Override
  public EpirusObjectList clear() {
    elements.clear();
    loadFromBackend();
    return this;
  }

  @Override
  public void delete() {
    getParent().delete();
  }

  @Override
  public EpirusObject add() {
    ListJointObject element = new ListJointObject(getPogo(), (MapEntity) getSchema().get(), this, size(), null);
    elements.add(element);
    return element.get();
  }

  @Override
  public int size() {
    return elements.size();
  }

  // implementation
  
  @Override
  public void writeCommit() {
    for(ListJointObject element: elements) {
      element.writeCommit();
    }
  }

  public Object getAt(Integer index) {
    return get(index.intValue());
  }
  
  private void loadFromBackend() {
    if(getBackend() != null) {
      for(int cnt = 0; cnt < getBackend().size(); cnt++) {
        PogoObject value = getBackend().get(cnt, PogoObject.class);
        elements.add(new ListJointObject(getPogo(), (MapEntity) getSchema().get(), this, cnt, value));
      }
    }
  }

  @Override
  public void delete(int index) {
    throw new AssertionError();
  }
}
