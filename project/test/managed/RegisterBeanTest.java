package managed;

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
public class RegisterBeanTest {
    
    RegisterBean instance = new RegisterBean();
    
    public RegisterBeanTest() {
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
     * Test of registerClient method, of class RegisterBean.
     */
    @Test
    public void testRegisterClient() {
        System.out.println("registerClient");
        instance.setLogin("test");
        instance.setPassword("test");
        instance.setForname("test");
        instance.setSurname("test");
        String expResult = "auth.xhtml?faces-redirect=true";
        String result = instance.registerClient();
        assertEquals(expResult, result);
    }
    
}
