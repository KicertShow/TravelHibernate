//package dao.dao.impl;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.NamingException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//
//import model.Hotel;
//import util.DBConnection;
//
//public class Fun_HotelDAO implements interface_DAO{
//	private static final String SELECT_ALL_SQL = "SELECT * FROM Hotel order by id";
//	private static final String SELECT_ORDERS_BY_ID_SQL = "select hotel_name, price, boss_name, phone, status, roomtype from Hotel where Id = ?";
//	private static final String INSERT_SQL = "INSERT INTO Hotel VALUES (?,?,?,?,?,?)";
//	private static final String UPDATE_SQL = "UPDATE Hotel set hotel_name=?, price=? ,boss_name = ?, phone=?,status=?,roomtype=? where id=?";
//	private static final String DELETE_SQL = "DELETE Hotel WHERE ID = ?";
//
//
//
//	
//	
//
//	@Override
//	public boolean update(Hotel hotel) {
//			boolean isUpdated = false;
//			int count;
//			try {
//				Connection conn;
//				PreparedStatement pstmt;
//				conn = DBConnection.getConnectionObject();
//				PreparedStatement ps = conn.prepareStatement(UPDATE_SQL);
//				System.out.println("update hotel"+ps);
//				ps.setString(1, hotel.getHotel_name());
//				ps.setInt(2, hotel.getPrice());
//				ps.setString(3, hotel.getBoss_name());
//				ps.setString(4, hotel.getPhone());
//				ps.setString(5, hotel.getStatus());
//				ps.setString(6, hotel.getRoomtype());
//				ps.setInt(7, hotel.getId());
//				int rowUpdate = ps.executeUpdate();
//				System.out.println("更新幾筆"+rowUpdate);
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				DBConnection.closeConnection();
//				
//			}
//			return isUpdated;
//		}
//	
//
//	@Override
//	public List<Hotel> selectAll() {
//		Connection con =null;
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		List<Hotel> hotels = new ArrayList<>();
//		
//			try {
//				Context ctx = new javax.naming.InitialContext();
//				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/travel_web");
//				con = ds.getConnection();
//				pstm = con.prepareStatement(SELECT_ALL_SQL);
//				rs = pstm.executeQuery();
//			while (rs.next()) {
//				
//				int    ID=rs.getInt("ID");
//				String hotelName=rs.getString("hotel_name");
//				int    price = rs.getInt("price");
//				String boss_name= rs.getString("boss_name");
//				String    phone = rs.getString("phone");
//				String status =rs.getString("status");
//				String roomtype = rs.getString("roomtype");
//				hotels.add( new Hotel(ID, hotelName, price, boss_name, phone, status, roomtype));
//				
//			}
//			rs.close();
//			pstm.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}catch (NamingException e2) {
//			e2.printStackTrace();
//		}
//		 finally {
//			DBConnection.closeConnection();
//		}
//		return hotels;
//	}
//
//	
//
//	@Override
//	public boolean delete(int id) {
//		boolean isDeleted = false;
//		Connection conn;
//		PreparedStatement pstmt;
//		int count;
//		try {
//			conn = DBConnection.getConnectionObject();
//			pstmt = conn.prepareStatement(DELETE_SQL);
//			pstmt.setInt(1, id);
//			count = pstmt.executeUpdate();
//			if (count > 0) isDeleted = true;
//			pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBConnection.closeConnection();
//		}
//		return isDeleted;
//	}
//
//
//	@Override
//	public void insert(Hotel hotel) {
//		boolean isInserted = false;
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//		
//		try {
//			conn = DBConnection.getConnectionObject();
//			pstmt = conn.prepareStatement(INSERT_SQL); // 要放進statement，才能回傳ID
//			pstmt.setString(1, hotel.getHotel_name());
//			pstmt.setInt(2, hotel.getPrice());
//			pstmt.setString(3, hotel.getBoss_name());
//			pstmt.setString(4, hotel.getPhone());
//			pstmt.setString(5, hotel.getStatus());
//			pstmt.setString(6, hotel.getRoomtype());
//		     int count1 =pstmt.executeUpdate();
//			System.out.println("成功新增"+count1);
//			
//			
//			pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBConnection.closeConnection();
//		}
//
//	}
//
//
//	
//	
//	@Override
//	public   Hotel findOrderByid(int a) {
//		Hotel	hotels =null;
//		Connection con =null;
//		
//			try {
//				Context ctx = new javax.naming.InitialContext();
//				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/travel_web");
//				con = ds.getConnection();
//				PreparedStatement pstm = con.prepareStatement(SELECT_ORDERS_BY_ID_SQL);
//				pstm.setInt(1, a);
//				  ResultSet rs = pstm.executeQuery();
//			while (rs.next()) {
//				String hotelName=rs.getString("hotel_name");
//				int    price = rs.getInt("price");
//				String boss_name= rs.getString("boss_name");
//				String    phone = rs.getString("phone");
//				String status =rs.getString("status");
//				String roomtype = rs.getString("roomtype");
//				hotels = new Hotel(a, hotelName, price, boss_name, phone, status, roomtype);
//				
//			}
//			pstm.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}catch (NamingException e2) {
//			e2.printStackTrace();
//		}
//		 finally {
//			DBConnection.closeConnection();
//		}
//		return hotels;
//	}
//	
//	
//	
//	}
//
//
//
