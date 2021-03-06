package apoc;

import apoc.util.Neo4jContainerExtension;
import apoc.util.TestUtil;
import org.junit.Test;

import static apoc.util.TestContainerUtil.createEnterpriseDB;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/*
 This test is just to verify if the APOC are correctly deployed
 into a Neo4j instance without any startup issue.
 If you don't have docker installed it will fail, and you can simply ignore it.
 */
public class StartupTest {
    @Test
    public void test() {
        try {
            Neo4jContainerExtension neo4jContainer = createEnterpriseDB(!TestUtil.isTravis())
                    .withNeo4jConfig("dbms.transaction.timeout", "5s");
            neo4jContainer.start();
            assertTrue("Neo4j Instance should be up-and-running", neo4jContainer.isRunning());
            neo4jContainer.close();
        } catch (Exception ex) {
            fail("Should not have thrown exception when trying to start Neo4j: " + ex);
        }
    }
}
