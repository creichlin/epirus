package ch.kerbtier.epirus

import org.junit.Test;

import ch.kerbtier.epirus.util.EpirusTests;

class ObjectTests extends EpirusTests {
  
  @Test
  public void setString() {
    root['post']['title'] = 'LALA'
    assert root['post']['title'] == 'LALA'
  }
  
  @Test
  public void setInteger() {
    root['post']['count'] = 17
    assert root['post']['count'] == 17
  }
  
  @Test(expected = WrongFieldType.class)
  public void setWrongType() {
    root['post']['title'] = 17
  }
  
  @Test
  public void setFieldOnDeepInstance() {
    root['post']['meta']['foo'] = 'LOLO'
    assert root['post']['meta']['foo'] == 'LOLO'
  }
}
