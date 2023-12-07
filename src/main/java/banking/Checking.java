package banking;

public class Checking extends Account {
	private double apr;
	private int id;

	public Checking(int id, double apr) {
		super(id, apr);
	}
}
