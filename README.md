# Capstone Project
-----
The app that enables users to find colleagues with certain skill sets.

## About
This project is an android app written with Java with a Firebase database.


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
│   │               │   └── FirebaseDatabaseHelper.java           Fetches information about users and colleagues from database
│   │               ├── view
│   │               │   ├── UserItemView.java                     
│   │               │   └── UsersAdapter.java                     
│   │               └── viewmodel
│   │                   ├── ColleagueProfileActivity.java         Profile page of colleague
│   │                   ├── LoginActivity.java                    Allows an already created user to login with their info
│   │                   ├── ProfileActivity.java                  Profile page of current active user
│   │                   ├── RegisterActivity.java                 Page where user inputs their information for a new account
│   │                   ├── RoleActivity.java                     Part of the registration process, user provides role
│   │                   ├── SearchActivity.java     
│   │                   ├── SettingsActivity.java                 Allows user to edit the fields found on their profile page
│   │                   └── UserListActivity.java                 Displays colleagues and allows the user to filter results
│   └── res
│       ├── drawable
│       │   ├── ic_icon.xml
│       │   ├── ic_launcher_background.xml
│       │   ├── ic_search_background.xml
│       │   └── ic_search_foreground.xml
│       ├── drawable-v24
│       │   └── ic_launcher_foreground.xml
│       ├── layout
│       │   ├── activity_colleague_profile.xml
│       │   ├── activity_login.xml
│       │   ├── activity_profile.xml
│       │   ├── activity_register.xml
│       │   ├── activity_role.xml
│       │   ├── activity_search.xml
│       │   ├── activity_settings.xml
│       │   ├── activity_user_list.xml
│       │   ├── layout_listitem.xml
│       │   ├── toolbar.xml
│       │   └── user_list_item.xml
│       ├── menu
│       │   └── toolbar_menu.xml
│       ├── mipmap-anydpi-v26
│       │   └── ...
│       ├── mipmap-hdpi
│       │   └── ...
│       ├── mipmap-mdpi
│       │   └── ...
│       ├── mipmap-xhdpi
│       │   └── ...
│       ├── mipmap-xxhdpi
│       │   └── ...
│       ├── mipmap-xxxhdpi
│       │   └── ...
│       ├── values
│       │   ├── colors.xml
│       │   ├── strings.xml
│       │   └── styles.xml
│       └── view
└── test
    └── ...
```

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




