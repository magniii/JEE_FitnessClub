package managed;

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
public class DoctorIndexBeanTest {
    
    public DoctorIndexBeanTest() {
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
     * Test of getDoctorsClients method, of class DoctorIndexBean.
     */
    @Test
    public void testGetDoctorsClients() {
        System.out.println("getDoctorsClients");
        DoctorIndexBean instance = new DoctorIndexBean();
        List<Client> expResult = new ArrayList<>();
        List<Client> result = instance.getDoctorsClients();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDoctorsClients method, of class DoctorIndexBean.
     */
    @Test
    public void testSetDoctorsClients() {
        System.out.println("setDoctorsClients");
        List<Client> clients = null;
        DoctorIndexBean instance = new DoctorIndexBean();
        instance.setDoctorsClients(clients);
    }

    /**
     * Test of modifyAppStatus method, of class DoctorIndexBean.
     */
    @Test
    public void testModifyAppStatus() {
        System.out.println("modifyAppStatus");
        DoctorIndexBean instance = new DoctorIndexBean();
        String expResult = "";
        String result = instance.modifyAppStatus();
        assertEquals(expResult, result);
    }
}
