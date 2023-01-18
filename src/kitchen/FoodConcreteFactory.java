/* CLASS COMMENT:
 * An subclass for the food factory. 
 * Instantiate objects in the createFood(String type) method and assign them string types*/

package kitchen;

import main.TaiyakiPanel;

public class FoodConcreteFactory extends FoodFactory{

	@Override
	public BaseButton createFood(String type) {
		BaseButton buttons = null;
		if (type == "startBtn")
			buttons = new StartButton(TaiyakiPanel.W_WIDTH/ 2+45, 155, 0.35);
		else if (type == "restartBtn")
			buttons = new RestartButton(TaiyakiPanel.W_WIDTH / 2+45, 150, 0.35);
		else if (type == "bowl")
			buttons = new Bowl(TaiyakiPanel.W_WIDTH / 2 - 270, TaiyakiPanel.W_HEIGHT / 2 + 110, 0.23);
		else if (type == "pan")
			buttons = new Pan(TaiyakiPanel.W_WIDTH / 2 + 90, TaiyakiPanel.W_HEIGHT / 2 + 110, 0.3);
		else if (type == "tiyk")
			buttons = new Taiyaki(TaiyakiPanel.W_WIDTH / 2-20, TaiyakiPanel.W_HEIGHT / 2-110, 0.55);
		
		return buttons;
	}

}
