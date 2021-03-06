package ch.kerbtier.epirus

import org.junit.Test;

import ch.kerbtier.epirus.util.EpirusTests;

class ListTests extends EpirusTests {
  
  @Test
  public void checkEmptyList() {
    assert root['post']['tags'].size() == 0
  }
  
  @Test
  public void addPrimitive() {
    root['post']['tags'] << "A"
    
    assert root['post']['tags'].size() == 1
  }
  
  @Test
  public void addAndReadPrimitive() {
    root['post']['tags'] << "BAR"
    
    assert root['post']['tags'][0] == 'BAR'
  }
  
  @Test
  public void addAndCommitPrimitive() {
    root['post']['tags'] << "BAR"
    
    root.commit().clear()
    
    assert root['post']['tags'][0] == 'BAR'
  }
  
  @Test
  public void deleteWholeList() {
    
    enableStatementLog()
    
    root['post']['tags'] << "BAR"
    root['post']['tags'] << "FOO"
    
    root['post']['tags'].delete()

    assert root['post']['tags'].size() == 0
  }

    @Test
  public void deleteWholeListAsField() {
    root['post']['tags'] << "BAR"
    root['post']['tags'] << "FOO"

    root.commit().clear()
    
    root['post'].delete('tags')

    root.commit().clear()
    
    assert root['post']['tags'].size() == 0
  }
}
