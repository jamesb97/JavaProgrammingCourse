# Unit Conversion

## Learning Objectives

Create a section with id home, within the body. This section will represent the top section of your webpage.

````
4. Within the home section, create a header, using the `<header>` tag, with the text `Unit Conversions`. Bold the text to make it stand out
```html
<section id="home">
	<!-- This is the main heading -->
	<header><b>Unit Conversions</b></header>
</section>
````

Create a navigation bar inside the home section, after the header tag

```
<nav>
  <!-- This will have the main unit conversion buttons -->
</nav>

```

You need to create unit conversions for:

- i. Temperature
- ii. Weight
- iii. Distance

- We will anchor tags with buttons which redirect users to certain sections of the same page.
- We will be using the `id` attribute to reference these sections. `id`s are represented with the # symbol.

Add 3 anchor & button tags for the 3 types of conversions (temperature, weight, and distance) inside the navigation bar.

```
<nav>
  <!-- Button for redirecting users to the temperature section -->
  <a href="#temperature"><button>Temperature</button></a>
  <!-- Button for redirecting users to the weight section -->
  <a href="#weight"><button>Weight</button></a>
  <!-- Button for redirecting users to the distance section -->
  <a href="#distance"><button>Distance</button></a>
</nav>

```

Create a button for the Temperature conversions (Celcius to Farenheit).

1. Create a div tag, which will be used to hold all the conversion sections.

2. Add a section tag inside, and set its attribute id to temperature inside this all-conversion-sections div tag.

````
3. Create a div tag with `id` set to `tmp`. Add a figure tag inside this div tag, where you will be adding a visual depiction of the conversion.
```html
<section id="temperature">
  <div id="tmp">
    <figure>

      <!-- Figure and its caption will come here -->

    </figure>
  </div>
</section>
````

4. Add an image tag inside the figure, having `src` attribute set to the URL and a width set to 200px. Then, add a `figcaption` tag to give a caption to this figure.

```
<figure>
  <img src="https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-CD0101EN-SkillsNetwork/labs/Theia%20Labs/02%20-%20HTML5%20Elements/images/thermo.png" width="200px"/>
  <figcaption>Celsius to Fahrenheit Conversion</figcaption>
</figure>

```

Complete the following:

- Display temperature as a heading
- Create two input fields and two labels
- Create a button to convert

5. Add an article tag to the `tmp` div tag to contain an article that will hold the elements for temperature conversion. We are using the article tag since this conversion is meaningful on it's own.

### Celsius to Fahrenheit Conversion

6. Add fieldset and legend tags inside the article to group the fields pertaining to temperature conversion.

```
<!-- The fields and button for temperature input will come here -->
```

7. Add labels and input fields, within the fieldset tag, for the temperature input (in Celsius) and output (in Fahrenheit). Use the `number` input type for both these fields.

8. Insert a "Convert" button between the input and output fields.

9. Add an inside tag after the article to teach a user how to do this calculation themselves.
   To convert celsius to farenheit yourself, use this formula replacing the `C` with your temperature in celcius: (C X 9/5) + 32.

10. Save the code updated so far.
    Unit Conversion
    Temperature Weight Distance

```
<div id="all-conversion-sections">
    <!-- This will have the conversion sections for Temperature, Weight, and Distance -->

    <section id="temperature">
        <!-- Temperature conversion section -->
        <div id="tmp">
            <figure>
                <img src="https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-CD0101EN-SkillsNetwork/labs/Theia%20Labs/02%20-%20HTML5%20Elements/images/thermo.png" width="200px"/>
                <figcaption>Celsius to Fahrenheit Conversion</figcaption>
            </figure>

            <article>
                <!-- This contains the specific elements for temperature conversion -->
                <fieldset>
                    <legend>Temperature</legend>
                    <!-- Label for Temperature input -->
                    <label for="celsius">Celsius</label> <br/>

                    <input type="number" id="celsius"> <br/>


                    <!-- The conversion button -->
                    <button id="temperature"> Convert </button> <br/>


                    <!-- Label for Temperature output -->
                    <input type="number" id="fahrenheit"> <br/>

                    <label for="fahrenheit">Fahrenheit</label>
                </fieldset>
            </article>

            <aside>
                To convert celsius to fahrenheit yourself, use this formula replacing the `C` with your temperature in celsius: (C × 9/5) + 32
            </aside>
        </div>
	</section>
</div>
```

11. Styling the Web Page.
    Set the margin of all elements to 10px on all sides and the background-color to black.

12. Style the `Home` section (having the app name & the nav bar) to have:

- A margin of -1 to the top.left & right
- Bottom padding of 1 or 2 cm
- Background color to be a lighter shade of blue magenta

13. Style the `Header` section as follows:

- Font-size as 30 pixels
- Colour to be light purple
- Floating to the left
- Padding of 2 cms to the left

14. The `Navigation Bar Buttons` can have `static` styling as:

- A light gray color
- Background color as dark purple
- Margin & padding of 10 pixels each. A border radius of 1mm
- Font-size as 20 pixels
- Optional text decoration

15. The `Navigation Bar Buttons` can have the below `on-hover` style:

