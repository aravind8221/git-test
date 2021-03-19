package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.*;

public class MenuItemDaoCollectionImplTest {
	public static void main(String[] args) {
		try {
			MenuItemDao obj=new MenuItemDaoCollectionImpl();
			testGetMenuItemListAdmin(obj);
			testGetMenuItemListCustomer(obj);
			testModifyMenuItem(obj);
			testGetMenuItemListAdmin(obj);
			testGetMenuItemListCustomer(obj);
		} catch (ParseException e) {
			System.out.println("Enter the date in format of dd/mm/yyyy");
		}
	}
	
	public static void testgetMenuitem(MenuItemDao obj, MenuItem obj1) {
		if(obj.getMenuItem(obj1.getId())!=null) {
			System.out.println("The Menu modified successfully");
		}
		else {
			System.out.println("There is no menu with specified and hence not modified");
			
		}
	}
	public static void testModifyMenuItem(MenuItemDao obj) throws ParseException {
		MenuItem obj1=new MenuItem(1, "Pizza", 200.00f, false, DateUtil.convertTo("18/03/2021"),  "MainCourse", true);
		obj.modifyMenuItem(obj1);
		MenuItemDaoCollectionImplTest.testgetMenuitem(obj, obj1);
	}
	
	public static void testGetMenuItemListAdmin(MenuItemDao adminMenuObj)  {
		
		List<MenuItem> menuitems=adminMenuObj.getMenuItemListAdmin();
		System.out.println("Admin Menu");
		
		for(MenuItem menuitem: menuitems) {
			System.out.println(menuitem);
		}
		
	}
	public static void testGetMenuItemListCustomer(MenuItemDao custmorMenuObj) {
		List<MenuItem> customermenu=custmorMenuObj.getMenuItemListCustomer();
		
		System.out.println("Customer menu");
		if(customermenu==null || customermenu.isEmpty()) {
			System.out.println("No menu found");
		}
		
		else {
			for(MenuItem cusmenu: customermenu) {
				System.out.println(cusmenu);
			}
		}
	}

}
