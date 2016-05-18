package session;

import entities.Coach;
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
public class CoachFacadeTest {
    
    public CoachFacadeTest() {
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
     * Test of getCoachByPersonId method, of class CoachFacade.
     */
    @Test
    public void testGetCoachByPersonId() throws Exception {
        System.out.println("getCoachByPersonId");
        Object personId = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CoachFacade instance = (CoachFacade)container.getContext().lookup("java:global/classes/CoachFacade");
        Coach expResult = null;
        Coach result = instance.getCoachByPersonId(personId);
        assertEquals(expResult, result);
        container.close();
    }

    /**
     * Test of create method, of class CoachFacade.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Coach entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CoachFacade instance = (CoachFacade)container.getContext().lookup("java:global/classes/CoachFacade");
        instance.create(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class CoachFacade.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Coach entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CoachFacade instance = (CoachFacade)container.getContext().lookup("java:global/classes/CoachFacade");
        instance.edit(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class CoachFacade.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        Coach entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CoachFacade instance = (CoachFacade)container.getContext().lookup("java:global/classes/CoachFacade");
        instance.remove(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class CoachFacade.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Object id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CoachFacade instance = (CoachFacade)container.getContext().lookup("java:global/classes/CoachFacade");
        Coach expResult = null;
        Coach result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class CoachFacade.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CoachFacade instance = (CoachFacade)container.getContext().lookup("java:global/classes/CoachFacade");
        List<Coach> expResult = null;
        List<Coach> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRange method, of class CoachFacade.
     */
    @Test
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        int[] range = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CoachFacade instance = (CoachFacade)container.getContext().lookup("java:global/classes/CoachFacade");
        List<Coach> expResult = null;
        List<Coach> result = instance.findRange(range);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class CoachFacade.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CoachFacade instance = (CoachFacade)container.getContext().lookup("java:global/classes/CoachFacade");
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
