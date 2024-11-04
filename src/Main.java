import java.util.ArrayList;
import java.util.Scanner;

interface Ingredient {
    String getName();

    double getQuantity();
}

class SolidIngredient implements Ingredient {
    private String _ingredientName;
    private double _qtyInGrams;

    public SolidIngredient(String n, double a) {
        this._ingredientName = n;
        this._qtyInGrams = a;
    }

    public String getName() {
        return this._ingredientName;
    }

    public double getQuantity() {
        return this._qtyInGrams;
    }
}

class LiquidIngredient implements Ingredient {
    private String _ingredientName;
    private double _qtyInMl;

    public LiquidIngredient(String n, double a) {
        this._ingredientName = n;
        this._qtyInMl = a;
    }

    public String getName() {
        return this._ingredientName;
    }

    public double getQuantity() {
        return this._qtyInMl;
    }
}

class Recipe<T extends Ingredient> {
    private String _recipeName;
    private String _instructions;
    private ArrayList<T> _ingredients;

    public Recipe(String recipeName, String instructions) {
        this._ingredients = new ArrayList<T>();
        this._instructions = instructions;
        this._recipeName = recipeName;
    }

    public Recipe(String recipeName, String instructions, int size) {
        this._ingredients = new ArrayList<T>(size);
        this._instructions = instructions;
        this._recipeName = recipeName;
    }

    public void addIngredient(T ingredient) {
        _ingredients.add(ingredient);
    }

    public void print() {
        System.out.println("Recipe name: " + _recipeName);
        System.out.println("Instructions: " + _instructions);
        System.out.println("Ingredients: ");
        int itemNum = 1;
        for (T ingredient : _ingredients)
            System.out.println(itemNum++ + ". "+ingredient.getName() + " " + ingredient.getQuantity());
    }
}

public class Main {
    public static void addIngredient(Recipe<Ingredient> recipe, Scanner scan) {
        System.out.println("Solid (s) or Liquid(l): ");
        char type = scan.nextLine().charAt(0);
        System.out.println("Enter an ingredient name: ");
        String name = scan.nextLine();
        System.out.println("Quantity of ingredient (ml/grams): ");
        double quantity = Double.parseDouble(scan.nextLine());
        Ingredient ingredient;
        if (type == 's') {
            ingredient = new SolidIngredient(name, quantity);
        } else {
            ingredient = new LiquidIngredient(name, quantity);
        }
        recipe.addIngredient(ingredient);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
            System.out.println("enter a name for recipe: ");
            String recipeName = scan.nextLine();
            System.out.println("Enter list of instructions");
            String instructions = scan.nextLine();
            Recipe<Ingredient> recipe = new Recipe<>(recipeName,instructions);
            int option = menu(scan);
            while(option!=3) {
                switch (option) {
                    case 1 -> addIngredient(recipe, scan);
                    case 2 -> recipe.print();
                    default -> System.out.println("Invalid choice. Try again");
                }
                option=menu(scan);
            }
    }

    public static int menu(Scanner scan) {
        int option = 0;
        System.out.println("Choose an option\n1.Add an Ingredient\n2.Print Instructions\n3. Quit");
        try {
            option = Integer.valueOf(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid data entered " + e.getMessage());
            System.exit(-1);
        }
        return option;
    }
}