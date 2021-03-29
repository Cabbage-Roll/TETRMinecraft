package tetrminecraft.menus;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.cryptomorin.xseries.XMaterial;

import tetrminecraft.Main;
import tetrminecraft.Table;

public class SettingsMenu extends BaseMenu {

    public final static int BACK_LOCATION = 0;
    public final static int TORCH_LOCATION = 8;

    public SettingsMenu(Player player) {
        Main.instance.lastMenuOpened.put(player, "settings");
        createInventory(this, 54, "Settings");
        ItemStack border = XMaterial.GLASS_PANE.parseItem();
        // fill the border with glass
        for (int i = 0; i < 9; i++) {
            getInventory().setItem(i, border);
        }
        for (int i = 45; i < 54; i++) {
            getInventory().setItem(i, border);
        }

        Table table = Main.instance.inWhichRoomIs.get(player).playerTableMap.get(player);

        getInventory().setItem(BACK_LOCATION, createItem(XMaterial.BEDROCK, ChatColor.WHITE + "Back"));
        getInventory().setItem(TORCH_LOCATION,
                createItem(XMaterial.REDSTONE_TORCH, ChatColor.DARK_RED + "This is advanced settings menu",
                        ChatColor.YELLOW + "" + ChatColor.BOLD + "Click to go to standard menu"));

        getInventory().setItem(11, createItem(XMaterial.DIRT, "your pos"));
        getInventory().setItem(12, createItem(XMaterial.RED_WOOL, ChatColor.WHITE + "Move X", "X: " + table.getGx()));
        getInventory().setItem(13, createItem(XMaterial.GREEN_WOOL, ChatColor.WHITE + "Move Y", "Y: " + table.getGy()));
        getInventory().setItem(14, createItem(XMaterial.BLUE_WOOL, ChatColor.WHITE + "Move Z", "Z: " + table.getGz()));
        getInventory().setItem(21, createItem(XMaterial.DIRT, "Width X multiplier: " + table.mwx));
        getInventory().setItem(22, createItem(XMaterial.DIRT, "Width Y multiplier: " + table.mwy));
        getInventory().setItem(23, createItem(XMaterial.DIRT, "Width Z multiplier: " + table.mwz));
        getInventory().setItem(30, createItem(XMaterial.RED_CARPET, ChatColor.WHITE + "Rotate X"));
        getInventory().setItem(31, createItem(XMaterial.GREEN_CARPET, ChatColor.WHITE + "Rotate Y"));
        getInventory().setItem(32, createItem(XMaterial.BLUE_CARPET, ChatColor.WHITE + "Rotate Z"));
        getInventory().setItem(39, createItem(XMaterial.DIRT, "Height X multiplier: " + table.mhx));
        getInventory().setItem(40, createItem(XMaterial.DIRT, "Height Y multiplier: " + table.mhy));
        getInventory().setItem(41, createItem(XMaterial.DIRT, "Height Z multiplier: " + table.mhz));
        getInventory().setItem(53,
                createItem(XMaterial.DIRT, "BACKFIRE: " + Main.instance.inWhichRoomIs.get(player).backfire));
        getInventory().setItem(1,
                createItem(XMaterial.PACKED_ICE, ChatColor.WHITE + "Falling blocks: " + table.enableFallingSand));

        player.openInventory(getInventory());
    }

    public static void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);

        int by = 0;
        if (event.getClick() == ClickType.LEFT) {
            by = +1;
        } else if (event.getClick() == ClickType.RIGHT) {
            by = -1;
        }

        Table table = Main.instance.inWhichRoomIs.get(player).playerTableMap.get(player);

        switch (event.getSlot()) {
        case SettingsMenu.BACK_LOCATION:
            new RoomMenu(player);
            return;
        case SettingsMenu.TORCH_LOCATION:
            new SimpleSettingsMenu(player);
            return;
        case 11:
            table.cleanAll();
            table.reposition();
            table.drawAll(16);
            break;
        case 12:
            table.moveTableRelative(by, 0, 0);
            break;
        case 13:
            table.moveTableRelative(0, by, 0);
            break;
        case 14:
            table.moveTableRelative(0, 0, by);
            break;
        case 21:
            table.mwx += by;
            break;
        case 22:
            table.mwy += by;
            break;
        case 23:
            table.mwz += by;
            break;
        case 30:
            table.rotateTable("X");
            break;
        case 31:
            table.rotateTable("Y");
            break;
        case 32:
            table.rotateTable("Z");
            break;
        case 39:
            table.mhx += by;
            break;
        case 40:
            table.mhy += by;
            break;
        case 41:
            table.mhz += by;
            break;
        case 53:
            Main.instance.inWhichRoomIs.get(player).backfire = !Main.instance.inWhichRoomIs.get(player).backfire;
            break;
        case 1:
            table.enableFallingSand = !table.enableFallingSand;
            break;
        default:
            return;
        }

        new SettingsMenu(player);

    }
}
