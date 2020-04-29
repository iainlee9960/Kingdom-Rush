package stages;
import java.util.*;

import javax.swing.JPanel;

import towers.*;
import enemies.*;

public abstract class Stage extends JPanel {
	ArrayList<Checkpoint> checkpoints;
	int waves;
	Archer a;
	int[] xPositions;
	int[] yPositions;
	
	abstract public void initializeCheckpoints();
	
}
