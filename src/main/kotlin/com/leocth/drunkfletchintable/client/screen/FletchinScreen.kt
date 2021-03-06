package com.leocth.drunkfletchintable.client.screen

import com.leocth.drunkfletchintable.DrunkFletchinTable
import com.leocth.drunkfletchintable.block.entity.FletchinTableBlockEntity
import com.leocth.drunkfletchintable.block.entity.modules.CraftingModule
import com.leocth.drunkfletchintable.block.entity.modules.FletchinModule
import com.leocth.drunkfletchintable.screen.FletchinScreenHandler
import com.leocth.drunkfletchintable.screen.TippingScreenHandler
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.Drawable
import net.minecraft.client.gui.Element
import net.minecraft.client.gui.Selectable
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.widget.TexturedButtonWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction

abstract class FletchinScreen<T: FletchinScreenHandler>(
    handler: T,
    inventory: PlayerInventory,
    title: Text
) : HandledScreen<T>(handler, inventory, title) {

    open fun addModuleButtons(modules: Map<Direction, FletchinModule>) {
        val xOffset = x + 4
        var yOffset = y + 20

        for ((_, module) in modules) {
            val button = module.createButton(xOffset, yOffset)
            addDrawableChild(button)

            yOffset += button.height + 1
        }
    }

    companion object {
        val TEXTURE = DrunkFletchinTable.id("textures/gui/fletchin_table.png")
    }

    override fun init() {
        super.init()
        initLayout()
    }

    fun initLayout() {
        backgroundWidth = 216
        backgroundHeight = 175
        titleX = 44
        titleY = 6
        playerInventoryTitleX = 44
        playerInventoryTitleY = 81
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)
    }

    protected fun drawModuleBg(matrices: MatrixStack, texture: Identifier) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        RenderSystem.setShaderTexture(0, texture)
        drawTexture(matrices, x+45, y+17, 0, 0, 160, 61)

    }
}