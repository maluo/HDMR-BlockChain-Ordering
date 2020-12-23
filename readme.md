# CP630 Final Project

## Name: Ma Luo
## Email: luox1180@mylauerier.ca

## Write a project proposal (2-3 pages) - Done. [30/30/*]

![Sequence Diagram From Proposal](screenshots/proposal.png)

Done with the first version of proposal, will keep adding more functionalities here.

## Design data format, collect data, create dataset for the application - Done. [30/30/*]

Data is from a real world example on facebook market store.  The importing and sales orders are sitting on the same dashboard which is a perfect example of semi-structured data.  We have them exported from google sheet and would like to perform analysis on the dashboard.  The service calculation would be totally rely on Map-Reduce algorithms. We have pulled two csv files as resources.  ``item.csv`` is like an iventory, and ``orders-split.csv`` will be a dashboard for all the importing and exporting goods.  The main purpose of this dashboard is to try a Block-chain styling of book keeping.

![Sample Data](screenshots/sampledata.png)

## Develop and implement data application algorithms for the proposed application problem. - Done. [30/30/*]

I don't want to mention too much for the JSP and Hibernate part which relies on the Eclipse Tools and Java Framework.  The interesting point here is the Map-Reduce algorithms.  Basically, a portal is implemented and we can perform operations on the order list.  Map-reduce on keyword mappings would be the key logic in our computations.

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

### 2. Scala Planing service (Continous Knapsack)

This part has been impelemented with java stream which also provides Map-Reduce based operations.  Local machine is so struggling with Hadoop and Scala implementation.

