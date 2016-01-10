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
}
