# Creating Frontend Pages Lab

## Learning Objectives

After completing this lab, will be able to:

- Structure HTML, CSS, and JS files for a large frontend project
- Design responsive and interactive user interfaces
- Implement reusable UI components (e.g., Header, Footer, Cards)
- Trigger modals and manage page-level state using vanilla JavaScript

## Introduction

This lab is designed to help lay the foundation of a fully functional and scalable frontend for a Clinic Management System. Will structure the HTML, CSS, and JavaScript files, and build modular components that are clean, reusable, and maintainable.

## Key Terms

- **Component**: A reusable piece of UI (header, footer, card)
- **Page-Specific JS**: JavaScript files that control behavior specific to individual pages
- **Modularity**: Organizing code into self-contained units
- **Separation of Concerns**: Dividing a program into distinct sections with minimal overlap
- **Local Storage**: Storage space of the browser

## Project Structure

```txt
app/src/main/resources
├── application.properties
├── static
│   ├── index.html
│   ├── assets
│   │   ├── css
│   │   │   ├── addPrescription.css
│   │   │   ├── adminDashboard.css
│   │   │   ├── doctorDashboard.css
│   │   │   ├── index.css
│   │   │   ├── patientDashboard.css
│   │   │   ├── style.css
│   │   │   └── updateAppointment.css
│   │   └── images
│   │       ├── addPrescriptionIcon
│   │       │   └── addPrescription.png
│   │       ├── edit
│   │       │   └── edit.png
│   │       ├── defineRole
│   │       │   └── index.png
│   │       └── logo
│   │           └── logo.png
│   ├── js
│   │   ├── components
│   │   │   ├── appointmentRow.js
│   │   │   ├── doctorCard.js
│   │   │   ├── footer.js
│   │   │   ├── header.js
│   │   │   ├── modals.js
│   │   │   ├── patientRecordRow.js
│   │   │   └── patientRows.js
│   │   ├── config
│   │   │   ├── config.js
│   │   ├── services
│   │   │   ├── appointmentRecordService.js
│   │   │   ├── doctorServices.js
│   │   │   ├── index.js
│   │   │   ├── patientServices.js
│   │   │   └── prescriptionServices.js
│   │   ├── addPrescription.js
│   │   ├── adminDashboard.js
│   │   ├── appointmentRecord.js
│   │   ├── doctorDashboard.js
│   │   ├── loggedPatient.js
│   │   ├── patientAppointment.js
│   │   ├── patientDashboard.js
│   │   ├── patientRecordServices.js
│   │   ├── render.js
│   │   ├── updateAppointment.js
│   │   └── util.js
│   └── pages
│       ├── addPrescription.html
│       ├── loggedPatientDashboard.html
│       ├── patientAppointments.html
│       ├── patientDashboard.html
│       ├── patientRecord.html
│       └── updateAppointment.html
└── templates
    ├── admin
    │   └── adminDashboard.html
    └── doctor
        └── doctorDashboard.html
```

Each folder has a specific purpose

- `assets/css`: CSS files for styling individual pages and shared components like buttons, headers, and modals.
- `assets/images`: Icons, logos, and illustrations used throughout the UI. Organized into folders like `logo`, `edit`, and `index` for easier reuse.
- `pages`: Standalone HTML files for different user roles and screens, such as `patientDashboard.html` or `addPrescription.html`. These are dynamically updated by JavaScript.
- `js`: JavaScript logic is broken down into:
- `services/`: API communication logic for doctors, patients, prescriptions, and appointments. Handles all fetch and CRUD operations.
- `components/`: Reusable UI components like `header.js`, `doctorCard.js`, and `modal.js` to keep layout and interaction code modular.
- `config/`: Stores shared constants and settings such as API base URLs or environment variables in `config.js`.
- Page-specific files: Role-based scripts like `adminDashboard.js`, `updateAppointment.js` or `patientDashboard.js` for handling user interaction, rendering, and calling services.
- `util.js`: Helper functions shared across pages, such as token handling, date formatting, or alerts.
- `templates`: Thymeleaf-based HTML templates rendered by the backend for authenticated users. Splits into folders like `admin/` and `doctor/` to serve role-specific views.
- `application.properties`: Central configuration file for Spring Boot that sets up server ports, database access, and environment properties.

## HTML Pages

### 1. index.html

In this step, create the initial landing page where users can choose their role in the Clinic System - Admin, Doctor, or Patient. This page sets the stage for a personalized user experience by allowing the user to select their identity, which will be used to tailor the dashboard and access permissions.

