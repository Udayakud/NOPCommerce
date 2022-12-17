package com.libglobal;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyDownAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LibGlobal {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public static String path;

	public static WebDriver driver;
	static WebElement findElement;
	static Actions a;
	static Robot r;
	static JavascriptExecutor js;
	static Alert alert;
	static Select s;
	static Boolean multiple;
	static String select_FirstSelectedOption;
	static List <WebElement> select_AllSelectedOptions;
	static List <WebElement> allOptions;
	static String parId;
	static Set<String> allIds;
	static List<String> li;
	static TakesScreenshot ts;
	static List<WebElement> findElements;
	
	// 1 browser config
	public static void launchBrowser (String browserName) {	
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;		
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	// 2 browser launch
	public static void launchUrl (String url) {
		driver.get(url);
	}
	// 3 driver.findElement
	public static WebElement locator(String locatorName, String value) {
		if (locatorName.equals("id")) {
			findElement = driver.findElement(By.id(value));
		}
		else if (locatorName.equals("name")){
			findElement = driver.findElement(By.name(value));
		}
		else if (locatorName.equals("xpath")){
			findElement = driver.findElement(By.xpath(value));
		}
		else if (locatorName.equals("tagname")) {

			findElement = driver.findElement(By.tagName(value));
		}
		else if (locatorName.equals("classname")) {

			findElement = driver.findElement(By.className(value));

		} else if (locatorName.equals("linktext")) {

			findElement = driver.findElement(By.linkText(value));

		} else if (locatorName.equals("partlink")) {

			findElement = driver.findElement(By.partialLinkText(value));

		} else if (locatorName.equals("css")) {

			findElement = driver.findElement(By.cssSelector(value));
		}
		return findElement;			
	}
	// 4 close
	public static void close () {
		driver.close();
	}
	// 5 quit
	public static void quit() {
		driver.quit();
	}
	// 6 get title
	public static String title() {
		String title = driver.getTitle();
		return title;
		//		System.out.println("Webpage Title is : "+title);
	}
	// 7 get current url
	public static String url() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;		
//		System.out.println("Webpage Current URL is : "+currentUrl);
	}	
	// 8 sendkeys
	public static void sendKeys (WebElement ref, String text) {
		ref.sendKeys(text);
	}
	// 9 click
	public static void click (WebElement ref) {
		ref.click();
	}
	// 10 clear
	public static void clear (WebElement ref) {
		ref.clear();
	}
	// 11 get text
	public static String getText(WebElement ref) {
		String text = ref.getText();
		return text;
		
	}
	// 12 dimension
	public static void windowSize(int x,int y) {
		Dimension d = new Dimension (x,y);
		driver.manage().window().setSize(d);
	}
	// 13 position
	public static void windowPosition(int x,int y) {
		Point p = new Point (x,y);
		driver.manage().window().setPosition(p);
	}
	// 14 getAttribute
	public static String getAttribute(WebElement ref, String att) {
		String attribute = ref.getAttribute(att);
		return attribute;
	}
	// 15 Actions
	public static void actions(String operation, WebElement target) {
		a = new Actions (driver);
		switch (operation) {
		case "click":
			a.click(target).perform();
			break;
		case "contextClick":
			a.contextClick(target).perform();
			break;
		case "doubleClick":
			a.doubleClick(target).perform();
			break;
		case "moveToElement":
			a.moveToElement(target).perform();
			break;
		}
	}
	// 16 Actions drag & drop
	public static void dragAndDrop (WebElement source, WebElement target) {
		a = new Actions (driver);
		a.dragAndDrop(source, target).perform();
	}
	// 17 Actions click, hold & release
	public static void clickHoldAndRelease(WebElement source, WebElement target) {
		a= new Actions(driver);
		a.clickAndHold(source).release(target).perform();
	}
	// 18 Actions cut & paste
	public static void action_Cut(WebElement tocut, WebElement topaste ) {
		a=new Actions(driver);
		a.moveToElement(tocut).perform();
		a.keyDown(Keys.CONTROL).perform();
		a.sendKeys("a").perform();
		a.sendKeys("x").perform();
		a.keyUp(Keys.CONTROL).perform();
		a.moveToElement(topaste).click().perform();
		a.keyDown(Keys.CONTROL).perform();
		a.sendKeys("v").perform();
	}
	// 19 Actions copy & paste
	public static void action_Copy(WebElement tocopy, WebElement topaste ) {
		a=new Actions(driver);
		a.moveToElement(tocopy).perform();
		a.keyDown(Keys.CONTROL).perform();
		a.sendKeys("a").perform();
		a.sendKeys("c").perform();
		a.keyUp(Keys.CONTROL).perform();
		a.moveToElement(topaste).click().perform();
		a.keyDown(Keys.CONTROL).perform();
		a.sendKeys("v").perform();
	}
	// 20 Actions 
	public static void actions_Sendkeys (CharSequence sendkey) {
		a=new Actions(driver);
		a.sendKeys(sendkey).perform();
	}
	// 21 Actions
	public static void actions_keyDown (CharSequence key) {
		a=new Actions(driver);
		a.keyDown(key).perform();
	}
	// 22 Actions
	public static void actions_keyUp (CharSequence key) {
		a=new Actions(driver);
		a.keyUp(key).perform();
	}
	// 23 Robot Key
	public static void robot(String method, int keycode) throws AWTException {
		r=new Robot();
		if (method.equals("keyPress")){
			r.keyPress(keycode);	
		}
		else if (method.equals("keyRelease")) {
			r.keyRelease(keycode);
		}
	}
	// 24 Navigate back, forward & refresh
	public static void navigate(String method) {
		if(method.equals("back")) {
			driver.navigate().back();
		}
		else if(method.equals("forward")){
			driver.navigate().forward();
		}
		else if (method.equals("refresh")) {
			driver.navigate().refresh();
		}
	}
	// 25 Navigate to
	public static void navigate_To(String url) {
		driver.navigate().to(url);
	}
	// 26 JavaScript Executor js_ScrollDown
	public static void js_ScrollDown(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ref);
	}
	// 27 JavaScript Executor js_ScrollUp
	public static void js_ScrollUp(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", ref);
	}
	// 28 JavaScript passing text
	public static void js_enteringText(WebElement ref, String attributeName, String attributeValue) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('"+attributeName+"','"+attributeValue+"')", ref);
	}
	// 29 Alert
	public static Alert alert() {
		alert=driver.switchTo().alert();
		return alert;
	}
	// 30 Select
	public static void select(WebElement ref, String selectType, int index, String value) {
		s= new Select(ref);
		switch (selectType) {
		case "selectByIndex":
			s.selectByIndex(index);
			break;
		case "selectByValue":
			s.selectByValue(value);
			break;
		case "selectByVisibleText":
			s.selectByVisibleText(value);
			break;
		}
	}
	// 31 is Multiple
	public static void isMultiple (WebElement ref) {
		s=new Select(ref);
		multiple = s.isMultiple();
		System.out.println(multiple);
//		return multiple;
	}
	// 32 select - first selected option
	public static String select_FirstSelectedOption(WebElement ref) {
		s=new Select(ref);
		WebElement firstSelectedOption = s.getFirstSelectedOption();
		String select_FirstSelectedOption = firstSelectedOption.getText();
		return select_FirstSelectedOption;
	}
	// 33 select - All Selected Options
	public static List<WebElement> select_AllSelectedOptions(WebElement ref) {
		s=new Select(ref);
		select_AllSelectedOptions= s.getAllSelectedOptions();
		return select_AllSelectedOptions;
	}
	// 34 select - All Options 
	public static List<WebElement> select_AllOptions(WebElement ref) {
		s=new Select(ref);
		allOptions = s.getOptions();
		return allOptions;
	}
	// 35 deselect all
	public static void deselectAll (WebElement ref) {
		s=new Select(ref);
		s.deselectAll();		
	}
	// 36 deselectbyindex
	public static void deselect(WebElement ref, String selectType, int index, String value) {
		s= new Select(ref);
		switch (selectType) {
		case "selectByIndex":
			s.deselectByIndex(index);
			break;
		case "selectByValue":
			s.deselectByValue(value);
			break;
		case "selectByVisibleText":
			s.deselectByVisibleText(value);
			break;
		}
	}
	// 37 WindowsHandle
	public static String windowHandle() {
		parId = driver.getWindowHandle();
		return parId;
	}
	// 38 WindowsHandles
	public static Set<String> windowHandles() {
		allIds = driver.getWindowHandles();
		return allIds;
	}
	// 39 window movement by index
	public static List<String> windowHandlingByIndex() {
		parId = driver.getWindowHandle();
		allIds = driver.getWindowHandles();
		li= new ArrayList();
		li.addAll(allIds);
		return li;
	}
	// 40 window movement to parent window
	public static void windowHandle_MovetoParentWindow() {
		parId = driver.getWindowHandle();
		allIds = driver.getWindowHandles();
		driver.switchTo().window(parId);
	}
	// 41 screenshot
	public static void screenshot(String location) throws IOException {
		ts = (TakesScreenshot) driver;
		File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
		File f = new File (location);
		FileUtils.copyFile(screenshotAs, f);
	}
	// 42 screenshot -- need to check
	public static void screenshotElement(WebElement ref, String location) throws IOException {
		ts = (TakesScreenshot) driver;
		File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
		File f = new File (location);
		FileUtils.copyFile(screenshotAs, f);
	}
	// 43 Frames
	public static void frames_Index(int index) {
		driver.switchTo().frame(index);
	}
	// 44 Frames
	public static void frames_IdOrName(String value) {
		driver.switchTo().frame(value);
	}
	// 45 Frames
	public static void frames_WebElement(WebElement ref) {
		driver.switchTo().frame(ref);
	}
	// 46 Wait
	public static void waits_ExplicitWait(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		until.click();
	}
	// 47 Wait
	public static void waits_FluentWait(final String xpath) {
		Wait <WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
		
		WebElement until = (WebElement) wait.until(new Function<WebDriver, WebElement>(){

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpath));
			}});
		until.click();
	}
	
	// 48 Webtable - headings
	public static List<String> webtable_Headings(WebElement table) {
		List<WebElement> tHeadings = table.findElements(By.tagName("th"));
		li = new LinkedList<String>();
		for(int i=0; i < tHeadings.size(); i++) {
			WebElement heading = tHeadings.get(i);
			String text = heading.getText();
//			System.out.println(text);
			li.add(text);
		}
		return li;
	}
	// 49 Webtable - datas
	public static List<String> webtable_Datas(WebElement table) {
			List<WebElement> tRow = table.findElements(By.tagName("tr"));
			li = new LinkedList<String>();
			// edited for adact project
			for(int i=1;i<tRow.size();i++) {
				WebElement row = tRow.get(i);
				String text = row.getText();
//				System.out.println(text);
				li.add(text);
			}
		return li;
	}
	// 50 Excel_getRowCount
	public static int getRowCount (String path, String sheetName) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
