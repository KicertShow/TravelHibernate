package backend.hotel.model.hotel;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




@Repository
public class HotelDao implements IinterFaceHotelDao{
	
	
	SessionFactory sessionFactory;
	Hotel hotel;
	@Autowired
	public HotelDao(SessionFactory sessionFactory, Hotel hotel) {
		super();
		this.sessionFactory = sessionFactory;
		this.hotel = hotel;
	}

	@Override
	public Object save(Hotel hol) {
		Session session = sessionFactory.openSession();
		session.save(hol);
		return hol;
	}

	@Override
	public Hotel findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Hotel hotelBean = session.get(Hotel.class, id);
		return hotelBean;
	}

	@Override
	public boolean existsById(String id) {
		return false;
	}

	@Override
	public void update(Hotel hol) {
		Session session = sessionFactory.getCurrentSession();
		session.update("Hotel",hol);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Hotel hotelBean = session.byId(Hotel.class).load(id);
		session.delete(hotelBean);
	}

	@Override
	public List<Hotel> findAll() {
		String hql = "from Hotel ";
		List<Hotel>	allHotels = null;
		Session session = sessionFactory.openSession();
		allHotels = session.createQuery(hql, Hotel.class).getResultList();
		return allHotels;
	}

	@Override
	public void close() {
		Session session = sessionFactory.openSession();
		session.close();
	}
	


	
	

}



