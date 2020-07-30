package com.github.parangee.customitems.classes

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import com.github.parangee.customitems.plugin.CustomItemsAPIPlugin
import org.bukkit.event.player.PlayerFishEvent

abstract class CustomItem(val memberName: String, material: Material, amount: Int = 1) : ItemStack() {
    init {
        this.type = material
        this.amount = amount
        val meta = this.itemMeta
        meta.persistentDataContainer.set(NamespacedKey(CustomItemsAPIPlugin.instance, "customItem"), PersistentDataType.STRING, memberName)
        this.itemMeta = meta
    }
    open fun onUse(player: Player, event: PlayerInteractEvent) {
    }
    open fun onAttack(player: Player, event: EntityDamageByEntityEvent) {
    }
    open fun onInteract(player: Player, event: PlayerInteractEvent) {
    }
    open fun onLeftClick(player: Player, event: PlayerInteractEvent) {
    }
    open fun onFish(event: PlayerFishEvent) {
    }
}