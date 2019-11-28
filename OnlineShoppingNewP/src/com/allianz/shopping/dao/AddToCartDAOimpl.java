package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.AddToCart;
import com.allianz.shopping.model.Order;
import com.allianz.shopping.utility.DBUtilityConnection;

public class AddToCartDAOimpl implements AddToCartDAO  
{
	@Override
	public boolean addToCart(AddToCart add)
	{
		Connection con=DBUtilityConnection.getConnection();
		String sql;
		try
		{
			sql="INSERT INTO add_to_cart(Order_ID,productID,quantity,Total_price) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setInt(1, add.getOrder_id());
			ps.setInt(2,add.getProduct_id());
			ps.setInt(3, add.getQuantity());
			ps.setFloat(4,add.getTotal());
			int no=ps.executeUpdate();
			if(no>0)
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AddToCart> getAllAddToCartByOrder(int order_id) {
   Connection con=DBUtilityConnection.getConnection();
   String sql="select * from add_to_cart where Order_ID=?";
   List<AddToCart> listAdd=new ArrayList<AddToCart>();
   try {  
   PreparedStatement ps = con.prepareStatement(sql);
   ps.setInt(1, order_id);
ResultSet rs=ps.executeQuery();
while(rs.next()) {
	AddToCart add=new AddToCart();
	add.setAdd_To_Cart_id(rs.getInt(1));
	add.setOrder_id(rs.getInt(2));
	add.setProduct_id(rs.getInt(3));
	add.setQuantity(rs.getInt(4));
	add.setTotal(rs.getFloat(5));
listAdd.add(add);
}
	
} catch (SQLException e) {
	
}
  
		
		
		
		
		return listAdd;
	}

	
		
		
	
	

	}
