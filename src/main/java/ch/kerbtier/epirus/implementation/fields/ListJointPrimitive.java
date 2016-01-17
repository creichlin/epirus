package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.epirus.implementation.EpirusPrimitiveListImplementation;
import ch.kerbtier.pogo.Pogo;
import static ch.kerbtier.epirus.implementation.fields.ValueState.*;

public class ListJointPrimitive implements ListJoint {
  
  private EpirusPrimitiveListImplementation parent;
  private int index;
  
  private ValueState state = UNSAVED;
  
  private Pogo pogoRoot;
  private Object pogoValue;
  private Object value;
  
  public ListJointPrimitive(Pogo pogoRoot, EpirusPrimitiveListImplementation subject, int index, Object pogoValue) {
    this.pogoRoot = pogoRoot;
    this.parent = subject;
    this.index = index;
    this.pogoValue = pogoValue;
    this.state = SAVED;
  }

  public ListJointPrimitive(Pogo pogoRoot, EpirusPrimitiveListImplementation subject, int index) {
    this.pogoRoot = pogoRoot;
    this.parent = subject;
    this.index = index;
    this.state = UNSAVED;
  }

  public Object get() {
    if(state == UNSAVED || state == MODIFIED) {
      return value;
    }
    
    return pogoValue;
  }

  public void set(Object value) {
    this.value = value;
    state = change(state);
  }

  public ValueState getState() {
    return state;
  }

  public void write() {
    if(state == UNSAVED) {
      parent.getBackend().add(value);
      pogoValue = parent.getBackend().get(index);
      state = save(state);
    } else if(state == MODIFIED) {
      throw new AssertionError();
    }
  }

  @Override
  public Pogo getPogoRoot() {
    return pogoRoot;
  }

  @Override
  public void delete() {
    parent.delete(index);
  }
}
