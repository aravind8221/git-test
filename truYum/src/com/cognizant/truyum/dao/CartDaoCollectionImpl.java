package com.cognizant.truyum.dao;
import com.cognizant.truyum.model.*;
import java.util.List;
import java.util.Set;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CartDaoCollectionImpl implements CartDao{
	
	private static HashMap<Long, Cart> userCarts;
	CartDaoCollectionImpl() {
		if(userCarts==null) {
			userCarts=new HashMap<>();
		}
	}

	@Override
	public void addCartItem(long userId, long menuItemId)  {
		MenuItemDao menuItemDao=null;
		try {
			menuItemDao = new MenuItemDaoCollectionImpl();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MenuItem menuItem=menuItemDao.getMenuItem(menuItemId);
		Set<Long> id=userCarts.keySet();
		double total=menuItem.getPrice();
		if(userCarts.isEmpty() || !(id.contains(userId))) {		// When the hashMap is empty (or) map does not contain user id	
			List<MenuItem> menucart=new ArrayList<>();
			menucart.add(menuItem);
			Cart obj=new Cart(menucart, total);
			userCarts.put(userId, obj);
		}
		else {// When the hashMap contains the user
			Cart obj=userCarts.get(userId);
			List<MenuItem> menucart=obj.getMenuItemList();
			menucart.add(menuItem);  // adding menuitem to the cart
			obj.setMenuItemList(menucart);
			userCarts.put(userId, obj);		
		}
	}

	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		Cart obj=userCarts.get(userId);
		double amount=0;
		if(obj.getMenuItemList().isEmpty()) {
			throw new CartEmptyException("No item is carted");
		}
		else {
			List<MenuItem> obj1=obj.getMenuItemList();
			for(MenuItem mitem : obj1) {
				amount+=mitem.getPrice(); // adding the individual item price
			}
		}
		obj.setTotal(amount); //Setting total price to cart object.
		return obj;
		
	}

	@Override
	public void removeCartItem(long id, long menuItemId) {
		Cart item=userCarts.get(id);
		List<MenuItem> obj=item.getMenuItemList();
		int index=0;
		for(MenuItem mitem:obj) {
			if(mitem.getId()==menuItemId) {
				obj.remove(index);
				break;
			}
			index+=1;
		}
		
	}

}
