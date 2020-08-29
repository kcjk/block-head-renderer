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

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BlockHeadRenderer.MODID, name = BlockHeadRenderer.NAME, version = BlockHeadRenderer.VERSION, acceptedMinecraftVersions = BlockHeadRenderer.ACCEPTED_MINECRAFT_VERSIONS)
public class BlockHeadRenderer {
    public static final String MODID = "blockheadrenderer";
    public static final String NAME = "BlockHeadRenderer";
    public static final String VERSION = "1.12.2-0.2";
    public static final String ACCEPTED_MINECRAFT_VERSIONS = "[1.12.2]";

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityArmorStand.class, new RenderArmorStand(Minecraft.getMinecraft().getRenderManager()));
    }
}
