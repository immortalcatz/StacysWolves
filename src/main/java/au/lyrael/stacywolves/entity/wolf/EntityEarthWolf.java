package au.lyrael.stacywolves.entity.wolf;

import au.lyrael.stacywolves.annotation.WolfMetadata;
import au.lyrael.stacywolves.annotation.WolfSpawn;
import au.lyrael.stacywolves.annotation.WolfSpawnBiome;
import au.lyrael.stacywolves.client.render.IRenderableWolf;
import au.lyrael.stacywolves.registry.ItemRegistry;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

@WolfMetadata(name = "EntityEarthWolf", primaryColour = 0xB8845B, secondaryColour = 0x583C28,
        spawns = {
                @WolfSpawn(spawnBiomes = {
                        @WolfSpawnBiome(requireBiomeTypes = {PLAINS}, excludeBiomeTypes = {MOUNTAIN, SANDY, NETHER, END, COLD, SAVANNA}),
                        @WolfSpawnBiome(requireBiomeTypes = {FOREST}, excludeBiomeTypes = {MOUNTAIN, SANDY, NETHER, END, COLD, SAVANNA}),
                        @WolfSpawnBiome(requireBiomeTypes = {HILLS}, excludeBiomeTypes = {MOUNTAIN, SANDY, NETHER, END, COLD, SAVANNA}),
                }, probability = 10, min = 1, max = 4),
        })
public class EntityEarthWolf extends EntityWolfBase implements IRenderableWolf {

    public EntityEarthWolf(World worldObj) {
        super(worldObj);
        addLikedItem(ItemRegistry.getWolfFood("earth_bone"));
        this.addEdibleItem(new ItemStack(Items.beef));
        this.addEdibleItem(new ItemStack(Items.chicken));
    }

    @Override
    public EntityWolfBase createChild(EntityAgeable parent) {
        EntityWolfBase child = new EntityEarthWolf(this.worldObj);
        return createChild(parent, child);
    }

    @Override
    public String getTextureFolderName() {
        return "earth";
    }
}
