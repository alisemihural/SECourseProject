package banking;

public class CD extends Account {
	private double apr;
	private int id;

	public CD(int id, double apr, double startingBalance) {
		super(id, apr);
		balance = startingBalance;
	}

}
