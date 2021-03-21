package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {
	public static void main(String args[])  {
		try {
			CartDao obj=new CartDaoCollectionImpl();
			testAddCartItem(obj);
			getAllCartItems(obj);
			testRemoveCartItem(obj);
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
	}

	public static void testRemoveCartItem(CartDao obj) {
		long userId=1;
		long menuitem=1;
		obj.removeCartItem(userId, menuitem);
		try {
			Cart obj1=obj.getAllCartItems(userId);
			List<MenuItem> mitem=obj1.getMenuItemList();
			for(MenuItem m: mitem) {
				System.out.println(m);
			}
		} catch (CartEmptyException e) {
			e.getMessage();
		}
		
		
	}

	public static void getAllCartItems(CartDao obj) throws CartEmptyException {
		
		System.out.println("All the cart items are: ");
		Cart obj1=obj.getAllCartItems(1);
		List<MenuItem> menuitem=obj1.getMenuItemList();
		for(MenuItem m:menuitem)
				System.out.println(m);
	}

	public static void testAddCartItem(CartDao obj) throws CartEmptyException {
		obj.addCartItem(1, 1);
		Cart obj1=obj.getAllCartItems(1);
		List<MenuItem> menuitem=obj1.getMenuItemList();
		for(MenuItem m:menuitem)
				System.out.println(m);
	}
	
}
