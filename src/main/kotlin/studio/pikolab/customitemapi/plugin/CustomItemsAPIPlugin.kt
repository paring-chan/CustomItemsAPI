package studio.pikolab.customitemapi.plugin

import com.github.noonmaru.kommand.argument.player
import com.github.noonmaru.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin
import studio.pikolab.customitemapi.Registry
import studio.pikolab.customitemapi.arguments.item
import studio.pikolab.customitemapi.events.OnItemUse
import studio.pikolab.customitemapi.classes.CustomItem
import org.bukkit.entity.Player


class CustomItemsAPIPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: CustomItemsAPIPlugin
    }

    override fun onEnable() {
        instance = this
        server.pluginManager.registerEvents(OnItemUse(), this)
        kommand {
            register("cgive") {
                requirement = {
                    isOp
                }
                then("player" to player()) {
                    then("item" to item()) {
                        executes {
                            if ((it.parseArgument("player") as Player?) == null) {
                                it.sender.sendMessage("유저가 없습니다.")
                            } else {
                                if ((it.parseArgument("item") as CustomItem?) == null) {
                                    it.sender.sendMessage("아이템을 찾을 수 없습니다.")
                                } else {
                                    (it.sender as Player).inventory.addItem(Registry.customItems[it.getArgument("item")])
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}