package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Hotel;


@Service
@Transactional
public class HotelService implements InterFaceHotelService {
	HotelDao hotelDao;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Object save(Hotel hol) {
	
			hotelDao.save(hol);
			
		return hol;
	}

	@Override
	public Hotel findById(int id) {
		Hotel mHotel =null;
			mHotel =hotelDao.findById(id);
			return mHotel;
		}

	@Override
	public boolean existsById(String id) {
		
		return false;
	}

	@Override
	public void update(Hotel hol) {
	
			hotelDao.update(hol);
		
	}

	@Override
	public void delete(int id) {
	
			hotelDao.delete(id);
		

	}

	@Override
	public List<Hotel> findAll() {
		String hql = "from Hotel order by id";
		List<Hotel>	allHotels = null;
		Session session = sessionFactory.openSession();
		allHotels = session.createQuery(hql, Hotel.class).getResultList();
		return allHotels;
	}

	@Override
	public void close() {
		hotelDao.close();
	}

}
