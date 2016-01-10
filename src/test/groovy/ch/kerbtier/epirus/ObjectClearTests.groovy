package ch.kerbtier.epirus

import org.junit.Test;

import ch.kerbtier.epirus.util.EpirusTests;

class ObjectClearTests extends EpirusTests {
  
  @Test
  public void setString() {
    root['post']['title'] = 'LALA'
    root.clear()
    assert root['post']['title'] == null
  }
  
  @Test
  public void setInteger() {
    root['post']['count'] = 17
    root.clear()
    assert root['post']['count'] == null
  }
  
  @Test
  public void setFieldOnDeepInstance() {
    root['post']['meta']['foo'] = 'LOLO'
    root.clear()
    assert root['post']['meta']['foo'] == null
  }
}
