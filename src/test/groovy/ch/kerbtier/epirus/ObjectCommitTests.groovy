package ch.kerbtier.epirus

import org.junit.Test;

import ch.kerbtier.epirus.util.EpirusTests;

class ObjectCommitTests extends EpirusTests {
  
  @Test
  public void setString() {
    root['post']['title'] = 'LALA'
    root.commit().clear()
    assert root['post']['title'] == 'LALA'
  }
  
  @Test
  public void setInteger() {
    root['post']['count'] = 17
    root.commit().clear()
    assert root['post']['count'] == 17
  }
  
  @Test
  public void setFieldOnDeepInstance() {
    root['post']['meta']['foo'] = 'LOLO'
    root.commit().clear()
    assert root['post']['meta']['foo'] == 'LOLO'
  }
}
