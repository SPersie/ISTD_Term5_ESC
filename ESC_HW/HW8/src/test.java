public class test {
    public static void main(String[] args) {
//        System.out.println(System.identityHashCode());
        father fatherr =new father();
        son sonn =new son();
        son son2 = new son();
        Thread father =new runson(sonn);
        Thread son =new runson(son2);
        father.start();
        son.start();
    }

}

class father {
    public father() {};
    synchronized void dosomething() throws InterruptedException {
        System.out.println("Im your father");
        Thread.sleep(10000);
        System.out.println("father ends");
    }
}

class son extends father {
    public son(){};
    public father fat = new father();
    synchronized void dootherthing() throws InterruptedException {
        System.out.println("I'm here.");
        super.dosomething();
    }
}

class runfather extends Thread {
    father father;
    public runfather(father father) {
        this.father =father;
    }

    public void run() {
        try {
            father.dosomething();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class runson extends Thread {
    son son;
    public runson(son son) {
        this.son =son;
    }

    public void run() {
        try {
            son.dootherthing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
