package stages;

import java.util.ArrayList;

import enemies.*;

public class Wave {
	Enemy[] enemies;
	public Wave(Enemy[] enemies) {
		this.enemies = enemies;
	}
	public Enemy[] getEnemies() {
		return enemies;
	}
}
