package org.hospitalmanagementsystem.databaseadministrator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hospitalmanagementsystem.entities.PatientDiagnosisDetails;
import org.hospitalmanagementsystem.entities.Physician;
import org.hospitalmanagementsystem.entities.PhysicianLoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Physician_DAO {

	@Autowired
	@Qualifier(value = "entityManager")
	EntityManager entityManager;

	private static final Logger logger = LogManager.getLogger(HMS_DAO.class.getName());

	@Transactional
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

}
