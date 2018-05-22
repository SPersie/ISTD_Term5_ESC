package CC8;

class Robot {
    String name;
    IBehaviour behaviour;

    public Robot (String name)
    {
        this.name = name;
    }

    public void behave ()
    {
        //the robots behave differently
        System.out.println(name +" is moving based on the current behaviour.");
        int steps =behaviour.moveCommand();
        System.out.println(name +" has moved " +steps +" steps.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBehavior(IBehaviour behavior) {
        //todo
        this.behaviour =behavior;
    }

    public IBehaviour getBehaviour() {
        return behaviour;
    }
}

interface IBehaviour {
    public int moveCommand();
}

class agressiveBehaviour implements IBehaviour {

    @Override
    public int moveCommand() {
        return 100;
    }
}

class defensiveBehaviour implements IBehaviour {

    @Override
    public int moveCommand() {
        return -100;
    }
}

class normalBehaviour implements IBehaviour {

    @Override
    public int moveCommand() {
        return 0;
    }
}

public class RobotGameFixed {

    public static void main(String[] args) {

        Robot r1 = new Robot("Big Robot");
        Robot r2 = new Robot("George v.2.1");
        Robot r3 = new Robot("R2");

        r1.setBehavior(new agressiveBehaviour());
        r2.setBehavior(new defensiveBehaviour());
        r3.setBehavior(new normalBehaviour());

        r1.behave();
        r2.behave();
        r3.behave();

        //change the behaviors of each robot.
        r1.setBehavior(new normalBehaviour());
        r2.setBehavior(new defensiveBehaviour());
        r3.setBehavior(new agressiveBehaviour());

        r1.behave();
        r2.behave();
        r3.behave();
    }
}
