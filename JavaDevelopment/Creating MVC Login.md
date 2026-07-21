# Creating an MVC for Login Lab

## Overview

This lab demonstrates how to use Thymeleaf with Spring Boot for rendering views after validating user roles (admin and doctor) through token-based access control. Will implement a controller that dynamically serves Thymeleaf templates based on user authentication status.

## Learning Objectives

By the end of this lab, will be able to:

- Explain how to use `@Controller` for returning Thymeleaf views.
- Implement token validation logic using a Spring Boot service.
- Secure routes conditionally and render different views for different roles.
- Apply best practices in separating MVC logic and using `@GetMapping` with path variables.

## Key Terms

- **Thymeleaf**: A modern server-side Java template engine used for rendering HTML views.
- **@Controller**: A Spring annotation that marks a class as a web controller for returning views.
- **@GetMapping**: Annotation for mapping HTTP GET requests to specific handler methods.
- **@PathVariable**: Used to extract values from URI templates and bind them to method parameters.
- Token Validation: The process of checking whether a provided authentication token is valid.
- Redirect: An HTTP response that instructs the browser to navigate to a different URL.

## Configuring the Application

### Thymeleaf configuration in `application.properties`

This section explains how to configure Thymeleaf and static resource handling in a Spring Boot application to support rendering HTML templates and serving static files.

1. **Open the `application.properties` file**

2. **Configure static resource locations**

`spring.web.resources.static-locations=classpath:/static/`

- This setting tells Spring Boot where to look for static assets (e.g., CSS, JS, images).
- Files placed in `src/main/resources/static/` will be served directly by Spring without the need for a controller.
- Example: A CSS file in `src/main/resources/static/css/style.css` will be accessible at `http://localhost:8080/css/style.css`.

3. Set Thymeleaf template location and behavior

`spring.thymeleaf.prefix=classpath:/templates/`

- Defines the base directory for Thymeleaf templates.
- Spring will look for templates inside `src/main/resources/templates/`.

`spring.thymeleaf.suffix=.html`

- Specifies that all Thymeleaf templates will have an `.html` suffix.
- Can return `"admin/adminDashboard"` and Spring will resolve it to `admin/adminDashboard.html`.

`spring.thymeleaf.mode=HTML`

- Sets the rendering mode for templates. `HTML` mode is most commonly used for rendering well-formed HTML5 pages.

`spring.thymeleaf.cache=false`

- Disables caching of templates.
- Useful during development to see changes immediately without restarting the app.

`spring.thymeleaf.encoding=UTF-8`

- Ensures that all templates are read using UTF-8 encoding to support internationalization and special characters.

### Steps Summary

1. **Static resources**: Set `spring.web.resources.static-locations` to serve files like CSS/JS from `/static/`.
2. **Templates directory**: Point `spring.thymeleaf.prefix` to `/templates/` to locate HTML files.
3. **Template behavior**: Configure suffix, mode, cache, and encoding for optimal development settings.

## Creating Controller

The dashboard controller handles view rendering for Thymeleaf templates after validating a token for either admin or doctor users.

**Purpose**: This controller serves as a gatekeeper to the Thymeleaf dashboard views (`adminDashboard` and `doctorDashboard`) by verifying access tokens for authenticated users.

1. **Open the `DashboardController.java` file**

2. **Set up the Controller class**

- Annotate the class with `@Controller` to indicate it returns views, not JSON responses.
- This class maps requests to Thymeleaf templates based on user roles and tokens.

3. **Autowired dependencies**

- Autowire the required service:

- `Service` for handling the token validation logic.

4. **Define the `adminDashboard` method**

- Annotate with `@GetMapping("/adminDashboard/{token}")` to handle requests to the admin dashboard.

- Accept the token as a `@PathVariable`.

- Call `validateToken(token, "admin")` using the service and check if the returned map is empty:

- If empty: Token is valid -> return the `admin/adminDashboard` view.
- If not empty: Redirect to login page.

5. **Define the `doctorDashboard` method**

- Annotate with `@GetMapping("/doctorDashboard/{token}")` to handle doctor dashboard access.
- Accept the token as a `@PathVariable`.
- Call `validateToken(token, "doctor")` and apply the same logic as in the `adminDashboard`.

6. **Response**

- Each method returns a view name (`String`) for Thymeleaf to resolve:

- If token is valid: Returns the respective dashboard template.
- If invalid: Redirects the user to the login page at `http://localhost:8080`.

### Steps Summary

1. **Set up the controller**: Annotate with `@Controller`.
2. **Inject the `Service`**: Autowire the token validation logic.
3. **Define the view endpoints**: Use `@GetMapping`, validate tokens, and return the appropriate dashboard view or redirect.

## Conclusion

In this lab, have configured and worked with Spring Boot's integration with Thymeleaf to render dynamic views and validate user tokens for different user roles. By setting the appropriate properties in the `application.properties` file, ensured that static resources and Thymeleaf templates are served correctly, and the application works seamlessly during development.

**Key takeways**:

- How to configure Thymeleaf for rendering HTML templates.
- How to serve static resources (CSS, JavaScript, etc.) from the `static` folder.
- How to ensure efficient development through template caching and character encoding settings.

Also implemented a dashboard controller that uses token validation to render user-specific views for both admins and doctors.

## Next Steps

1. **Implement authentication & authorization**:

- Explore Spring Security to add real authentication (JWT, OAuth, etc.) to further secure the app.
- Use roles and permissions to restrict access to specific resources based on user credentials.

2. **Enhance token validation**:

- Build a more robust token validation system (e.g., JWT token expiration handling).
- Add error handling to provide user-friendly messages when token validation fails.
