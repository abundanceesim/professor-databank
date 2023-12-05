/*****************************************************************
 * File:  NewProfessorView.java Course materials (23W) CST8277
 *
 * @author Abundance Esim
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.jsf;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import databank.model.ProfessorPojo;

/**
 * This class represents the scope of adding a new professor to the DB.
 * 
 * TODO 09 - This class is a managed bean.  Use the name "newProfessor".<br>
 * TODO 10 - Like previous assignment where ProfessorPojo was view scoped, this class is?<br>
 * TODO 11 - Add the missing variables and their getters and setters.  Have in mind dates and version are internal
 * only.<br>
 * 
 */
@ViewScoped //not entirely complete yet
@Named("newProfessor")
public class NewProfessorView implements Serializable {
	/** explicit set serialVersionUID */
	private static final long serialVersionUID = 1L;

	protected String lastName;
	protected String firstName;
	
	//missing variables
	private String email;
	private String degree;
	private String major;
	private String phoneNumber;
	private LocalDateTime created;
	private LocalDateTime updated;
	
	@Inject
	@ManagedProperty("#{professorController}")
	protected ProfessorController professorController;

	public NewProfessorView() {
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public void addProfessor() {
		if (allNotNullOrEmpty(firstName, lastName /* TODO 11 - Don't forget to add the other variables that are not nullable */)) {
			ProfessorPojo theNewProfessor = new ProfessorPojo();
			theNewProfessor.setFirstName(getFirstName());
			theNewProfessor.setLastName(getLastName());
			
			theNewProfessor.setEmail(getEmail());
			theNewProfessor.setPhoneNumber(getPhoneNumber());
			theNewProfessor.setDegree(getDegree());
			theNewProfessor.setMajor(getMajor());
			
			//TODO 12 - Call other setters
			professorController.addNewProfessor(theNewProfessor);
			//Clean up
			professorController.toggleAdding();
			setFirstName(null);
			setLastName(null);
			
			//TODO 13 - Set everything else to null
			setEmail(null);
			setPhoneNumber(null);
			setDegree(null);
			setMajor(null);
		}
	}

	static boolean allNotNullOrEmpty(final Object... values) {
		if (values == null) {
			return false;
		}
		for (final Object val : values) {
			if (val == null) {
				return false;
			}
			if (val instanceof String) {
				String str = (String) val;
				if (str.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}
