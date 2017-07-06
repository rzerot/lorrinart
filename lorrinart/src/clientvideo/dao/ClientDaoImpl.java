package clientvideo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import clientvideo.POJO.Client;
import clientvideo.util.HibernateUtil;

public class ClientDaoImpl implements ClientDao<Client, String> {
	private Session currentSession;
	private Transaction currentTransaction;

	public Session openCurrentSession() {
		currentSession = HibernateUtil.getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionWithTransaction() {
		currentSession = HibernateUtil.getSessionFactory().openSession();
		System.out.println("BETWEEN OPEN SESSION AND GET TRANSACTION");
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		System.out.println("BETWEEN COMMIT AND CLOSE");
		currentSession.close();

	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;

	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;

	}

	@Override
	public void insert(Client entity) {
		getCurrentSession().save(entity);

	}

	@Override
	public void update(Client entity) {
		getCurrentSession().update(entity);

	}

	@Override
	public Client findByUsername(String username) {
		Client client = getCurrentSession().get(Client.class, username);
		System.out.println(client.getUsername());
		return client;
	}

	@Override
	public void delete(Client entity) {
		getCurrentSession().delete(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAll() {
		List<Client> clients = getCurrentSession().createQuery("from Client")
				.list();
		for(Client c: clients){
			System.out.println(c.getUsername());
		}
		return clients;
	}

}
