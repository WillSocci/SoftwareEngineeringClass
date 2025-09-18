package c;

public abstract class Pizza implements PizzaMaker {
    public String name;

    @Override
    public void prepare() {
        System.out.println("Preparing " + name + " pizza...");
    }

    @Override
    public void bake() {
        System.out.println("Baking " + name + " pizza...");
    }

    @Override
    public void cut() {
        System.out.println("Cutting " + name + " pizza...");
    }

    @Override
    public void box() {
        System.out.println("Boxing " + name + " pizza...");
    }
}

class Cheese extends Pizza {
    public Cheese() {
        this.name = "Cheese";
    }
}

class Pepperoni extends Pizza {
    public Pepperoni() {
        this.name = "Pepperoni";
    }
}

class Hotoil extends Pizza {
    public Hotoil() {
        this.name = "Hot Oil";
    }
}

class GlutenFree extends Pizza {
    public GlutenFree() {
        this.name = "Gluten Free";
    }
}

class Sausage extends Pizza {
    public Sausage() {
        this.name = "Sausage";
    }
}

class BufChick extends Pizza {
    public BufChick() {
        this.name = "Buffalo Chicken";
    }
}

class NashHot extends Pizza {
    public NashHot() {
        this.name = "Nashville Hot";
    }
}

class Margherita extends Pizza {
    public Margherita() {
        this.name = "Margherita";
    }
}