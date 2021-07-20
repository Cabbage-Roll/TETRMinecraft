package cabbageroll.notrisdefect.menus;

import cabbageroll.notrisdefect.Main;
import cabbageroll.notrisdefect.Menu;
import cabbageroll.notrisdefect.Room;
import com.cryptomorin.xseries.XMaterial;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import cabbageroll.notrisdefect.functions.softdepend.NoteBlockAPIYes;

//used only if noteblockapi is present
public class SongMenu extends BaseMenu {

    public SongMenu(Player player) {
        Main.gs.setLastMenuOpened(player, Menu.ROOMSONG);
        createInventory(this, 54, "Choose song");
        ItemStack border = XMaterial.GLASS_PANE.parseItem();
        // fill the border with glass
        for (int i = 0; i < 9; i++) {
            getInventory().setItem(i, border);
        }
        for (int i = 45; i < 54; i++) {
            getInventory().setItem(i, border);
        }

        // clickable items

        getInventory().setItem(BACK_LOCATION, createItem(XMaterial.BEDROCK, ChatColor.WHITE + "Back"));
        getInventory().setItem(9, createItem(XMaterial.NOTE_BLOCK, ChatColor.YELLOW + "Random"));

        Playlist playlist = NoteBlockAPIYes.playlist;
        for (int i = 0; i < playlist.getCount(); i++) {
            String name;
            if (playlist.get(i).getPath() == null) {
                name = NoteBlockAPIYes.classpathSongs[i];
            } else {
                name = playlist.get(i).getPath().getName().replaceAll(".nbs$", "");
            }
            getInventory().setItem(10 + i, createItem(XMaterial.NOTE_BLOCK, ChatColor.WHITE + name));
        }

        player.openInventory(getInventory());
    }

    public static void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        Room room = Main.gs.getRoom(player);
        if (event.getSlot() == SongMenu.BACK_LOCATION) {
            new RoomMenu(player);
        } else if (event.getSlot() == 9) {
            room.index = -1;
        } else if (event.getSlot() - 10 < NoteBlockAPIYes.playlist.getCount()) {
            room.index = event.getSlot() - 10;
        }

    }
}
