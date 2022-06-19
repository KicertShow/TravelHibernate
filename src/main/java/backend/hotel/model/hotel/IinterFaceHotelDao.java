package backend.hotel.model.hotel;

import java.util.List;


public interface IinterFaceHotelDao {
	public Object save(Hotel hol);

	public Hotel findById(int id);

	public boolean existsById(String id);

	public void update(Hotel hol);

	public void delete(int id);

	public List<Hotel> findAll();

	public void close();
}