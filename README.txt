README DOCUMENTATION PROJECT 2

Introduction:
	- For this project we designed the interface around three users: the customer, the employee, and the admin. The customer is able to view the menu and place an order of donuts. The employee is able to view the menu, view the orders ( add or update status of an order), and view the inventory (add and manage trays of donuts). The admin is able to update the menu itself (add product, edit product, delete product) as well as generate reports of the business and stale donuts. 

	- All diagrams (use case, UML, sequential) were written and submitted in the dia program.

	- All uses (employee, admin, customer) have 2 cases written in each one's documentation file. 

APPLICATIONS OF JAVA PRINCIPLES:
	- We implemented Polymorphism by creating simple class calls and functions that will be easily 	called by Main. 
	- We implemented Inheritance in our order->inventory->tray->donut structure. This ensures that 	each class is linked to another. This allows for easy code reuse. 


Files listed below were used in the creation of our program. The csv files listed below hold the date we used to store orders, the menu, and the inventory of the store; while the .java files were our general code itself. All of their functionality is listed below. 
	*csv files: 
		- menu.csv - Stores the menu that all customers will see. This will keep an updated menu 	with possible updates based upon the admin's input. 

		- inventory.csv - Stores the live store inventory with the number of trays of each donut and 	the number of donuts on each tray. The employees are able to update this csv every time they add 	a tray, or a tray is deleted naturally. 

		- order.csv - Stores the live orders. Will automatically read and subtract the number of donuts 	from inventory once a customer has placed one. The employee is able to update the status 		within the csv through the employee menu. 


	*.java files: 
		Main - Holds all main functionality and interface for Project 2. Includes the user(customer), 	employee, and admin menus as well as all constructors and function calls. 

		Order - Holds setters and getters for the order data class. Also uses a print function to print 	the data within the csv file. 

		Inventory - Includes the logical functions to increment and decrement trays. Also will print the 	info of trays and returns the number of stale donuts thrown away. 

		Reports - Creates and updates reports of both stale donuts, as well as total amount sold and 	money made for each week, month, and year. 

		Tray - Holds setters and getters for the Tray data class. Also uses a print function to print the 	data within the inventory csv file.


SUMMARY
~ All functionality that has been requested has been implemented to the fullest extent with all of the user, the employee, and the admin in mind. Each menu is catered with excellent functionality and all code is streamlined for fast execution and provides a more logical approach to this project's general solution. 