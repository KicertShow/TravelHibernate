package backend.hotel.model.hotel;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import backend.hotel.error.RecordNotFoundException;



@Repository
@Transactional
public class HotelDao implements IinterFaceHotelDao{
	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	Hotel hotel;
	
	@Override
	public Object save(Hotel hol) {
		Session session = sessionFactory.openSession();
		session.save(hol);
		return hol;
	}

	@Override
	public Hotel findById(int id) {
		Hotel hotel = null;
		Session session = sessionFactory.openSession();
		Integer ipk = Integer.valueOf(id);
		hotel = session.get(Hotel.class, ipk);
		return hotel;
	}

	@Override
	public boolean existsById(String id) {
		return false;
	}

	@Override
	public void update(Hotel hol) {
		Session session = sessionFactory.openSession();
		session.update("Hotel",hol);
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Hotel hotel= findById(id);
		if (hotel ==null) {
			throw new RecordNotFoundException
				("要刪除紀錄不存在: 主鑑值為 :"+id);
		}else {
			session.delete(hotel);
		}
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



