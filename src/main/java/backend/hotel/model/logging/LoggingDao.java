package backend.hotel.model.logging;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class LoggingDao implements LoggingInterFaceDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public boolean checklog(Logging users) {
		Session session = sessionFactory.openSession();
		String hqlsrt ="from Logging where username=:user and userpwd=:pwd";
		 Query<Logging> query = session.createQuery(hqlsrt,Logging.class);
		query.setParameter("user",users.getUsername());
		query.setParameter("pwd",users.getUserpwd());
		Logging account = query.uniqueResult();
		session.close();
		
		if (account!=null) {
			return true;
		}
		return false;
	}
	
	

}

