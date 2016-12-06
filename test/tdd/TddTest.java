package tdd;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paweł
 */
public class TddTest {
    Product productA;
    Product productB;
    Product productC;
    Purchase purchase;
    Client client;
        
    @Before
    public void setUp() {
        productA = new Product("Koszulka", 49.99, 10, 3);
        productB = new Product("Buty", 60, 12, 4);
        productC = new Product("HDD", 1000, 30, 1);
        purchase = new Purchase();
        client = new Client("Paweł", "Jaruga", "pawel.jaruga@o2.pl", 200);
        
    }
    
    @Test
    public void creatingProduct()
    {      
        assertEquals(productA.name, "Koszulka");
        assertEquals(productA.price, 49.99, 0);
        assertEquals(productA.quantity, 3);
    }
    
    @Test
    public void creatingPurchase()
    {     
        assertNotNull(purchase);
    }
    
    @Test
    public void addingProductToPurchase()
    {
        purchase.addCashProduct(productA);
        
        assertTrue(purchase.productsCash.contains(productA));
        
    }
    
    @Test
    public void removingProductFromPurchase()
    {
        purchase.addCashProduct(productA);
        purchase.removeCashProduct(productA);
        
        assertFalse(purchase.productsCash.contains(productA));
    }
    
    @Test
    public void creatingClient()
    {
        assertEquals(client.firstName, "Paweł");
        assertEquals(client.lastName, "Jaruga");
        assertEquals(client.email, "pawel.jaruga@o2.pl");
        assertEquals(client.cash, 200, 0);
    }
    
    //Nieaktyalny od iteracji 7 - brak złapanego wyjątku
    /*@Test
    public void buyingProducts()
    {
        client.addCashProduct(productA);
        client.buyWithCash();
        
        assertEquals(50.03, client.cash, 0);
    }*/
    
    @Test
    public void noProductsExceptionTest()
    {
        try {
            client.buyWithCash();
        } catch (NoMoney e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoMoney został złapany");
        } catch (NoProducts e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoProducts został złapany");
        }
    }
    
    @Test
    public void noMoneyExceptionTest()
    {
        client.addCashProduct(productB);
        try {
            client.buyWithCash();
        } catch (NoMoney e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoMoney został złapany");
        } catch (NoProducts e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoProducts został złapany");
        }
    }
    
    @Test
    public void addingExtraPoints()
    {
        client.addCashProduct(productA);
        try {
            client.buyWithCash();
        } catch (NoMoney e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoMoney został złapany");
        } catch (NoProducts e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoProducts został złapany");
        }
        
        assertEquals(client.extraPoints, 14);
    }
    
    //Nieaktualne od iteracji 10 - nieobsługiwane wyjątki
    /*@Test
    public void buyingProductsWithExtraPoints()
    {
        client.addExtraProduct(productA);
        client.addExtraPoints(40);
        
        client.buyWithPoints();
        
        assertEquals(10, client.extraPoints);
    }*/
    
    @Test
    public void noExtraPointsExceptionTest()
    {
        client.addExtraPoints(20);
        client.addExtraProduct(productB);
        try {
            client.buyWithPoints();
        } catch (NoExtraPoints e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoMoney został złapany");
        } catch (NoProducts e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoProducts został złapany");
        }
    }
    
    @Test
    public void noProductsExtraExceptionTest()
    {
        try {
            client.buyWithPoints();
        } catch (NoExtraPoints e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoMoney został złapany");
        } catch (NoProducts e) {
            e.printStackTrace();
            System.out.println("Wyjątek NoProducts został złapany");
        }
    }
    
    @Test
    public void buyMixedTest() {
        client.addCashProduct(productA);
        client.addExtraPoints(50);
        client.addExtraProduct(productC); //z jakiegoś powodu netbeans myśli, że jest tu addCashProduct
        
        client.buyMixed();
        
        assertEquals(50.03, client.cash, 0.0);
        assertEquals(20, client.extraPoints, 0);
    }
}
