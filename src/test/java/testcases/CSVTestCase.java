package testcases;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Utility.CSVUtility;

import java.util.*;
import java.io.*;

public class CSVTestCase {

	@Test
	public void f() throws InterruptedException, IOException
	{
		//readcsv("csvs/test.csv");
		// readAllDataAtOnce("csvs/test.csv");

		// this method for all data to be read 

		CSVUtility obj=new CSVUtility();
		System.out.println("all records");
		List<String []> allrecords=obj.getAllRecords("csvs/test1.csv");
		for (String[] row : allrecords)
		{
			for (String cell : row)
			{
				System.out.print(cell + "\t" );
			}
			System.out.println();
		}


		// this method for to read first record 
		System.out.println("\n first record");
		String[] frecord = obj.getFirstRecord("csvs/test1.csv");
		for (String cell : frecord)
		{
			System.out.print(cell + "\t");
		}

		// this method for to read nth record
		System.out.println("\n nth record");

		String[] nthrecord = obj.getnthRecord("csvs/test1.csv",2);
		for (String cell : nthrecord)
		{
			System.out.print(cell + "\t");
		}
		// this method for to read last record

		System.out.println("\n last record");

		String[] lrecord = obj.getLastRecord("csvs/test1.csv");
		for (String cell : lrecord)
		{
			System.out.print(cell + "\t");	
		}

		System.out.println("\n skipping");
		allrecords=obj.getAllBySkippingRecord("csvs/test1.csv",2);
		for (String[] row : allrecords)
		{
			for (String cell : row)
			{
				System.out.print(cell + "\t");
			}
			System.out.println();
		}


		System.out.println("\n range");
		allrecords=obj.getAllWithrange("csvs/test1.csv",1,5);
		for (String[] row : allrecords)
		{
			for (String cell : row)
			{
				System.out.print(cell + "\t");
			}
			System.out.println();
		}


		System.out.println("column");
		List<String> list=obj.getColumn("csvs/test1.csv",1);
		for (String val : list)
		{

			System.out.println(val.toString());
		}

		System.out.println("column by testcaseid");
		String[] list1=obj.getTestCaseDetails("csvs/test4.csv","tc1");
		for (String val : list1)
		{

			System.out.println(val.toString());
		}



	}
	//  
	public List<Object []> readcsv(String file ) throws InterruptedException, IOException
	{
		List<Object []> testCases = new ArrayList<Object []>();
		String[] data= null;

		//this loop is pseudo code
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			// use comma as separator
			data= line.split(",");
			testCases.add(data);
		}

		for(Object[] line1:testCases) {
			for(Object obj:line1) 
			{
				System.out.println((String)obj);
			}
		}

		System.out.println(testCases.iterator());

		return testCases;

	}
	//
	//  

	//Java code to illustrate reading  all data at once

	public List<String []> readAllDataAtOnce(String file)
	{
		List<String []> allData=null;
		try {
			// Create an object of file reader
			// class with CSV file as a parameter.
			FileReader filereader = new FileReader(file);

			// create csvReader object and skip first Line
			CSVReader csvReader = new CSVReaderBuilder(filereader)
					.withSkipLines(0)
					.build();
			allData = csvReader.readAll();

			// print Data
			for (String[] row : allData)
			{
				for (String cell : row)
				{
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return allData;
	}
}
