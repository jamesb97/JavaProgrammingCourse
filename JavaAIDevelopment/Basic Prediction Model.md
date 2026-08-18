# Basic Prediction Model Lab

## Introduction

In this lab, the primary objective is to use the environment for Java development and load a preconfigured AI prediction project that is created using a regression model. This model predicts the customer purchase depending on the data provided.

## Learning Objectives

After completing this lab, should be able to perform the following tasks:

- Load a preconfigured regression prediction model project in the IDE
- Load the project and test it with data provided
- Alter the regression model and test it with fresh data

## Loading the prediction model

Machine learning models can be broadly categorized into supervised, unsupervised and reinforcement learning. Under supervised learning, have classification model that predicts discrete categorical labels called classes and regression model that predicts continuous numerical values.

In this lab, will be creating a regression model which are provided with, which provides a prediction model that predicts the customer purchases depending on the data provided. Will be doing this lab inside of the Cloud IDE.

1. Open a new terminal and clone the repository:

`git clone https://github.com/ibm-developer-skills-network/lblvd-maven-regression-prediction.git`

2. Click the button to observe the `pom.xml` file. It has dependencies on the `math` package to use regression to make a prediction model and the `csv` package to deal with the data in the csv file.

Open up the `pom.xml` file in the IDE.

3. The main class of this application is `CustomerPurchasePredictor`. Click the button below to open the file and observe the content.

Open the `CustomerPurchasePredictor.java` in the IDE.

This code implements a simple linear regression model to predict customer purchase amounts based on their income.

- It uses Apache Commons Math's SimpleRegression for the regression model
- It uses OpenCSV to read data from a CSV file

This application loads customer purchase data from a CSV file. Trains a regression model with income as the independent variable (X) and purchase amount as the dependent variable (Y). Makes predictions for new income values.

4. Navigate the project root directory using the terminal.

`cd lblvd-maven-regression-prediction`

5. Execute the Maven clean install command to download dependencies and compile the project.

`mvn clean install`

This might take a few minutes as it pulls down the required dependencies.

6. Execute the main class directly from the terminal.

`java -jar target/prediction-0.0.1-SNAPSHOT.jar`

## Practice Exercise

1. Save the following data into a csv file.

```csv
customer_id,age,income,purchase_amount
1,27,39000,150
2,34,58000,225
3,45,72000,88
4,56,85000,330
5,67,99000,120
6,78,110000,450
7,89,125000,290
8,90,130000,360
9,100,140000,180
10,110,150000,270
11,120,160000,340
12,130,170000,420
13,140,180000,190
14,150,190000,310
15,160,200000,470
16,170,210000,230
17,180,220000,390
18,190,230000,520
19,200,240000,280
20,210,250000,410
21,220,260000,350
22,230,270000,580
23,240,280000,260
24,250,290000,440
```

This CSV data includes 25 rows of sample data for each of the specified fields: "customer_id", "age", "income", and "purchase_amount". Each row represents a different customer with varying attributes.

2. Run the basic predictor using the new csv data.

## Summary

This lab showcased the potential of leveraging AI using Java to create and test a prediction model.

Have completed the lab on working with the Java AI prediction model.
