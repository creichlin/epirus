package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.epirus.implementation.EpirusPrimitiveListImplementation;
import static ch.kerbtier.epirus.implementation.fields.ValueState.*;

public class PrimitiveListElement {

  
  private EpirusPrimitiveListImplementation subject;
  private int index;
  
  private ValueState state = UNSAVED;
  
  private Object pogoValue;
  private Object value;
  
  
  public PrimitiveListElement(EpirusPrimitiveListImplementation subject, int index, Object pogoValue) {
    this.subject = subject;
    this.index = index;
    this.pogoValue = pogoValue;
    this.state = SAVED;
  }

  public PrimitiveListElement(EpirusPrimitiveListImplementation subject, int index) {
    this.subject = subject;
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
      subject.getSubject().add(value);
      pogoValue = subject.getSubject().get(index);
      state = save(state);
    } else if(state == MODIFIED) {
      throw new AssertionError();
    }
  }
}
