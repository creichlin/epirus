package ch.kerbtier.epirus;

import org.junit.Test;

import ch.kerbtier.epirus.util.EpirusTests;

public class ObjectDeleteTests extends EpirusTests {
  
  @Test
  public void testDeleteObject() {
    root['post']['title'] = 'lala'
    root['post'].delete()
    assert root['post']['title'] == null
  }

  @Test
  public void testDeletePrimitive() {
    root['post']['title'] = 'lala'
    root['post'].delete('title')
    assert root['post']['title'] == null
  }

  @Test
  public void testDeleteObjectFromAttribute() {
    root['post']['title'] = 'lala'
    root.delete('post')
    assert root['post']['title'] == null
  }

  @Test
  public void testDeleteObjectWithCommit() {
    root['post']['title'] = 'lala'
    root.commit()
    root['post'].delete()
    assert root['post']['title'] == null
  }

  @Test
  public void testDeleteObjectFromAttributeWithCommit() {
    root['post']['title'] = 'lala'
    root.commit()
    root.delete('post')
    assert root['post']['title'] == null
  }
}