Include buttons for each role, and store the selected role in the browser's `localStorage` so it can be accessed on subsequent pages. Later, trigger login modals based on the selected role.

Let's begin by creating the HTML layout and adding placeholders for the necessary scripts and components.

### HTML code

1. Open the `index.html` file in `app/src/main/resources/static/index.html`

2. Add the following basic HTML structure:

- Use `<!DOCTYPE html>` and `<html lang="en">`.
- Inside `<head>`, include:
- `<meta charset="UTF-8">`
- `<title>` for the page.
- Favicon `<link rel="icon" ... />`.
- CSS links (`index.css`, `style.css`).

Example:

```html
<link rel="stylesheet" href="./assets/css/index.css" />
<link rel="stylesheet" href="./assets/css/style.css" />
```

- JS links for utility (`util.js`, `render.js`) with defer keyword to avoid render-blocking i.e. defer allows the browser to keep parsing and rendering the HTML while it downloads the JS. .

Example:

```html
<script src="./js/util.js" defer></script>
<script src="./js/render.js" defer></script>
```

- JS links for the components (`header.js`, `footer.js`).

Example:

```html
<script src="./js/components/header.js" defer></script>
<script src="./js/components/footer.js" defer></script>
```

3. In the `<body></body>`, add the following content inside a `<main>` tag:

- Wrap the content inside a div with class name `container` `<div class="container">`.
- Inside the container div create another `<div>` with class name wrapper (`<div class="wrapper">`) to group the sections.
- Add placeholders for **Header** (`<div id="header"></div>`) and placeholder for **Footer**(`<div id="footer"></div>`).
- In the `<main class="main-content">`, create:
- A heading in h2 tag: "Select Your Role".
- Buttons for each role: Admin, Doctor, Patient.
- Use `onclick` for dynamic role selection.
- Also assign unique ids to these elements for easy DOM access in JavaScript.

4. Add a modal that is hidden by default and appears when triggered by an event listener.

- Create a hidden modal `<div id="modal" class="modal">` with:
- Close button (`id="closeModal"`)
- Dynamic content area (`id="modal-body"`).

5. Include the JavaScript file at the end of the `<body>` or just before the `<body>` tag to ensure the DOM is fully loaded.

- Use `<script type="module" src="../js/services/index.js" defer></script>` to include the service for role selection and modal behavior.

### CSS Code

1. Open the `index.css` file in `app/src/main/resources/static/assets/css/index.css`

2. Add the following global styling:

- `* { margin: 0; padding: 0; box-sizing: border-box; }`: Resets default margins, paddings, and sets consistent box-sizing for all elements.
- The body and HTML elements are styled for full height and to use the 'Roboto' font.

3. Style the main wrapper:

- `.wrapper` sets the layout to be a flex column, ensuring the main content grows to take available space.

4. Heading (h2) and Buttons:

- Styled for large, bold text with a specific color and font family.
- Buttons are centered and styled with background color, padding, rounded corners, and hover effects to change background color.

5. Main Content Area:

