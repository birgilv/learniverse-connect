# Sprints 

## Week 7: 

Date: 15th February  
Participants: Vegard Arnesen Mytting, Øystein Grande, Chris Sivert Sylte, Birgitte Vik 

### What was accomplished: 

* Started on a wireframe 
* Installed MySQL on OpenStack server 
* Begun creating front-end webpages 
* Added basic search bar 
* Added routing between pages 
* Added some images 

 

### What didn’t go well: 

* Confusion using Node.js (npm) 
* Confusion when pushing and making different branches 
* Disagreement on what to work on/do 

 

### What to do: 

* Create web design guidelines 
* Connect database to the application 
* Make website more visually pleasing
 - Add effects
 - Center elements and text

## Discussion: 
We have created a basic react application where the user can go between different webpages. The group decided to create a react application early on as we thought it would lessen the amount of code having to be refactored. The largest issue is figuring out what we should focus on and spend our time on. We don’t know how the application is going to communicate with the front-end, how to connect the database to the application, how to use APIs and so on. Our concern is that if we begin creating something there might be another way easier method which would mean we wasted our time. 

## Week 9

Date: 29th February  
Participants: Vegard Arnesen Mytting, Øystein Grande, Chris Sivert Sylte, Birgitte Vik 

### What was accomplished: 

* Connected database
* Some improvement on styling
* Improved project strucutre, more concise and removed
unecessary files


### What didn’t go well: 

* Not too much work has been done
* Unclear what to do and work on

### What to do: 

* Convert flowchart to use-case
* Imporve/expand upon database schema
* Clean up file strucutre
* Øystein currently taking a course and will update the
website continuously
* Chris Sivert focuses on communicating with the database
and making front-end elements retrieve necessary information
* Vegard works on hamburgermenu and header
* Birgitte works on front-end overall


## Discussion: 
Each person works on their indivudal page to avoid merge conflicts. Important to remember to divide the work among the group memebers on both front-end and back-end. As of now we continue working on front-end, Chris Sivert will attempt to make the front-end be able to retrieve information from the database. We feel as we are waiting for the lecture to move on. Now we only create styling but have no functionality.


## Week 11

Date: 14th March  
Participants: Vegard Arnesen Mytting, Øystein Grande, Chris Sivert Sylte, Birgitte Vik 

### What was accomplished: 

* Able to fetch APIs from backend connected to database
* Populated the db with three courses
* Added ability to sort by credits and title
* Added API that converts all currencies to NOK
* Added ability to search for coursetitle(searchbar)
* Added ability to signup
* Added loginpage (not working)
* Added contact form (email not working)
* Began adding JWT


### What didn’t go well: 

* Still missing quite a bit on styling
* Little hard for everyone to understand exactly what is happening when everyone works on their own stuff
* Currency conversion has only 5000 total requests so not a permanent solution

### What to do: 

* Add authentication of user using JWTs
* Add more improved styling
* Improve fetching from backend (make seperate classes for fetching)


## Discussion: 
A lot was achieved the past two weeks especially on backend. The database works as intended and fetching works. A little hard to understand all the changes that has happend since everyone solves them independently although this will probably become easier as we get more familiar with frontend and backend and how it all works.




 ## Week 14

Date: 4th April
Participants: Vegard Arnesen Mytting, Øystein Grande, Chris Sivert Sylte, Birgitte Vik 

### What was accomplished: 

* Added connection to DBeaver instead of using SQL scripts inside of Vscode
* Readded the filters (such as price and sortby price, credits and title)
* Added ability to sort by categories, although not implemented as intended
* Added a simple cartPage (not working)


### What didn’t go well: 

* Easter break so less has been done
* Some things that have been added need to be redone due to not being implemented correctly

### What to do: 

* Øystein will work on authentication, will look at code girts presented in class. Will attempt to connect
user in database to show on profile page. Vegard will look at backend specifically using ResponseEntities instead of
models. Chris Sivert will try to make cart page work as intended (able to add courses from different providers). Birgitte will work on the overall project not a specific page, she will work on styling aswell.


## Discussion: 
We have a good plan of what to do forward, and understand what happens pretty well. There is a lot to do and a little stressed with the time left. 


## Week 17: 

Date: 25th April 
Participants: Vegard Arnesen Mytting, Øystein Grande, Chris Sivert Sylte, Birgitte Vik 

### What was accomplished: 

* Added speech mechanism
* Added ability to change font-size
* Added some ARIA elements
* Fixed ability to register and login (showcases if user is active or not, added startdate)
* Added ability to add course from multiple providers
* Added api documentation (swagger)
* Began creating tests in Postman
* Began having ability to store images in database


### What didn’t go well: 

* application.properties changed after merging
* Other files and folders were duplicated when merging
* Problem with cycle errors due to autowiring (jwtRequestFilter-->jwtUtil-->Securityconfig--> jwtRequestFilter)
* Trouble adding Postman tests due to foreign keys (fixed now)
* Id apparently needs to be determined by coursename
* Difficult with styling and different sizes of screens
* Retrieving images from db is hard
* Solving authentication was hard


### What to do: 

* Add ability to add admin (perhaps have a superadmin)
* Add ability to change user credentials
* Show the correct info on userpage once user is logged in (Øystein)
* Add ability for user to add favorite courses (Chris Sivert)
* Remove Impl-classes
* Combine AccessUserService and UserService (Øystein)
* Retrieve images from database (Vegard)

## Discussion: 
Great to have finished the registration and login. Plan to change how axios fetches. Focus on acheving the mandatory tasks such as adding HTTPS, having a page for each role, adding design guideline and so on. Perhaps admin needs to have their own page showing overview of courses and users.

## Week 20
Date: 15th May
Participants: Vegard Arnesen Mytting, Øystein Grande, Chris Sivert Sylte, Birgitte Vik

### What was accomplished:

* Authentication in backend is complete.
* Fixed a merge conflict by creating a new branch and cherry-picking changes.
* Managed parsing JWT in frontend and fixed a related bug.
* Worked on storing JWT contents into cookies.
* Created Postman tests for model and authentication.
* Added a design guideline file.
* Gave admin users access to an admin page.
* Added images to the database and implemented CRUD operations for both BLOB and JSON data.
* Ensured the footer works correctly.
* Added a purchasedpage
* Added restriction of only buying one instance of a course
* Max price is decided by most expensive course and currency selected

### What didn’t go well:

* A lot of time was spent studying for the OS exam.
* Challenging using foreign keys
* Boring to have to update the database for everyone when someone changes it locally

### What to do:

* Ensure cookies are correctly set and user info is retrieved into the user page.
* Include tokens in subsequent requests to the backend and implement user logout.
* Style the profile page.
* Continue developing the admin page with functionalities to create, edit, delete, and hide courses and users.
* Implement these functions with a simple and organized design and create more Postman tests.
* Make the frontend more responsive and fill in missing product information.
* Work on different error pages and fine-tune the API-request 'super-method'.

### Discussion:

* Review the overall format and ensure all necessary information is included.
* Continue to address and resolve any merge conflicts promptly.
* Prioritize tasks based on project deadlines and exam schedules to balance study and work effectively.