package dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import model.Hotel;

public interface interface_DAO { //介面用泛型，實作時要定義自己的model
	
	
	
	
	public List<Hotel> selectAll(); //選取全部
	
	public Hotel findOrderByid(int id);
	
	public void insert(Hotel hotel);
	
	public boolean update(Hotel t); //更新Data，回傳boolean以讓controller可以接收執行成功與否的資訊
	
	public boolean delete(int id); //刪除Data，回傳boolean以讓controller可以接收執行成功與否的資訊
	

	

	
}