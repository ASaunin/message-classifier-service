# SMS Classifier

**At a present moment project is under slowpoke construction**

## Introduction

SMS Classifier is simple application that implements sms classification by categories: spam, notifications, time-sensitive messages and etc.
Application should determine the SMS category by text, sender or country, for the future prioritizing and filtering purposes.

## Features & requirements

- Rules-engine based approach
- Caching rule models for the high performance
- Data loading & updating via MSSQL stored procedures (`production` mode restriction)
- RESTful API