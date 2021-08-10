package Entities;

public class NormalPizza extends Pizza{

	private double modifier = 0.2;
	private int enemyXp = (int) (5 + (stage * 0.2));
	
	public double getModifier() {
		return modifier;
	}

	public void setModifier(double modifier) {
		this.modifier = modifier;
	}

	public int getEnemyXp() {
		return enemyXp;
	}

	public void setEnemyXp(int enemyXp) {
		this.enemyXp = enemyXp;
	}

	public NormalPizza(int s) {
		super(s);
		pizzaHealth = pizzaHealthFormula();
	}

	@Override
	public int pizzaHealthFormula() {
		
		double formula = base + ((modifier * base)*1.5);
		
		return (int) Math.round(formula);
	}

}
