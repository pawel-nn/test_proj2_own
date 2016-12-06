/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdd;

/**
 *
 * @author Peasant
 */
public class Client {
    String firstName;
    String lastName;
    String email;
    double cash;
    int extraPoints;
    Purchase purchase;
    
    public Client(String fname, String lname, String email, double cash)
    {
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.cash = cash;
        this.purchase = new Purchase();
        this.extraPoints = 0;
    }
    
    public void addProduct(Product product)
    {
        this.purchase.add(product);
    }
    
    public void removeProduct(Product product)
    {
        this.purchase.remove(product);
    }
    
    public void addExtraPoints(int i)
    {
        this.extraPoints += i;
    }
    
    public void removeExtraPoints(int i)
    {
        this.extraPoints -= i;
    }
    
    public void buyWithCash() throws NoMoney, NoProducts
    {
        if(this.purchase.products.isEmpty())
            throw new NoProducts();

        double price = this.purchase.getPrice();
        if(price > this.cash)
            throw new NoMoney();

        this.cash -= price;
        if(price >= 50)
            this.addExtraPoints((int)price/10);
        this.purchase = new Purchase();

    }
    
    public void buyWithPoints() throws NoExtraPoints, NoProducts
    {
        if(this.purchase.products.isEmpty())
            throw new NoProducts();

        int price = this.purchase.getExtraPrice();
        if(price > this.extraPoints)
            throw new NoExtraPoints();

        this.removeExtraPoints(price);
        this.purchase = new Purchase();
    }
}
