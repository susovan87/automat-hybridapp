# automat-hybridapp
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

> Hybrid mobile app's functional testing automation made easy

This code contains a framework component (yet to setup [travis-ci](https://travis-ci.org/) & 
publish artifacts to [Maven](https://mvnrepository.com/)) and sample test project inside `src/test/system`.


[TOC]

## Problem statement
If you are building hybrid apps like me and prefer to design layouts using `HTML`, `Javascript` 
and then build your iOS or Android application using popular hybrid application development 
framework ([cordova](https://cordova.apache.org/), [phonegap](http://phonegap.com/), 
[ionic](http://ionicframework.com/)), then you must be searching for some hybrid app functional 
testing framework where if you write test once -- it will run in every platform (iOS, Android).

## Objective
 - Write test once, run it for all platforms (iOS, Android) in true sense, not a single line of code or configuration change needed.
 - Parallel execution of testcases on multiple devices including iOS and Android.
 - Device specific test report generation.
 - Take screenshot on error and publish it on html test report.


## External component dependencies
 -  [Appium](http://appium.io/)
 -  [TestNG](http://testng.org/doc/index.html)
 -  [ReportNG](http://reportng.uncommons.org/)

