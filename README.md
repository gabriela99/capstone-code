# Reto

This project is an Android app written in Java for the purpose of assisting a Product Management student's capstone project. The goal for this app was to connect colleagues within an organization by means of searching for colleagues with specific skills and by providing the user with the ability to see the contact information of said colleagues. 

## Prerequisites

#### Install/Configure Android Studio
* [Install Android Studio](http://developer.android.com/sdk/installing/index.html?pkg=studio)

#### Install/Configure Emulators or Android Devices
There are multiple ways to run an Android application. The following are 3 different solutions: 
* [Create/Configure an emulator](http://developer.android.com/tools/devices/managing-avds.html)
* [Install](https://www.genymotion.com/#!/download) and [Create/Configure](https://www.genymotion.com/#!/developers/user-guide) GenyMotion emulator
* [Configure an Android device](http://developer.android.com/tools/device.html)

#### Import the project into Android Studio
* Repository URL : **git@github.com:gabriela99/capstone-code.git**
* Clone and change directory to capstone-code

#### More information
* [Android studio overview](https://developer.android.com/tools/studio/index.html)

## Directory Tree

```
.
├── androidTest
│   └── ...
├── main                                                    
│   ├── AndroidManifest.xml                                       Includes all activities and indicates starting activity
│   ├── ic_search-web.png
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── capstone_code
│   │               ├── model
│   │               │   └── User.java                             Model of what a user entity must include
│   │               ├── utils
│   │               │   └── autoCompleteText.java                 Autocompletion of roles and skills that is used in settings
│   │               │   └── FirebaseDatabaseHelper.java           Fetches information about users and colleagues from database
│   │               │   └── getUserInfo.java                      Gets user information from Firebase datasnapshot
│   │               ├── view
│   │               │   ├── UserItemView.java                     For the UserList recycler view, model is bound to XML fields
│   │               │   └── UsersAdapter.java                     Uses UserItemView to fill recycler view with current users
│   │               └── viewmodel
│   │                   ├── ColleagueProfileActivity.java         Profile page of colleague
│   │                   ├── LoginActivity.java                    Allows an already created user to login with their info
│   │                   ├── ProfileActivity.java                  Profile page of current active user
│   │                   ├── RegisterActivity.java                 Page where user inputs their information for a new account
│   │                   ├── RoleActivity.java                     Part of the registration process, user provides role
│   │                   ├── SensorActivity.java                   Displays accelerometer information - for mobile module
│   │                   ├── SettingsActivity.java                 Allows user to edit the fields found on their profile page
│   │                   └── UserListActivity.java                 Displays colleagues and allows the user to filter results
│   └── res                                                       Directory for XML, including layout of all Activity pages
|       └── ...
└── test                                                          Directory for unit, instrumented, and UI tests
    └── ...
```

## Features
As was seen previously in the short directory tree explanations, here are the features:

* **Registration** 
  - Page where user inputs their information for a new account
  - Accessible via the Login page 
  - Composed of `RegisterActivity.java` and `activity_register.xml`
* **Login** 
  - Page where user inputs their information for a new account
  - First page to appear
  - Composed of `LoginActivity.java` and `activity_login.xml`
* **User List** 
  - Page where user inputs their information for a new account 
  - Found after user successfully logs in
  - Composed of `UserListActivity.java` and `activity_user_list.xml`
* **User Item** 
  - Displays the individual users that are found within the user list 
  - Composed of `UserListView.java` and `user_list_item.xml`
* **Toolbar** 
  - Toolbar used across activities
  - Found at the top of User List, Profile, Colleague Profile, and Sensor pages
  - Composed of `toolbar.xml` and either `toolbar_menu.xml` or `toolbar_profile_menu.xml`
* **Colleague Profile** 
  - Profile page of colleagues of the user 
  - Found by clicking on the card belonging to said colleague within the User List
  - Composed of `ColleagueProfileActivity.java` and `activity_colleague_profile.xml`
* **Profile** 
  - Profile page of active user 
  - Found by accessing the menu in the toolbar
  - Composed of `ProfileActivity.java` and `activity_profile.xml`
* **Settings** 
  - Page where user can change own `name`, `role`, or `skills` 
  - Accessible via the Profile or after Registration
  - Composed of `SettingsActivity.java` and `activity_settings.xml`
* **Sensor** 
  - Page with sensor data from an accelerometer
  - Found by accessing the menu in the toolbar
  - Composed of `SensorActivity.java` and `activity_sensor.xml`
  
## Demo
![](https://raw.githubusercontent.com/gabriela99/capstone-code/master/documentation/mAndroidApp.gif)

## Technical paper

* The technical details for the features mentioned above, as well as general architecture details can be found in the technical paper. It is located in [this repo's wiki](https://github.com/gabriela99/capstone-code/wiki/Technical-Paper)

