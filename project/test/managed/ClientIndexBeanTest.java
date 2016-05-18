package managed;

import auth.ClientAuthBean;
import entities.Coach;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import session.ApplicationFacade;
import session.ApplicationStateFacade;
import session.ClientFacade;
import session.CoachFacade;
import session.CoachclientsFacade;
import session.DoctorFacade;
import session.DoctorclientsFacade;

/**
 *
 * @author magniii
 */
public class ClientIndexBeanTest {
    
    ClientIndexBean instance = new ClientIndexBean();
    
    public ClientIndexBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance.af = new ApplicationFacade();
        instance.asf = new ApplicationStateFacade();
        instance.cab =  new ClientAuthBean();
        instance.ccf = new CoachclientsFacade();
        instance.cf = new ClientFacade();
        instance.cof = new CoachFacade();
        instance.dcf = new DoctorclientsFacade();
        instance.df = new DoctorFacade();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAppState method, of class ClientIndexBean.
     */
    @Test
    public void testGetAppState() {
        System.out.println("getAppState");
        String expResult = "Your program";
        instance.cab.getClient().setId(952);
        String result = instance.getAppState();
        assertEquals(expResult, result);
    }

    /**
     * Test of modifyAppState method, of class ClientIndexBean.
     */
    @Test
    public void testModifyAppState() {
        System.out.println("modifyAppState");
        String expResult = "client.xhtml?faces-redirect=true";
        String result = instance.modifyAppState();
        assertEquals(expResult, result);
    }

    /**
     * Test of showAppText method, of class ClientIndexBean.
     */
    @Test
    public void testShowAppText() {
        System.out.println("showAppText");
        String expResult = "test";
        instance.af.getAppByClient(instance.cab.getClient()).setText("test");
        String result = instance.showAppText();
        assertEquals(expResult, result);
    }

    /**
     * Test of assignClientToCoach method, of class ClientIndexBean.
     */
    @Test
    public void testAssignClientToCoach() {
        System.out.println("assignClientToCoach");
        String expResult = null;
        instance.cof.remove(instance.cof.find(1));
        String result = instance.assignClientToCoach();
        assertEquals(expResult, result);
    }
    
}
