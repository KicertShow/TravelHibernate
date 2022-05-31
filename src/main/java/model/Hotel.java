package model;

import java.net.Inet4Address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Hotelpro3")
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String hotel_name;
	private Integer price;
	private String boss_name;
	private String phone;
	private String status;
	private String roomtype;

	public Hotel() {
		super();
	}

	
	
	
	public Hotel(String hotel_name, Integer price, String boss_name, String phone, String status, String roomtype) {
		this.hotel_name = hotel_name;
		this.price = price;
		this.boss_name = boss_name;
		this.phone = phone;
		this.status = status;
		this.roomtype = roomtype;
	}

	



	public Hotel(Integer id, String hotel_name, Integer price, String boss_name, String phone, String status, String roomtype) {
		this.id = id;
		this.hotel_name = hotel_name;
		this.price = price;
		this.boss_name = boss_name;
		this.phone = phone;
		this.status = status;
		this.roomtype = roomtype;
	}




	public Hotel( String hotel_name, Integer price, String boss_name, String phone, String status, Integer id) {
		
		this.hotel_name = hotel_name;
		this.price = price;
		this.boss_name = boss_name;
		this.phone = phone;
		this.status = status;
		this.roomtype = roomtype;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getBoss_name() {
		return boss_name;
	}

	public void setBoss_name(String boss_name) {
		this.boss_name = boss_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

}	