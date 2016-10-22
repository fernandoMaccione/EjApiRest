package desarrollosMaccione.testLaboralMomentous.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFacade {
	private static EntityManagerFactory emf = null;
	
	public static EntityManager getInstance(){
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("TestLaboralMomentous"); 
        EntityManager em = emf.createEntityManager(); 
        return em;    
	}
}