//		int rowcount = sheet.getLastRowNum();
		int rowcount = sheet.getPhysicalNumberOfRows();
		workbook.close();
		fi.close();
		return rowcount;
	}
	// 51 Excel_getCellCount
	public static int getCellCount (String path, String sheetName, int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
//		int cellcount=row.getLastCellNum();
		int cellcount=row.getPhysicalNumberOfCells();
		workbook.close();
		fi.close();
		return cellcount;
	}
	// 52 Excel_getCellData
	public static String getCellData (String path, String sheetName, int rownum, int colnum) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		}
		catch (Exception e) {
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	// 53 Excel_setCellData
	public static void setCellData(String path, String sheetName, int rownum, int colnum, String data) throws IOException
	{
		File xlfile=new File (path);
		if(!xlfile.exists())
		{
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1) // if sheet not exists then create 1
  			workbook.createSheet(sheetName);
			sheet=workbook.getSheet(sheetName);
			
		if(sheet.getRow(rownum)==null) // if row not exists then create 1
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);
			
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	// 54 Excel_fillGreenColor
	public static void fillGreenColor (String path, String sheetName, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	// 55 Excel_fillRedColor
	public static void fillRedColor (String path, String sheetName, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	// 56 isSelcted
	public static boolean isSelected(WebElement ref) {
		boolean selected = ref.isSelected();
//		System.out.println(ref + "is selected :" + selected);
		return selected;
	}
	// 57 isDisplayed
	public static boolean isDisplayed(WebElement ref) {
		boolean displayed = ref.isDisplayed();
//		System.out.println(ref + "is displayed :" + displayed);
		return displayed;
	}
	// 58 isEnabled
	public static boolean isEnabled(WebElement ref) {
		boolean enabled = ref.isEnabled();
		return enabled;
//		System.out.println(ref + "is enabled :" + enabled);
	}
	// 59 get location
	public static void getLocation(WebElement ref) {
		Point location = ref.getLocation();
//		System.out.println(ref + "location is :" + location);
	}
	// 60 js Javascript executor highlight webelement
	public static void js_Highlight(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: white; border:"
				+"solid 3px red')", ref);
	}
	// 61 Find Elements
	public static List<WebElement> findElements(String locatorName, String value) {
		if (locatorName.equals("id")) {

			findElements = driver.findElements(By.id(value));
			
		}

		else if (locatorName.equals("name")) {

			findElements = driver.findElements(By.name(value));

		} else if (locatorName.equals("classname")) {

			findElements = driver.findElements(By.className(value));

		} else if (locatorName.equals("xpath")) {

			findElements = driver.findElements(By.xpath(value));

		} else if (locatorName.equals("linktext")) {

			findElements = driver.findElements(By.linkText(value));

		} else if (locatorName.equals("partlink")) {

			findElements = driver.findElements(By.partialLinkText(value));

		} else if (locatorName.equals("tagname")) {

			findElements = driver.findElements(By.tagName(value));

		} else if (locatorName.equals("css")) {

			findElements = driver.findElements(By.cssSelector(value));
		}
		
		return findElements;
	}
	
}
