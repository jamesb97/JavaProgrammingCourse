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
