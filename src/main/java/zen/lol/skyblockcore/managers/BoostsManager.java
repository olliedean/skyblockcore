package zen.lol.skyblockcore.managers;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;

public class BoostsManager {
    public static boolean spawnBoostsMenu(String type){
        String name = type.substring(0, 1).toUpperCase() + type.substring(1);
        ChestGui gui = new ChestGui(3, name + "Boosters");
        return false;
    }
}
