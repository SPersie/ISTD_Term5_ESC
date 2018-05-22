package CC4;

public class PizzaStoreFixed {

    public Pizza orderPizza (String type) {
        Pizza pizza = null;

        PizzaFactory thePizzaFactory =new PizzaFactory();
        pizza =thePizzaFactory.makePizzaForthisOption(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}

class PizzaFactory {

    public Pizza makePizzaForthisOption(String type) {

        Pizza newPizza =null;

        if (type.equals("cheese")) {
            return new CheesePizza();
        }
        if (type.equals("greek")) {
            return new GreekPizza();
        }
        if (type.equals("pepperoni")) {
            return new PepperoniPizza();
        } else {
            return newPizza;
        }
    }
}

class Pizza {

    public void prepare() {
    }

    public void box() {
    }

    public void cut() {
    }

    public void bake() {
    }
}

class CheesePizza extends Pizza {}
class GreekPizza extends Pizza {}
class PepperoniPizza extends Pizza {}


