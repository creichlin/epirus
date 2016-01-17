package ch.kerbtier.epirus.implementation;

import java.util.ArrayList;
import java.util.List;

import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.epirus.EpirusPrimitiveList;
import ch.kerbtier.epirus.implementation.fields.PrimitiveListElement;
import ch.kerbtier.epirus.implementation.fields.ValueState;
import ch.kerbtier.epirus.implementation.parents.Parent;
import ch.kerbtier.pogo.PogoList;

public class EpirusPrimitiveListImplementation extends EpirusListImplementation implements EpirusPrimitiveList {

  private List<PrimitiveListElement> elements = new ArrayList<>();
  
  public EpirusPrimitiveListImplementation(ListEntity schema, Parent parent, PogoList subject) {
    super(parent, schema, subject);
    
    loadFromBackend();
  }

  public EpirusPrimitiveListImplementation(ListEntity schema, Parent parent) {
    this(schema, parent, null);
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
    PrimitiveListElement element = new PrimitiveListElement(this, size());
    element.set(value);
    elements.add(element);
  }

  @Override
  public int size() {
    return elements.size();
  }

  // implementation
  
  @Override
  public void writeFields() {
    for(PrimitiveListElement element: elements) {
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
    if(getSubject() != null) {
      for(int cnt = 0; cnt < getSubject().size(); cnt++) {
        Object value = getSubject().get(cnt);
        elements.add(new PrimitiveListElement(this, cnt, value));
      }
    }
  }
}
