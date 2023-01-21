package dev.dr0n1k.thermal_science.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import static cofh.lib.util.constants.BlockStatePropertiesCoFH.AGE_0_7;
import static cofh.thermal.core.util.RegistrationHelper.deepslate;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static dev.dr0n1k.thermal_science.Constants.*;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;
import static dev.dr0n1k.thermal_science.ThermalScience.ITEMS;

public class TSciLootTableProvider extends LootTableProviderCoFH {
    public TSciLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Thermal Science: Loot Tables";
    }

    @Override
    protected void addTables() {
        //createCropTable(ENDER_LILLY_ID);
        this.createSilkDropTable(ID_CURSED_EARTH, BLOCKS.get(ID_CURSED_EARTH), Items.DIRT);
        this.createSyncDropTable(BLOCKS.get(ID_MACHINE_ASSEMBLY));

        this.createSilkDropTable(ID_REDROCK_STONE, BLOCKS.get(ID_REDROCK_STONE), ITEMS.get(ID_REDROCK_COBBLESTONE));
        this.createSimpleDropTable(BLOCKS.get(ID_REDROCK_COBBLESTONE));
        this.createSimpleDropTable(BLOCKS.get(ID_REDROCK_BRICKS));

        this.createSilkDropTable(ID_BASALT_STONE, BLOCKS.get(ID_BASALT_STONE), ITEMS.get(ID_BASALT_COBBLESTONE));
        this.createSimpleDropTable(BLOCKS.get(ID_BASALT_COBBLESTONE));
        this.createSimpleDropTable(BLOCKS.get(ID_BASALT_PAVER));
        this.createSimpleDropTable(BLOCKS.get(ID_BASALT_BRICKS));
        this.createSimpleDropTable(BLOCKS.get(ID_BASALT_CHISELED_BRICKS));

        this.createSimpleDropTable(BLOCKS.get(ID_MARBLE_STONE));
        this.createSimpleDropTable(BLOCKS.get(ID_MARBLE_BRICKS));
        this.createSimpleDropTable(BLOCKS.get("peridot_block"));
        this.blockLootTables.put(BLOCKS.get("peridot_ore"), this.getSilkTouchOreTable(BLOCKS.get("peridot_ore"), ITEMS.get("peridot")));
        this.blockLootTables.put(BLOCKS.get(deepslate("peridot_ore")), this.getSilkTouchOreTable(BLOCKS.get(deepslate("peridot_ore")), ITEMS.get("peridot")));
    }

    protected void createCropTable(String id) {
        blockLootTables.put(BLOCKS.get(id), getCropTable(BLOCKS.get(id), ITEMS.get(id), ITEMS.get(seeds(id)), AGE_0_7, 7));
    }

    protected void createSilkDropTable(String id, Block block, Item lootItem) {
        this.blockLootTables.put(BLOCKS.get(id), getSilkDropTable(id, block, lootItem));
    }

    protected LootTable.Builder getSilkDropTable(String id, Block block, Item lootItem) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(id)
                .setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(LootItem.lootTableItem(block)
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))), LootItem.lootTableItem(lootItem)
                        .apply(ApplyExplosionDecay.explosionDecay())));

        return LootTable.lootTable().withPool(builder);
    }
}