- Color as white
  Bolder font weight
- Background colour as purple
- Optional text decoration

````
The 3 conversion sections of the calculator will now be styled:
a. All the sections should have the below styling:
- Items are aligned vertically and positioned at the center. <br>
- Grid-based layout
b. The figure tag can be styled as follows:
- Float to the left <br>
- Alignment (justification) can be automatic <br>
- Width as 200 pixels
c. The image tag can have a width of 200 pixels
d. The figcaption tag should:
- Have a black color <br>
- Font size as 17 pixels <br>
- Text aligned to the centre
e. The conversion buttons can have static styling as:
- Font size of 20px <br>
- A transparent border with border-radius as 40px and the content fitting the page width <br>
- Background-color as light green <br>
- Left and right padding as 15px each
f. The conversion button should have `on-hover` styling of changing the mouse appearance from an arrow to a **pointer**
<details><summary>Click to see the content of style.css</summary>
```css
/* Container for all the three conversion calculator sections   */
#all-conversion-sections{
    display: grid;
    justify-content: center;
}

figure {
    float:left;
    justify-self: auto;
    width:200px
}

img {
    width: 200px;
}

figcaption {
    color:black;
    font-size: 17px;
    text-align: center;
}

button{
    font-size: 20px;
    border: transparent;
    width: fit-content;
    background-color: rgb(173, 218, 173);
    border-radius: 40px;
    padding-right: 15px;
    padding-left: 15px;
}

button:hover {
    cursor: pointer;
}
````

16. The Calculator panel will now be styled:

```
.temperature{ border-top-color: green; }

.weight{ border-top-color: coral; }

.distance{ border-top-color: cyan;

}

legend { font-size: 30px; font-weight: bolder; }
```

````
12. The floating home button will now be styled.
a. The button icon can:
- Have width and height as 40px each <br>
- Items aligned and justified to the center <br>
- Border radius as 100% and background colour as **cyan** <br>
- Flexible box display<br>
- The icon should be fixed and not move with scrolling. It can be at the extreme left and 40 pixels from the bottom of the page
b. The image for the icon button can have a width of 30 px
13. The footer tag can have:
- The background colour as **light pink** & top margin as 20px
14. Save the changes made so far
<details><summary>Click to see the completed code for style.css</summary>
```css
/* sets the margin of all the element to 10px on all sides and the background-color to black */
body{
    margin: 10px;
	background-color: black;
}

/* Home section  which has the app name and nav bar. The styling is applied by id */

#home {
    background-color:  #483355;
    padding-bottom: 1cm;
    margin-left:-1cm;
    margin-right:-1cm;
    margin-top:-1cm;
    padding-top: 2cm;
    padding-bottom: 2cm;
   }

/* Application name inside the home section. The styling is applied by tag name */

  header {
    font-size: 30px;
    color: rgb(240, 234, 245);
    float: left;
    padding-left: 2cm;
  }

/* Navigation Bar Container*/

  .topdiv {
    float: right;
    padding-right: 1cm;
  }

/* Navigation Bar Buttons */
  .topmenu {
    color: lightgray;
    background-color:   #483355;
    margin: 10px;
    padding: 10px;
    font-size: 20px;
    text-decoration:none;
    border-radius: 1mm;
  }

  .topmenu:hover {
    color: white;
    font-weight: bolder;
    background-color: #382742;
    text-decoration: underline;
  }

/* Container for all the three conversion calculator sections   */
#all-conversion-sections{
    display: grid;
    justify-content: center;
}

figure {
    float:left;
    justify-self: auto;
    width:200px
}

img {
    width: 200px;
}

figcaption {
    color:black;
    font-size: 17px;
    text-align: center;
}

button{
    font-size: 20px;
    border: transparent;
    width: fit-content;
    background-color: rgb(173, 218, 173);
    border-radius: 40px;
    padding-right: 15px;
    padding-left: 15px;
}

button:hover {
    cursor: pointer;
}

/* The calculator panel */
.b{
    border-top-style: solid;
    background-color: white;
    border-top-width: 20px;
    width: 600px;
    height: 400px;
    border-radius: 10px;
    margin-bottom: 25px;
    margin-top: 25px;
    display: flex;
}

.temperature{
    border-top-color: green;
}

.weight{
    border-top-color: coral;
}

.distance{
    border-top-color: cyan;

}

legend {
    font-size: 30px;
    font-weight: bolder;
}

/* Floating icon for the home button */
.iconbutton{
    width:40px;
    height:40px;
    border-radius: 100%;
    background-color: rgb(225, 235, 235);
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    bottom:40px;
    left:0;
    }

.iconbutton img {
    width:30px
}

footer {
    background-color:rgb(240, 234, 245);
    margin-top: 20px;
}
````

17. Set the values in index.html of the class attribute of the `div` inside the nav tag to `topdiv`. Set the value of the class attribute of the buttons inside the nav bar to `topmenu`. Set the value of the class attribute of the section with id `weight` to `b weight`. Set the value of the class attribute of the section with id `distance` to `b distance`. Set the value of the class attribute of the div with id `go-home` to `iconbutton`.

18. Save the index.html file and view it with Live Server.
