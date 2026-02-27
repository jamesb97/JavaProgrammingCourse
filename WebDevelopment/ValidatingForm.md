# Validating a JavaScript Form

JavaScript is a client-side scripting language and is commonly used to create dynamic web pages. It helps change web page content dynamically, as well as enable to validate forms and perform other actions. 

## Learning Objectives

1. Create a basic web form
2. Use the `<script>` tag
3. Add a JavaScript function
4. Acess the form controls from JavaScript
5. Access a textbox and check if it is blank
6. Execute a set of statements based on a condition
7. Display error messages
8. Execute a function where the form is submitted
9. Practice Exercise: Add JavaScript interactivity

### 1. Create an HTML Form

Create a simple form that accepts a person's name and email address and then performs a simple validation on the entered input.

Go to the project explorer, and create a new file called form_validation.html as the file name.

Start off by creating a simple form designed to accept the user's name and email address. The form will have a Submit button and a Reset button. 

```
<!DOCTYPE html>
<html>
    <head>
        <title>Contact Details</title>
    </head>
    <body>
        <h2>Enter your contact details:</h2> <br>
        <form id="form1">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name">
            <br><br>
            <label for="email">Email Address:</label>
            <input type="text" id="email" name="email">
            <br><br>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
```

Save the file and right click on to Open it with Live Server

A notification will appear stating that the server has started on port 5500

### 2. Use the <script> tag

The `<script>` tag is used to embed executable code, usually JavaScript, into an HTML page. The tag can contain scripting elements, or it can refer to an external script file. A `type` attribute is used to specify the scripting language.

Replace the `<head>` section of the file with the following code: This tells the browser that the code is about to be put inside the `<script>` tag and must be executed as JavaScript.



### 3. Add a JavaScript function

Now, specify what will happen when a user clicks the Submit button. Specify this behavior with a user-defined JavaScript function, which is a block of code that gets executed when it is called. A function can be called any number of times.



A function in JavaScript may look like this:

```
function function_name()
{
   // code goes here
}
```

Add an empty function that has the name `checkdata`. Use this to validate the data in the form. Replace the <script> tag in the file with the following code:

```
<script type="application/javascript">
    function checkdata()
        {
        }
 </script>
```



#### 4. Access HTML controls within JavaScript

The function created is intended to validate the contents of each input element in the form. To acess the data for an element, the script needs to refer to the correct element.

One way to identify an element is to use a method called `getElementByID(elementID)`. Thte following line of code returns the element with the ID name:

```
document.getElementById("name");
```

The following lines of code enable us to access the name and email input elements of the form. Note that the id's of both these elements have already been specified in the HTML code. Store the references to the elements in two JavaScript variables named `username` and `email_address`.

```
var username = document.getElementById("name");
var email_address = document.getElementById("email");
```

Add the following code into the checkdata() function.



#### 5. Access and check the data

When the references to the elements are stored in the variables, the value of the elements can be retrieved using the `value` attribute. If `username` is the variable that contains the input element's reference, then its value can be accessed using `username.value`

To check if this value is blank, we can use the following statement:

username.value == ""

" " indicates an empty string.



#### 6. Execute a set of statements based on a condition

If the value is blank, print out an error message and return the focus back to the empty element.

To perform this action, use a JavaScript *conditional statement* called the if statement. The if conditional statement allows us to specify a block of code to be *executed* if a condition is true.

The syntax of the statement is as follows:

```
        if(condition){
            //block of code to be executed, if the condition is true.
        }
```

Check if the username value is empty by using an if statement. Copy the following code and paste it to the bottom of the `checkdata()` function:

```
        if(username.value==""){
            return false;
        }
```

If the value is blank, `return false;` statement returns a boolean value false from the checkdata() function.

Check that all input elements of the form in this way to determine whether they are empty.



#### 7. Display error messages

Display an error message to a user with the help of a pop-up alert message box. To do this, use the ```alert``` method.



Let's use this method within the function to alert the user.

```
        if(username.value==""){
            alert("Please enter the name");
            username.focus();
            return false;
        }
```

The `username.focus()` statement is used to bring the input focus back to the element where we found a problem, in this case the *username*. 

Replace the current conditional in the function with the new code. Try to add the same conditional logic, but instead for the `email_address` element instead.

Next, indicate that none of the elements are blank by returning `true`. So, instead add a return true` statement at the end of the function.

  
It is a good practice to include comments in the code. Comments will help you and other programmers easily debug any errors that we might encounter while running the code. In JavaScript, single-line comments are added using two forward slashes: //

The final `checkdata()` function with comments will look like this:

```
function checkdata(){
      //Create references to the input elements we wish to validate
      var username = document.getElementById("name");
      var email_address = document.getElementById("email");

      //Check if username field is empty
      if(username.value == ""){
            alert("Please enter the name");
            username.focus();
            return false;
      }
     //Check if email field is empty
      if(email_address.value == ""){
            alert("Please enter the email");
            email_address.focus();
            return false;
      }
     //If all is well return true.
      return true;
}
```

The function should now work as expected and is now ready to be called, so that we can use it whenever needed.



#### 8. Execute a function when  the form is submitted

Now that there is a working `checkdata()` function, the final step is to call it whenever the form is submitted. This can be done using the onsubmit attribute for validation.

```
<form id="form1" onsubmit="return checkdata()">
```

Update the <form> tag located just below the <h2> heading ("Enter your contact details") to include the onsubmit attribute for validation.

This ensures the `checkdata()` function runs to validate the form before submission.

This code ensures that the `checkdata()` function is invoked every time the form is submitted.

