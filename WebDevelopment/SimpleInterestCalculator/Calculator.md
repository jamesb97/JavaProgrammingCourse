# Simple Interest Calculator

## Objectives

1. Download the project folder.
2. Modify the HTML file as per the requirements.
3. Modify the CSS file as per the requirements.
4. Modify the JavaScript file as per the requirements.
5. Verify that the webpage is working properly.

### Exercise 1: Download the project folder

1. Open a new terminal

2. Paste the following commands into the terminal to download the project folder and unzip it.

`wget https://github.com/ibm-developer-skills-network/vftvk-Simple-Interest-Calculator/archive/refs/heads/master.zip
`

`unzip master.zip`

3. Open the file explorer and verify that the project contains the following files:

- index.html
- styles.css
- script.js

### Exercise 2. Modify the HTML file

Correct any mistakes in the existing code and add any missing tags.

1. In the file explorer, navigate to the `index.html` file.

2. All of the HTML files must begin with a doctype tag, to indicate that HTML content will be replaced in the file. Add this tag to the beginning of `index.html`.

```
<!DOCTYPE html>
```

3. Use the title tag to change the browser title to be "Simple Interest Calculator". Remember that the title tag should be placed in the `head` section of the markup.

`<title>Simple Interest Calculator</title>
`

4. Move all of the content which is currently in the `<body></body>` to a new `<div></div>` tag.

5. Set the class attribute of this new `div` to `maindiv`.

```
<body>
    <div class="maindiv">
        <h1>Simple Interest Calculator</h1>

        Amount <input type="number"  id="principal">  <br/>

        Rate <input type="number"  id="rate">  <br/>

        No. of Years <input type="number"  id="years">  <br/>

        Interest : <span id="result"></span><br>

        <button onclick="compute()">Compute</button>
    </div>
</body>
```

6. Modify the `input id="rate"` tag for the interest rate to be a slider. Recall from earlier lessons that this can be done by changing the `type` to range.

`<input type="range" id="rate">`

7. For the rate input, add the following attributes and their corresponding values:

- min should be set to 1
- max should be set to 20
- value should be set to 10.25
- step should be set to 0.25

`Rate <input type="range" id="rate" min="1" max="20" value="10.25" step="0.25">
`

Range is an elegant way to input numeric input, but the drawback is that it does not visually show value the user has selected.

8. To show the value selected by the range, create a `<span></span>` element right after the range, with the id `rate_val`.

`<span id="rate_val">
</span>`

9. Inside of the `<span></span>` tag, add the text "10.25" to represent the default value. Add a "%" outside this span tag. The span will be updated dynamically later on, but the "%" should always remain, so this is placed outside the tag.

Insert a line break after this tag, so the next input appears on a new line.

`<span id="rate_val">10.25</span>%<br />`

10. Modify the input text box for "No. of Years" into an input with autocomplete suggestions using a datalist containing options 1 to 10.

Recall from the "HTML5 Input Element" video, the correct way to insert a datalist in HTML.

`No. of Years 
<input type="number" id="years" list="all_years">
<datalist id="all_years">
    <option value="1">1</option>
    <option value="2">2</option>
    <!-- Fill in the rest of the values -->
</datalist>
`

11. Change the name of the "Compute" button to "Compute Interest".

`<button onclick="compute()">Compound Interest</button>`

12. Below the "Compute Interest" button, create an empty `<span></span>` and set its id to result. This will be used to dynamically display the result of the calculation when the "Compute Interest" button is clicked.

`<span id="result"></span>`

13. Outside of the `maindiv`, add a copyright message using the `<footer></footer>` tag, like below:

14. Save the changes made in index.html

15. Open the application using Live Server.

### Exercise 3: Modify the CSS File

1. On the file explorer navigate to the `style.css` file.

2. Set the body background color to 'black', font family to 'arial' and font color to 'white'.

```
body {
    background-color: black;
    font-family: arial;
    color: white;
}
```

3. Set the h1 color to 'grey' and font to 'verdana'.

```
h1 {
    color: grey;
    font-family: verdanda;
}
```

4. Create an entry for class 'maindiv'.

```
.maindiv {

}
```

5. In the newly created maindiv class, set the following styles:

- Background color to 'white'
- Font color to 'black'
- Width to '300px'
- Padding to '20px'
- Border radius to '25px'
- Text alignment to 'center'

