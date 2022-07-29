package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("carPartsInv1")
public class CarPartsInventoryImpl1 implements CarPartsInventory {

	public void addNewPart(CarPart carPart) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//long ms1 = System.currentTimeMillis();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "passw0rd");
			//long ms2 = System.currentTimeMillis();
			//System.out.println("Approx time to connect : " + (ms2 - ms1) +" ms");
			st = conn.prepareStatement("insert into tbl_carpart values(?, ?, ?, ?, ?)");
			
			st.setInt(1, carPart.getPartNo());
			st.setString(2, carPart.getPartName());
			st.setString(3, carPart.getCarModel());
			st.setDouble(4, carPart.getPrice());
			st.setInt(5, carPart.getQuantity());
			st.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}

	public List<CarPart> getAvailableParts() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "passw0rd");
			st = conn.prepareStatement("select * from tbl_carpart");
			rs = st.executeQuery();
			
			List<CarPart> list = new ArrayList<>();
			while(rs.next()) {
				CarPart cp = new CarPart();
				cp.setPartNo(rs.getInt("part_no"));
				cp.setPartName(rs.getString("part_name"));
				cp.setCarModel(rs.getString("car_model"));
				cp.setPrice(rs.getDouble("price"));
				cp.setQuantity(rs.getInt("quantity"));
				list.add(cp);
			}
			return list;
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null; //bad, rather we should have thrown the exception
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}

	}

}
