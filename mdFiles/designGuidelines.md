# Design Guidelines for Learniverse Connect

## Introduction
This is the design guidelines for the web-application Learniverse Connect. The first part of the guidelines take 
inspiration from "Design Guidelines" provided by lecturer in Blackboard., while the other part takes inspirations 
from Don Norman's usability principles.

## Design Guide checklist
This checklist will be compared to the wireframes and design ideas throughout the time of the project. These wireframes
vary, and therefore the design guidelines was refactored. 

### Concept phase
In the concept phase, before writing code, you should decide the general rules and direction:
1. The **theme** for Learniverse Connect should be professional. This to encourage serious learners to apply for 
a course through this website. 
2. The first wireframes **color scheme** is inspired by other course providing website, such as UIA (https://www.uia.no/) 
and UIB(https://www.uib.no/). Both websites use red as its primary color.
3. The website focuses on **hierarchy and layout**. The most important message on the website should be the courses
the website provides. The website should have different pages, with different purposes and therefore different messages. 
The homepage should show popular courses while all courses will be shown in a different page called *courses*. The idea
behind this is to make the homepage easily accessible for viewers, while users who are more interested can visit the 
course page for more detailed information. The different sections of a page should include a navigation bar with a 
hamburger menu, a main part and a footer. 
   * **Header**: Top section with logo and company name, selection of currency, a cart, a login and a hamburger menu.
   * **Main part/body**: Central content area. This varies with the functions of the current page. 
   * **Footer**: Bottom section with links and copyright info.
   
   The main parts layout varies from page to page, where the login and signup or example should have a form for the user 
to fill out. 
4. All the **images** used in the website should be stored in a folder. These images include course cover images and
profile pictures. The images include an *alt text* which is an alternative text for the image. It's used to provide a 
textual alternative to images for people who are visually impaired or for situations where the image cannot be displayed. 
Alt text also helps search engines understand the content of images. 
5. The **icons** used is either black-and-white/greyscale icons. This is to achieve simplicity, clarity, consistency, 
and accessibility.
6. **Typography**: The text should be a thin sans-serif font with sharp edges. This to achieve a modern but professional
look. 
7. The design uses both **border rounding** and sharp edges. Rounded borders on buttons and the display of the courses, 
while sharp edges on the login/signup form and elements in profile page. Using a mix of rounded borders and sharp edges 
in design achieves a balanced visual hierarchy, adds aesthetic interest, and enhances functionality. Rounded borders 
offer a softer, friendlier feel for engaging elements like buttons, while sharp edges provide clarity and focus for 
important areas like forms. This blend caters to different aspects of brand identity and improves user experience by 
guiding attention and aiding navigation.
8. Not much **shadowing** will be used. Shadows may lead to a more unwelcoming experience for the user. 

### Sketch phase
Create a sketch of the page which shows the general layout of the page.
![Wireframe](/wireframe.png)

### Specific technical decisions
This comes at a later stage when you start implementing the site:
1. The **color** of purple (#9758a3) is the main color of the website, while different gradients of the same 
color becomes its accent color. There is no specific shadows/tints used on the website. Changed from concept phase 
where the color is red. This because red is a strong color, and the website may feel aggressive and unwelcoming. 
Learniverse Connect should have a balance of both professionalism and approachability. The website should feel 
inviting, easy to use and professional. This to encourage user to engage with the site.
2. The **typography** for the website includes the same font for every page and different font sizes for different
purposes. 
![Fontscale](/fontscale.png)
3. **Hierarchy and layout**. The focus of the website is the courses. All other information is additional to the main purpose of Learniverse Connect. A user can view the page as a viewer, use the page as a user and modify the page as an admin. Therefore
funcions for user and admins, are in second focus and not main. The purpose of Learniverse is to provide courses and therefore
is the courses the most important element.
4. **Icons**
Learniverse Connect uses icons for a more userfriendly design. It's easier to navigate and selfexplainig icons for important features such as home, login and cart. Home is symbolised by a house, the login with an avatar and cart with a shopping cart. The icons are in the same style, to create a connection between them. In addition to icons, there is also an explanatory text for the icon. The icons is retrived from [iconfinder.](https://www.iconfinder.com/).
5. **Spacing**
To create a more organized layout, spacing between elements are taken to account. By creating a fulfilling but not crowded, makes the page more userfriendly. All elements have good spacing between them and not to many elements on the same surface, creates a calmer experience for the user. 
6. **Border rounding**
To create a softer and more welcoming look for the pages, the elements mostly have rounded edges and border rounding. Mainly buttons but also element containers have round features. The course card has both rounded borders and round elements.

## Conclusion
By adhering to these design guidelines, we aim to create a user-friendly and accessible web application that provides a 
delightful experience for all users. Consistency, clarity, and accessibility are at the core of our design philosophy, 
and we encourage all team members to follow these principles in their design and development efforts.