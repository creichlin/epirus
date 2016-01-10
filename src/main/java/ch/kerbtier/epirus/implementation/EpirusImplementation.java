package ch.kerbtier.epirus.implementation;

import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.epirus.Epirus;
import ch.kerbtier.epirus.implementation.parents.RootParent;
import ch.kerbtier.pogo.Pogo;

public class EpirusImplementation extends EpirusObjectImplementation implements Epirus {
  
  public EpirusImplementation(MapEntity schema, Pogo backend) {
    super(schema, new RootParent(backend), backend);
  }

}
