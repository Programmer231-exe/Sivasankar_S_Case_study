package org.hospitalmanagementsystem.databaseadministrator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hospitalmanagementsystem.entities.Patient;
import org.hospitalmanagementsystem.entities.PatientDiagnosisDetails;
import org.hospitalmanagementsystem.entities.PatientLoginDetails;
import org.hospitalmanagementsystem.entities.Physician;
import org.hospitalmanagementsystem.entities.PhysicianLoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HMS_DAO {

	private static final String EMAIL_SUFFIX = "@hms.com";

	@Autowired
	@Qualifier(value = "entityManager")
	EntityManager entityManager;

	private static final Logger logger = LogManager.getLogger(HMS_DAO.class.getName());

	@Transactional
	public PatientLoginDetails enrollPatient(Patient patient) {
		Session session = entityManager.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		Long id = null;
		logger.info("Session Successfully created");
		PatientLoginDetails loginDetails = new PatientLoginDetails();
		loginDetails.setPatient(patient);
		loginDetails.setUsername(patient.getEmail());
		loginDetails.setPassword(loginDetails.setPassword());
		try {
			id = (Long) session.save(loginDetails);
		} catch (ConstraintViolationException nse) {
			id = null;
			logger.warn("Constraint cannot match the requiredments");
			return null;
		}

		if (id != null) {
			try {
				tx.commit();
			} catch (RollbackException re) {
				logger.warn("Error while commiting the request");
				return null;
			}
			logger.info("Patient objects successfully stored");
			return loginDetails;
		} else {
			logger.info("Patient Objects cannot be stored");
			try {
				tx.rollback();
			} catch (RollbackException re) {
				logger.warn("Roll Back Exception occurs");
				return null;
			}

			return null;
		}
	}

	@Transactional
	public boolean isValidUser(String username, String password) {

		PatientLoginDetails result = null;
		try {
			result = (PatientLoginDetails) entityManager.createNamedQuery("getLoginDetails")
					.setParameter("username", username).getResultList().get(0);
		} catch (IndexOutOfBoundsException e) {
			logger.warn("There is no record for given user name");
			return false;

		}

		if (result == null) {
			logger.warn("There is no matching record found. Check the credentials");
			return false;
		} else {
			if (password.equals(result.getPassword())) {
				logger.info("Login Successful");
				return true;
			} else {
				logger.warn("Password doesn't match");
				return false;
			}
		}

	}

	@Transactional
	public Patient getPatientDetail(String username) {

		Patient patient = null;
		try {
			patient = (Patient) entityManager.createNamedQuery("getPatient").setParameter("username", username)
					.getResultList().get(0);
		} catch (IndexOutOfBoundsException ie) {
			logger.info("Patient object with particular user name not found in the database");
			return null;
		}

		if (patient != null) {
			logger.info("Patient Object Successfully Retrieved From Database");
		} else {
			logger.warn("Patient Details Not Found");
		}
		return patient;
	}

	@Transactional
	public PhysicianLoginDetails registerPhysician(Physician physician) {
		Long id = null;
		Session session = entityManager.unwrap(Session.class);
		logger.info("Session Successfully created");
		PhysicianLoginDetails loginDetails = new PhysicianLoginDetails();
		Transaction tx = session.beginTransaction();
		loginDetails.setPhysician(physician);
		loginDetails.setUsername(generateEmailForPhysician(physician));
		loginDetails.setPassword(loginDetails.setPassword());
		try {
			id = (Long) session.save(loginDetails);
		} catch (ConstraintViolationException cve) {
			logger.warn("Constraint does not match .Once again check the credentials");
			id = null;
			return null;
		}

		if (id != null) {
			try {
				tx.commit();
			} catch (RollbackException re) {
				logger.warn("Exception occurs while commiting the transaction");
				return null;
			}

			logger.info("Registration of Physician details into database is successful");
			return loginDetails;
		} else {
			logger.info("Patient Objects cannot be stored");
			try {
				tx.rollback();
			} catch (RollbackException re) {
				logger.warn("Roll Back Exception occurs");
				return null;
			}

			return null;
		}

	}

	@Transactional
	public List<Physician> getPhysicianByState(String cond, String base) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Physician> q = cb.createQuery(Physician.class);
		Root<Physician> root = q.from(Physician.class);
		q.where(cb.equal(root.get(base), cond));
		CriteriaQuery<Physician> select = q.select(root);
		TypedQuery<Physician> typedQuery = entityManager.createQuery(select);
		List<Physician> results = typedQuery.getResultList();

		for (Physician p : results) {
			logger.info(p.getFirstName() + " " + p.getLastName());
		}

		return results;
	}

	@Transactional
	public boolean resetPassword(String username, String password) {
		int rows = 0;

		try {
			entityManager.getTransaction().begin();
			rows = entityManager.createNamedQuery("resetPasswordForPatient").setParameter("newpassword", password)
					.setParameter("user", username).executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			logger.warn("Password change cannot be changed");
			return false;
		}

		if (rows == 1) {
			return true;
		} else {
			return false;
		}

	}

	@Transactional
	public boolean registerAppointment(String username, String physicianId) {
		PatientLoginDetails result = null;
		entityManager.getTransaction().begin();
		try {
			result = (PatientLoginDetails) entityManager.createNamedQuery("getLoginDetails")
					.setParameter("username", username).getResultList().get(0);
		} catch (IndexOutOfBoundsException ie) {
			logger.warn("patient login details not found in the database");
			return false;
		}
		System.out.println(result.getPassword());
		Patient patient = result.getPatient();
		PhysicianLoginDetails physicianloginDetails = (PhysicianLoginDetails) entityManager
				.createNamedQuery("getPhysicianLoginDetails").setParameter("physicianId", physicianId).getResultList()
				.get(0);
		Physician physician = physicianloginDetails.getPhysician();
		PatientDiagnosisDetails pdDetails = new PatientDiagnosisDetails();
		pdDetails.setPatientId(patient);
		pdDetails.setPhysician(physician);
		pdDetails.setStatus("open");
		try {
			entityManager.persist(pdDetails);
			entityManager.getTransaction().commit();
		} catch (ConstraintViolationException cve) {
			logger.warn("Constraint Violation exception occurs");
			return false;
		}

		return true;
	}

	private String generateEmailForPhysician(Physician physician) {
		return physician.getFirstName() + "-" + physician.getLastName() + EMAIL_SUFFIX;
	}

	public List<PatientDiagnosisDetails> getDiagnosisReportofPatient(String username) {

		PatientLoginDetails result = (PatientLoginDetails) entityManager.createNamedQuery("getLoginDetails")
				.setParameter("username", username).getResultList().get(0);

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientDiagnosisDetails> q = cb.createQuery(PatientDiagnosisDetails.class);
		Root<PatientDiagnosisDetails> root = q.from(PatientDiagnosisDetails.class);
		q.where(cb.equal(root.get("patientId").get("id"), result.getPatient().getId()));
		CriteriaQuery<PatientDiagnosisDetails> select = q.select(root);
		TypedQuery<PatientDiagnosisDetails> typedQuery = entityManager.createQuery(select);
		List<PatientDiagnosisDetails> results = typedQuery.getResultList();

		return results;

	}

	public Physician isValidPhysician(String username, String password) {

		PhysicianLoginDetails result = null;
		try {
			result = (PhysicianLoginDetails) entityManager.createNamedQuery("getPhysicianDetailsusingUsername")
					.setParameter("username", username).getResultList().get(0);
		} catch (IndexOutOfBoundsException e) {
			logger.warn("Physician for particular username not found");
			return null;
		}

		if (result == null) {
			logger.warn("There is no matching record found. Check the credentials");
			return null;
		} else {
			if (password.equals(result.getPassword())) {
				logger.info("Login Successful");
				return result.getPhysician();
			} else {
				logger.warn("Password doesn't match");
				return null;
			}
		}

	}

	@Transactional
	public List<PatientDiagnosisDetails> getDiagnosisReportofPatientforDoctor(String physicianId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientDiagnosisDetails> q = cb.createQuery(PatientDiagnosisDetails.class);
		Root<PatientDiagnosisDetails> root = q.from(PatientDiagnosisDetails.class);
		q.where(cb.equal(root.get("physician").get("id"), physicianId));
		q.where(cb.equal(root.get("status"), "open"));
		CriteriaQuery<PatientDiagnosisDetails> select = q.select(root);
		TypedQuery<PatientDiagnosisDetails> typedQuery = entityManager.createQuery(select);
		List<PatientDiagnosisDetails> results = typedQuery.getResultList();

		return results;

	}

	public PatientDiagnosisDetails getDiagnosisReport(String id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientDiagnosisDetails> q = cb.createQuery(PatientDiagnosisDetails.class);
		Root<PatientDiagnosisDetails> root = q.from(PatientDiagnosisDetails.class);
		q.where(cb.equal(root.get("diagnosisId"), id));
		CriteriaQuery<PatientDiagnosisDetails> select = q.select(root);
		TypedQuery<PatientDiagnosisDetails> typedQuery = entityManager.createQuery(select);
		List<PatientDiagnosisDetails> results = typedQuery.getResultList();

		return results.get(0);
	}

	@Transactional
	public boolean updateDiagnosisReport(PatientDiagnosisDetails pdd) {
		Session session = entityManager.unwrap(Session.class);
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			pdd.setStatus("close");
			session.saveOrUpdate(pdd);
			tx.commit();
			return true;

		} catch (ConstraintViolationException cve) {
			logger.warn("Constraint Violation Exception occurs");
			return false;
		}

	}

	@Transactional
	public List<PatientDiagnosisDetails> getAllDiagnosisReports() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientDiagnosisDetails> q = cb.createQuery(PatientDiagnosisDetails.class);
		Root<PatientDiagnosisDetails> root = q.from(PatientDiagnosisDetails.class);
		CriteriaQuery<PatientDiagnosisDetails> select = q.select(root);
		TypedQuery<PatientDiagnosisDetails> typedQuery = entityManager.createQuery(select);
		List<PatientDiagnosisDetails> results = typedQuery.getResultList();
		return results;
	}

}
