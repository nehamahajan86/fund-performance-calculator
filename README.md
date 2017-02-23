# Fund Performance Calculator

Overview
----------
Excess calculator app will accept 4 input files in CSV format.
fund.csv:					File containing information about funds.
benchmark.csv:					File containing information about benchmarks.
fundReturnSeries.csv & benchReturnSeries.csv:	Files containing the return series of funds or benchmarks.

Requirements:
--------------
For each fund return in the fundReturnSerices.csv file
* Use the fundCode to lookup the fundName from the fund.csv file
* Use the date to lookup the benchmark return from the benchmarkReturnSeries.csv file
* Excess = fund return - benchmark return
* OutPerformance = If Excess<-1 then "under performed"; if Excess > 1 then "out performed" else <<blank>>

For each date in fundReturnSeries.csv
* take the group of funds for that date and rank them by their return value

Output:
* The application will produce one output file called monthlyOutperformance.csv.
* The output is to be sorted by [Date] descending, then sub-sorted by [Rank] ascending


Assumptions:
------------
*	Benchmark.csv is not used by the code for any calculation. Yet it has been read and mapped to handle any future calculations.
*	Date format for benchReturnSeries is "yyyy-MM-dd" and fundReturnSeries is "dd/MM/yyyy".
*	Input file will be a CSV and delimited by comma ",".
*	All the 4 input files must be provided for the code to run.
*	Properties file contains key-value pair format.
		Such as: ExcessLessThan=-1
*	The keys in properties file are required and should not be changed by the user. User can choose to leave the value blank.

System Requirements
--------------------
1. Java 8 installed
2. Maven installed
3. Test cases require JUnit

Instructions for running the Performance-Calculator-App
------------------------------------------------------
1. Download the code from github and navigate to the project directory.
2. To build the project run
		 "mvn clean install"

3. Executable JAR will be created in the "target" folder under project directory.
4. Copy the "config" folder from github and place it same directory as jar.
5. config/fund-performance-calculator.properties contains following parameters:
	
	a) fundFile : Complete path to fund csv file. [For windows set path in '\\' format such as: fundFile = C:\\Lonsec TestData_V2\\fund.csv]

	b) benchFile : Complete path to benchmark csv file

	c) benchReturnFile : Complete path to benchmark return series csv file.

	d) fundReturnFile : Complete path to fund returns series csv file.

	e) performanceOutputFileLocation : Complete path to output file. Default location is in the same directory as executable.

	f) excessLessThan : lower limit of threshold value for which excess will be calculated. Default value is -1.

	g) excessGreaterThan : upper limit of threshold value for which excess will be calculated. Default value is 1. 
	
	h) excessOutPerformText : text to be written in performance report for excess out performance. Default value is "out performance".

	i) excessUnderPerformText : text to be written in performance report for excess under performance. Default value is "under performance".

6. Set the locations for fund, benchmark, benchReturnSeries & fundReturnSeries files and execute the command: 

	"java -jar fund-performance-calculator-0.0.1-SNAPSHOT.jar"

7. Output CSV will be created at the location specified in the config/fund-performance-calculator.properties file.


Instruction for running JUnit
-----------------------------
1. Download the code from Github
2. Navigate to the project directory.
3. To run JUnits execute the command 
		"mvn test"
