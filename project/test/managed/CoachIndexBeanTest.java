package managed;

import auth.CoachAuthBean;
import entities.Client;
import java.util.ArrayList;
import java.util.List;
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
public class CoachIndexBeanTest {
    
    public CoachIndexBeanTest() {
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
     * Test of getClients method, of class CoachIndexBean.
     */
    @Test
    public void testGetClients() {
        System.out.println("getClients");
        CoachIndexBean instance = new CoachIndexBean();
        List<Client> expResult = new ArrayList<>();
        expResult.add(new Client(952));
        List<Client> result = instance.getClients();
        assertEquals(expResult, result);
    }

    /**
     * Test of modifyAppStatus method, of class CoachIndexBean.
     */
    @Test
    public void testModifyAppStatus() {
        System.out.println("modifyAppStatus");
        CoachIndexBean instance = new CoachIndexBean();
        String expResult = "coach.xhtml?faces-redirect=true";
        String result = instance.modifyAppStatus();
        assertEquals(expResult, result);
    }
    
}
