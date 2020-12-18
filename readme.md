# CP631 Final Project

## Name: Ma Luo
## Email: luox1180@mylauerier.ca

## Write a project proposal (2-3 pages) - Done. [30/30/*]

## Design data format, collect data, create dataset for the application - Done. [30/30/*]
Data is from a real world example on facebook market store.  The importing and sales orders are sitting on the same dashboard which is a perfect example of semi-structured data.  We have them exported from google sheet and would like to perform analysis on the dashboard.  The service calculation would be totally rely on Map-Reduce algorithms.

### HDSF Map-Reduce Service points:

1. Balancing - do an overall count and see if we have unfinished items in stock or missing some new orders.

2. Profit service - over all sum and get the profit.

3. Scala Planing service (0/1 Knapsack) - Generate a Map-Reduce model giving price and profit value for each item, give a best strategy based on the budget.  We could include size of the product and give stock size as another feature for multi-dimentional Knapsack, which is super popular these days, but they will cost way more time as we are doing the research.  An example for this would be given in the [Elephant56](https://github.com/pasqualesalza/elephant56) We could do data clean up and map them back to a 3 3NF tables in mysql, but I strongly feel like No-sql reseach will push data solution forward.  Tese cases would be build to make sure we have the proper result before pushing the service to WAR file.

## Develop and implement data application algorithms for the proposed application problem. - Done. [0/30/*]

I don't want to mention too much for the JPA and H2B Hibernate part which relies on the Eclipse Tools and Java Framework.

The interesting point here is the Map-Reduce algorithms.

## Do data computing to generate models, representing models in portable format and stored in file or database. More credit is given if distributed approach is used data mining of big dataset

Write a short description (including the new features).

Balancing:

Profit service:

Scala planing service:

## Create deployable service components using application models using Java based enterprise computing technologies, to create client program to do remote call of the data mining services.

Need to start on it first before the complex algorithms.

### Task breakdown:

#### 1.
#### 2.
#### 3.

## Deploy service components onto enterprise application servers.

Write a short description (including the new features).

## Create web services (SOAP, RESTful) to use the data service components.

Write a short description (including the new features).

## Create web user interface/mobile applications to use the application/web services.

Write a short description (including the new features).

## Test your services, log your services, and document your term project.

Write a short description (including the new features).

## Demonstrate your term project in final project presentation, slides, or short video.

Write a short description (including the new features).
