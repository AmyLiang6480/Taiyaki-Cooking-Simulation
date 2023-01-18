/* CLASS COMMENT:
 * An abstract class for the factory at the super level.
 * Using createFood(String type) to call type of objects in the panel constructor*/

package kitchen;

public abstract class FoodFactory {
	public abstract BaseButton createFood(String type);
}
