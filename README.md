#au-datadriven-test 

**The data driven using Selenium 4, Maven, TestNG and Java Thread Local**

##Environment variables syntax

###BROWSER

	Browser=BrowserName:Mode

- **BrowserName** - Defines the type of browser to be invoked (ex: chrome)<br/><br/>
- **Mode** - Defines the normal or incognito mode in chrome browser (ex: incog)

&emsp;&emsp;&emsp;**Note: Incognito mode is provided only for chrome browser. For other browsers only normal mode.**

###Test Group Name

	GTag=GroupName
- **GroupName** - Test Group Name of the test cases to be executed (ex: swagtest)

###ENVIRONMENT

	Env=AppEnvironment
- **AppEnvironment** - Application environment where the test need to be executed (ex: test)

###REPORTING EMAIL

	Mail=VerboseType:ToEmailAddress
- **VerboseType** - Verbose type are integer value defines the customizable email body while sending report (ex: 1)

&emsp;&emsp;**There are few verbose which allows to customize mail body**

&emsp;&emsp;&emsp;**1** ====> Send email body with all passed, failed and skipped scenarios.<br/><br/>
&emsp;&emsp;&emsp;**2** ====> Send email body with passed and failed scenarios. Skipped scenarios will not be displayed.<br/><br/>
&emsp;&emsp;&emsp;**3** ====> Send email body with passed, failed and skipped scenarios but skipped scenarios will be documented in separate table.<br/><br/>

- **ToEmailAddress** - To Email address for whom the report to be sent Multiple TO - abc@gmail.com,def@gmail.com<br/><br/>
- **ToEmailAddress|CCEmailAddress** - To and CC email address for whom the report to be sent Multiple TO with Multiple CC - abc@gmail.com,def@gmail.com|ccm@gmail.com,xyz@gmail.com

&emsp;&emsp;&emsp;**Note: Atleast one TO email address mandatory to have CC recipients**