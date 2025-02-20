class Animal {
    public int age;
    public String gender;

    public boolean isMammal() {
        System.out.println("This is a public method isMammal() from class Animal");
        return true;
    }

    public void mate() {
        System.out.println("This is a public method mate() from class Animal");
    }

    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        myAnimal.age = 5;
        myAnimal.gender = "Male";
        myAnimal.isMammal();
        myAnimal.mate();

        Fish myFish = new Fish();
        myFish.age = 2;
        myFish.gender = "Female";

        Zebra myZebra = new Zebra();
        myZebra.age = 4;
        myZebra.gender = "Male";
        myZebra.is_wild = true;
        myZebra.run();
    }
}

class Fish extends Animal {
    private int sizeInFeet;

    private void canEat() {
        System.out.println("This is a private method canEat() from class Fish");
    }
}

class Zebra extends Animal {
    public boolean is_wild;

    public void run() {
        System.out.println("This is a public method run() from class Zebra");
    }
}