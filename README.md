# SMS Classifier

**At a present moment project is under slowpoke construction**

## Introduction

SMS Classifier is simple application that implements sms classification by categories: spam, marketing, time-sensitive messages and etc.
Application should determine the SMS category by text, sender or country, for the future prioritizing and filtering purposes.

## Features & requirements

- Rules-engine based approach
- Caching rule models for the high performance
- Data loading & updating using MSSQL stored procedures (`production` mode restriction)
- RESTful API

## About

#### Cache library
Cache library module contains simple `Cacheable` interface:

```java
public interface Cacheable<K, V> {

    int size();
    boolean isEmpty();
    void clear();
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    //...

}
```
with different kinds of implementations:
- [DeletableItemCache](cache/src/main/java/com/asaunin/cache/DeletableItemCache.java)
- [DeletableCache](cache/src/main/java/com/asaunin/cache/DeletableCache.java)
- [LoadableItemCache](cache/src/main/java/com/asaunin/cache/LoadableItemCache.java)
- [LoadableCache](cache/src/main/java/com/asaunin/cache/LoadableCache.java)

These classes provide a base toolkit for convenient work with deletable DB records, their updating and storing into the cache.
You can easily customise your own caches according to your requirements. For example see the [Rule service](classifier/src/main/java/com/asaunin/classifier/service/RuleService.java), that is based on [Listable cache](classifier/src/main/java/com/asaunin/classifier/cache/ListableCache.java)) implementation.

#### Classifier service

Service is a RESTful API rules engine, which classifies sms's into different types. 
According to the performance requirements, all the data are stored in fast cached structures above described.

## Installation

```
git clone https://github.com/ASaunin/sms-classifier
cd sms-classifier
mvnw clean install
```

## Running

### Locally with embedded H2 database

```
mvnw spring-boot:run -pl classifier
```
You can customize your own classification rules in appropriate [H2 data file](classifier/src/main/resources/db/h2/data.sql)

### Staging / Production mode

```
mvnw spring-boot:run -pl classifier -Dactive.profile=stage
```
Using `stage` or `prod` mode, you need to:
- setup MSSQL database with your own correct schema & data
- configure datasource url & credentials in `application-{env}.yml`

## Swagger support

Use [Swagger-UI endpoint](http://localhost:8080/swagger-ui.html) to get API description

