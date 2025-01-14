package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.QueuePOF;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.LoggerLoad;

public class QueueSteps {
	
	public QueuePOF QueuePageObject = new QueuePOF();
	String PythonCode;
    String Output;
    String ExpectedResult,ExpectedError,ExpectedMsg;
	String Excelpath=ConfigReader.getexcelfilepath();


@Given("The user is on the Home page")
public void the_user_is_on_the_home_page() { 
	LoggerLoad.info("User is on the Homepage");
}

@When("User clicks the Get Started button in Queue")
public void user_clicks_the_get_started_button_in_queue() {
	 LoggerLoad.info("Click on get Started button");
	 QueuePageObject.getStarted_Q();
}

@Then("User should be redirected to the {string} page clicking on Get Started")
public void user_should_be_redirected_to_the_queue_page_clicking_on_get_started(String ExpectedPageTitle ) {
	String actualPageTitle = QueuePageObject.getpageTitle();
	 LoggerLoad.info("User successfully lands on the Queue page");
	 Assert.assertEquals(ExpectedPageTitle, actualPageTitle,"User is redirected to the Queue page");
}


@Given("User is on the Queue Page")
public void user_is_on_the_queue_page() {
	 LoggerLoad.info("User is on the Queue Page");
	 QueuePageObject.getStarted_Q();  
}

@When("user clicks Implementation of Queue in python link")
public void user_clicks_implementation_of_queue_in_python_link() {
	 LoggerLoad.info("User is clicking on Implementation of Queue");
	 QueuePageObject.ImplementationofQueueinPythonLink();
}

@Then("User should be redirected to the {string} Page")
public void user_should_be_redirected_to_the_implementation_of_queue_in_python_page(String ExpectedPageTitle ) {
	String actualPageTitle = QueuePageObject.getpageTitle();
	 LoggerLoad.info("User successfully Lands on the Implementation of queue in Python page");
	 Assert.assertEquals(ExpectedPageTitle, actualPageTitle,"User redirected to  the Implementation of queue in Python page");
	
}

@Given("User is on the Implementation of Queue in python link")
public void user_is_on_the_implementation_of_queue_in_python_link() {
	LoggerLoad.info("User is on the Implementation of Queue");
	QueuePageObject.getStarted_Q();
	QueuePageObject.ImplementationofQueueinPythonLink();
	
    }

@When("user clicks try here button")
public void user_clicks_try_here_button() {
	LoggerLoad.info("User is clicking on try here button");
	QueuePageObject.clickTry();
}


@Then("user should be redirected to a page having an tryEditor with a Run button to test")
public void user_should_be_redirected_to_a_page_having_an_try_editor_with_a_run_button_to_test() {
	LoggerLoad.info("User successfully lands to the tryEditor----");
	 }

@Given("Users is on {string}.")
public void users_is_on(String string){
	LoggerLoad.info("-----User on Try Editor----");
	QueuePageObject.getStarted_Q();
	QueuePageObject.ImplementationofQueueinPythonLink();
	QueuePageObject.clickTry();

}

@When("User should enter valid Python code from sheet {string} and {int}")
public void user_should_enter_valid_python_code_from_sheet_and(String SheetName, Integer RowNumber) throws InvalidFormatException, IOException {
	LoggerLoad.info("User is on Try Editor page");
    ExcelReader reader = new ExcelReader();

    List<Map<String, String>> testdata = reader.getData(Excelpath, SheetName);
    LoggerLoad.info(SheetName);
    PythonCode = testdata.get(RowNumber).get("PythonCode");
    ExpectedResult=testdata.get(RowNumber).get("Output");
    LoggerLoad.info("Expected Result is "+ExpectedResult);
    LoggerLoad.info("User enters PythonCode as \" " + PythonCode);
    
    if ( PythonCode!= null )
    	QueuePageObject.fetchPythonCode(PythonCode);
}
@When("User clicks on Run.")
public void user_clicks_on_run() {
	LoggerLoad.info("User clicks on Run Button");
	QueuePageObject.Run_btn_Queue();
}
@Then("User should see the valid output")
public void user_should_see_the_valid_output() {
	LoggerLoad.info("User gets the Result of entered Python Code");
    String fetchOutput = QueuePageObject.fetchOutput();
    
    LoggerLoad.info("Displayed Result is "+fetchOutput);
    assertEquals(fetchOutput,ExpectedResult,"Expected and actual result  matches");
}

@Given("User is on the {string}")
public void user_is_on (String string) {
	LoggerLoad.info("-----User on Try Editor----");
	QueuePageObject.getStarted_Q();
	QueuePageObject.ImplementationofQueueinPythonLink();
	QueuePageObject.clickTry();
	
}


@When("User should enter InValid Python code from sheet {string} and {int}")
public void user_should_enter_invalid_python_code_from_sheet_and(String SheetName, Integer RowNumber) throws InvalidFormatException, IOException {
	LoggerLoad.info("User is on Try Editor page");
    ExcelReader reader = new ExcelReader();

    List<Map<String, String>> testdata = reader.getData(Excelpath, SheetName);
    PythonCode = testdata.get(RowNumber).get("PythonCode");
    ExpectedResult=testdata.get(RowNumber).get("Output");
    LoggerLoad.info("Expected Result is "+ExpectedResult);

    LoggerLoad.info("User enters PythonCode as \" " + PythonCode);
    if ( PythonCode!= null )
    	QueuePageObject.fetchPythonCode(PythonCode);

}
@When("User clicks on the Run.")
public void user_clicks_on_the_run() {
	LoggerLoad.info("User clicks on Run Button");
	QueuePageObject.Run_btn_Queue();
}
@Then("User should be able to see the Error messages")
public void user_should_be_able_to_see_the_error_message() {
	 LoggerLoad.info("User gets the error message in an Alert");
     String actualError = QueuePageObject.fetchErrorMessage();
     String ExpectedError="NameError: name 'hello' is not defined on line 1";
     LoggerLoad.info("Displayed Error Message  is "+actualError);
     assertEquals(actualError,ExpectedError,"Invalid Syntax error message");
     
}

@When("User clicks  Implementation using Collection.deque link")
public void user_clicks_implementation_using_collection_deque_link() {
	LoggerLoad.info("-----User is clicking on Implementation using Collections.deque----");
	QueuePageObject.ImplementationUsingCollectiondeQueueLink();
}


@Given("User is on the Implementation using Collection.deque link")
public void user_is_on_the_implementation_using_collection_deque_link() {
	LoggerLoad.info("-----User is on Implementation using collection.deque----");
	QueuePageObject.getStarted_Q();
	QueuePageObject.ImplementationUsingCollectiondeQueueLink(); 
}

@When("user clicks  Implementation using array page  link")
public void user_clicks_implementation_using_array_page_link() {
	LoggerLoad.info("-----User clicking on Implementation using array page----");
	QueuePageObject.ImplementationUsingArray();	
}  


@When("user clicks  Queue Operations page link")
public void user_clicks_queue_operations_page_link() {
	LoggerLoad.info("-----User is clicking on Queue Operation page----");
	QueuePageObject.QueueOperations();
}

@Then("User should be directed to the {string} page")
public void user_should_be_directed_to_the_queue_operations_page(String ExpectedPageTitle) {
	String actualPageTitle = QueuePageObject.getpageTitle();
	LoggerLoad.info("-----User successfully lands on the Queue Operations----");
	Assert.assertEquals(ExpectedPageTitle, actualPageTitle,"User is redirected to the Queue Operations");
    } 

 @Given("User is on the Queue Operation page")
 public void user_is_on_the_queue_operation_page() {
	 LoggerLoad.info("-----User is on the Queue Operation Page----");
	 QueuePageObject.getStarted_Q();
	 QueuePageObject.QueueOperations();

 }

@When("user clicks Practice Questions link")
public void user_clicks_practice_questions_link() {
	LoggerLoad.info("-----User clicks on Practice Questions----");
	QueuePageObject.clickPracticeQuestions();
}

@Then("User should be directed to the {string} Page with list of Questions Page")
public void user_should_be_directed_to_the_practice_questions_page_with_list_of_questions_page(String ExpectedPageTitle) {
	String actualPageTitle = QueuePageObject.getpageTitle();
	LoggerLoad.info("User successfully lands on the Practice Question page----");
	Assert.assertEquals(ExpectedPageTitle, actualPageTitle,"User is redirected to the Practice Question page");
    } 

}



