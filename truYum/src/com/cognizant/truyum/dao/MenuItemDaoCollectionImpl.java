package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.*;

public class MenuItemDaoCollectionImpl implements MenuItemDao{
	
	private static List<MenuItem> menuItemList;
	
	public MenuItemDaoCollectionImpl() throws ParseException {
		if(menuItemList==null) {
			menuItemList=new ArrayList<>();
		}
		
		MenuItem menuitem1=new MenuItem(1, "Pizza", 150.00f, true, DateUtil.convertTo("17/3/2021"), "MainCourse", true);
		MenuItem menuitem2=new MenuItem(2, "Burger", 250.00f, true, DateUtil.convertTo("18/03/2021"), "MainCourse", true);
		menuItemList.add(menuitem1);
		menuItemList.add(menuitem2);
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return MenuItemDaoCollectionImpl.menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		LocalDate now=LocalDate.now();
		LocalDate yesterday=now.minusDays(1);	
		
		List<MenuItem> customerMenu=new ArrayList<>();

		for(MenuItem menuitems : MenuItemDaoCollectionImpl.menuItemList) {
			Date d1=menuitems.getDateOfLaunch();
			LocalDate d2=d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if((d2.equals(yesterday) || d2.equals(now)) && menuitems.isActive()) {
				customerMenu.add(menuitems);
			}
		}
		return customerMenu;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		int i=0;
		for(MenuItem m: MenuItemDaoCollectionImpl.menuItemList) {
			if(m.getId()==menuItem.getId()) {
				menuItemList.remove(i);
				menuItemList.add(i, menuItem);
				break;
			}
			i+=1;
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		for(MenuItem m: MenuItemDaoCollectionImpl.menuItemList) {
			if(m.getId()==menuItemId)
				return m; 
		}
		return null;
	}

}
