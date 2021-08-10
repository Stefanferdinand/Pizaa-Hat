package Entities;

public abstract class Pizza {
	
	protected int pizzaHealth;
	protected int stage;
	protected int base;
	
	public int getPizzaHealth() {
		return pizzaHealth;
	}

	public void setPizzaHealth(int pizzaHealth) {
		this.pizzaHealth = pizzaHealth;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public abstract int pizzaHealthFormula();
	
	public Pizza(int stage) {
		this.stage = stage;
		int add = (int) (stage * 0.5);
		base = (int) ((stage * 1.5) + 100 + add);
	}

}
