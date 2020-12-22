# CP630 Final Project

## Name: Ma Luo
## Email: luox1180@mylauerier.ca

## Write a project proposal (2-3 pages) - Done. [30/30/*]

Done with the first version of proposal, will keep adding more functionalities here.

## Design data format, collect data, create dataset for the application - Done. [30/30/*]

Data is from a real world example on facebook market store.  The importing and sales orders are sitting on the same dashboard which is a perfect example of semi-structured data.  We have them exported from google sheet and would like to perform analysis on the dashboard.  The service calculation would be totally rely on Map-Reduce algorithms. We have pulled two csv files as resources.  ``item.csv`` is like an iventory, and ``orders-split.csv`` will be a dashboard for all the importing and exporting goods.  The main purpose of this dashboard is to try a Block-chain styling of book keeping.

Please see this attached screenshot.

## Develop and implement data application algorithms for the proposed application problem. - Done. [30/30/*]

I don't want to mention too much for the JSP and Hibernate part which relies on the Eclipse Tools and Java Framework.  The interesting point here is the Map-Reduce algorithms.  Basically, a portal is implemented and we can perform operations on the order list.

### Map-Reduce Service points:

#### 1. Profit service - over all sum and get the profit.  With Java 1.8 Streams this time, need to gain more knowledge on the Hadoop composite key implementation.

##### Step 1. Line 1 and 2 are the importing and sales value respectively.  The composite key is <Importing Order,Item Number>

``{[I0022, 8]=72.0, [I0021, 3]=25.0, [I0020, 2]=13.0, [I0014, 7]=0.0, [I0013, 6]=0.0, [I0010, 5]=55.0, [I0024, 9]=13.0, [I0002, 2]=20.0, [I0001, 1]=10.0, [I0023, 3]=26.0, [I0012, 3]=30.0, [I0015, 7]=0.0, [I0019, 4]=17.329999923706055, [I0018, 3]=26.0, [I0016, 1]=0.0, [I0008, 4]=23.0, [I0017, 3]=27.0, [I0007, 5]=55.0, [I0005, 3]=30.0, [I0004, 2]=17.0, [I0003, 2]=19.0, [I0009, 2]=18.0}``

``{[I0022, 8]=90.0, [I0021, 3]=38.333333333333336, [I0020, 2]=30.0, [I0010, 5]=77.0, [I0002, 2]=30.0, [I0001, 1]=10.0, [I0023, 3]=38.333333333333336, [I0012, 3]=50.0, [I0021, 2]=45.0, [I0016, 1]=15.272727272727273, [I0019, 4]=31.666666666666668, [I0018, 3]=48.42777760823568, [I0008, 4]=36.9975004196167, [I0015, 1]=12.5, [I0017, 3]=52.5, [I0007, 5]=70.0, [I0005, 3]=53.5, [I0004, 2]=29.285714285714285, [I0003, 2]=25.714285714285715, [I0009, 2]=35.0, [I0019, 2]=27.0}``

##### Step 2. Based on it, we perform a reduction and list our the result as line 1 and 2.  A reduction is performed and this result is <Item Number,Blance> set.

``{1=-5.0, 2=-17.4, 3=-27.333333333333332, 4=-20.164999961853027, 5=-55.0, 6=0.0, 7=0.0, 8=-72.0, 9=-13.0}``

``{1=12.590909090909092, 2=31.714285714285715, 3=46.849074045817055, 4=34.332083543141685, 5=73.5, 8=90.0}``

##### Step 3. Line 5 is the final knapsack input we will have, a rest service is going to implement for it.

``{1=7.590909090909092, 2=14.314285714285717, 3=19.515740712483723, 4=14.167083581288658, 5=18.5, 8=18.0}``

### 2. Scala Planing service (0/1 Knapsack) - Generate a Map-Reduce model giving price and profit value for each item, give a best strategy based on the budget.  We could include size of the product and give stock size as another feature for multi-dimentional Knapsack, which is super popular these days, but they will cost way more time as we are doing the research.  An example for this would be given in the [Elephant56](https://github.com/pasqualesalza/elephant56) We could do data clean up and map them back to a 3 3NF tables in mysql, but I strongly feel like No-sql reseach will push data solution forward.  Tese cases would be build to make sure we have the proper result before pushing the service to WAR file.

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
