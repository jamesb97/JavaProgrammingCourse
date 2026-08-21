# Sentiment Analysis for Product Reviews Lab

## Introduction

In this lab, our primary objective is to use the environment for Java development and load a preconfigured AI sentiment analyzer project that is created using Stanford NLP pretrained model and test it. Will be focusing on the specific task of loading project in the IDE in the system and analysing the sentiment of product reviews.

## Learning Objectives

After completing this lab, should be able to perform the following tasks:

- Load a preconfigured project in the Cloud IDE
- Load the sentiment analyzer project and test it with the given data
- Add some additional text and analyze it with the sentiment analyzer model

## Load sentiment analyzer model project and Run

Your ultimate goal is to import the project into the Java development environment in the IDE installed, so that can test the sentiment analyzer model. Are provided with the project which provides a sentiment analyzer model that predicts the sentiment of product review based on data provided.

1. To build and run the imported code in the Cloud IDE, open a terminal and run the following command.

`git clone https://github.com/ibm-developer-skills-network/gxbpy-java_sentiment_analyzer.git`

2. Open up the `pom.xml` file in the project directory and observe the AI model that is being used.

These code blocks are configuration entries fro a Java project that utilizes Stanford CoreNLP, a suite of natural language processing tools developed by Stanford University.

### Purpose of Stanford CoreNLP\*\*

Stanford CoreNLP provides a collection of language analysis tools that enable computers to process and understand human language. One of its key features is sentiment analysis, which determines the emotional tone (such as positive, negative, or neutral) of a piece of text.

This dependency adds the language models required by CoreNLP. These models are essential datasets that enable the library to analyze and interpret human language accurately.

### Why Are There Two Dependencies?

The first dependency brings in the program logic - the actual algorithms and methods for processing text.
The second dependency provides the data models needed for these algorithms to function, such as language rules and sentiment patterns.

This application uses `StanfordNLP` model that is pre-trained for sentiment analysis and can categorize the sentiments into 5 categories.

- Very positive
- Positive
- Neutral
- Negative
- Very Negative

3. The main class of this application is `ProductReviewAnalyzer`.

Open the `ProductReviewAnalyzer.java` in the IDE

Explanation of the **ProductReviewAnalyzer** class

- The program analyzes the sentiment of product reviews using Stanford CoreNLP, a natural language processing library.
- It reads reviews from a CSV file with the help of the OpenCSV library.
- The code first sets up a pipeline that can break text into sentences and determine the overall feeling (sentiment) of each sentence.
- For each review, it finds the sentiment of the longest sentence and uses that as the main sentiment for the whole review.
- Sentiments are classified into five categories: Very positive, Positive, Neutral, Negative, and Very Negative.
- As it processes each review, it keeps track of how many reviews fall into each sentiment category.
- After all reviews are analyzed, it prints out a report showing the number and percentage of reviews in each sentiment type, along with a simple visual bar to make the results easier to see.
- The program includes error handling to deal with issues like missing files or problems reading the CSV data.
- In summary, this code shows how to use Java to automatically check if customer feedback is positive or negative and summarize the results in an easy-to-read format.

This application is a Product Review Sentiment Analyzer that is created using Stanford CoreNLP for natural language processing and OpenCSV to read review data from a CSV file.

### Running the Application

When running this application,

- It initializes the analyzer and NLP pipeline.
- Loads reviews from a CSV file using OpenCSV.
- Returns a list of string arrays (String[]) where each array is a row from the CSV.
- For each review,
- Analyzes the sentiment.
- Prints a shortend version of the review with its sentiment.
- Finally, calls generateReport() to summarize sentiment stats.

4. Navigate to the project root directory using the terminal:

`cd gxbpy-java_sentiment_analyzer`

5. Build the project by executing the Maven clean install command to download dependencies and compile the project.

`mvn clean install`

This might take a few minutes as it pulls down the required dependencies.

6. Execute the application directly from the terminal in VS Code:

`java -jar target/senti-0.0.1-SNAPSHOT.jar`

## Practice Exercise

1. Create a new file inside of `src/main/resources` directory called `new_products_review.csv`. Paste the following data into the CSV file.

2. Load `new_products_review.csv` in application instead of the `product_reviews.csv` and run the sentiment analysis.

## Summary

In this lab, imported the AI sentiment analyzer code into the IDE and ran it to review and categorize the sentiments.

This lab showcased the potential of leveraging AI tools, such as sentiment analyzer with pre-trained models.

Have completed the lab on working with the Java AI sentiment analyer model.
