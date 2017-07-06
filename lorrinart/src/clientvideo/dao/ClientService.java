package clientvideo.dao;

import java.util.List;

import clientvideo.POJO.Client;

public class ClientService {
	static ClientDaoImpl clientDaoImpl;

	public ClientService() {
		clientDaoImpl = new ClientDaoImpl();
	}

	public void insert(Client entity) {
		clientDaoImpl.openCurrentSessionWithTransaction();
		System.out.println("before insert");
		clientDaoImpl.insert(entity);
		System.out.println("after insert");
		clientDaoImpl.closeCurrentSessionwithTransaction();

	}

	public void update(Client entity) {
		clientDaoImpl.openCurrentSessionWithTransaction();
		clientDaoImpl.update(entity);
		clientDaoImpl.closeCurrentSessionwithTransaction();
	}

	public Client findByUsername(String username) {
		clientDaoImpl.openCurrentSession();
		Client client = clientDaoImpl.findByUsername(username);
		
		clientDaoImpl.closeCurrentSession();
		return client;
	}

	public void delete(Client entity) {
		clientDaoImpl.openCurrentSessionWithTransaction();
		clientDaoImpl.delete(entity);
		clientDaoImpl.closeCurrentSessionwithTransaction();
	}

	public List<Client> findAll() {
		clientDaoImpl.openCurrentSession();
		List<Client> clients = clientDaoImpl.findAll();
		return clients;
	}

}
