package backend.hotel.model.hotel;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.hotel.model.hotel.Hotel;


@Service
public class HotelService implements InterFaceHotelService {
	
	@Autowired
	IinterFaceHotelDao IinterFaceHotelDao;
	

	@Override
	public Object save(Hotel hol) {
	
		IinterFaceHotelDao.save(hol);
			
		return hol;
	}

	@Override
	public Hotel findById(int id) {
		Hotel mHotel =null;
			mHotel =IinterFaceHotelDao.findById(id);
			return mHotel;
		}

	@Override
	public boolean existsById(String id) {
		
		return false;
	}

	@Override
	public void update(Hotel hol) {
	
		IinterFaceHotelDao.update(hol);
		
	}

	@Override
	public void delete(int id) {
	
		IinterFaceHotelDao.delete(id);
		

	}

	@Override
	public List<Hotel> findAll() {
		return IinterFaceHotelDao.findAll();
	}

	@Override
	public void close() {
		IinterFaceHotelDao.close();
	}

}
