# Command Line Application using Spring Boot
A command line application to search the data and return the results in a human readable format. Using Spring Boot to build this application.
* Feel free to use libraries or roll your own code as you see fit.
* Where the data exists, values from any related entities **MUST** be included in the results, i.e.
  * Searching **organization MUST** return its **ticket subject** and **users name**.
  * Searching **users MUST** return his/her **assignee ticket subject** and **submitted ticket subject** and his/her
  **organization name**.
  * Searching **tickets MUST** return its **assignee name**, **submitter name**, **and organization name**.
* The user should be able to search on any field, full value matching is fine (e.g. "mar" won't return "mary").
* The user should also be able to search for empty values, e.g. where description is empty.
* Search can get pretty complicated pretty easily, we just want to see that you can code a basic search application.

## Solution

## Technologies are used in the project
1. Spring Boot version ``2.2.6``
2. Maven to manage dependency and build project
3. Code quality assurance with Sonar and PMD
4. Libraries: Lombok ``1.18.12``, Jackson ``2.10.3``

## Class Diagram

## How to start project?

## Test Coverage - ``80%``
- I will use ``Mockito`` to write unit test.

## Code Quality - ``Passed``
- Sonar and PMD

## API List

## Project Structure

## Issues
