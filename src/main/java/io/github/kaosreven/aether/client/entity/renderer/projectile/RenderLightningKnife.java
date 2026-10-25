package io.github.kaosreven.aether.client.entity.renderer.projectile;

import io.github.kaosreven.aether.entity.projectile.EntityLightningKnife;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class RenderLightningKnife extends EntityRenderer {
    public RenderLightningKnife() {
    }

    public void render(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        this.doRenderKnife((EntityLightningKnife) var1, var2, var4, var6, var8, var9);
    }

    public void doRenderKnife(EntityLightningKnife entity, double d, double d1, double d2, float yaw, float time) {
        float texMinX = 0;
        float texMaxX = 1;
        float texMinY = 0;
        float texMaxY = 1;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-(entity.prevPitch + (entity.pitch - entity.prevPitch) * time), 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);

        this.bindTexture("aether:stationapi/textures/item/LightningKnife.png");
        Tessellator tessellator = Tessellator.INSTANCE;
        float f4 = 1.0F;
        GL11.glEnable(32826);
        float f8 = 0.0625F;
        GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
        tessellator.startQuads();
        tessellator.normal(0.0F, 0.0F, 1.0F);
        tessellator.vertex(0.0, 0.0, 0.0, texMaxX, texMaxY);
        tessellator.vertex(f4, 0.0, 0.0, texMinX, texMaxY);
        tessellator.vertex(f4, 0.0, 1.0, texMinX, texMinY);
        tessellator.vertex(0.0, 0.0, 1.0, texMaxX, texMinY);
        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(0.0F, 0.0F, -1.0F);
        tessellator.vertex(0.0, 0.0F - f8, 1.0, texMaxX, texMinY);
        tessellator.vertex(f4, 0.0F - f8, 1.0, texMinX, texMinY);
        tessellator.vertex(f4, 0.0F - f8, 0.0, texMinX, texMaxY);
        tessellator.vertex(0.0, 0.0F - f8, 0.0, texMaxX, texMaxY);
        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(-1.0F, 0.0F, 0.0F);

        int l;
        float f12;
        float f16;
        float f20;
        for (l = 0; l < 16; ++l) {
            f12 = (float) l / 16.0F;
            f16 = texMaxX + (texMinX - texMaxX) * f12 - 0.001953125F;
            f20 = f4 * f12;
            tessellator.vertex(f20, 0.0F - f8, 0.0, f16, texMaxY);
            tessellator.vertex(f20, 0.0, 0.0, f16, texMaxY);
            tessellator.vertex(f20, 0.0, 1.0, f16, texMinY);
            tessellator.vertex(f20, 0.0F - f8, 1.0, f16, texMinY);
        }

        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(1.0F, 0.0F, 0.0F);

        for (l = 0; l < 16; ++l) {
            f12 = (float) l / 16.0F;
            f16 = texMaxX + (texMinX - texMaxX) * f12 - 0.001953125F;
            f20 = f4 * f12 + 0.0625F;
            tessellator.vertex(f20, 0.0F - f8, 1.0, f16, texMinY);
            tessellator.vertex(f20, 0.0, 1.0, f16, texMinY);
            tessellator.vertex(f20, 0.0, 0.0, f16, texMaxY);
            tessellator.vertex(f20, 0.0F - f8, 0.0, f16, texMaxY);
        }

        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(0.0F, 1.0F, 0.0F);

        for (l = 0; l < 16; ++l) {
            f12 = (float) l / 16.0F;
            f16 = texMaxY + (texMinY - texMaxY) * f12 - 0.001953125F;
            f20 = f4 * f12 + 0.0625F;
            tessellator.vertex(0.0, 0.0, f20, texMaxX, f16);
            tessellator.vertex(f4, 0.0, f20, texMinX, f16);
            tessellator.vertex(f4, 0.0F - f8, f20, texMinX, f16);
            tessellator.vertex(0.0, 0.0F - f8, f20, texMaxX, f16);
        }

        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(0.0F, -1.0F, 0.0F);

        for (l = 0; l < 16; ++l) {
            f12 = (float) l / 16.0F;
            f16 = texMaxY + (texMinY - texMaxY) * f12 - 0.001953125F;
            f20 = f4 * f12;
            tessellator.vertex(f4, 0.0, f20, texMinX, f16);
            tessellator.vertex(0.0, 0.0, f20, texMaxX, f16);
            tessellator.vertex(0.0, 0.0F - f8, f20, texMaxX, f16);
            tessellator.vertex(f4, 0.0F - f8, f20, texMinX, f16);
        }

        tessellator.draw();
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }
}
