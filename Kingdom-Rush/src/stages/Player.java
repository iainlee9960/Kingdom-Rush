package stages;

public class Player {
	int money, hearts;
	public Player(int money) {
		hearts = 20;
		this.money = money;
	}
	public int getMoney() {
		return money;
	}
	public int getHearts() {
		return hearts;
	}
	public void removeHearts(int value) {
		this.hearts-=value;
	}
	public void spendMoney(int value) {
		this.money-=value;
	}
	public void increaseMoney(int value) {
		this.money+=value;
	}
}
