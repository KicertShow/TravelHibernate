package dao.service;

import java.util.List;

import model.Hotel;

public interface HotelService {
	public Object save(Hotel hol);

	public Hotel findById(int id);

	public boolean existsById(String id);

	public void update(Hotel hol);

	public void delete(int id);

	public List<Hotel> findAll();

	public void close();
}
