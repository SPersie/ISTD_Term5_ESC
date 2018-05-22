package CC2;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.Scanner;

public class EnemyShipDemoFixed {

    public static void main(String[] args){

        EnemyShip theEnemy = null;

        Scanner userInput = new Scanner(System.in);

        String enemyShipOption = "";

        System.out.print("What type of ship? (U or R)");

        if (userInput.hasNextLine()){
            enemyShipOption = userInput.nextLine();
        }

        enemyFactory theenemyFactory =new enemyFactory();

        theEnemy =theenemyFactory.makeenemyShipForthisCall(enemyShipOption);

        doStuffEnemy(theEnemy);
    }

    public static void doStuffEnemy(EnemyShip anEnemyShip){
        anEnemyShip.displayEnemyShip();
    }
}

class enemyFactory {

    public EnemyShip makeenemyShipForthisCall(String enemyShipOption) {

        //why need this? (it's in the cohort demo)
        EnemyShip newEnemyShip =null;

        if (enemyShipOption.equals("U")){
            return new UFOEnemyShip();
        } else if (enemyShipOption.equals("R")){
            return new RocketEnemyShip();
        } else {
            return new BigUFOEnemyShip();
        }
    }
}

abstract class EnemyShip {

    private String name;
    // sudiptac: damage factor distinguishes a ship
    private double amtDamage;

    public String getName() { return name; }
    public void setName(String newName) { name = newName; }

    public double getDamage() { return amtDamage; }
    public void setDamage(double newDamage) { amtDamage = newDamage; }

    public void displayEnemyShip(){
        System.out.println(getName() + " is on the screen");
    }
}

class UFOEnemyShip extends EnemyShip {
    public UFOEnemyShip(){
        setName("UFO Enemy Ship");
        setDamage(20.0);
    }
}

class BigUFOEnemyShip extends EnemyShip {
    public BigUFOEnemyShip(){
        setName("Big UFO Enemy Ship");
        setDamage(40.0);
    }
}

class RocketEnemyShip extends EnemyShip {
    public RocketEnemyShip(){
        setName("Rocket Enemy Ship");
        setDamage(10.0);
    }
}


