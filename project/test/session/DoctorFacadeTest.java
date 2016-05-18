package session;

import entities.Doctor;
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
public class DoctorFacadeTest {
    
    public DoctorFacadeTest() {
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
     * Test of getDoctorByPersonId method, of class DoctorFacade.
     */
    @Test
    public void testGetDoctorByPersonId() throws Exception {
        System.out.println("getDoctorByPersonId");
        Object personId = 954;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DoctorFacade instance = (DoctorFacade)container.getContext().lookup("java:global/classes/DoctorFacade");
        Doctor expResult = new Doctor(954);
        Doctor result = instance.getDoctorByPersonId(personId);
        assertEquals(expResult, result);
        container.close();
    }
    
}
