import java.util.ArrayList;

interface Ingredient {
    public String getName();
    public double getQuantity();
}
class SolidIngredient implements Ingredient{
    private String _ingredientName;
    private double _ingredientAmt;
    public SolidIngredient(String n,double a){
        this._ingredientName = n;
        this._ingredientAmt = a;
    }
    public String getName() {return this._ingredientName;}
    public double getQuantity() {return this._ingredientAmt;}
}
class LiquidIngredient implements Ingredient{
    private String _ingredientName;
    private double _ingredientAmt;
    public LiquidIngredient(String n,int a){
        this._ingredientName = n;
        this._ingredientAmt = a;
    }
    public String getName() {return this._ingredientName;}
    public double getQuantity() {return this._ingredientAmt;}
}
class Recipe<T extends Ingredient>{
    private String _recipeName;
    private String _instructions;
    private ArrayList<T> _ingredients;
    public Recipe(String recipeName,String instructions,int size){
        this._ingredients = new ArrayList<T>(size);
        this._instructions = instructions;
        this._recipeName = recipeName;
    }
    public void addIngredient(T ingredient){
        _ingredients.add(ingredient);
    }
    public void print(){
        System.out.println("Ingredients: "+_ingredients+ "instructions: "+ _instructions+" recipe name: "+_recipeName);
    }
}

public class Main {
    public static void main(String[] args) {
        SolidIngredient si = new SolidIngredient("flour",1.5);
        LiquidIngredient li = new LiquidIngredient("milk",1);
        Recipe r1 = new Recipe("bread","1,2,3,4,5",1);
        r1.addIngredient(si);
        r1.print();
        Recipe r2 = new Recipe("bread","1,2,3,4,5",1);


    }
}