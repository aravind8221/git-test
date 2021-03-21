package com.cognizant.truyum.dao;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {

    public static void main(String[] args) {
        System.out.println("Admin List:");
        testGetMenuItemListAdmin();

        System.out.println("Customer List:");
        testGetMenuItemListCustomer();

        System.out.println("Modified Item");
        testModifyMenuItem();

        //testGetMenuItem();
    }

    private static void testModifyMenuItem() {
        MenuItem burger = new MenuItem(200, "Burger", 170.00f, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false);
        MenuItemDaoSqlImpl menuItemDaoSql = new MenuItemDaoSqlImpl();
        menuItemDaoSql.modifyMenuItem(burger);
        System.out.println(menuItemDaoSql.getMenuItem(200));

    }

    private static void testGetMenuItemListCustomer() {
        MenuItemDaoSqlImpl menuItemDaoSql = new MenuItemDaoSqlImpl();
        for (MenuItem item: menuItemDaoSql.getMenuItemListCustomer()) {
            System.out.println(item.toString());
        }

    }

    private static void testGetMenuItemListAdmin() {
        MenuItemDaoSqlImpl menuItemDaoSql = new MenuItemDaoSqlImpl();
        for (MenuItem item: menuItemDaoSql.getMenuItemListAdmin()) {
            System.out.println(item.toString());
        }
    }
}