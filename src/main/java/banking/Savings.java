package banking;

public class Savings extends Account {
	private double apr;
	private int id;

	public Savings(int id, double apr) {
		super(id, apr);
	}
}
