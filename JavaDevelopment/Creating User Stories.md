# Creating User Stories Lab

## Learning Objectives

After completing this lab

- Create GitHub repositories
- Draft user stories for real-world business scenarios

## Introduction

In this lab, we will write user stories for a smart clinic portal supporting admins, doctors, and patients. These stories help define the functional requirements and guide backend development. Begin by creating a new GitHub repository using a provided template, and then add the user stories to the project.

### Exercise 1: Add User Stories

Adding a user story template

1. In the repository, click on `Add File` and select `Create new file`.
2. Name the file `user_stories.md`.
3. Copy the following template into the file:

```
# User Story Template

**Title:**
_As a [user role], I want [feature/goal], so that [reason]._

**Acceptance Criteria:**
1. [Criteria 1]
2. [Criteria 2]
3. [Criteria 3]

**Priority:** [High/Medium/Low]
**Story Points:** [Estimated Effort in Points]
**Notes:**
- [Additional information or edge cases]
```

4. Click `Commit new file` to save the template.
5. Use this template for all the user stories in the exercises. Will commit and push the changes to GitHub after completing all the user story exercises. See the Submission page for Git commands.

### Exercise 2: Define Admin User Stories

Add the following admin user stories as issues using the template

As an admin, I can:

- Log into the portal with your username and password to manage the platform securely
- Log out of the portal to protect system access
- Add doctors to the portal
- Delete doctor's profile from the portal
- Run a stored procedure in MySQL CLI to get the number of appointments per month and track usage statistics

Note: Need to add at least five user stories for this exercise.

### Exercise 3: Define Patient User Stories

Patients interact with the portal to find doctors and manage appointments. Add the following patient user stories as issues using the template:

As a patient, I can:

- View a list of doctors without logging to explore options before registering
- Sign up using your email and password to book appointments
- Log into the portal to manage your bookings
- Log out of the portal to secure your account
- Log in and book an hour-long appointment to consult with a doctor
- View my upcoming appointments so that I can prepare accordingly

Note: Need to add at least five users stories for this exercise.

### Exercise 4: Define Doctor User Stories

Doctors manage their availability and appointments. Add the following doctor user stories:

As a doctor, I can:

- Log into the portal to manage your appointments
- Log out of the portal to protect my data
- View my appointments calendar to stay organized
- Mark your unavailability to inform patients only the available slots
- Update your profile with specialization and contact information so that patients have up-to-date information
- View the patient details for upcoming appointments so that I can be prepared

Note: Need to add at least five user stories for this exercise.

#### Summary

In this lab:

- Created user stories issue template markdown file.
- Created user stories for admin, doctor, and patient personas.

#### Next Steps

- Build model classes
- Implement authentication and authorization
- Add business logic and CRUD operations
