package ch.kerbtier.epirus.implementation.fields;

public enum ValueState {
  UNSAVED, SAVED, MODIFIED;

  public static ValueState save(ValueState state) {
    return SAVED;
  }

  public static ValueState change(ValueState state) {
    if(state == SAVED) {
      return MODIFIED;
    }
    return state;
  }
}
