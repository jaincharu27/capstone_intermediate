package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import actions.Capstone_actions;
import io.qameta.allure.Allure;

public class TestStock extends BaseTest{
	
	@Test(description = "Verify stock information display")
	public void verify_stock_info_display() {
		Capstone_actions stock_actions = new Capstone_actions(getDriver());
		Allure.step("Enter stock name in search box");
		stock_actions.enter_stock_name_in_search_box("adanient");
		Allure.step("Select related stock from search listing");
		stock_actions.select_stock_from_listing();
		Allure.step("Verify navigation to stock detail page");
		stock_actions.verify_stock_search_page();
		Allure.step("Verify price data for 52 week High of the stock");
		String value_52_week_high = stock_actions.get_text_52_week_high();
		System.out.println(value_52_week_high);
		Assert.assertEquals(value_52_week_high, "3,743.90");//2.58
		log.info("Price data 52 week high matched with actual data");
		Allure.step("Verify price data for 52 week Low of the stock");
		String value_52_week_low = stock_actions.get_text_52_week_low();
		System.out.println(value_52_week_low);
		Assert.assertEquals(value_52_week_low, "2,025.00");//1.45
		log.info("Price data 52 week low matched with actual data");
	}
}
