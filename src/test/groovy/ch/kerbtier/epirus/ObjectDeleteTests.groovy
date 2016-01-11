package ch.kerbtier.epirus;

import org.junit.Test;

import ch.kerbtier.epirus.util.EpirusTests;

public class ObjectDeleteTests extends EpirusTests {
  
  @Test
  public void testDeletObject() {
    root['post']['title'] = 'lala'
    root['post'].delete()
    assert root['post']['title'] == null
  }

  @Test
  public void testDeletObjectFromAttribute() {
    root['post']['title'] = 'lala'
    root.delete('post')
    assert root['post']['title'] == null
  }

    @Test
  public void testDeletObjectWithCommit() {
    root['post']['title'] = 'lala'
    root.commit();
    root['post'].delete()
    assert root['post']['title'] == null
  }

  @Test
  public void testDeletObjectFromAttributeWithCommit() {
    root['post']['title'] = 'lala'
    root.commit();
    root.delete('post')
    assert root['post']['title'] == null
  }
}
