## Additional Challenges

#### Item Search Functionality

Write a method called `searchItem` that takes a String array of items and a String item name as parameters.
Use a for loop to iterate through the items array.
If the item is found, print its index position. If the item is not found, print “Item not found.”
Call this method within your main program and test the method using different item names.

#### Calculate Average Price

Write a method called `calculateAveragePrice` that takes a float array of prices as a parameter.
Use a for loop to sum all the prices in the array.
Divide the total of all prices by the length of the array to find the average.
Return the average price and print the average price in your main program.

#### Filter Items Below a Certain Price

Write a method called `filterItemsBelowPrice` that takes a String array of items and a float array of prices, along with a float threshold price.
Use a for loop to check each price against the threshold.
If the price is below the threshold, print the corresponding item name.
Call this method using different threshold prices in your main program.

#### Total Bill with Discounts

Use conditional statements to implement the following logic:

After calculating the total bill, check if the total exceeds $100.
If the total exeeeds $100, apply a 10% discount on the total bill.
Print both the original total and the discounted total.

#### Inventory Management

Implement the following logic within your purchase loop.

Create an integer array named stock that corresponds to your items array, representing the stock available for each item.
After a purchase is made, decrease the stock for that item by the quantity purchased.
If a user tries to purchase an item that has insufficient stock, print a message indicating that the item is out of stock.
