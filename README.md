# au-datadriven-test
**This test made of data driven using Selenium4, maven, TestNG and Thread local**


## Environment variables syntax

### Browser = BrowserName:BrowserMode

    ```bash
        Browser=BrowserName:Mode
    ```
	- BrowserName - Defines the type of browser to be invoked (ex: chrome)
	- Mode - Defines the normal or incognito mode in chrome browser (ex: incog)
**Note: Incognito mode is provided only for chrome browser. For other browsers only normal mode.**
	 
2. TEST_XLS = ExcelSheetName.xlsx
	- ExcelSheetName - Excel Sheet of the test cases to be executed (ex: BAT.xlsx)
	
**VM Arguments syntax**

1. -Dmail=VerboseType:ToEmailAddress

	# VerboseType - Verbose type are integer value defines the customizable email body while sending report (ex: 1)
		
		There are few verbose which allows to customize mail body
		1 ====> Send email body with all passed, failed and skipped scenarios.
		2 ====> Send email body with passed and failed scenarios. Skipped scenarios will not be displayed.
		3 ====> Send email body with passed, failed and skipped scenarios but skipped scenarios will be documented in separate table.
		
	# ToEmailAddress - To Email address for whom the report to be sent
		Multiple TO - abc@gmail.com,def@gmail.com 
		
	# ToEmailAddress|CCEmailAddress - To and CC email address for whom the report to be sent 
		Multiple TO with Multiple CC - abc@gmail.com,def@gmail.com|ccm@gmail.com,xyz@gmail.com
		
		Note: Atleast one TO email address mandatory to have CC recipients
		 
