### Exchange Rates

This is the android codebase for getting the Exchange Rates from the Fixer.io Back end Services

- [Description](#description)
- [Platform](#platform)
- [How to run the code base](#how-to-run-the-code-base)
- [Demo Video](#demo-video)
- [Unit Tests](#unit-tests)
- [Modules](#modules)
- [Code Flow](#code-flow)
- [Screen Grabs](#screen-grabs)


### Description
This sample project is to delve deep into the work of foreign Exchange (FX) to provide an interactive mobile app for Android comparing FX rates. It's likely we'll use an alternative provider in future. 

- Screen 1:
  Displays the Exchange Rates from the free tier data source of FX rates (http://fixer.io)
  API. 
- Step 2:
  The currency rows are selectable. On selection of two rows, it opens a view with the exchange
  rates of the selected currencies over the last 5 days period.


### Platform

- Compile SDK 34
- Min SDK 23
- Target SDK 34

### How to run the code base

- Go to fixer.io and generate a free access_key from (http://fixer.io)
- Download the latest stable Android Studio
- Clone the project into the desktop and use the above IDE to open the project
- Clean and Build the project
- In the `local.properties` file add ACCESS_KEY="xyz"
- Replace xyz with the access key you have generated earlier
- Run the codebase into an emulator of your choice or any real devices
- You can change the BackEndUrl for the 3 flavors under buildSrc folder in BackEndURL object

### Demo Video
Can be found under DemoVideo folder in the root project


### Unit Tests

- Unit Test for data layer can be found under **`fixer-data`** module
- Unit Test for domain layer can be found under **`fixer-domain`** module
- Unit Test for View Model layer can be found under **`ExchangeRates`** / app module

### Modules

Can be found under Architecture folder in the root project
![Architecture](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/Architecture/ModulesOverview.png)

- **`ExchangeRates:`** The source code for Android Application.
- **`buildSrc:`** The build source files related to the project resides in here. Custom Android
  Application, Android Library, Kotlin Library and Plugin extensions resides here.
- **`core:`** The common core classes used across domain, data and UI layer resides here.
- **`network-common:`** The common classes required to do network call like common retrofit and
  okhttp resides here.
- **`fixer-data:`** This module contains the data layer logic shown in the code flow diagram under
  code flow folder in the root project.
- **`fixer-domain:`** This module contains the domain layer logic shown in the code flow diagram
  under code flow folder in the root project.


### Code Flow

Can be found under Architecture folder in the root project
![CodeFlow](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/Architecture/CodeFlow.png)


### Screen Grabs

The Screen Grabs can be found under ScreenShots folder

###  Light Mode


![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Light/Screenshot_20240907-172237.png)

![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Light/Screenshot_20240907-172241.png)

![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Light/Screenshot_20240907-172245.png)

![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Light/Screenshot_20240907-172249.png)


###  Foldables

![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Light/Screenshot_20240907-172302.png)

![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Light/Screenshot_20240907-172309.png)


### Dark Mode


![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Dark/Screenshot_20240907-172147.png)


![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Dark/Screenshot_20240907-172158.png)


![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Dark/Screenshot_20240907-172218.png)


### Foldables

![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Dark/Screenshot_20240907-172336.png)


![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Dark/Screenshot_20240907-172342.png)


![alt text](https://github.com/GeorgePeterPandian/ExchangeRate/blob/main/ScreenShots/Dark/Screenshot_20240907-172346.png)
