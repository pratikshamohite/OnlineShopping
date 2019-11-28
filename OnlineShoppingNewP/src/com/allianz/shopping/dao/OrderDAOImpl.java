package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.Order;
import com.allianz.shopping.utility.DBUtilityConnection;
import com.allianz.shopping.utility.DateUtility;

public class OrderDAOImpl implements OrderDAO
{

	@Override
	public int addOrder(Order o) {
		int orderID = 0;
		Connection con=DBUtilityConnection.getConnection();
		String sql;
		
		try
		{
			sql="INSERT INTO orders(Order_Date,Status,Username,Total_Price) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setDate(1, DateUtility.convertUtilDateToSQLDate(o.getDate()));
			ps.setString(2,o.getOredr_status());
			ps.setString(3, o.getUserName());
			ps.setFloat(4, o.getPrice());
			int no=ps.executeUpdate();
			if(no>0)

			{
				try(ResultSet generateKeys=ps.getGeneratedKeys())
				{
					if(generateKeys.next())
					{
						orderID=generateKeys.getInt(1);
						System.out.println(orderID);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return orderID;
	}

	@Override
	public List<Order> getAllOrder() {
     Connection con=DBUtilityConnection.getConnection();
     List<Order> orderlist=new ArrayList<Order>();
		String sql="select * from orders";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				Order order=new Order();
				order.setOrder_id(rs.getInt(1));
				order.setDate(rs.getDate(2));
				order.setOredr_status(rs.getString(3));
				order.setUserName(rs.getString(4));
				order.setPrice(rs.getFloat(5));
				orderlist.add(order);
			}
		} catch (SQLException e) {
			
		}
		
		return orderlist;
	}

	@Override
	public Order getOrderById(int order_id) {

		Connection con=DBUtilityConnection.getConnection();
		String sql="select * from orders where Order_ID=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOrder_id(rs.getInt(1));
				order.setDate(rs.getDate(2));
				order.setOredr_status(rs.getString(3));
				order.setUserName(rs.getString(4));
				order.setPrice(rs.getFloat(5));
			}

		} catch (SQLException e) {
			
		}
		
		return null;
	}

	@Override
	public List<Order> getAllOrderByUserName(String userName) {

		Connection con=DBUtilityConnection.getConnection();
		String sql="select * from orders where Username=?";
		List<Order> listOrder=new ArrayList<Order>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOrder_id(rs.getInt(1));
				order.setDate(rs.getDate(2));
				order.setOredr_status(rs.getString(3));
				order.setUserName(rs.getString(4));
				order.setPrice(rs.getFloat(5));
				listOrder.add(order);
			}

		} catch (SQLException e) {
			
		}
		
		return listOrder;
		
		
	}

}
