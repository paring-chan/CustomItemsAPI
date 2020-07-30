package studio.pikolab.customitemapi

import studio.pikolab.customitemapi.classes.CustomItem

object Registry {
    val customItems = HashMap<String, CustomItem>()
    fun registerItem(item: CustomItem) {
        customItems[item.memberName] = item
    }
}