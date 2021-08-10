package Entities;

public class BossPizza extends Pizza{
	
	
	private double modifier = 0.6;
	private int enemyXp = (int) (100 + (stage * 0.5));
	
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

	public BossPizza(int s) {
		super(s);
		pizzaHealth = pizzaHealthFormula();
	}

	@Override
	public int pizzaHealthFormula() {
		
		double formula = base + (modifier * base) + stage;
		
		return (int) Math.round(formula);
	}

}
