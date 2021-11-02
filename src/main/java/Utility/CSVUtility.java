package Utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CSVUtility {
	
		//get all records
		public List<String []> getAllRecords(String file)
		{
			List<String []> allData=null;
			try {
				// Create an object of file reader
				// class with CSV file as a parameter.
				FileReader filereader = new FileReader(file);

				// create csvReader object and skip first Line
				 CSVReader csvReader = new CSVReaderBuilder(filereader).build();
				 allData = csvReader.readAll();
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return allData;
		}
		
		  //get first record
			public String[] getFirstRecord(String file)
			{
				String [] firstrecord=null;
				try {
					// Create an object of file reader
					// class with CSV file as a parameter.
					FileReader filereader = new FileReader(file);

					// create csvReader object and skip first Line
					CSVReader csvReader = new CSVReaderBuilder(filereader).build();
					firstrecord = csvReader.readAll().get(0);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				return firstrecord;
			}
		
			//get last record
			public String[] getLastRecord(String file)
				{
				List<String []> records=null;
					try {
						// Create an object of file reader
						// class with CSV file as a parameter.
						FileReader filereader = new FileReader(file);

						// create csvReader object and skip first Line
						CSVReader csvReader = new CSVReaderBuilder(filereader).build();
					
						records = csvReader.readAll();
						
						//System.out.println(records.size());
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					return records.get(records.size()-1);
				}
				
				//get nth record
				public String[] getnthRecord(String file,int n)
				{
					String [] nthrecord=null;
					try {
						// Create an object of file reader
						// class with CSV file as a parameter.
						FileReader filereader = new FileReader(file);

						// create csvReader object and skip first Line
						CSVReader csvReader = new CSVReaderBuilder(filereader).build();
						nthrecord = csvReader.readAll().get(n-1);
						
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					return nthrecord;
				}
				
			//get all by skipping nth record
			public  List<String []>  getAllBySkippingRecord(String file,int n)
				{
					 List<String []>  records=null;
					try {
						// Create an object of file reader
						// class with CSV file as a parameter.
						FileReader filereader = new FileReader(file);

						// create csvReader object and skip first Line
						CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(n).build();
						records = csvReader.readAll();
						
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					return records;
				}
			
			   //get all by skipping nth record
				public  List<String []> getAllWithrange(String file,int start,int end)
					{
						 List<String []>  records=null;
						 List<String[] > records1= new ArrayList<String[]>();
						try {
							// Create an object of file reader
							// class with CSV file as a parameter.
							FileReader filereader = new FileReader(file);

							// create csvReader object and skip first Line
							CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(start-1).build();
							records = csvReader.readAll();
							int range=end-start;
							
							for(int i=0;i<=range;i++)
								records1.add(records.get(i));
							
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						return records1;
					}
				
				
				
			//get column 
				
		public  List<String> getColumn(String file,int n)
				{
					 List<String []>  records=null;
					 List<String> column = new ArrayList<String>();
					 
					try {
						// Create an object of file reader
						// class with CSV file as a parameter.
						FileReader filereader = new FileReader(file);

						// create csvReader object and skip first Line
						CSVReader csvReader = new CSVReaderBuilder(filereader).build();
						records = csvReader.readAll();
						
						for(int i=0;i<records.size();i++) {
							String[] rec = records.get(i);
							String val = rec[n-1];
							column.add(val);
						}
						
						System.out.println(column);
						
						
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					return column;
				}
		
		
		//get testcae record
		public String[] getTestCaseDetails(String file,String testcaseid)
		{
			List<String []> records=null;
			String[] testcase=null;
			try {
				// Create an object of file reader
				// class with CSV file as a parameter.
				FileReader filereader = new FileReader(file);

				// create csvReader object and skip first Line
				CSVReader csvReader = new CSVReaderBuilder(filereader).build();
				records = csvReader.readAll();
				
				for(String[] str:records) {
					if(str[0].equalsIgnoreCase(testcaseid))
					{
						testcase = str;
						break;
					}
				}
				
				
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return testcase;
		}
			
}
