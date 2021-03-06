package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public interface CartDao {
	void addCartItem(long userId, long menuItemId);
	Cart getAllCartItems(long userId) throws CartEmptyException;
	void removeCartItem(long id, long menuItemId);
}
