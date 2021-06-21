package com.leocth.drunkfletchintable.block.entity.modules

import com.leocth.drunkfletchintable.block.entity.FletchinTableBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.CraftingScreenHandler
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText

class CraftingModule(blockEntity: FletchinTableBlockEntity) : FletchinModule(blockEntity) {
    override val type: ModuleType<*> = TYPE

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler
        = CraftingScreenHandler(syncId, inv, screenHandlerContext)

    override fun getDisplayName(): Text = TranslatableText("screen.drunkfletchintable.crafting")

    companion object {
        val TYPE = ModuleType(::CraftingModule)
    }
}
