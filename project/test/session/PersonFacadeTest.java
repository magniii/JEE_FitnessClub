package session;

import entities.Person;
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
public class PersonFacadeTest {
    
    public PersonFacadeTest() {
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
     * Test of getPerson method, of class PersonFacade.
     */
    @Test
    public void testGetPerson() throws Exception {
        System.out.println("getPerson");
        String login = "test";
        String password = "test";
        Person p = new Person("test", "test", login, password);
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacade instance = (PersonFacade)container.getContext().lookup("java:global/classes/PersonFacade");
        Person expResult = p;
        Person result = instance.getPerson(login, password);
        assertEquals(expResult, result);
        container.close();
    }
}
