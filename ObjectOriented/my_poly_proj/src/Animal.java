package ObjectOriented.my_poly_proj.src;

public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String sound() {
        return null;
    }

    public String toString() {
        return name.concat(" says ").concat(sound());
    }

}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public String sound() {
        return "Woof";
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    public String sound() {
        return "Meow";
    }
}

class Cow extends Animal {
    public Cow(String name) {
        super(name);
    }

    public String sound() {
        return "Moo";
    }
}