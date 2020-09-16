

	package dd_util;


	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;

	public class ReadExcel 
	
	{
		Workbook book;
		Sheet sheet;

		public ReadExcel(String fname,String sheetname) throws  IOException    
		{
			super();
			File f=new File(fname);
			FileInputStream fis=new FileInputStream(f);
			book=WorkbookFactory.create(fis);
			sheet=book.getSheet(sheetname);
		
		}

			int rowcount=0;
			int cellcount=0;
		
		public int getRowCount()
		{		
			for(Row row:sheet)
				
			{
				rowcount++;
			}
			return rowcount;
		}
		public int getCellCount()
		{		
			for(Row row1:sheet)		
			{
				for(Cell cell:row1)
				{
					cellcount++;
				}
				break;
			}
			return cellcount;
		}
		Row row;
		Cell cell;
		Object value =null;

		public Object getData(int rw,int cols)
		{		
	        for (int r = 0; r <=rw; r++) 
	        {
	            row = sheet.getRow(r); // bring row
	            if (row != null) {
	                for (int c = 0; c <=cols; c++) 
	                {
	                    cell = row.getCell(c);
	                    if (cell != null) 
	                    {
	                        switch (cell.getCellType()) 
	                        {                   

	                        case FORMULA:
	                        	String form=(String)cell.getCellFormula();
	                            value = String.valueOf(form);
	                            break;

	                        case NUMERIC:
	                        	double d=cell.getNumericCellValue();
	                            value = String.valueOf(d);
	                            break;

	                        case STRING:
	                            value =cell.getStringCellValue();
	                            break;

	                        case BLANK:
	                            value = "[BLANK]";
	                            break;
							case ERROR:
	                            value = "" + cell.getErrorCellValue();
	                            break;
	                        default:    
	                        	System.out.println(value);
	                        }                               
	                    } 
	                } 
	            }
	        } 
			
			return value;
	    }
	}
	/*public static void main(String[] agrs) throws Exception
	{
		ExcelReadDataRowCell ex=new ExcelReadDataRowCell("H:\\Documents\\java projects\\Excel Handling\\src\\fileconfig\\Product1.xlsx");
		
		int row=ex.getRowCount(0);
		int cell=ex.getCellCount();
		
		System.out.println("Row="+row);
		System.out.println("Cell="+cell);
	}
	*/

