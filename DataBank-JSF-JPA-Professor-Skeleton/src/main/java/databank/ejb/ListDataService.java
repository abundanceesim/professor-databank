package databank.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

@Singleton
public class ListDataService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext( name = "PU_DataBank")
	protected EntityManager em;

	private static final String READ_ALL_DEGREES = "SELECT * FROM degree";
	private static final String READ_ALL_MAJORS = "SELECT * FROM major";

	@Inject
	protected ExternalContext externalContext;

	private void logMsg(String msg) {
		((ServletContext) externalContext.getContext()).log(msg);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> findAllDegrees() {
		logMsg("reading all degrees");
		List<String> degrees = new ArrayList<>();
		try {
			degrees = (List<String>) em.createNativeQuery(READ_ALL_DEGREES).getResultList();
		}
		catch (Exception e) {
			logMsg("something went wrong:  " + e.getLocalizedMessage());
		}
		return degrees;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> findAllMajors() {
		logMsg("reading all majors");
		List<String> majors = new ArrayList<>();
		try {
			majors = (List<String>) em.createNativeQuery(READ_ALL_MAJORS).getResultList();
		}
		catch (Exception e) {
			logMsg("something went wrong:  " + e.getLocalizedMessage());
		}
		return majors;
	}
	
	
}
