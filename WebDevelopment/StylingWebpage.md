# Styling a Web Page

1. Inline CSS - Applied directly within HTML Elements. Ideal for quick styling of a single element. Highlighting a specific word or line with unique styling.
   Pros: Simpler use for small changes.
   Cons: Difficult to maintain for large projects as it violates separation of content and style.

2. Internal CSS

- Defined within the `<style></style>` tag in the `<head></head>` section of the HTML document.
- Best for styling specific to a single HTML page.
- Example use case: Designing a webpage where styles aren't shared across multiple pages.
- Pros: Allows centralized styling for one document
- Cons: Not reusable across multiple files

3. External CSS

- Written in a separate `.css` file and linked to the HTML using a `<link>` tag
- Preferred for websites using multiple pages requiring consistent styling
- Example use case: Large websites where styles are shared across pages
- Pros: Promotes reusability and cleaner code; separation of concerns
- Cons: Requires an extra HTTP request to load the CSS file

4. Create a new webpage call it `solarsystem.html` as the file name.

5. Add some HTML to the file and then apply the CSS styles.

```
<!DOCTYPE html>
<html>
    <head>
        <title>Solar System</title>
    </head>
    <body>
        <h1>Solar System</h1>
        <img src="https://upload.wikimedia.org/wikipedia/commons/c/cb/Planets2013.svg" alt="Solar System image" height= "250px" width="700px">
        <p>The solar system consists of the sun and everything that orbits the sun. This includes the eight planets and their moons, the dwarf planets, asteroids, comets and other small objects.
        </p>

        <p> All the planets and dwarf planets, the rocky asteroids, and the icy bodies in the Kuiper belt move around the Sun in elliptical orbits in the same direction that the Sun rotates. This motion is termed prograde, or direct, motion.
        </p>
        <p><strong><u> Planets in the solar system: </u></strong> </p>
        <table>
            <tr>
                <th>No. </th>
                <th>Planet Name </th>
                <th>Distance from Sun (in million km)</th>
            </tr>
            <tr>
                <td>1 </td> <td>Mercury</td> <td>57.91 m km</td>
            </tr>
            <tr>
                <td>2 </td> <td>Venus</td> <td>108.2 m km</td>
            </tr>
            <tr>
                <td>3 </td> <td>Earth</td> <td>149.6 m km</td>
            </tr>
            <tr>
                <td>4 </td> <td>Mars</td> <td>227.9 m km</td>
            </tr>
            <tr>
                <td>5 </td> <td>Jupiter</td> <td>778.5 m km</td>
            </tr>
            <tr>
                <td>6 </td> <td>Saturn</td> <td>1.434 b km</td>
            </tr>
            <tr>
                <td>7 </td> <td>Uranus</td> <td>2.871 b km</td>
            </tr>
            <tr>
                <td>8</td> <td>Neptune</td> <td>4.495 billion km</td>
            </tr>
        </table>
    </body>
</html>

```

6. Right click on the file and click on Open with Live Server. The server would then show a notification mentioning that the server has started on port 5500.

7. Specify a font family and font size for text
   CSS helps define style, layout, colors, and fonts for your pages.

8. Change the heading font
   Modify the `<h1></h1>` tag so that it looks like this:

```
<h1 style="font-family: Cursive">Solar System</h1>
```

9. Change the font size
   Add the font-size property to the `<h1></h1>` style.

```
<h1 style="font-family: Cursive; font-size: 70px"> Solar System </h1>
```

10. Change the color of an element
    Set the background color of the page and update the color of an HTML element. To change the background color, use the CSS background-color property. To change an element's color, use the color property.

11. Change the background color
    To change the background color of the body of the web page, update the `<body></body>` tag as in the following code:

```
<body style="background-color:wheat">

```

12. Change the font color
    You specify a color for an element using a predefined color name, or by using RGB, HEX, HSL, RGBA, or HSLA values. The RGB system enables you to specify a color as a combination of the primary colors red, green, and blue. RGB colors range from rgb(0,0,0), which is black to rgb(255,255,255) which is white. Change the color of the heading using the RGB system, as follows:

```
<h1 style="font-family: Cursive; font-size:70px; color:rgb(139,0,0)">Solar System</h1>
```

13. Create Borders
    Tables created in HTML do not display a border by default. Using CSS, we can apply borders to tables, `<div></div>` elements, and other tags.

14. Add a table border
    Add a border to the table in the webpage. The border will be solid, black, and two pixels wide. Update the `<table></table>` tag with the following CSS code:

```
<table style="border: 2px solid black">
```

15. Add a border to table headings and table cells.
    CSS gives you the flexibility to add style to multiple instances of the same HTML tag. For example, instead of specifying the style for each individual `<th></th>` and `<td></td>` tag, we can add a style to all of those tags using interal CSS. Add a `<style></style>` tag within the `<head></head>` section of our page to specify how we want all `<th></th>` and `<td></td>` tags to display. To apply internal CSS style to the page, replace the `<head></head>` section with the code below:

```
<head>
    <title>Solar System</title>
    <style>
        table,th,td {
            border: 2px solid black;
        }
    </style>
</head>
```

16. Highlight the table header
    Let's make the table header stand out by specifying a background color for the entire table row. By adding the style to the row, you do not need to update each individual `<th></th>` tag within the row. Add a style to the first `<tr></tr>` tag in the table (the header row), as follows:

```
 <tr style="background-color:yellow">
    <th>No. </th>
    <th>Planet Name </th>
    <th>Distance from Sun </th>
</tr>
```

## Practice Exercise 1

Part 1: Inline CSS

- Create a new file named `inline-css.html`
- Use the `style` attribute within an HTML element to apply inline CSS
- Apply styles to an `<h1></h1>` element to make the text blue and center-aligned
- Add a `<p></p>` element with a font-size of 18px and green text color
- Save the file and view it in a browser

Part 2: Create a new file called internal-css.html.
Add a List of Items

- Item 1
- Item 2
- Item 3

Part 3: Create a new file called `external-css.html`. Create a separate CSS file named `styles.css`. Style the element with two rows and two columns. Save both files and view the `external-css.html` file in the browser.
