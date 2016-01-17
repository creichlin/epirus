package ch.kerbtier.epirus.implementation.fields;

import ch.kerbtier.achaia.Type;
import ch.kerbtier.achaia.schema.Entity;
import ch.kerbtier.achaia.schema.ListEntity;
import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.EpirusObject;
import ch.kerbtier.epirus.implementation.EpirusObjectImplementation;
import ch.kerbtier.epirus.implementation.parents.ObjectParent;
import ch.kerbtier.pogo.PogoList;
import ch.kerbtier.pogo.PogoObject;
import ch.kerbtier.pogo.exceptions.NoSuchField;

public interface ObjectField {

  public static ObjectField create(EpirusObject epirusParent, Entity entity, PogoObject pogoParent) {

    Object pogoValue;

    try {
      pogoValue = pogoParent.get(entity.getName());
    } catch (NoSuchField | NullPointerException e) {
      pogoValue = null;
    }

    ObjectParent parent = new ObjectParent((EpirusObjectImplementation) epirusParent, entity.getName());

    if (entity.is(Type.MAP)) {
      return new ObjectToObjectField((MapEntity) entity, parent, (PogoObject) pogoValue);

    } else if (entity.is(Type.LIST)) {
      return new ObjectToListField((ListEntity) entity, parent, (PogoList) pogoValue);

    } else if (entity.getType().isPrimitive()) {
      return new ObjectToPrimitiveField(entity, parent, pogoValue);

    } else {
      throw new RuntimeException();
    }

  }

  Object get();

  void set(Object value);

  void write();

  void delete();
}
