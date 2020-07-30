package com.github.parangee.customitems

import com.github.parangee.customitems.classes.CustomItem

class Registry {
    companion object {
        val customItems = HashMap<String, CustomItem>()
        fun registerItem(item: CustomItem) {
            customItems[item.memberName] = item
        }
    }
}