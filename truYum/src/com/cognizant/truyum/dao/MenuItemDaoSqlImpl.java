package com.cognizant.truyum.dao;
import com.cognizant.truyum.model.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuItemDaoSqlImpl implements MenuItemDao{

    
    public List<MenuItem> getMenuItemList(String query) {

        try {
            Connection conn = ConnectionHandler.getConnection();
            List<MenuItem> menuItemList = new ArrayList<>();
 
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet result = pstmt.executeQuery();
            while (result.next()){
                long id = result.getInt(1);
                String name = result.getString(2);
                float price = result.getFloat(3);
                boolean active = result.getBoolean(4);
                Date dateOfLaunch = result.getDate(5);
                String category = result.getString(6);
                boolean freeDelivery = result.getBoolean(7);
                MenuItem menuItem = new MenuItem(id, name, price, active, dateOfLaunch, category, freeDelivery);
                menuItemList.add(menuItem);
            }
            return  menuItemList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MenuItem> getMenuItemListAdmin() {
		 String query = "select * from menu_item";
		 return getMenuItemList (query) ;
	}
    
    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        
            String query = "select * from menu_item where active is true and date_of_launch < curdate()";
           return getMenuItemList(query);
          
    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        try {
            Connection conn = ConnectionHandler.getConnection();
            String query = "update menu_item set name=?, price=?, active=?, date_of_launch=?, category=?, free_delivery=? where id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, menuItem.getName());
            pstmt.setFloat(2, menuItem.getPrice());
            pstmt.setBoolean(3, menuItem.isActive());
            //pstmt.setDate(4,  menuItem.getDateOfLaunch());
            pstmt.setDate(4, new java.sql.Date(menuItem.getDateOfLaunch().getTime()));
            pstmt.setString(5, menuItem.getCategory());
            pstmt.setBoolean(6, menuItem.isFreeDelivery());
            pstmt.setLong(7,menuItem.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public MenuItem getMenuItem(long menuItemId) {
        try {
            Connection conn = ConnectionHandler.getConnection();
            //List<MenuItem> menuItemList = new ArrayList<>();
            String query = "select * from menu_item where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, menuItemId);
            ResultSet result = pstmt.executeQuery();
            long id = 0;
            String name = null;
            float price = 0;
            boolean active = false;
            Date dateOfLaunch = null;
            String category = null;
            boolean freeDelivery = false;
            while(result.next()){
                id = result.getLong(1);
                name = result.getString(2);
                price = result.getFloat(3);
                active = result.getBoolean(4);
                dateOfLaunch = result.getDate(5);
                category = result.getString(6);
                freeDelivery = result.getBoolean(7);
            }
            MenuItem menuItem = new MenuItem(id, name, price,active, dateOfLaunch, category,freeDelivery);
            //menuItem.add(menuItem);
            return menuItem;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}