kompat-json-lib
=========

A Kotlin compatibility layer for working with the `net.sf.json-lib` JSON Library.

[ ![Download](https://api.bintray.com/packages/ahwnz/kompat/kompat-json-lib/images/download.svg) ](https://bintray.com/ahwnz/kompat/kompat-json-lib/_latestVersion)

## Building JSON

```kotlin
import nz.ahw.kompat.jsonlib.*

val values = jsonArrayOf(1,2,3) // [1,2,3]

val json = jsonObjectOf("values" to values) // {"values":[1,2,3]}
```

## Add To an existing JSON Object

```kotlin
json["message"] = "An important note" 
```

## Add to an existing JSON Array

```kotlin
values += 4
```

## Checking for Keys

```kotlin
if("message" in json) { ... }
```

## Maven

Configure to use the kompat repository

```xml
<repository>
    <id>bintray-ahwnz-kompat</id>
    <name>bintray-kompat</name>
    <url>https://dl.bintray.com/ahwnz/kompat</url>
</repository>
```

Add the dependency

```xml
<dependency>
    <groupId>nz.ahw.kompat</groupId>
    <artifactId>kompat-json-lib</artifactId>
    <version>{version}</version>
</dependency>
```