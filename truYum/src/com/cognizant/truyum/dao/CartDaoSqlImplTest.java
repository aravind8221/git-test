package com.cognizant.truyum.dao;
import com.cognizant.truyum.model.MenuItem;

import java.util.List;
public class CartDaoSqlImplTest{

    public static void main(String[] args) {
        //System.out.println("Adding items into Cart;");
        //testAddCartItem();

        System.out.println("Getting all Cart Items:");
        testGetAllCartItem();

        System.out.println("After removing a cart Item:");
        testRemoveCartItem();
    }

    private static void testAddCartItem() {
        CartDaoSqlImpl cartDaoSql = new CartDaoSqlImpl();
        cartDaoSql.addCartItem(1,100);
        cartDaoSql.addCartItem(2,500);
        cartDaoSql.addCartItem(1,300);

        try {
            List<MenuItem> cartItems = (List<MenuItem>) cartDaoSql.getAllCartItems(1);
            for (MenuItem item: cartItems) {
                System.out.println(item.toString());
            }

        } catch (CartEmptyException e) {
            e.printStackTrace();
        }

    }

    private static void testGetAllCartItem() {
        CartDaoSqlImpl cartDaoSql = new CartDaoSqlImpl();
        try {
            List<MenuItem> cartItems = (List<MenuItem>) cartDaoSql.getAllCartItems(1);
            for (MenuItem item: cartItems) {
                System.out.println(item.toString());
            }

        } catch (CartEmptyException e) {
            e.printStackTrace();
        }

    }

    private static void testRemoveCartItem() {
        CartDaoSqlImpl cartDaoSql = new CartDaoSqlImpl();
        cartDaoSql.removeCartItem(1, 100);
        try {
            List<MenuItem> cartItems = (List<MenuItem>) cartDaoSql.getAllCartItems(1);
            for (MenuItem item: cartItems) {
                System.out.println(item.toString());
            }
        } catch (CartEmptyException e) {
            e.printStackTrace();
        }

    }
}