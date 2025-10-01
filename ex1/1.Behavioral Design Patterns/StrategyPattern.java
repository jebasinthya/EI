import java.util.*;

interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy strategy) { this.paymentStrategy = strategy; }
    public void checkout(int amount) { paymentStrategy.pay(amount); }
}

public class StrategyPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        System.out.print("Enter amount to pay: ");
        int amount = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Choose payment method (CreditCard/PayPal): ");
        String method = sc.nextLine();

        if (method.equalsIgnoreCase("CreditCard")) cart.setPaymentStrategy(new CreditCardPayment());
        else if (method.equalsIgnoreCase("PayPal")) cart.setPaymentStrategy(new PayPalPayment());
        else { System.out.println("Invalid method."); sc.close(); return; }

        cart.checkout(amount);
        sc.close();
    }
}
