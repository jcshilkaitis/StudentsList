package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.StudentInfo;

/**
 * @author Joe Shilkaitis - jshilkaitis
 * CIS175 - Fall 2022
 * Sep 15, 2022
 */
public class StudentInfoHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudentsList");

	public void	insertStudent(StudentInfo si) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(si);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<StudentInfo>showAllStudents(){
		EntityManager em = emfactory.createEntityManager();
		List<StudentInfo> allItems	= em.createQuery("SELECT i FROM StudentInfo i").getResultList();
		return	allItems;
		}
	
	public void deleteStudent(StudentInfo toDelete)	{
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<StudentInfo> typedQuery	= em.createQuery("select si from StudentInfo si where si.firstName = :selectedFirstName and si.lastName = :selectedLastName", StudentInfo.class);
		
		//Substitute	parameter	with	actual	data	from	the	toDelete	item
		typedQuery.setParameter("selectedFirstName", toDelete.getFirstName());
		typedQuery.setParameter("selectedLastName",	toDelete.getLastName());
		//we	only	want	one	result
		typedQuery.setMaxResults(1);
		//get	the	result	and	save	it	into	a	new	list	item
		StudentInfo result	= typedQuery.getSingleResult();
		//remove	it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		}

	/**
	 * @param storeName
	 * @return
	 */
	public List<StudentInfo> searchForStudentByLastName(String lastName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<StudentInfo> typedQuery = em.createQuery("select	si	from StudentInfo si	where si.lastName = :selectedLastName", StudentInfo.class);
		typedQuery.setParameter("selectedLastName", lastName);
		List<StudentInfo> foundStudentInfo = typedQuery.getResultList();
		em.close();
		return foundStudentInfo;
	}

	/**
	 * @param belt
	 * @return
	 */
	public List<StudentInfo> searchForStudentByRank(String belt) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<StudentInfo> typedQuery = em.createQuery("select	si	from StudentInfo si	where si.belt = :selectedBelt", StudentInfo.class);
		typedQuery.setParameter("selectedBelt", belt);
		List<StudentInfo> foundStudentInfo = typedQuery.getResultList();
		em.close();
		return foundStudentInfo;
	}

	/**
	 * @param idToEdit
	 * @return
	 */
	public StudentInfo searchForStudentById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		StudentInfo found = em.find(StudentInfo.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit
	 */
	public void updateStudent(StudentInfo toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	public	void	cleanUp(){
		emfactory.close();
		}
}
