# Part 1 - GitHub UI

## Scenario

You recently got hired as a developer in a micro-finance startup with a mission to empower and provide opportunities for low income individuals. The core team currently uses Subversion (SVN) for managing code. They want to slowly move their code to Git. You are asked to host their sample code to calculate simple interest on GitHub in a new repository as the first step in this journey. You will not only host the script, but also follow best practices introduced in this course and create supporting documents for the open source project including a code of conduct, and contribution guidelines. Additionally, the repository should be available to the community under the Apache License 2.0.

## Objectives

After completing this lab, you would have demonstrated that you can:

1. Create a new repository in your GitHub account.
2. Select the appropriate license for your project.
3. Create a README.md file that explains the purpose of the project.
4. Create a Code of Conduct markdown that explains how you want the community to behave and interact with each other.
5. Create a Contribution Guidelines markdown that tells the community how to contribute.
6. Commit the new files to the repository.

Note:

1. As mentioned in the Final Project Overview Criteria, you can submit your project deliverables through either Option 1: AI-Graded Submission and Evaluation or Option 2: Peer-Graded Submission and Evaluation.

2. For Option 1: AI-Graded Submission and Evaluation:
   Submission requires the URLs for Task 1 to 5.

3. For Option 2: Peer-Graded Submission and Evaluation:
   Submission requires the URLs for Task 1 to 6.

### Task 1: Create a GitHub repository and update the README.md file

1. Go to GitHub and create a new GitHub repository called github-final-project and make sure that it is public. The repository name must be exactly the same as given.

2. Select the Add a README file checkbox and under Add license, choose Apache License 2.0 from the dropdown list.

3. Click Create repository. This will create your repository with the README and LICENSE files. You can now update these files to include relevant project details for your community.

4. Add the following information to the README.md file:

```
# This is the README.md file for the **github-final-project**
A calculator that calculates simple interest given principal, annual rate of interest and time period in years.
Input:
   p, principal amount
   t, time period in years
   r, annual rate of interest
Output
   simple interest = p*t*r
```

For Option 1: AI-Graded Submission and Evaluation:

- Copy and save the public GitHub repository URL of README.md in a text file, which contains the details about a simple interest calculator.

For Option 2: Peer-Graded Submission and Evaluation:

- Save the URL of the repository named github-final-project and the URL of the README.md file in a text file to submit later.

## Task 2: Add a license file

1. As part of Task 1, you picked a licence when creating the repository.
2. Open the LICENSE file and check that its content are pointing to Apache License 2.0.

- For Option 1: AI-Graded Submission and Evaluation and For Option 2: Peer-Graded Submission and Evaluation

- Copy and save the public GitHub repository URL of Apache License 2.0 in a text file to submit later.

### Task 3: Add a code of conduct

A code of conduct helps set the ground rules for the behavior of your project's participants. It defines standards for how to engage in a community.

GitHub provides templates for common codes of conduct to help you quickly add one to your project. To add a code of conduct to your project, complete the following steps:

1. Add a new file named CODE_OF_CONDUCT.md to the root folder of the repository with the "Contributor Covenant" template.

2. Go to your repositories homepage and check if the file has been created.

For Option 1: AI-Graded Submission and Evaluation and For Option 2: Peer-Graded Submission and Evaluation

- Copy and save the public GitHub repository URL of CODE_OF_CONDUCT.md in a text file to submit later.

### Task 4: Add contribution guidelines

The contribution guidelines tell project participants how to contribute to the project. To add contributions guidelines, complete the following steps:

1. Create a new file named CONTRIBUTING.md in the root directory of the repository with the following information:

```
All contributions, bug reports, bug fixes, documentation improvements, enhancements, and ideas are welcome.
```

3. Commit the file into the main branch and check if it is listed on the homepage of the repository.
   For Option 1: AI-Graded Submission and Evaluation and For Option 2: Peer-Graded Submission and Evaluation

- Copy and save the public GitHub repository URL of CONTRIBUTING.md in a text file to submit later.

### Task 5: Host the Script File

1. Create a new file named simple-interest.sh in the root directory of the repository.

2. Add the following code in the new file:

```
 #!/bin/bash
   # This script calculates simple interest given principal,
   # annual rate of interest and time period in years.
   # Do not use this in production. Sample purpose only.
   # Author: Upkar Lidder (IBM)
   # Additional Authors:
   # <your GitHub username>
   # Input:
   # p, principal amount
   # t, time period in years
   # r, annual rate of interest
   # Output:
   # simple interest = p*t*r
   echo "Enter the principal:"
   read p
   echo "Enter rate of interest per year:"
   read r
   echo "Enter time period in years:"
   read t
   s=`expr $p \* $t \* $r / 100`
   echo "The simple interest is: "
   echo $s
```

3. Commit the file into the main branch.

For Option 1: AI-Graded Submission and Evaluation and For Option 2: Peer-Graded Submission and Evaluation

- Copy and save the public GitHub repository URL of simple-interest.sh in a text file to submit later.
