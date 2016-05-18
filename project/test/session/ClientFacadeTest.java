package session;

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
public class ClientFacadeTest {
    
    public ClientFacadeTest() {
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
     * Test of getClientByPersonId method, of class ClientFacade.
     */
    @Test
    public void testGetClientByPersonId() throws Exception {
        System.out.println("getClientByPersonId");
        Object personId = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientFacade instance = (ClientFacade)container.getContext().lookup("java:global/classes/ClientFacade");
        Client expResult = null;
        Client result = instance.getClientByPersonId(personId);
        assertEquals(expResult, result);
        container.close();
    }

    /**
     * Test of getClients method, of class ClientFacade.
     */
    @Test
    public void testGetClients() throws Exception {
        System.out.println("getClients");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientFacade instance = (ClientFacade)container.getContext().lookup("java:global/classes/ClientFacade");
        List<Client> expResult = null;
        List<Client> result = instance.getClients();
        assertEquals(expResult, result);
        container.close();
    }
    
}
