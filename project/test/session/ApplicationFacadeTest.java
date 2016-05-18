package session;

import entities.Application;
import entities.Client;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author magniii
 */
public class ApplicationFacadeTest {
    
    public ApplicationFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAppByClient method, of class ApplicationFacade.
     */
    @Test
    public void testGetAppByClient() throws Exception {
        System.out.println("getAppByClient");
        Client client = new Client();
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ApplicationFacade instance = (ApplicationFacade)container.getContext().lookup("java:global/classes/ApplicationFacade");
        Application expResult = null;
        Application result = instance.getAppByClient(client);
        assertEquals(expResult, result);
        container.close();
    }
    
}
