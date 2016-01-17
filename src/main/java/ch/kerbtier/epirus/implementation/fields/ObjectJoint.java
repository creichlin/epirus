package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.Type;
import ch.kerbtier.achaia.schema.Entity;
import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.pogo.PogoList;
import ch.kerbtier.pogo.PogoObject;
import ch.kerbtier.pogo.exceptions.NoSuchField;

public interface ObjectJoint extends Joint {

  public static ObjectJoint create(EpirusObjectImplementation epirusParent, Entity entity, PogoObject pogoParent) {

    Object pogoValue;

    try {
      pogoValue = pogoParent.get(entity.getName());
    } catch (NoSuchField | NullPointerException e) {
      pogoValue = null;
    }

    if (entity.is(Type.MAP)) {
      return new ObjectJointObject((MapEntity) entity, epirusParent, (PogoObject) pogoValue);

    } else if (entity.is(Type.LIST)) {
      return new ObjectJointList((ListEntity) entity, epirusParent, (PogoList) pogoValue);

    } else if (entity.getType().isPrimitive()) {
      return new ObjectJointPrimitive(entity, epirusParent, pogoValue);

    } else {
      throw new RuntimeException();
    }

  }

  void set(Object value);

  void writeCommit();

  void delete();
  
}
