# Array Methods

Display Employee Information using Array Methods

## Learning Objectives

Create an employee management system. That will employee buttons to trigger functions such as displaying all employees, calculating total salaries, filtering and displaying HR department employees, and finding employees by their IDs. A JavaScript function will be used to generate dynamic list of employees, utilizing array methods like forEach, filter, reduce, and find, to manage and present data interactively.

- Event-driven programming: Learn to trigger functions through button clicks for DOM manipulation.

- Array method proficiency: Gain expertise in JavaScript array methods (forEach, filter, reduce, find) for data manipulation.

- Dynamic manipulation: Understand how to create and update HTML elments within a webpage dynamically.

- Front-end development skills: Develop foundational skills to create interactive interfaces and manage data presentation on webpages.

### Setting up the project environment

1. Create a new folder named employeeDetails.
   Select the employeeDetails folder and right click to create a New File. Enter the file name as employee_details.html.

2. Select the employeeDetails folder, and create another new file called employee_details.js.

3. Create a basic template structure of an HTML file by adding th econtent provided below:

```
<!DOCTYPE html>
<html>
<head>
  <title>Employee Management System</title>
</head>
<body>
   <h1>Employee Management System</h1>

  <div>
    <button onclick="displayEmployees()">Display Employees</button>
    <button onclick="calculateTotalSalaries()">Calculate Total Salaries</button>
    <button onclick="displayHREmployees()">Display HR Employees</button>
    <button onclick="findEmployeeById(2)">Find Employee by ID 2</button>
  </div>

 <div id="employeesDetails"></div>
<script src="./employee_details.js"></script>
</body>
</html>

```

- The HTML code defines buttons within a div to trigger JavaScript functions for managing an employee management system.

- It includes four buttons. Clicking on these buttons will execute JavaScript function to display employees, calculate total salaries, filter HR employees, and find and employee by ID.

- HTML code includes two `<div></div>` to showcase employee information based on user-triggered actions without the page dynamically reloading when clicking on any of the buttons.

- script tag is used in the HTML file above `<body></body>` tag to include JS file in employee_details.html

After parsing the code, save the file.

4. Inside of the employee_details.js, initialize the employees array object. Initialize it with key value pairs as follows:

```
const employees = [
      { id: 1, name: 'John Doe', age: 30, department: 'IT', salary: 50000 },
      { id: 2, name: 'Alice Smith', age: 28, department: 'HR', salary: 45000 },
      { id: 3, name: 'Bob Johnson', age: 35, department: 'Finance', salary: 60000 },
      //... More employee records can be added here
    ];

```

5. Create the displayEmployees() function to display employee details in the employee_details.js file.

```
// Function to display all employees
function displayEmployees() {
    const totalEmployees = employees
        .map(employee => `<p>${employee.id}: ${employee.name} - ${employee.department} - $${employee.salary}</p>`)
        .join('');
    document.getElementById('employeesDetails').innerHTML = totalEmployees;
}

```

- The map method iterates through each employee in the employees array. For each employee, it constructs a string template literal `<p>${employee.id}: ${employee.name}: ${employee.name} - ${employee.department} - $${employee.salary}</p>`, encapsulated within the `<p></p>` tags, to represent employee details.

- The resulting array of constructed strings is joined into a single string using join(""). This concatenation merges all the HTML-formatted employee details into one continuous string withou separators.

- The map method stores employees' details in the variable totalEmployees, which shows details in the `<div>` element (with the help of an ID) displays employee information on the webpage.

6. Create the calculateTotalSalaries() function to calculate employees' total salaries. Include the codes below after the previous JavaScript code.

```
function calculateTotalSalaries() {
      const totalSalaries = employees.reduce((acc, employee) => acc + employee.salary, 0);
      alert(`Total Salaries: $${totalSalaries}`);
    }

```

- The reduce method iterates through each employee and accumulates their salaries to calcuate the total. The initial value of the accumulator (acc) is 0.

- The reduce method continuously accumulates these salaries by adding each employee's salary to the previous total.

- Each employee's salary (employee.salary) is added to the accumulator (acc). After calculating the total sum of salaries, an alert dialog box is triggered using alert(). It showcases the total calculated salaries with the message. "Total Salaries: $" followed by the computed totalSalaries variable value. This alert displays the overall sum of all employee salaries.

7. Create a displayHREmployees() function to display employee details based on the department such as the HR department. Include the given code below after the previous JavaScript code.

```
function displayHREmployees() {
     const hrEmployees = employees.filter(employee => employee.department === 'HR');
      const hrEmployeesDisplay = hrEmployees.map((employee, index) => `<p>${employee.id}: ${employee.name}: ${employee.name} - ${employee.department} - $${employee.salary}</p>`).join('');
      document.getElementById('employeesDetails').innerHTML = hrEmployeesDisplay;
}

```

- The above code filters the employee array using the filter array method, isolating and collecting employees whose department property matches 'HR' into a new array named hrEmployees.

- It then displays the matched details on the font page as shown in the displayEmployee function using map method within `<div></div>` using its ID employeeDetails.

8. Create findEmployeeById() function to display employees' details based on ID. Include the below given code after the previous JavaScript code.

```
function findEmployeeById(employeeId) {
      const foundEmployee = employees.find(employee => employee.id === employeeId);
      if (foundEmployee) {
      document.getElementById('employeesDetails').innerHTML =`<p>${foundEmployee.id}: ${foundEmployee.name}: ${foundEmployee.name} - ${foundEmployee.department} - $${foundEmployee.salary}</p>`;
      }
      else{
        document.getElementById('employeesDetails').innerHTML = 'no employee has been found with this ID';
       }
   }

```

- This code uses the find method to locate an employee in the employees array whose ID matches the provided employee ID, storing the found employee object in the foundEmployee varable.

- An if-statement is employeed to see wheter the details for that particular ID are found and display details accordingly.

9. To view the HTML page, right click on the employee_details.html and select "Open with Live Server".

10. The server should start on port 5500, indicated by a notification on the bottom-right side.

### Practice Task

1. Perform a functionality in which information can be fetched on the basis of employee's specialization as well.

2. For this, include one more key value pair for employees array of objects inside each object.

3. Create a button to search for an employee based on a specialization as shown in the given screenshot.

4. Create a JavaScript function to display the details of employees who have specialization in JavaScript. You can refer to the findEmployeeById function in the JavaScript code of the lab for guidance.

5. The output will be as shown in the given screenshot.

#### Summary

- Setting up the environment: HTML code includes two `<div></div>` to showcase employee information based on user-triggered actions without the page dynamically reloading when clicking on any buttons.

- Defining variables and functions: The map method stores employees' details in the variable totalEmployees, which shows details in the `<div></div>` element (with the help of an ID) displays employee information on the webpage while the reduce method iterates through each employee and accumulates their salaries to calculate the total.

- Checking the output: Open the server on port 5500 to test the functionality.
