# Healthcare Provider Summary App #

### Overview ###

This Application lets you search health care provider details. On first page load , it retrieves all the details.
User can apply specific filters from UI. Few filters are numeric, some with precision of 2 decimal places.
User can select specific fields which are of interest to user from the select drop down. Submit button is provided to search.
Min and Max filter values are inclusive.
 
Upon successful search, results are shown in tabular format along with search result count.
If User decides to select specific fields, then only those selected fields will be retrieved and display on UI.
Clear button is provided to clear all kind of filters applied.


### Architecture & Solution ###

Technologies used :
* HTML `Markup language`
* Angularjs `Main Js library`
* BootStrap `Styling`
* Jquery `Used to access multiple-select data`
* multiple-select.js `Open source library for checkbox selector dropdown`
* SpringBoot Web `Used to expose REST endpoint`
* SpringBoot Data ElasticSearch `Used to interact with Elastic search`
* SpringBoot test (Junit) `Unit testing framework`
* Maven `Build tool`

This application has a controller layer which handles web requests.
* `ProviderController` exposes ``/providers`` GET API and accepts filter parameters which are optional.
* `AdminController` exposed `/@index` GET API for invoking dummy data generation.

This application has a Service layer which executes all the business function.
* `ProviderSearchService` creates ES queries and executes queries depending on the filters passed from controller layer. It uses `elasticSearchProviderRepository` and `elasticsearchTemplate` from Spring.

* `ElasticSearchService` class is only user for indexing purpose.

* `CSVAdapter` is a utility class for reading data from CSV file placed in resource folder.

This application used Embedded Elastic Search as a datastore for fast querying.

For indexing the data, Admin should invoke the `/@index` GET API. Once done user can use the application.



### Installation & Setup ###

1. Download and install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Download and install [Apache Maven 3.5.2](https://archive.apache.org/dist/maven/maven-3/3.5.2)

### Test structure ###

Run ``mvn test``  to run all Junit tests. The tests inside ``src\test\java\com\healthcare\provider`` tests below cases 
by placing a ``GET`` call to ``/providers`` :

1. Querying with multiple filters.
2. Querying with multiple filters and with selected fields.

### Configuration & Start ###

All relevant application settings are located in the ``src\main\resources\application.yml`` file.

You can start application using ``mvn spring-boot:run`` on command line.

### Deployments ###

This application deployed using [Heroku](https://www.heroku.com).

Before the deployment change the version property 'project.version' in the pom.xml to the aimed deployment version (x.x.x) e.g. 1.0.1

### Known Issues ###

ElasticSearch needs to be externalized.

