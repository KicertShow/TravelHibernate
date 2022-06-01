package dao.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.dao.HotelDao;
import dao.dao.impl.HotelServiceDao_Hibernate;
import model.Hotel;
import util.HibernateUtils;

public class HotelServiceImpl implements HotelService {
	SessionFactory factory;
	HotelDao hotelDao;
	
	public HotelServiceImpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.hotelDao = new HotelServiceDao_Hibernate();
	}

	

	@Override
	public Object save(Hotel hol) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			obj =hotelDao.save(hol);
			tx.commit();
		} catch (Exception e) {
			if (tx!= null) {
				tx.rollback();
				throw new RuntimeException(e);
			}
		}
		return obj;
	}

	@Override
	public Hotel findById(int id) {
		Hotel mHotel =null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mHotel =hotelDao.findById(id);
			tx.commit();
		} catch (Exception e) {
			if (tx!= null) {
				tx.rollback();
				throw new RuntimeException(e);
			}
		}
		return mHotel;
	}

	@Override
	public boolean existsById(String id) {
		
		return false;
	}

	@Override
	public void update(Hotel hol) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			hotelDao.update(hol);
			tx.commit();
		} catch (Exception e) {
			if (tx!= null) {
				tx.rollback();
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public void delete(int id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			hotelDao.delete(id);
			tx.commit();
		} catch (Exception e) {
			if (tx!= null) {
				tx.rollback();
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public List<Hotel> findAll() {
		List<Hotel> fHotels =null;
		Session session =factory.getCurrentSession();
		Transaction tx =null;
			try {
				tx = session.beginTransaction();
				fHotels = hotelDao.findAll();
				tx.commit();
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
					throw new RuntimeException(e);
				}
			}
		return fHotels;
	}

	@Override
	public void close() {
		hotelDao.close();
	}

}
