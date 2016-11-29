# automat-hybridapp
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
The main goal of this project is to automate mobile app's functional testing. This code contains a framework component (to promote reusability) and sample project inside `src/test/system` to describe how to consume this framework.

I am using [Appium](http://appium.io/), an open source app automation framework which works perfectly well with `native`, `hybrid` or `mobile web apps` and has support for iOS, Android and Windows apps.

## Problem statement
If you are building hybrid apps like me and prefer to design layouts using `HTML`, `Javascript` and then build your iOS or Android application using popular hybrid application development framework ([cordova](https://cordova.apache.org/), [phonegap](http://phonegap.com/), [ionic](http://ionicframework.com/)), then you must be searching for some hybrid app functional testing framework where if you write test once -- it will run in every platform (iOS, Android).

## Objective
 - Write test once, run it for all platforms (iOS, Android) in true sense, not a single line of code or configuration change needed.
 - Parallel execution of testcases on multiple devices including iOS and Android.
 - Device specific test report generation.
 - Take screenshot on error and publish it on html test report.



