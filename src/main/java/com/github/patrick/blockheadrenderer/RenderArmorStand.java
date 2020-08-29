/*
 * Copyright (C) 2020 PatrickKR
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * Contact me on <mailpatrickkr@gmail.com>
 */

package com.github.patrick.blockheadrenderer;

import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderArmorStand extends RenderLivingBase<EntityArmorStand> {
    public static final ResourceLocation TEXTURE_ARMOR_STAND = new ResourceLocation("textures/entity/armorstand/wood.png");

    public RenderArmorStand(RenderManager manager) {
        super(manager, new ModelArmorStand(), 0.0F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
            protected void initArmor() {
                this.modelLeggings = new ModelArmorStandArmor(0.5F);
                this.modelArmor = new ModelArmorStandArmor(1.0F);
            }
        };
        this.addLayer(layerbipedarmor);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerElytra(this));
        this.addLayer(new LayerItemHead(this.getMainModel().bipedHead));
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nullable EntityArmorStand entity) {
        return TEXTURE_ARMOR_STAND;
    }

    @Override
    @Nonnull
    public ModelArmorStand getMainModel() {
        return (ModelArmorStand)super.getMainModel();
    }

    protected void applyRotations(EntityArmorStand entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        float f = (float)(entityLiving.world.getTotalWorldTime() - entityLiving.punchCooldown) + partialTicks;
        if (f < 5.0F) {
            GlStateManager.rotate(MathHelper.sin(f / 1.5F * (float)Math.PI) * 3.0F, 0.0F, 1.0F, 0.0F);
        }
    }

    protected boolean canRenderName(EntityArmorStand entity)
    {
        return entity.getAlwaysRenderNameTag();
    }

    public void doRender(EntityArmorStand entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.hasMarker()) {
            this.renderMarker = true;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        if (entity.hasMarker()) {
            this.renderMarker = false;
        }

    }

    protected void renderModel(EntityArmorStand entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        if (!entitylivingbaseIn.isInvisible()) {
            super.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        }
    }
}