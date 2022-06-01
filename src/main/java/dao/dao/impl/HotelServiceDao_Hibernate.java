package dao.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch04.ude.RecordNotFoundException;
import dao.dao.HotelDao;
import model.Hotel;
import util.HibernateUtils;

public class HotelServiceDao_Hibernate implements HotelDao{
	
	SessionFactory factory = HibernateUtils.getSessionFactory();
	
	@Override
	public Object save(Hotel hol) {
		Session session = factory.getCurrentSession();
		session.save(hol);
		return hol;
	}

	@Override
	public Hotel findById(int id) {
		Hotel hotel = null;
		Session session = factory.getCurrentSession();
		Integer ipk = Integer.valueOf(id);
		hotel = session.get(Hotel.class, id);
		return hotel;
	}

	@Override
	public boolean existsById(String id) {
		return false;
	}

	@Override
	public void update(Hotel hol) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate("Hotel",hol);
	}

	@Override
	public void delete(int id) {
		Session session = factory.getCurrentSession();
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
		String hql = "from Hotel order by id";
		List<Hotel>	allHotels = null;
		Session session = factory.getCurrentSession();
		allHotels = session.createQuery(hql, Hotel.class).getResultList();
		return allHotels;
	}

	@Override
	public void close() {
		factory.close();
	}
	


	
	

}



