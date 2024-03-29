package Behavioral.Strategy;

import java.util.ArrayList;
import java.util.List;

interface PaymentStrategy {
	public void pay(int amount);
}

class CreditCardStrategy implements PaymentStrategy {
	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;
	
	public CreditCardStrategy(String nm, String ccNum, String cvv, String expiryDate){
		this.name=nm;
		this.cardNumber=ccNum;
		this.cvv=cvv;
		this.dateOfExpiry=expiryDate;
	}
	@Override
	public void pay(int amount) {
		final int operationWithPersonalData = (name+cardNumber+cvv+dateOfExpiry).hashCode();
		System.out.println(amount +" paid with credit/debit card #"+operationWithPersonalData);
	}

}

class PaypalStrategy implements PaymentStrategy {
	private String emailId;
	private String password;
	
	public PaypalStrategy(String email, String pwd){
		this.emailId=email;
		this.password=pwd;
	}
	@Override
	public void pay(int amount) {
		final int operationWithPersonalData = (emailId+password).hashCode();
		System.out.println(amount + " paid using Paypal. #"+operationWithPersonalData);
	}
}

class Item {
	private String upcCode;
	private int price;
	
	public Item(String upc, int cost){
		this.upcCode=upc;
		this.price=cost;
	}
	public String getUpcCode() {
		return upcCode;
	}
	public int getPrice() {
		return price;
	}
}

class ShoppingCart {
	private final List<Item> items;
	
	public ShoppingCart(){
		this.items=new ArrayList<Item>();
	}
	public void addItem(Item item){
		this.items.add(item);
	}
	public void removeItem(Item item){
		this.items.remove(item);
	}
	public int calculateTotal(){
		int sum = 0;
		for(Item item : items){
			sum += item.getPrice();
		}
		return sum;
	}
	public void pay(PaymentStrategy paymentMethod){
		paymentMethod.pay( calculateTotal() );
	}
}

public class StrategyDemo {
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();

		Item item1 = new Item("1234", 10);
		Item item2 = new Item("5678", 40);

		cart.addItem(item1);
		cart.addItem(item2);

		cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));
		cart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"));
	}
}
