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
	public boolean checklog(Logging log) {
		Session session = sessionFactory.openSession();
		String hqlstr = "from Logging where username=:user and user=:pwd";
		Query<Logging> query = session.createQuery(hqlstr,Logging.class);
		query.setParameter("user", log.getUsername());
		query.setParameter("pwd",  log.getUserpwd());
		Logging logging = query.uniqueResult();
		session.close();
		
		if (logging!=null) {
			return true;
		}
		return false;
	}
	

}
