package tetrminecraft.functions.versions;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import tetrminecraft.functions.versions.sendblockchangecustom.SendBlockChangeCustom_V2;

public class Functions_1_16_R1 implements Functions {

    @Override
    public void sendActionBarCustom(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(message).create());
    }

    @Override
    public void sendBlockChangeCustom(Player player, Location loc, Block block) {
        SendBlockChangeCustom_V2.sendBlockChangeCustom(player, loc, block);
    }

    @Override
    public void sendBlockChangeCustom(Player player, Location loc, int color) {
        SendBlockChangeCustom_V2.sendBlockChangeCustom(player, loc, color);
    }

    @Override
    public void sendFallingBlockCustom(Player player, Location loc, int color, double xVel, double yVel, double zVel) {

    }

    @Override
    public void sendTitleCustom(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
}
