# CP631 Final Project

## Name: Ma Luo
## Email: luox1180@mylauerier.ca

## Write a project proposal (2-3 pages) - Done. [30/30/*]

## Design data format, collect data, create dataset for the application - Done. [30/30/*]

Data is from a real world example on facebook market store.  The importing and sales orders are sitting on the same dashboard which is a perfect example of semi-structured data.  We have them exported from google sheet and would like to perform analysis on the dashboard.  The service calculation would be totally rely on Map-Reduce algorithms.

### HDSF Map-Reduce Service points:

1. Profit service - over all sum and get the profit.

2. Scala Planing service (0/1 Knapsack) - Generate a Map-Reduce model giving price and profit value for each item, give a best strategy based on the budget.  We could include size of the product and give stock size as another feature for multi-dimentional Knapsack, which is super popular these days, but they will cost way more time as we are doing the research.  An example for this would be given in the [Elephant56](https://github.com/pasqualesalza/elephant56) We could do data clean up and map them back to a 3 3NF tables in mysql, but I strongly feel like No-sql reseach will push data solution forward.  Tese cases would be build to make sure we have the proper result before pushing the service to WAR file.

## Develop and implement data application algorithms for the proposed application problem. - Done. [30/30/*]

I don't want to mention too much for the JSP and Hibernate part which relies on the Eclipse Tools and Java Framework.  The interesting point here is the Map-Reduce algorithms.  Basically, a portal is implemented and we can perform operations on the order list.

## Do data computing to generate models, representing models in portable format and stored in file or database. More credit is given if distributed approach is used data mining of big dataset

We would use Hadoop API and Spark API cause we need to integrate this section to beans and call it with soap service from portal side.

### Profit Service:

#### 1.<Order_Num_Post,item_num,unit_price> reduction - store avg unit price for that imported item order to part-r-0001, and keep values positive.

#### 2.<Order_Num_Post,item_num,unit_price> reduction - store avg unit price for importing to part-r-0002, and keep values negative.

#### 3.<item_num,unit_price> reduction - store avg profit for each item.  If negative which means it is never sold.

### Scala Planing Service:

#### 1.Based on step 3. result from Profit Service, we would could do a 0/1 knapsack with a given budget.

## Create deployable service components using application models using Java based enterprise computing technologies, to create client program to do remote call of the data mining services.

### Task breakdown:

#### 1.Maven client based back-end with hibernate h2b - covert sql table to model entity - Done.

#### 2.CRUD Portal back-end - done with Hibernate. - Done.

#### 3.Spring MVC failed, would go for Servlet and JSP on the portal - Done.

#### 4.Start Hadoop on client side - focus on this part, need to get this major part as of tonight.

## Deploy service components onto enterprise application servers.

Portal deployed.  Working on the Hadoop and Spark client API.  Need to inject EJB and call with SOAP service from portal side.

### Task breakdown:

#### 1.Inject the Hadoop client code into EJB.

#### 2.Deploy EJB component and test out with local client.

## Create web services (SOAP, RESTful) to use the data service components.

### Task breakdown:

#### 1.Develop rest service with EJB.

#### 2.Direct the dashboard service back to the main Spring MVC Web Portal.

Write a short description (including the new features).

## Create web user interface/mobile applications to use the application/web services.

Done with the previous tasks.

## Test your services, log your services, and document your term project.

Testing and logging would be saved for future development

## Demonstrate your term project in final project presentation, slides, or short video.

Write a short description (including the new features).
