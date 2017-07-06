package clientvideo.util;

import java.util.List;

import org.hibernate.Session;

import clientvideo.POJO.Client;
import clientvideo.dao.ClientDaoImpl;

public class HibernateMain {

	public boolean checkIdUnique(Session session, String username) {
		@SuppressWarnings("unchecked")
		List<Client> clients = session
				.createQuery(
						"select username from Client p "
								+ "where p.username like :name")
				.setParameter("name", username).list(
						
						
						
						
						
						
						);
		if (clients.size() == 0) {
			return true;
		}
		return false;

	}

	public boolean createAndSave(String linkOne, String linkTwo,
			String nameOne, String nameTwo, String nameOneImage,
			String nameTwoImage, String username, String clientPass) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (checkIdUnique(session, username)) {
			Client client = new Client();
			client.setLinkOne(linkOne);
			client.setLinkTwo(linkTwo);
			client.setNameOne(nameOne);
			client.setNameTwo(nameTwo);
			client.setNameOneImage(nameOneImage);
			client.setNameTwoImage(nameTwoImage);
			client.setUsername(username);
			client.setClientPass(clientPass);
			session.save(client);
			// session.save(user);
			session.getTransaction().commit();
			HibernateUtil.shutdown();
			return true;
		} else {
			HibernateUtil.shutdown();
			return false;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientDaoImpl client = new ClientDaoImpl();
		client.openCurrentSession();
		client.findAll();
	
	}
}
