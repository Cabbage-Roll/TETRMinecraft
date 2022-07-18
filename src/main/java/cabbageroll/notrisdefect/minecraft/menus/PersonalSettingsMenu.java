package cabbageroll.notrisdefect.minecraft.menus;

import cabbageroll.notrisdefect.minecraft.Main;
import cabbageroll.notrisdefect.minecraft.Table;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PersonalSettingsMenu extends Menu {

    public PersonalSettingsMenu(Player player) {
        super(player);
    }

    @Override
    protected void afterInventoryClick(InventoryClickEvent event) {
        if (event.getSlot() != BACK_LOCATION) {
            new PersonalSettingsMenu((Player) event.getWhoClicked());
        }
    }

    @Override
    protected void prepare() {
        createInventory(this, 54, "My settings");
        Table table = Main.GS.getTable(player);

        for (int i = 0; i < 9; i++) {
            addButton(grid(i + 1, 1), empty);
            addButton(grid(i + 1, 6), empty);
        }

        addButton(BACK_LOCATION, event -> new RoomMenu(player), XMaterial.BEDROCK, ChatColor.WHITE + "Back");

        addButton(grid(1, 2), event -> table.moveTableRelative(howMuch(event.getClick()), 0, 0), XMaterial.RED_WOOL, ChatColor.WHITE + "Move X", "" + table.getX());
        addButton(grid(2, 2), event -> table.moveTableRelative(0, howMuch(event.getClick()), 0), XMaterial.GREEN_WOOL, ChatColor.WHITE + "Move Y", "" + table.getY());
        addButton(grid(3, 2), event -> table.moveTableRelative(0, 0, howMuch(event.getClick())), XMaterial.BLUE_WOOL, ChatColor.WHITE + "Move Z", "" + table.getZ());

        addButton(grid(1, 3), event -> table.rotateX(), XMaterial.RED_CARPET, ChatColor.WHITE + "Rotate X");
        addButton(grid(2, 3), event -> table.rotateY(), XMaterial.GREEN_CARPET, ChatColor.WHITE + "Rotate Y");
        addButton(grid(3, 3), event -> table.rotateZ(), XMaterial.BLUE_CARPET, ChatColor.WHITE + "Rotate Z");

        addButton(grid(1, 4), event -> table.skewWX(howMuch(event.getClick())), XMaterial.RED_BANNER, ChatColor.WHITE + "Width X multiplier", "" + table.getWMX());
        addButton(grid(2, 4), event -> table.skewWY(howMuch(event.getClick())), XMaterial.GREEN_BANNER, ChatColor.WHITE + "Width Y multiplier", "" + table.getWMY());
        addButton(grid(3, 4), event -> table.skewWZ(howMuch(event.getClick())), XMaterial.BLUE_BANNER, ChatColor.WHITE + "Width Z multiplier", "" + table.getWMZ());
        addButton(grid(1, 5), event -> table.skewHX(howMuch(event.getClick())), XMaterial.RED_BANNER, ChatColor.WHITE + "Height X multiplier", "" + table.getHMX());
        addButton(grid(2, 5), event -> table.skewHY(howMuch(event.getClick())), XMaterial.GREEN_BANNER, ChatColor.WHITE + "Height Y multiplier", "" + table.getHMY());
        addButton(grid(3, 5), event -> table.skewHZ(howMuch(event.getClick())), XMaterial.BLUE_BANNER, ChatColor.WHITE + "Height Z multiplier", "" + table.getHMZ());

        addButton(grid(4, 2), event -> table.automaticReposition(), XMaterial.COMPASS, ChatColor.WHITE + "Reposition (automatic)");
        addButton(grid(5, 2), event -> table.switchDeathAnim(), XMaterial.REDSTONE, ChatColor.WHITE + "Game over animation", "" + Table.getDeathAnim());

        addButton(grid(7, 2), event -> table.setARR(table.getARR() + 5 * howMuch(event.getClick())), XMaterial.SPONGE, ChatColor.WHITE + "ARR", table.getARR() + "ms");
        addButton(grid(8, 2), event -> table.setDAS(table.getDAS() + 5 * howMuch(event.getClick())), XMaterial.SPONGE, ChatColor.WHITE + "DAS", table.getDAS() + "ms");
        addButton(grid(9, 2), event -> table.setSDF(table.getSDF() + howMuch(event.getClick())), XMaterial.SPONGE, ChatColor.WHITE + "SDF", table.getSDF() + "X gravity");

        addButton(grid(9, 3), event -> table.setGAP(table.getGAP() + howMuch(event.getClick())), XMaterial.GLOWSTONE_DUST, ChatColor.WHITE + "[EXPERIMENTAL] GAP", "If your inputs get dropped", "due to lag, increase gap.", "If you press buttons too", "quickly, decrease gap.", "Doesn't get saved", "" + table.getGAP());
    }
}
