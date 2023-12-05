package databank.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import databank.model.ProfessorPojo;

@Singleton
public class ProfessorService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name="PU_DataBank")
	protected EntityManager em;
	
	/**
	 * Default constructor
	 */
	public ProfessorService() {
		
	}
	
	@Transactional
	public List<ProfessorPojo> findAllProfessors() {
		//Use the named JPQL query from the ProfessorPojo class to grab all the professors
		TypedQuery<ProfessorPojo> allProfessorsQuery = em.createNamedQuery(ProfessorPojo.PROFESSOR_FIND_ALL, ProfessorPojo.class);
		//Execute the query and return the result/s.
		return allProfessorsQuery.getResultList();
	}

	@Transactional
	public ProfessorPojo persistProfessor(ProfessorPojo professor) {
		em.persist(professor);
		return professor;
	}

	@Transactional
	public ProfessorPojo findProfessorByPrimaryKey(int professorPK) {
		return em.find(ProfessorPojo.class, professorPK);
	}

	@Transactional
	public ProfessorPojo mergeProfessor(ProfessorPojo professorWithUpdates) {
		ProfessorPojo mergedProfessorPojo = findProfessorByPrimaryKey(professorWithUpdates.getId());
		try {
			mergedProfessorPojo = em.merge(professorWithUpdates);
			return mergedProfessorPojo;
		} catch (OptimisticLockException e) {
			return null;
		}
	}

	@Transactional
	public void removeProfessor(int professorId) {
		ProfessorPojo professor = findProfessorByPrimaryKey(professorId);
		if (professor != null) {
			em.refresh(professor);
			em.remove(professor);
		}
	}
}
