package com.github.parangee.customitems.arguments

import com.github.noonmaru.kommand.KommandContext
import com.github.noonmaru.kommand.argument.KommandArgument
import com.github.noonmaru.kommand.argument.suggestions
import com.github.parangee.customitems.Registry
import com.github.parangee.customitems.classes.CustomItem

class ItemArgument internal constructor() : KommandArgument<CustomItem> {
    override val parseFailMessage: String
        get() = "${KommandArgument.TOKEN} 아이템이 없습니다."

    override fun parse(context: KommandContext, param: String): CustomItem? {
        return Registry.customItems[param]
    }

    override fun listSuggestion(context: KommandContext, target: String): Collection<String> {
        return Registry.customItems.map { it.key }.suggestions(target) { it }
    }

    companion object {
        internal val instance by lazy {
            ItemArgument()
        }
    }
}

fun item(): ItemArgument {
    return ItemArgument.instance
}