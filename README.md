# Travon

- [Group members](#group-members)
- [How to access to the repository](#how-access-to-the-repository)
- [Installation Notes](#installation-notes)
  * [Requirements](#requirements)


## Group members

| Name  | B00 number | Email |
| ------------- | ------------- | ------------- |
| Genny Andrea Centeno Metri  | B00786201  | gn936133@dal.ca  |
| Richa Khatri  | B00792218  | rc801868@dal.ca  |
| Jamuna Loganath  | B00811590  | jm284791@dal.ca  |
| Nikhil Tyagi  | B00809791  | nikhil.tyagi@dal.ca  |
| Dheeraj Varshney  | B00808467  | dh301823@dal.ca  |
| Charley LeBlanc  | B00727183  | ch875607@dal.ca  |


## How to access to the repository

- Download the code from repository. Or pull the latest changes.
```sh
git clone https://git.cs.dal.ca/metri/mobile-comp-project.git
```
- Open project in Android Studio
- Make your changes
- Build project and run the code
- If you have _Developer_ permissions, you have to create a branch before committing or pushing your code.
- Commit Changes
- Push changes
- Create a merge request.

## Installation Notes
Installation instructions for markers.

### Requirements
- **Android Studio**: This is an android studio project.
- **Java**: Java programming language is used. Download the latest version.
- **Git**: Git is used for code versioning and collaboration.


## Summary
Travon will help users to ease their difficulties, problems or issues they may face while moving to a new city. Users will be able to know the place better with anticipation and can prepare accordingly; they can also use the application during their stay in the new city. Travon will be a location based and informative app with features such as receiving news and events of that area, interesting facts about that place, and recommended places to visit such as hotels, hospitals, universities, and stores. Other features include finding rooms using GPS, performing search using voice recognition or typing, and getting important information regarding immigration policies.

## Scope
Users will be able to use most of the functionalities in anywhere of the world, except for Finding Rooms which will be available in Halifax, Toronto and Vancouver; and Immigration which will be available in Canada. Immigration Information is something which varies for different countries. Further in the future, the API will be extended so it covers other cities and countries apart from the initial ones.




## Libraries
**Volley**: Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster. Volley is available on [GitHub](https://developer.android.com/training/volley/).

**Bumptech Glide**: Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface. Bumptech Glide is available on [Github](https://github.com/bumptech/glide).

## API

 **Google Maps API**: this library allows you to access to the functionalities provided by Google Maps to mark places and get the users' location.  [Go to site](https://cloud.google.com/maps-platform/?hl=en)
  - Places: Help users discover the world with rich details for over 150 million points of interest.
  - Services: Provides information about the services provided in the selected city.

**Numbeo**: Numbeo API provides access to prices and other data from Numbeo.com. Currently uses JSON interchangable data output, so it can be used in various platforms or systems.

**OpenWeather API**: This API allows you to access current weather data for any location on Earth. Current weather is frequently updated based on global models and data from more than 40,000 weather stations. Data is available in JSON, XML, or HTML format. [Go to site](https://openweathermap.org/)

**NewsAPI**: Simple HTTP REST API for searching and retrieving live articles from all over the web. [Go to site](https://newsapi.org/docs)

**Mocked API for Finding Rooms and Immigration**: We created an API deployed in Heroku that provides information for Finding Rooms and Immigration functionalities.



## Code Examples
You will encounter roadblocks and problems while developing your project. Share 2-3 'problems' that your team solved while developing your project. Write a few sentences that describe your solution and provide a code snippet/block that shows your solution. Example:

**Problem 1: We needed a method to calculate a Fibonacci sequence**

A short description.
```
// The method we implemented that solved our problem
public static int fibonacci(int fibIndex) {
    if (memoized.containsKey(fibIndex)) {
        return memoized.get(fibIndex);
    } else {
        int answer = fibonacci(fibIndex - 1) + fibonacci(fibIndex - 2);
        memoized.put(fibIndex, answer);
        return answer;
    }
}

// Source: Wikipedia Java [1]
```

## Feature Section
List all the main features of your application with a brief description of each feature.

## Final Project Status
Write a description of the final status of the project. Did you achieve all your goals? What would be the next step for this project (if it were to continue)?

#### Minimum Functionality
- Feature 1 name (Completed)
- Feature 2 name (Partially Completed)
- Feature 3 (Not Implemented)

#### Expected Functionality
- Feature 1 name (Completed)
- Feature 2 name (Partially Completed)
- Feature 3 (Not Implemented)

#### Bonus Functionality
- Feature 1 name (Completed)
- Feature 2 name (Partially Completed)
- Feature 3 (Not Implemented)

## Sources
What to include in your project sources:
- Stock images
- Design guides
- Programming tutorials
- Research material
- Android libraries
- Everything listed on the Dalhousie Plagiarism and Cheating pages(https://www.dal.ca/dept/university_secretariat/academic-integrity/plagiarism-cheating.html)

[1] "Java (programming language)", En.wikipedia.org, 2018. [Online]. Available: https://en.wikipedia.org/wiki/Java_(programming_language).

//To do: Format references with IEEE
[2] https://help.github.com/articles/organizing-information-with-tables/
