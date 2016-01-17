package ch.kerbtier.epirus.implementation;

import java.util.ArrayList;
import java.util.List;

import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.epirus.EpirusPrimitiveList;
import ch.kerbtier.epirus.implementation.fields.JointList;
import ch.kerbtier.epirus.implementation.fields.ListJointPrimitive;
import ch.kerbtier.epirus.implementation.fields.ValueState;
import ch.kerbtier.pogo.PogoList;

public class EpirusPrimitiveListImplementation extends EpirusListImplementation implements EpirusPrimitiveList {

  private List<ListJointPrimitive> elements = new ArrayList<>();
  
  public EpirusPrimitiveListImplementation(ListEntity schema, JointList parent, PogoList subject) {
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
  public EpirusPrimitiveList clear() {
    elements.clear();
    loadFromBackend();
    return this;
  }

  @Override
  public void delete() {
    getParent().delete();
  }

  @Override
  public void add(Object value) {
    ListJointPrimitive element = new ListJointPrimitive(getPogo(), this, size());
    element.set(value);
    elements.add(element);
  }

  @Override
  public int size() {
    return elements.size();
  }

  // implementation
  
  @Override
  public void writeCommit() {
    for(ListJointPrimitive element: elements) {
      if(element.getState() == ValueState.UNSAVED) {
        element.write();
      }
    }
  }

  public void leftShift(Object obj) {
    add(obj);
  }

  public Object getAt(Integer index) {
    return get(index.intValue());
  }
  
  private void loadFromBackend() {
    if(getBackend() != null) {
      for(int cnt = 0; cnt < getBackend().size(); cnt++) {
        Object value = getBackend().get(cnt);
        elements.add(new ListJointPrimitive(getPogo(), this, cnt, value));
      }
    }
  }

  @Override
  public void delete(int index) {
    throw new AssertionError();
  }
}
