package com.cognizant.truyum.dao;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class CartDaoSqlImpl implements CartDao{

    @Override
    public void addCartItem(long userId, long menuItemId) {
        try {
            Connection conn = ConnectionHandler.getConnection();
            String query = "insert into cart(user_id, menu_item_id) values(?, ?)";
            PreparedStatement pt = conn.prepareStatement(query);
            pt.setLong(1, userId);
            pt.setLong(2, menuItemId);
            pt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Cart getAllCartItems(long userId) throws CartEmptyException {
        try {
            Connection conn = ConnectionHandler.getConnection();
            List<MenuItem> menuItemList = new ArrayList<>();
            double total = 0.0;
            String query = "select * from menu_item mi inner join cart c on c.menu_item_id = mi.id where c.user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1,userId);
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
            Cart cart = new Cart(menuItemList, total);
            String query2 = "select sum(mi.price) from cart c inner join menu_item mi on mi.id=c.menu_item_id where c.user_id=?";
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setLong(1,userId);
            ResultSet result2 = pstmt2.executeQuery();
            while (result2.next()){
                //System.out.println("price:" + result2.getFloat(1));
                cart.setTotal((double) result2.getFloat(1));
            }
            return (Cart) menuItemList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeCartItem(long userId, long menuItemId) {
        try {
            Connection conn = ConnectionHandler.getConnection();
            String query = "delete from cart where user_id=? and menu_item_id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, userId);
            pstmt.setLong(2, menuItemId);
            pstmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}