Basic idea is to generate a Map-Reduce model giving price and profit value for each item, give a best strategy based on the budget.  We could include size of the product and give stock size as another feature for multi-dimentional Knapsack, which is super popular these days, but they will cost way more time as we are doing the research.  An example for this would be given in the [Elephant56](https://github.com/pasqualesalza/elephant56) We could do data clean up and map them back to a 3 3NF tables in mysql, but I strongly feel like No-sql reseach will push data solution forward.  Tese cases would be build to make sure we have the proper result before pushing the service to WAR file.

## Do data computing to generate models, representing models in portable format and stored in file or database. More credit is given if distributed approach is used data mining of big dataset

We would like to use Hadoop API and Spark API cause we need to integrate this section to beans and call it with soap service from portal side.  But as we struggled at local machine with Hadoop, we shift the core logic to portal side with Java Stream and Hibernate.

![Portal Repo](screenshots/orderrepo.png)

### Profit Service:

Tried to implement this concept in Hadoop, but turns out Java Stream is also a good approach.

#### 1.<Order_Num_Post,item_num,unit_price> reduction - store avg unit price.

```
        public Map<Integer,Double> getSalesAvgPerItem(){

        List<Tuple> tuples = new ArrayList<Tuple>();
        List<Orders> out = orderDaoImpl.findAllOrders().stream().filter(x -> (x.getOrderNum().contains("-")))
                .collect(Collectors.toList());

        Function<Orders, List<Object>> compositeKey = orderRecord -> Arrays.<Object>asList(orderRecord.getOrderNum2(),
                orderRecord.getItems().getProductId());

        Map<List<Object>, Double> mapOut = out.stream().collect(
                Collectors.groupingBy(compositeKey, Collectors.averagingDouble(orderR -> orderR.getUnitPrice())));

        for (Entry<List<Object>, Double> entry : mapOut.entrySet()) {
            tuples.add(new Tuple(entry.getKey().toArray()[0].toString(), (int) entry.getKey().toArray()[1],
                    (Double) entry.getValue()));
        }

        Map<Integer, Double> sales = tuples.stream().collect(
                Collectors.groupingBy(record -> record.item_num, Collectors.averagingDouble(orderR -> orderR.profit)));

        return sales;
    }
```

#### 2.<Order_Num_Post,item_num,unit_price> reduction - store avg unit price for importing

```
    public Map<Integer,Double> getImportAvgPerItem(){

        List<Tuple> tuples = new ArrayList<Tuple>();
        List<Orders> in = orderDaoImpl.findAllOrders().stream().filter(x -> x.getOrderNum().contains("I")).collect(Collectors.toList());

        Function<Orders, List<Object>> compositeKey = orderRecord -> Arrays.<Object>asList(orderRecord.getOrderNum2(),
                orderRecord.getItems().getProductId());

        Map<List<Object>, Double> mapIn = in.stream().collect(
                Collectors.groupingBy(compositeKey, Collectors.averagingDouble(orderR -> orderR.getUnitPrice())));

        tuples = new ArrayList<Tuple>();
        for (Entry<List<Object>, Double> entry : mapIn.entrySet()) {
            tuples.add(new Tuple(entry.getKey().toArray()[0].toString(), (int) entry.getKey().toArray()[1],
                    (Double) entry.getValue()));
        }
        Map<Integer, Double> buget_In = tuples.stream().collect(
                Collectors.groupingBy(record -> record.item_num, Collectors.averagingDouble(orderR -> orderR.profit)));

        return buget_In;
    }
```

#### 3. List<item_name , importing price, sales price> reduction and pass this one to Knapsack calculation

```
    public List<ItemUnit> getKnapsackInput(){
        Map<Integer,Double> budget_In, sales;
        budget_In = getImportAvgPerItem();
        sales = getSalesAvgPerItem();

        Object[] keys = budget_In.keySet().toArray();
        for (Object obj : keys) {
            if (!sales.containsKey(obj)) {
                budget_In.remove(obj);
            }
        }

        List<Items> items= itemDaoImpl.findAllItems();
        HashMap<Integer,String> itemMaps= new HashMap<Integer,String>();
        for(Items i : items) {
            itemMaps.put(i.getId(), i.getProductName());
        }

        List<ItemUnit> units= new ArrayList<ItemUnit>();
        ItemUnit unit = null;
        for (Entry<Integer, String> entry : itemMaps.entrySet()) {
            try {
            unit = new ItemUnit(entry.getValue(),budget_In.get(entry.getKey()),sales.get(entry.getKey()));
            units.add(unit);
            }catch(Exception e) {}
        }

        return units;
    }
```

#### Based on step 3. result from Profit Service, we would could do a continuous knapsack with a given budget.

```
    @WebServlet("/order/dash")
    public class DashboardController extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private OrderDao orderDao = OrderDaoIMPL.getInstance();
        OrderRepo repo = new OrderRepo();;

        public DashboardController() {
            // Do Nothing
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

                Integer budget = Integer.parseInt(request.getParameter("budget"));

                ContinousKnapsackWrapper o = new ContinousKnapsackWrapper(budget,repo.getKnapsackInput());

                request.setAttribute("budgetPlan", o.getBuf());

                request.getRequestDispatcher("/").forward(request, response);
        }
    }
```

## Create deployable service components using application models using Java based enterprise computing technologies, to create client program to do remote call of the data mining services.

### Task breakdown:

#### 1.Maven client based back-end with hibernate h2b - covert sql table to model entity - Done.

#### 2.CRUD Portal back-end - done with Hibernate. - Done.

#### 3.Spring MVC failed, would go for Servlet and JSP on the portal - Done.

#### 4.Split Database layer with front-end layer and try to integrate services to ejb and call them by REST. - Failed.

#### 5.Shift order repositoty back to portal side and get project wrapped.

## Deploy service components onto enterprise application servers.

Portal deployed.  Db setup with phpmyadmin on XAMPP.  Db creation script is ready in git repo.  Failed to inject EJB and call with SOAP service from portal side.  Deploy with JSP and Hibernate porta.  The tricky point here is to deploy the regular jsp portal with external database lib in a pack to Enterprise server.  This has been an issue for a while case we do have lots of different versions and frameworks.  Maven provides enrich feature to let us convert the proejct to MAVEN and customized of build.

### Task breakdown:

#### Split database layer with portal layer.

#### Move order repository back from ejb, cause ejb keep getting issues with external libs.

#### Integrate the planning service back to portal home page.

![Budget Planning Service](screenshots/planning.png)

#### Deploy and test the application overall.  Give a short video.

Write a short description (including the new features).

## Create web user interface/mobile applications to use the application/web services.

Done with the previous tasks.  Please see the video demo afterwards.

![Home page](screenshots/home.png)

## Test your services, log your services, and document your term project.

Validation and logging would be saved to future work, but we have tested the service with enough local testing.  We use kind of TDD concept.  Each function is tested in the App.java file which has the main function to test locally.

## Demonstrate your term project in final project presentation, slides, or short video.

Please see the short video.