- `.main-content` has a background image with properties for size, position, and attachment (fixed background that doesn't scroll).
- The content is centered and aligned with the flexbox model.

6. Modal and Button Interactions:

- Modal and button interaction styles include close button (`.close`), hover effects, and modal positioning.

7. Footer and Header Styles:

- Includes `.footer` with layout for flexbox-based sectioning, responsive for mobile view.
- `.header` styles logo and nav items for a clean and responsive layout.

8. Interactive Styles:

- Buttons, inputs are styled with hover and focus effects for interactivity.
- Specific styles are included for `.dashboard-btn`, `.input-field`, `.select-dropdown`, `.checkbox-group`, and more for a clean UI experience.

#### 2. adminDashboard.html(Thymeleaf Code)

In this section, build the main dashboard for Admin users. This page will serve as the control panel for managing doctors - allowing admins to view, add, delete, and filter doctor profiles.

The page should include a search bar, dropdown filters (by speciality or time), and a dynamic list of doctor cards. Also add a modal popup for adding new doctors, which will be triggered by a button click.

This layout will eventually be connected to the backend and populated using JavaScript services.

Tips:

- The admin is validated first using token and userRole.
- Include modal forms for adding new doctors.

### HTML code

1. Open the HTML file:

- Open the `adminDashboard.html` located at `app/src/main/resources/templates/admin/adminDashboard.html`.

2. Add Basic HTML Structure:

- Use `<!DOCTYPE html>` and `<html lang="en">`.
- In the `<head>` tag, include:
- `<meta charset="UTF-8">`
- A `<title>` for the page (Admin Dashboard)
- CSS links for page-specific and global styles:

```html
<link rel="stylesheet" th:href="@{/assets/css/adminDashboard.css}" />
<link rel="stylesheet" th:href="@{/assets/css/style.css}" />
```

- JavaScript files with `defer`:

- Utility and rendering logic:

```html
<script th:src="@{/js/render.js}" defer></script>
<script th:src="@{/js/util.js}" defer></script>
```

- Layout components:

```html
<script th:src="@{/js/components/header.js}" defer></script>
<script th:src="@{/js/components/footer.js}" defer></script>
```

3. Create Body Layout:

- Inside the `<body>`, structure the layout as follows:

- Wrap everything in `<div class="container">` and then `<div class="wrapper">`.
- Insert `<div id="header"></div>` to load the dynamic header. `header.js` will provide a button labeled **Add Doctor** with a unique ID to trigger the modal.
- Create a `<main class="main-content"></main>` section containing:
- A **search bar** using `<input type="text" id="searchBar" placeholder="search by doctor name" />`
- A **filter section** with two `<select>` dropdowns for:
- Sorting by time
- Filtering by specialty
- A `<div id="content"></div>` to dynamically inject the list of doctors.
- Add `<div id="footer"></div>` for the dynamic footer.

4. Add Modal for Adding Doctor:

- At the end of the `<body></body>`, add a hidden modal:

```html
<div id="modal" class="modal">
  <span id="closeModal" class="close">&times;</span>
  <div id="modal-body"></div>
</div>
```

5. Include JavaScript File:

- Just before the closing `</body>`, include the service script for admin dashboard and doctorCard:

```html
<script type="module" src="../js/services/adminDashboard.js" defer></script>
<script type="module" src="../js/components/doctorCard.js" defer></script>
```

### CSS Code

1. **Open the CSS File**:

- Open `adminDashboard.css` located at `app/src/main/resources/static/assets/css/adminDashboard.css`.

2. Add Universal Styling:

- Reset and base setup:

```css
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
html,
body {
  height: 100%;
  font-family: Arial, sans-serif;
}
```

3. Layout and Wrappers:

- Use `.wrapper` and `.main-content` to apply:

- Vertical flex layout
- Background image settings
- Proper alignment and padding for clean layout

4. Interactive Elements:

- Style the search bar, filter dropdowns, and the Add Doctor button:
- Use `.button`, `.adminBtn`, and hover states
- Rounded corners, consistent spacing, and accent colors

5. Modal Styling:

- Ensure the modal is centered, hidden by default, and has smooth transitions
- Style inputs inside the modal form with padding and focus effects

6. Doctor Cards and Content Area:

- Use flexbox for displaying doctor cards
- Ensure responsive behavior for smaller devices

#### 3. doctorDashboard.html (Thymeleaf Code)

Next, create the dashboard for Doctor users. This page focuses on patient management and appointment tracking.

Build a clean interface that includes a table to list patient records, filters for dates or appointment status, and buttons for adding prescriptions, This layout should be easy to scan and interact with, enabling doctors to manage their workload efficiently.

### HTML Code

1. Open the HTML File:

- Open `doctorDashboard.html` located at `app/src/main/resources/templates/doctor/doctorDashboard.html`

2. HTML Structure:

- Start with `<!DOCTYPE html>` and `<html lang="en">`.
- In the `<head>`, include:

- Standard meta tags and the page title `"DoctorDashboard"`.
- Favicon link.
- Stylesheets: `adminDashboard.css`, `doctorDashboard.css`, `style.css`.
- Scripts:
- Utility files: `render.js`, `util.js`
- Components: `header.js`, `footer.js`, `patientRows.js`
- Services and main logic: `patientServices.js`, `doctorDashboard.js`

3. Body Layout:

- Use `<div class="container">` and wrap content inside `<div class="wrapper">`.
- Header is injected via `<div id="header"></div>`.
- Main dashboard includes:
- Search bar (`<input type="text" id="searchBar" />`)
- Filter section:
- "Today's Appointments" button
- Date input for selecting other dates
- Patient records table:
- Use `<table id="patientTable">` with columns: Patient ID, Name, Phone, Email, Prescription
- Body of the table is populated dynamically
- Footer is injected via `<div id="footer"></div>`

### CSS Code

1. Open the CSS File:

- Open `doctorDashboard.css` located at `app/src/main/resources/static/assets/css/doctorDashboard.css`.

2. Reset and Base Styles:

- Apply global reset with `* { margin: 0; padding: 0; box-sizing: border-box; }`
- Set fonts and page height in `html, body`

3. Layout:

- Use `.wrapper` as a vertical flex container
- Space and align `.main-content` elements for search, filters, and the table

4. Search and Filter Styling:

- Input and buttons are padded, rounded, and spaced
- Button hover: background color shift (`#015c5d` -> `#017d7e`)

5. Table Styling:

- `table`: Full width, border-collapse
- `thead`: Dark semi-transparent background
- `tbody tr`: Alternating row colors, highlight on hover

6. Extras:

- `.prescription-btn`: Add scale/brightness hover effect
- `.noPatientRecord`: Italic, gray text when no records

7. Responsive Design:

- Mobile-friendly layout using flexible widths
- Add media queries to adjust font sizes and layout for small screens

#### 4. patientDashboard.html (Static HTML Page)

Next, create the **Patient Dashboard** - a central page where patients can **browse and filter doctor profiles**, and initiate appointment bookings. This layout is interacticve and uses dynamic rendering to show relevant doctors based on the patient's selected filters or search input.

Will also integrate modals to allow appointment booking and provide a rich UI experience using reusable components and state management in JavaScript.

### HTML Code

1. Open the **HTML File**:

- Open `patientDashboard.html` located at `app/src/main/resources/static/pages/patientDashboard.html`

2. Set Up the Head Section:

Inside the `<head>` tag, include:

- Metadata and page title
- Favicon for branding
  -Links to CSS files:

```html
<link rel="stylesheet" href="../assets/css/adminDashboard.css" />
<link rel="stylesheet" href="../assets/css/style.css" />
<link rel="stylesheet" href="../assets/css/patientDashboard.css" />
```

- JS Utility and component scripts with `defer`:

```html
<script src="../js/render.js" defer></script>
<script src="../js/util.js" defer></script>
<script src="../js/components/header.js" defer></script>
<script src="../js/components/footer.js" defer></script>
<script type="module" src="../js/components/modals.js"></script>
```

3. Create the Body Structure:

In the `<body>`, follow this layout:

- Wrap all content inside `<div class="container">` and `<div class="wrapper">`.
- Insert the dynamic `<div id="header"></div>` for the role-based header.
- Inside the `<main class="main-content">`, add:

- A search bar:

`<input type="text" id="searchBar" class="searchBar" placeholder="Search Bar for custom output" />`

- A filter section:

```html
<div class="filter-wrapper">
  <select class="filter-select" id="filterTime">
    ...
  </select>
  <select class="filter-select" id="filterSpecialty">
    ...
  </select>
</div>
```

The dropdowns allow filtering doctors by **time availability (AM/PM)** and **specialty**.

- A content container for dynamically rendered doctor cards:

`<div id="content"></div>`

- Include `<div id="footer"></div>` to render the common footer.

- Add the reusable modal markup:

```html
<div id="modal" class="modal">
  <div class="modal-content">
    <span class="close" id="closeModal">&times;</span>
    <div id="modal-body"></div>
  </div>
</div>
```

4. Load Page Logic:

Add a JavaScript module to handle patient-specific rendering:

`<script type="module" src="../js/patientDashboard.js" defer></script>`

Also, call `renderContent()` on `body` load:

`<body onload="renderContent()">`

### CSS Code

1. **Open the CSS File:**

- Open `patientDashboard.css` located at `app/src/main/resources/static/assets/css/patientDashboard.css`

2. **Style the Doctor Card Actions:**

- Add a card-actions class to style the section where action buttons like "Book Now" appear.
- Use a dark background with centered text.
- Add a hover effect to slightly darken the background on mouseover for interactivity feedback.

3. **Ripple Overlay for Visual Feedback**:

- Create a circular animation that expands outwards to simulate a click ripple when booking is confirmed.
- Use fixed positioning so the effect appears anywhere on the screen.
- Make the animation smooth using CSS transitions and transform: scale(...) effects.

4. **Bottom Pop-Up Modal for Booking:**

- The .modalApp class defines a container that initially slides in from the bottom of the screen.
- Used fixed positioning and horizontal centering.
- Apply a background color of white, subtle padding, and rounded top corners.
- Add a smooth slide-in animation triggered by toggling the .active class.

5. **Form Fields Inside Modal**:

- Inputs and dropdowns in the modal should:
- Be centered on the page
- Have uniform width (90% of the container)
- Include padding for usability
- Be spaced vertically for visual clarity

6. **Booking Confirmation Button**:

- Use a dark-colored background to match the theme.
- Add padding and rounded corners for a clean, clickable look.
- On hover, increase brightness slightly for user feedback using filter: brightness (1.2).

## Components

### 1. Header.js

This file defines a reusable header component that appears at the top of every page. It dynamically changes based on the user's role (admin, doctor, and patient) and login state. It improves code reusability and reduces duplication across multiple HTML files.

Use JavaScript to insert navigation links, role selectors, and logout buttons depending on the context of the current page.

### Task

Open the `header.js` file

In this lab, will build a renderHeader() function that:

- Checks the current page. We don't want to show the role-based header on the homepage.

HINT:

```js
if (window.location.pathname.endsWith("/")) {
  localStorage.removeItem("userRole");
  localStorage.removeItem("token");
}
```

- Looks at the user's role and login token in localStorage, to determine which header layout to show.

HINT:

```js
const role = localStorage.getItem("userRole");
const token = localStorage.getItem("token");
```

- Add the condition to check invalid handle button.

HINT:

```js
if (
  (role === "loggedPatient" || role === "admin" || role === "doctor") &&
  !token
) {
  localStorage.removeItem("userRole");
  alert("Session expired or invalid login. Please log in again.");
  window.location.href = "/";
  return;
}
```

- Injects the appropriate header HTML into the page.

HINT:

```js
if (role === "admin") {
  headerContent += `
        <button id="addDocBtn" class="adminBtn" onclick="openModal('addDoctor')">Add Doctor</button>
        <a href="#" onclick="logout()">Logout</a>`;
}
```

Repeat for:

- `doctor` -> Show "Home" and Logout
- `patient` -> Show "Login" and "Sign Up"
- `loggedPatient` -> Show "Home", "Appointments", and Logout

- Provides navigation buttons and logout functionality tailored to each user type.

HINT:

```txt
Start with an empty string: headerContent = ""
If role is admin
 Add HTML string for "Add Doctor" button and Logout link
If role is doctor
 Add "Home" button and Logout
If role is patient
 Add Login and Signup buttons
If role is loggedPatient
 Add Home, Appointments, and Logout
```

- Finalize Header Injection. This replaces the contents of the `#header` div with the generated HTML.

HINT:

```js
headerDiv.innerHTML = headerContent;
attachHeaderButtonListeners();
```

- Attach Event Listeners because elements were dynamically created, need to attach listeners after insertion.

- Use document.getElementById("someBtnId")
- Check if the element exists (in case the button is not for all roles)
- Use .addEventListener("click", ...) to attach a handler.

HINT:

```txt
After rendering the header
Find buttons by ID
Attach 'click' event listeners (e.g. to open a modal or clear storage)
```

- Implementing Logout Functionality for clearing the session and going back to the start.

- Remove both `token` and `userRole` from localStorage.
- Redirect to homepage using `window.location.href = "/"`.
- For patient we can retain their "role" as just patient, not loggedPatient, to show login/signup again.

HINT:

```txt
Create a function called logout
Inside it, remove token and userRole
Redirect to homepage

Create logoutPatient function
Remove token
Set role back to "patient"
Redirect to patient dashboard
```

### 2. Footer.js

The footer component remains consistent across all pages and includes branding, navigation links, or any additional information you want users to see at the bottom of the screen. Unlike the header, the footer is static and doesn't change based on user role.

It improves modularity and makes layout maintenance easier by separating common footer content into a single file.

#### Task

Open the `footer.js` file

- Create the Function

`function renderFooter() {}`

- Define a reusable function named renderFooter.
- Call this on every page that needs a footer.

- Access the Footer Container.

`const footer = document.getElementById("footer");`

- This locates the DOM element `<div>` with id="footer" where we want to inject our content.
- Make sure each HTML page has this container present.

- Inject HTML Content

`footer.innerHTML =`...`;`

- Replace the contents of the footer container with an HTML template string.
- This block includes branding, navigation, and legal info.
- Write regular HTML tags (`<footer>`, `<div>`, `<h4>`, `<a>`) as part of the string.
- Top-level container: `<footer class="footer">` wraps the whole thing.
- Branding section:
  `js <div class="footer-logo"> <img src="..." /> <p>© Copyright ...</p> </div>`
- Links sections divided into 3 columns:
- Company (About, Careers, Press)
- Support (Account, Help Center, Contact)
- Legals (Terms, Privacy Policy, Licensing)
  Each column uses a `<div class="footer-column">` with a heading and anchor tags.

- Call the Function

`renderFooter();`

- Call this function at the bottom of the footer.js file so it runs when the file loads.
- We can also import and call it from each page if needed.

### 3. doctorCard.js

This component creates a dynamic, reusable card for displaying the doctor information on the Admin and Patient dashboards. Each card can show a doctor's name, specialty, availability, and contact info, along with action buttons like "Delete", or "Book Appointment".

It improves separation of concerns by encapsulating UI rendering and interactivity in a single module.

#### Task

Open the `doctorCard.js` file

- Define the Function

`export function createDoctorCard(doctor)`

- Create a named export so other files can import and use this function.
- Accept one argument: a `doctor` object containing info like `name`, `specialty`, etc.

- Create the Main Card Container

```js
const card = document.createElement("div");
card.classList.add("doctor-card");
```

- Dynamically create a `<div>` element.
- Add a CSS class doctor-card for styling purposes.

- Fetch the User's Role

`const role = localStorage.getItem("userRole");`

- Read the current user's role (admin, patient, loggedPatient) from localStorage.
- Use this later to decide which buttons to show.

- Create Doctor Info Section

```js
const infoDiv = document.createElement("div");
infoDiv.classList.add("doctor-info");
```

- Make a nested container to hold the doctor's name, specialty, email, and availability.

Then add individual elements:

```js
const name = document.createElement("h3");
name.textContent = doctor.name;
```

- Create a heading element and set the text to the doctor's name.

Repeat similarly for:

- `specialization`
- `email`
- `availability`(can join an array with `join(", ")` to display multiple times)

Then append them all:

```js
infoDiv.appendChild(name);
infoDiv.appendChild(specialization);
infoDiv.appendChild(email);
infoDiv.appendChild(availability);
```

- Create a Button Container

```js
const actionsDiv = document.createElement("div");
actionsDiv.classList.add("card-actions");
```

- A new `<div>` to hold buttons like "Delete" or "Book Now".
- Conditionally Add Buttons Based on Role

#### Admin

```js
if (role === "admin") {
  const removeBtn = document.createElement("button");
  removeBtn.textContent = "Delete";
```

- Add a delete button only for admins.

Attach an event:

```js
removeBtn.addEventListener("click", async () => {
  // 1. Confirm deletion
  // 2. Get token from localStorage
  // 3. Call API to delete
  // 4. On success: remove the card from the DOM
});
```

#### Patient (not logged in)

```js
else if (role === "patient") {
  const bookNow = document.createElement("button");
  bookNow.textContent = "Book Now";
  bookNow.addEventListener("click", () => {
    alert("Patient needs to login first.");
  });
}
```

- Shows a button but alerts the user that login is required.

#### Logged-in Patient

```js
else if (role === "loggedPatient") {
  const bookNow = document.createElement("button");
  bookNow.textContent = "Book Now";
  bookNow.addEventListener("click", async (e) => {
    const token = localStorage.getItem("token");
    const patientData = await getPatientData(token);
    showBookingOverlay(e, doctor, patientData);
  });
}
```

- Allows real booking by calling a function from another module.
- Fetches patient data first

#### Final Assembly

Add all the created pieces to the card:

```js
card.appendChild(infoDiv);
card.appendChild(actionsDiv);
```

Return the final card:

`return card;`

#### Notes

This component uses helper functions imported from service files that will be implemented in the next lab:

deleteDoctor() from `js/services/doctorServices.js`
getPatientData() from `js/services/patientServices.js`

These service modules will handle API interactions and are part of the modular architecture designed for better maintainability and code reuse.

## Conclusion

In this lab:

- Set up the frontend project structure with HTML, CSS, and JS files
- Created and styled pages for different user roles
- Implemented reusable components like headers, footers, and doctor cards
- Kept code modular and followed clean architecture principles

## Next Steps

- In the next lab, focus on implementing and organizing JavaScript service files.
- This includes proper API calling, error handling, and modularizing page-specific functionality by importing and using these services.
- Make sure that all the work is commited and pushed to GitHub.

Follow these steps to push the changes:

1. Stage changes

`git add .`

2. Commit the changes with a meaningful message:

`git commit -m "Completed front end pages for Clinic Management System"`

Push the changes:

`git push`
