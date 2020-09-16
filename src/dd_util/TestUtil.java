package dd_util;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import dd_core.TestCore;

public class TestUtil extends TestCore {
	
	public static String mailScreenshotpath;

	public static Object[][] getData(String sheetName) throws Exception {
		
		System.out.println("Inside common dataprovider");
		System.out.println(sheetName);
		int row=excel.getRowCount();
		int cols=excel.getCellCount();
		System.out.println(row);
		System.out.println(cols);

		Object[][] arrayObject=new Object[row][cols];

		for(int i=0;i<row;i++)
		{
			for(int j=0;j<cols;j++)
			{
			arrayObject[i][j] = String.valueOf(excel.getData(i, j));	
			}
		}		
		return arrayObject ;
	}
 public static void CapturedScreenshot() throws Exception {
	 
	 Calendar cal= new GregorianCalendar();
	 int month= cal.get(Calendar.MONTH);//3
	 int year= cal.get(Calendar.YEAR);//2014
	 int sec= cal.get(Calendar.SECOND);
	 int min= cal.get(Calendar.MINUTE);	 
	 int date=cal.get(Calendar.DATE);
	 int day=cal.get(Calendar.HOUR_OF_DAY);
	 
	 mailScreenshotpath=System.getProperty("user.dir")+"\\screenshots\\"+year+" "+date+" "+(month+1)+" "+day+" "+min+" "+sec+" "+".jpg";
	 	 
	 File f=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(f,new File(mailScreenshotpath));
	
     }	
}
