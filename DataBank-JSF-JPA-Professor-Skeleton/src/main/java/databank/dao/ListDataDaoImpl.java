/******************************************************
 * File:  ListDataDaoImpl.java Course materials (23W) CST8277
 *
 * @author Teddy Yap
 */
package databank.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import databank.ejb.ListDataService;

@SuppressWarnings("unused")
/**
 * Description:  API for reading list data from the database
 */
@Named
@ApplicationScoped
public class ListDataDaoImpl implements ListDataDao/*, Serializable */{
	/** Explicitly set serialVersionUID */
//	private static final long serialVersionUID = 1L;

	@EJB
	protected ListDataService listDataService;

//	@SuppressWarnings("unchecked")
	@Override
	public List<String> readAllDegrees() {
		return listDataService.findAllDegrees();
	}

//	@SuppressWarnings("unchecked")
	@Override
	public List<String> readAllMajors() {
		return listDataService.findAllMajors();
	}
	
}
