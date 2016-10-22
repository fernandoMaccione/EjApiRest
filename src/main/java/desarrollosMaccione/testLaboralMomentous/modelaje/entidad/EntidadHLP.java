package desarrollosMaccione.testLaboralMomentous.modelaje.entidad;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import desarrollosMaccione.testLaboralMomentous.Log.LogTimeMethod;
import desarrollosMaccione.testLaboralMomentous.excepciones.EntidadException;
import desarrollosMaccione.testLaboralMomentous.modelaje.constantes.MensajesCTE;
import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.model.EntidadVO;
import desarrollosMaccione.testLaboralMomentous.persistencia.EntityManagerFacade;

public abstract class EntidadHLP<T extends EntidadVO<?>> {
	protected abstract T getVO();
	
	public T insert(T entidad) throws EntidadException{
		LogTimeMethod logT = new LogTimeMethod();
		try {
			EntityManager em = EntityManagerFacade.getInstance();
	        EntityTransaction tx = em.getTransaction(); 
	        
	        tx.begin(); 
	        try { 
	            em.persist(entidad); 
	            tx.commit(); 
	        } catch(Exception e) { 
	            tx.rollback();
	            throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_GUARDAR, e);
	        }finally {
	        	em.close(); 
			} 
	        
			return entidad;
		} catch (EntidadException e) {
			throw e;
		}catch (Exception e){
			throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_GUARDAR, e);
		}finally {
			logT.finish();
		}
	}
	
	public T update(T entidad) throws EntidadException{
		LogTimeMethod logT = new LogTimeMethod();
		try {
			EntityManager em = EntityManagerFacade.getInstance();
	        EntityTransaction tx = em.getTransaction(); 
	     
	        tx.begin(); 
	        try { 
	            em.merge(entidad); 
	            tx.commit(); 
	        } catch(Exception e) { 
	            tx.rollback();
	            throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_ACTUALIZAR, e);
	        }finally {
	        	em.close(); 
			} 
	        
			return entidad;
		} catch (EntidadException e) {
			throw e;
		}catch (Exception e){
			throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_ACTUALIZAR, e);
		}finally {
			logT.finish();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T getByPk(Long id) throws EntidadException{
		LogTimeMethod logT = new LogTimeMethod();
		try {
			EntityManager em = EntityManagerFacade.getInstance();
	        EntityTransaction tx = em.getTransaction(); 
	        tx.begin();
	        try {
	        	T entidad = getVO();
	        	entidad = (T) em.find(entidad.getClass(),id);
	        	tx.commit(); 
	        	em.detach(entidad);	        	
	            return entidad;
	        } catch(Exception e) { 
	            tx.rollback();
	            throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_LEER, e);
	        }finally {
	        	em.close(); 
			} 
	        
		} catch (EntidadException e) {
			throw e;
		}catch (Exception e){
			throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_LEER, e);
		}finally {
			logT.finish();
		}
	}
	
	public T eliminar(T entidad) throws EntidadException{
		LogTimeMethod logT = new LogTimeMethod();
		try {
			EntityManager em = EntityManagerFacade.getInstance();
	        EntityTransaction tx = em.getTransaction(); 
	     
	        tx.begin(); 
	        try { 
	            em.remove(entidad); 
	            tx.commit(); 
	        } catch(Exception e) { 
	            tx.rollback();
	            throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_ELIMINAR, e);
	        }finally {
	        	em.close(); 
			} 
	        
			return entidad;
		} catch (EntidadException e) {
			throw e;
		}catch (Exception e){
			throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_ELIMINAR, e);
		}finally {
			logT.finish();
		}
	}
	
	
	public List<T> getByJPQL(String jpql) throws EntidadException{
		return getByJPQL(jpql, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByJPQL(String jpql, Object[] parametros) throws EntidadException{
		LogTimeMethod logT = new LogTimeMethod();
		try {
			EntityManager em = EntityManagerFacade.getInstance();
	        try { 
	        	Query query = em.createQuery(jpql);
	        	if (parametros != null && parametros.length>0){
	        		for (int i=0; i<parametros.length; i++)
	        			query.setParameter(i+1, parametros[i]);
	        	}	        	 
	        	List<T> resultados = query.getResultList();
	        	return resultados;
	        } catch(Exception e) {
	            throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_EJECUTAR_QUERY, e);
	        }finally {
	        	em.close(); 
			}
		} catch (EntidadException e) {
			throw e;
		}catch (Exception e){
			throw new EntidadException(MensajesCTE.ERROR_ENTIDAD_PERSIST_EJECUTAR_QUERY, e);
		}finally {
			logT.finish();
		}
	}
}
