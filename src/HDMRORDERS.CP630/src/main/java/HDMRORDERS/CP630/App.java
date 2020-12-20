package HDMRORDERS.CP630;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	CSVReader reader = new CSVReader();
    	//reader.ImportCSVsToDB(1);//items
    	reader.ImportCSVsToDB(0);//orders
    }
}
