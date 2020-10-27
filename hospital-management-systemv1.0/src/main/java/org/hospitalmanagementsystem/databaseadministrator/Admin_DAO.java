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
public class Admin_DAO {

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
	public List<PatientDiagnosisDetails> getAllDiagnosisReports() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientDiagnosisDetails> q = cb.createQuery(PatientDiagnosisDetails.class);
		Root<PatientDiagnosisDetails> root = q.from(PatientDiagnosisDetails.class);
		CriteriaQuery<PatientDiagnosisDetails> select = q.select(root);
		TypedQuery<PatientDiagnosisDetails> typedQuery = entityManager.createQuery(select);
		List<PatientDiagnosisDetails> results = typedQuery.getResultList();
		return results;
	}

	
	
	private String generateEmailForPhysician(Physician physician) {
		return physician.getFirstName() + "-" + physician.getLastName() + EMAIL_SUFFIX;
	}

}