```
.maindiv {
    background-color: white;
    color: black;
    width: 300px;
    padding: 20px;
    border-radius: 25px;
    text-align: center;
}
```

6. Save the changes made in the style.css file.

7. Open the application with Live Server, and preview to make sure nothing has been missed.

### Exercise 4: Modify the JavaScript file

Write the JavaScript code into the script.js file to implement the logic for the Simple Interest Calculator.

Display Rate Slider Value

1. Create an empty function called updateRate(). This will be used to display the value of the "Rate" slider.

```
function updateRate() {

}
```

2. Inside the updateRate() function, create a variable rateval that gets the value from the 'Rate' slider.
   Hint: the Rate slider is the element with an id rate.

```
function updateRate() {
    var rateval = document.getElementById("rate").value;
}
```

3. Modify the `<span id="rate_val"></span>` value to display the value of the rateval variable created above.

```
function updateRate()
{
    var rateval = document.getElementById("rate").value;
    document.getElementById("rate_val").innerText = rateval;
}
```

4. Link this function with an "onchange" event on the range input.

```
Rate <input type="range" id="rate" min="1" max="20" value="10.25" step="0.25" onchange="updateRate()">
```

5. Save the file and open the web page with the Live Server extension. Change the slider and verify that the value to the right of it updates with a new value each time the slider is changed.

**\*Compute Button Functionality**

1. Create the following variables inside the compute() function, and assign them to the corresponding value listed:

- principal initialized to the value of the input element with an id of principal, parsed as an int. This is needed to calculate the final amount, as well as the interest amount.
- rate initialized to the value of the input element with an id of rate. This is needed to calculate the interest amount
- years initialized to the value of the input element with an id of years. This is needed to calculate the interest amount
- interest amount with the value principal _ years _ rate / 100. This is needed to calculate the total amount
- amount which is the sum of the integer value of principal and the float value of interest
- result initialized to the input element with an id of result. This is needed to modify the text when the Compute button is pressed

```
var principal = document.getElementById("principal").value;
var rate = document.getElementById("rate").value;
var years = document.getElementById("years").value;
var interest = principal * years * rate / 100;
var amount = parseInt(principal) + parseFloat(interest);
var result = document.getElementById("result");
```

2. Add the logic to convert the 'No. of Years' into the actual year in the future. This can be done by adding the number of years(years) to the current year inside the compute() function.

```
    var year = new Date().getFullYear() + parseInt(years);
```

This will ensure that the input taken as "No. of Years" is converted into an actual year (e.g. 2022).

3. Add a validation for the "Principal" input box. If the user enters zero or a negative value, show an alert which says "Enter a positive number"

```
if (principal <= 0) {
    alert("Please enter a positive number!");
}
```

4. Once the user clicks on the alert "OK" button, take the user back to the "Principal" input box, by setting the focus on this box using the focus method in the code for principal input validation:

```
if (principal <= 0) {
    alert('Please enter a positive number!');
    document.getElementById("principal").focus();
}
```

5. Write an else clause, set the inner html property of the result to the text below, replacing anything within the square brackets [] with its actual value.

Note that when writing < or > within quotation marks, you must instead type \< or \>

If you deposit $[PRINCIPAL],
at an interest rate of [RATE]%.
You will receive an amount of $[INTEREST],
in the year [YEAR]

```
else {
    result.innerHTML = "If you deposit $" + principal + ",\<br\>at an interest rate of " + rate + "%\<br\>You will receive an amount of $" + amount + ",\<br\>in the year " + year + "\<br\>";
}
```

6. Make sure the number in the result are highlighted by using the mark HTML tag around each variable value:

```
else {
    result.innerHTML = "If you deposit $" + principal + ",\<br\>at an interest rate of " + rate + "%\<br\>You will receive an amount of $" + amount + ",\<br\>in the year " + year + "\<br\>";
}
```

### Exercise 5: Test the Calculator

Write out comments in the code to help with debug and maintain the code in the long term.

1. Enter these values in the form:
   Amount = 0 Rate = 1 % No. of Years = 1

Click on the Compute Interest button

The output should be an alert saying Enter a positive number.

2. Enter these values in the form.
   Amount = 1000 Rate = 10% No. of Years = 10
   Click on the Compute button.

3. Enter these values in the form.
   Amount = 4800 Rate = 15.25% No. of Years = 5
   Click on the Compute button.
