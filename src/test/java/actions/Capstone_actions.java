package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Capstone_actions extends BasePage{
	
	@FindBy(id = "header-search-input") public WebElement search_box;
	@FindBy(id = "header-search-input_listbox") public WebElement typeahead;
	@FindBy(className = "line1") public WebElement first_option;
	@FindBy(linkText = "Checkboxes") public WebElement Checkboxes_link;
	@FindBy(id = "quoteName") public WebElement stock_name_header;
	@FindBy(id = "week52highVal") public WebElement week_52_high;
	@FindBy(id = "week52lowVal") public WebElement week_52_low;

	WebDriverWait wait;
	
	public Capstone_actions(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void enter_stock_name_in_search_box(String stock_name) {
		waitForElementToAppear(search_box);
		log.info("Search box found");
		search_box.click();
		log.info("Search box clicked");
		search_box.sendKeys(stock_name);
		log.info("Stock name: "+ stock_name + " entered in search box");
	}

	public void select_stock_from_listing(){
		waitForElementToAppear(typeahead);
		log.info("Search listing found");
		waitForElementToAppear(first_option);
		log.info("Stock name found");
		first_option.click();
		log.info("Stock name clicked from search listing");
		search_box.click();
	}

	public String verify_stock_search_page() {
		waitForElementToAppear(stock_name_header);
		log.info("Navigated to stock detail page");
		return stock_name_header.getText();
	}

	public String get_text_52_week_high() {
		waitForElementToAppear(week_52_high);
		waitForTextToAppear(week_52_high, "week52highVal");
		log.info("Stock price data 52 week high found");
		return week_52_high.getText();
	}
	
	public String get_text_52_week_low() {
		waitForElementToAppear(week_52_low);
		waitForTextToAppear(week_52_low, "week52lowVal");
		log.info("Stock price data 52 week low found");
		return week_52_low.getText();
	}
	
}
