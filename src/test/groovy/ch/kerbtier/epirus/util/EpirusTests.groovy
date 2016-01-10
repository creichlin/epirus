package ch.kerbtier.epirus.util

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import ch.kerbtier.achaia.Parse;
import ch.kerbtier.achaia.schema.MapEntity;
import ch.kerbtier.achaia.schema.implementation.ImpMapEntity;
import ch.kerbtier.epirus.Epirus;
import ch.kerbtier.epirus.implementation.EpirusImplementation;
import ch.kerbtier.pogo.Pogo;
import ch.kerbtier.pogo.PogoFactory;
import ch.kerbtier.pogo.hops.HopsPogoFactory;

class EpirusTests {

  protected static int num = 0;

  protected MapEntity schema;
  protected Pogo backend;
  protected Epirus root_;

  public Epirus getRoot() {
    return root_;
  }

  public TestWatcher sqlDumper = new SqlDumper()
  public sessionSetup = new SessionSetup();

  @Rule
  public final TestRule webTestSupport = RuleChain.outerRule(sessionSetup).around(sqlDumper);


  class SessionSetup implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {

      return new Statement() {
        public void evaluate() throws Throwable {
          Properties p = new Properties(System.getProperties());
          p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
          p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF"); // Off or any other level
          System.setProperties(p);
    
    
          schema = new ImpMapEntity(null, "")
          Parse.extend(schema, Paths.get("src/test/resources", "post.model"))
    
          PogoFactory factory = new HopsPogoFactory("org.h2.Driver", "jdbc:h2:mem:m" + (num++)
              + ";USER=test;PASSWORD=test;DB_CLOSE_DELAY=-1")
          backend = factory.create()
    
          root_ = new EpirusImplementation(schema, backend)
    
          base.evaluate();
        }
      }
    }
  }

  class SqlDumper extends TestWatcher {
    @Override
    protected void failed(Throwable e, Description description) {
      println("SQL Dump")
      def list = ((CharSequence)backend.dumpSource()).tokenize(";")
      list.findAll { it.contains("INSERT") }.each { println it}
      println "SQL Done"
    }

    @Override
    protected void succeeded(Description description) {
    }
  }
}

