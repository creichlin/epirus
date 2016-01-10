package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.Type;
import ch.kerbtier.achaia.schema.Entity;
import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.EpirusContainer;
import ch.kerbtier.epirus.EpirusObject;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.epirus.implementation.parents.ObjectParent;
import ch.kerbtier.pogo.Pogo;
import ch.kerbtier.pogo.PogoObject;
import ch.kerbtier.pogo.exceptions.NoSuchField;

public interface ObjectField {

  public static ObjectField create(EpirusContainer epirusParent, Entity entity, PogoObject pogoParent) {

    Object pogoValue;
    if (epirusParent instanceof EpirusObject) {
      try {
        pogoValue = pogoParent.get(entity.getName());
      } catch (NoSuchField | NullPointerException e) {
        pogoValue = null;
      }

      ObjectParent parent = new ObjectParent((EpirusObjectImplementation) epirusParent, entity.getName());
      
      if (entity.is(Type.MAP)) {
        return new ObjectToObjectField((MapEntity) entity, parent,
            (PogoObject) pogoValue);

      } else if (entity.getType().isPrimitive()) {
        return new ObjectToPrimitiveField(entity, parent, pogoValue);

      } else {
        throw new RuntimeException();
      }
    } else {
      throw new AssertionError();
    }

  }

  Object get();

  void set(Object value);

  void write();
}
