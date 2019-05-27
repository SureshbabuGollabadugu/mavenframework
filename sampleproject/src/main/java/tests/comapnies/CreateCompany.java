package tests.comapnies;

import org.testng.annotations.Test;

import framework.Configuration;
import framework.Data;
import framework.Data.ResultStatus;
import framework.ExcelUtils;
import framework.Logs;
import framework.UtilityMethods;


public class CreateCompany extends Configuration {
	@Test
	public void CC_001_createCompany_Without_Address_Details() {
		
		Logs.writeReport(ResultStatus.PASS, "sample step.");
		Logs.writeReport(ResultStatus.PASS, "sample step 2.");
		
	}
	
	@Test
	public void CC_002_createCompany_With_Address_Details() {
		
		Logs.writeReport(ResultStatus.PASS, "sample step 4.");
		Logs.writeReport(ResultStatus.PASS, "sample step 3.");
	}

}
