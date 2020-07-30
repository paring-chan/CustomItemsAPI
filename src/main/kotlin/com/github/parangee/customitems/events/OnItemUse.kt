package com.github.parangee.customitems.events

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import com.github.parangee.customitems.Registry
import com.github.parangee.customitems.plugin.CustomItemsAPIPlugin
import org.bukkit.Material
import org.bukkit.event.player.PlayerFishEvent

class OnItemUse : Listener {
    @EventHandler
    fun onRightClient(e: PlayerInteractEvent) {
        if (e.item != null) {
            val memberName = e.item!!.itemMeta.persistentDataContainer.get(
                NamespacedKey(CustomItemsAPIPlugin.instance, "customItem"),
                PersistentDataType.STRING
            )
            Registry.customItems[memberName]?.onInteract(e.player, e)
            if (e.action == Action.RIGHT_CLICK_BLOCK || e.action == Action.RIGHT_CLICK_AIR) {
                Registry.customItems[memberName]?.onUse(e.player, e)
            }
            if (e.action == Action.LEFT_CLICK_AIR || e.action == Action.LEFT_CLICK_BLOCK) {
                Registry.customItems[memberName]?.onLeftClick(e.player, e)
            }
        }
    }

    @EventHandler
    fun onAttack(e: EntityDamageByEntityEvent) {
        if (e.damager is Player) {
            val p = e.damager as Player
            val memberName = p.activeItem?.itemMeta?.persistentDataContainer?.get(
                NamespacedKey(CustomItemsAPIPlugin.instance, "customItem"),
                PersistentDataType.STRING
            )
            Registry.customItems[memberName]?.onAttack(p, e)
        }
    }

    @EventHandler(ignoreCancelled = true)
    fun onFish(e: PlayerFishEvent) {
        if (e.player.activeItem != null && e.player.activeItem!!.type == Material.FISHING_ROD) {
            val memberName = e.player.activeItem?.itemMeta?.persistentDataContainer?.get(
                NamespacedKey(CustomItemsAPIPlugin.instance, "customItem"),
                PersistentDataType.STRING
            )
            Registry.customItems[memberName]?.onFish(e)
        }
    }
}