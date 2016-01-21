package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.EpirusObject;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.epirus.implementation.EpirusObjectListImplementation;
import ch.kerbtier.pogo.Pogo;
import ch.kerbtier.pogo.PogoObject;

public class ListJointObject implements ListJoint, JointObject {
  
  private EpirusObjectListImplementation parent;
  private int index;
  
  private Pogo pogoRoot;
  private PogoObject pogoValue;
  private EpirusObjectImplementation object;
  
  public ListJointObject(Pogo pogoRoot, MapEntity schema, EpirusObjectListImplementation parent, int index, PogoObject pogoValue) {
    this.pogoRoot = pogoRoot;
    this.parent = parent;
    this.index = index;
    this.pogoValue = pogoValue;
    object = new EpirusObjectImplementation(schema, this, pogoValue);
  }

  public EpirusObject get() {
    return object;
  }

  public void writeCommit() {
    if(pogoValue == null) {
      parent.getBackend().add(PogoObject.class);
      pogoValue = parent.getBackend().get(index, PogoObject.class);
      object.setPogo(pogoValue);
    }
    object.writeCommit();
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
