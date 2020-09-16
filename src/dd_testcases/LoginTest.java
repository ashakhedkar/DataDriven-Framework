package dd_testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import dd_core.TestCore;
import dd_util.TestUtil;

    public class LoginTest extends TestCore{
    	
    	String GlobalUserName;
	    @Test(dataProvider="getData")
        public void doLogin(String username,String password) throws Exception{
		System.out.println("Inside do login");
		logs.debug("inside Login Test");
	
	  findElement("username").sendKeys(username);
	  findElement("password").sendKeys(password);
	  findElement("login").click();
	
      /*	TestUtil.CaptureScreenshot();*/	  
        	GlobalUserName=username;     	 
	  	}
	@DataProvider
	  public static Object[][] getData() throws Exception {
		System.out.println("inside data provider");
		  return TestUtil.getData("LoginTest");
	  }
}
