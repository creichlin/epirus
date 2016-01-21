package ch.kerbtier.epirus

import org.junit.Test;

import ch.kerbtier.epirus.util.EpirusTests;

class ObjectListTests extends EpirusTests {

  
  @Test
  public void emptyList() {
    assert root['post']['comments'].size() == 0
  }

    
  @Test
  public void addObject() {
    root['post']['comments'].add()
    
    assert root['post']['comments'].size() == 1
  }
  
  @Test
  public void addObjectAndField() {
    root['post']['comments'].add()
    root['post']['comments'][0]['name'] = 'FooLaBar'
    assert root['post']['comments'][0]['name'] == 'FooLaBar'
  }
  
  @Test
  public void addObjectAndCommit() {
    root['post']['comments'].add()
    
    root.commit().clear()
    
    assert root['post']['comments'].size() == 1
  }
  
}
