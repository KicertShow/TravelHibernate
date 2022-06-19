package backend.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "JasonBean")
public class JasonBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String  hotelname;
	private String  bossname;
	public JasonBean(String hotelname, String bossname) {
		super();
		this.hotelname = hotelname;
		this.bossname = bossname;
	}
	public JasonBean() {
		super();
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getBossname() {
		return bossname;
	}
	public void setBossname(String bossname) {
		this.bossname = bossname;
	}

	
	
	
	

}
