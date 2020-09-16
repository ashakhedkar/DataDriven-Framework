package dd_core;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import dd_util.ReadExcel;
import dd_util.TestUtil;

//here we initialize all required properties for the framework like driver, logs etc

public class TestCore {

	public static WebDriver driver=null;
	public static Properties config=null;
	public static Properties object=null;
	public static FileInputStream fis;
	public static ReadExcel excel= null;
	public static Logger logs=Logger.getLogger("devpinoyLogger");
	
	   @BeforeSuite
	   public void init() throws Exception {
		   
		 if(driver==null){
			config=new Properties();
			object=new Properties();

			PropertyConfigurator.configure("log4j.properties");
			
			//load config.properties file
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\config.properties");
			config.load(fis);
			logs.debug("Loaded the Config property file");
			
			//load object.properties file
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\object.properties");
			object.load(fis);
			logs.debug("Loaded the Object property file");
			excel=new ReadExcel(System.getProperty("user.dir")+"\\src\\dd_properties\\facebookdata.xlsx","LoginTest");
			
			if(config.getProperty("browser").equals("firefox")){
		        System.setProperty("webdriver.gecko.driver", "E:\\Selenium Jars\\geckodriver.exe");
                 driver=new FirefoxDriver();
                 logs.debug("Loaded Firefox Driver");
                               
		  	}
			else if(config.getProperty("browser").equals("chrome")){
				System.setProperty("webdriver.chrome.driver", "E:\\Selenium Jars\\chromedriver_win32 (1)\\chromedriver.exe");
				driver=new ChromeDriver();
				logs.debug("Loaded Chrome Driver");
			}
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().timeouts().implicitlyWait(20L,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		 }	 
	}	
	   @AfterSuite
	   public void stop(){
		driver.quit();
		}
	   public static WebElement findElement(String key) throws Exception{
		try{
			return driver.findElement(By.xpath(object.getProperty(key)));
				}
		catch(Throwable t){
			TestUtil.CapturedScreenshot();
			logs.debug("Screenshot captured");
			return null;
		}
	}	
}
