package org.bukkit;

import java.lang.reflect.Constructor;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.bukkit.map.MapView;
import org.bukkit.material.Bed;
import org.bukkit.material.Button;
import org.bukkit.material.Cake;
import org.bukkit.material.Cauldron;
import org.bukkit.material.Chest;
import org.bukkit.material.Coal;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Command;
import org.bukkit.material.Crops;
import org.bukkit.material.DetectorRail;
import org.bukkit.material.Diode;
import org.bukkit.material.Dispenser;
import org.bukkit.material.Door;
import org.bukkit.material.Dye;
import org.bukkit.material.EnderChest;
import org.bukkit.material.FlowerPot;
import org.bukkit.material.Furnace;
import org.bukkit.material.Gate;
import org.bukkit.material.Ladder;
import org.bukkit.material.Lever;
import org.bukkit.material.LongGrass;
import org.bukkit.material.MaterialData;
import org.bukkit.material.MonsterEggs;
import org.bukkit.material.Mushroom;
import org.bukkit.material.PistonBaseMaterial;
import org.bukkit.material.PistonExtensionMaterial;
import org.bukkit.material.PoweredRail;
import org.bukkit.material.PressurePlate;
import org.bukkit.material.Pumpkin;
import org.bukkit.material.Rails;
import org.bukkit.material.RedstoneTorch;
import org.bukkit.material.RedstoneWire;
import org.bukkit.material.Sandstone;
import org.bukkit.material.Sign;
import org.bukkit.material.Skull;
import org.bukkit.material.SmoothBrick;
import org.bukkit.material.SpawnEgg;
import org.bukkit.material.Stairs;
import org.bukkit.material.Step;
import org.bukkit.material.Torch;
import org.bukkit.material.TrapDoor;
import org.bukkit.material.Tree;
import org.bukkit.material.Tripwire;
import org.bukkit.material.TripwireHook;
import org.bukkit.material.Vine;
import org.bukkit.material.WoodenStep;
import org.bukkit.material.Wool;
import org.bukkit.potion.Potion;
import org.bukkit.util.Java15Compat;

import com.google.common.collect.Maps;

/**
 * An enum of all material ids accepted by the official server + client
 */
public enum Material {
    AIR(0, 0, MaterialProperty.TRANSPARENT),
    STONE(1, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    GRASS(2, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    DIRT(3, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    COBBLESTONE(4, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    WOOD(5, Tree.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.OCCLUDING, MaterialProperty.FUEL),
    SAPLING(6, Tree.class, MaterialProperty.TRANSPARENT, MaterialProperty.FUEL),
    BEDROCK(7, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    WATER(8, MaterialData.class),
    STATIONARY_WATER(9, MaterialData.class),
    LAVA(10, MaterialData.class),
    STATIONARY_LAVA(11, MaterialData.class),
    SAND(12, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    GRAVEL(13, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    GOLD_ORE(14, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    IRON_ORE(15, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    COAL_ORE(16, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    LOG(17, Tree.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.OCCLUDING, MaterialProperty.FUEL),
    LEAVES(18, Tree.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE),
    SPONGE(19, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    GLASS(20, MaterialProperty.SOLID),
    LAPIS_ORE(21, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    LAPIS_BLOCK(22, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    DISPENSER(23, Dispenser.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    SANDSTONE(24, Sandstone.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    NOTE_BLOCK(25, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.OCCLUDING, MaterialProperty.FUEL),
    BED_BLOCK(26, Bed.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE),
    POWERED_RAIL(27, PoweredRail.class, MaterialProperty.TRANSPARENT),
    DETECTOR_RAIL(28, DetectorRail.class, MaterialProperty.TRANSPARENT),
    PISTON_STICKY_BASE(29, PistonBaseMaterial.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    WEB(30),
    LONG_GRASS(31, LongGrass.class, MaterialProperty.TRANSPARENT, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE),
    DEAD_BUSH(32, MaterialProperty.TRANSPARENT, MaterialProperty.FLAMMABLE), // should this be burnable??? or maybe it's not even flammable...
    PISTON_BASE(33, PistonBaseMaterial.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    PISTON_EXTENSION(34, PistonExtensionMaterial.class, MaterialProperty.SOLID),
    WOOL(35, Wool.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.OCCLUDING),
    PISTON_MOVING_PIECE(36, MaterialProperty.SOLID),
    YELLOW_FLOWER(37, MaterialProperty.TRANSPARENT),
    RED_ROSE(38, MaterialProperty.TRANSPARENT),
    BROWN_MUSHROOM(39, MaterialProperty.TRANSPARENT),
    RED_MUSHROOM(40, MaterialProperty.TRANSPARENT),
    GOLD_BLOCK(41, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    IRON_BLOCK(42, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    DOUBLE_STEP(43, Step.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    STEP(44, Step.class, MaterialProperty.SOLID),
    BRICK(45, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    TNT(46, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.OCCLUDING),
    BOOKSHELF(47, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.OCCLUDING),
    MOSSY_COBBLESTONE(48, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    OBSIDIAN(49, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    TORCH(50, Torch.class, MaterialProperty.TRANSPARENT),
    FIRE(51, MaterialProperty.TRANSPARENT),
    MOB_SPAWNER(52, MaterialProperty.SOLID),
    WOOD_STAIRS(53, Stairs.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.FUEL),
    CHEST(54, Chest.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.FUEL),
    REDSTONE_WIRE(55, RedstoneWire.class, MaterialProperty.TRANSPARENT),
    DIAMOND_ORE(56, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    DIAMOND_BLOCK(57, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    WORKBENCH(58, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.OCCLUDING, MaterialProperty.FUEL),
    CROPS(59, Crops.class, MaterialProperty.TRANSPARENT),
    SOIL(60, MaterialData.class, MaterialProperty.SOLID),
    FURNACE(61, Furnace.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    BURNING_FURNACE(62, Furnace.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    SIGN_POST(63, 64, Sign.class, MaterialProperty.FLAMMABLE), // should this be transparent???
    WOODEN_DOOR(64, Door.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE),
    LADDER(65, Ladder.class, MaterialProperty.TRANSPARENT),
    RAILS(66, Rails.class, MaterialProperty.TRANSPARENT),
    COBBLESTONE_STAIRS(67, Stairs.class, MaterialProperty.SOLID),
    WALL_SIGN(68, 64, Sign.class, MaterialProperty.FLAMMABLE), // should this be transparent???
    LEVER(69, Lever.class, MaterialProperty.TRANSPARENT),
    STONE_PLATE(70, PressurePlate.class, MaterialProperty.SOLID), // is this really solid???
    IRON_DOOR_BLOCK(71, Door.class, MaterialProperty.SOLID),
    WOOD_PLATE(72, PressurePlate.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.FUEL), // is this really solid???
    REDSTONE_ORE(73, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    GLOWING_REDSTONE_ORE(74, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    REDSTONE_TORCH_OFF(75, RedstoneTorch.class, MaterialProperty.TRANSPARENT),
    REDSTONE_TORCH_ON(76, RedstoneTorch.class, MaterialProperty.TRANSPARENT),
    STONE_BUTTON(77, Button.class, MaterialProperty.TRANSPARENT),
    SNOW(78, MaterialProperty.TRANSPARENT),
    ICE(79, MaterialProperty.SOLID),
    SNOW_BLOCK(80, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    CACTUS(81, MaterialData.class, MaterialProperty.SOLID),
    CLAY(82, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    SUGAR_CANE_BLOCK(83, MaterialData.class, MaterialProperty.TRANSPARENT),
    JUKEBOX(84, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.OCCLUDING, MaterialProperty.FUEL),
    FENCE(85, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.FUEL),
    PUMPKIN(86, Pumpkin.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    NETHERRACK(87, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.OCCLUDING),
    SOUL_SAND(88, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    GLOWSTONE(89, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    PORTAL(90, MaterialProperty.TRANSPARENT),
    JACK_O_LANTERN(91, Pumpkin.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    CAKE_BLOCK(92, 64, Cake.class, MaterialProperty.SOLID),
    DIODE_BLOCK_OFF(93, Diode.class, MaterialProperty.TRANSPARENT),
    DIODE_BLOCK_ON(94, Diode.class, MaterialProperty.TRANSPARENT),
    LOCKED_CHEST(95, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.OCCLUDING),
    TRAP_DOOR(96, TrapDoor.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.FUEL),
    MONSTER_EGGS(97, MonsterEggs.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    SMOOTH_BRICK(98, SmoothBrick.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    HUGE_MUSHROOM_1(99, Mushroom.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.OCCLUDING, MaterialProperty.FUEL),
    HUGE_MUSHROOM_2(100, Mushroom.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.OCCLUDING, MaterialProperty.FUEL),
    IRON_FENCE(101, MaterialProperty.SOLID),
    THIN_GLASS(102, MaterialProperty.SOLID),
    MELON_BLOCK(103, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    PUMPKIN_STEM(104, MaterialData.class, MaterialProperty.TRANSPARENT), // should this be flammable???
    MELON_STEM(105, MaterialData.class, MaterialProperty.TRANSPARENT), // should this be flammable???
    VINE(106, Vine.class, MaterialProperty.TRANSPARENT, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE),
    FENCE_GATE(107, Gate.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE),
    BRICK_STAIRS(108, Stairs.class, MaterialProperty.SOLID),
    SMOOTH_STAIRS(109, Stairs.class, MaterialProperty.SOLID),
    MYCEL(110, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    WATER_LILY(111, MaterialProperty.TRANSPARENT), // shouldn't this be solid??? is it really transparent???
    NETHER_BRICK(112, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    NETHER_FENCE(113, MaterialProperty.SOLID),
    NETHER_BRICK_STAIRS(114, Stairs.class, MaterialProperty.SOLID),
    NETHER_WARTS(115, MaterialData.class, MaterialProperty.TRANSPARENT),
    ENCHANTMENT_TABLE(116, MaterialProperty.SOLID),
    BREWING_STAND(117, MaterialData.class, MaterialProperty.SOLID),
    CAULDRON(118, Cauldron.class, MaterialProperty.SOLID),
    ENDER_PORTAL(119, MaterialProperty.TRANSPARENT),
    ENDER_PORTAL_FRAME(120, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    ENDER_STONE(121, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    DRAGON_EGG(122, MaterialProperty.SOLID),
    REDSTONE_LAMP_OFF(123, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    REDSTONE_LAMP_ON(124, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    WOOD_DOUBLE_STEP(125, WoodenStep.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.OCCLUDING),
    WOOD_STEP(126, WoodenStep.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.FUEL),
    COCOA(127, CocoaPlant.class, MaterialProperty.TRANSPARENT), // shouldn't this be solid??? is it really transparent??? should it be flammable???
    SANDSTONE_STAIRS(128, Stairs.class, MaterialProperty.SOLID),
    EMERALD_ORE(129, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    ENDER_CHEST(130, EnderChest.class, MaterialProperty.SOLID),
    TRIPWIRE_HOOK(131, TripwireHook.class, MaterialProperty.TRANSPARENT),
    TRIPWIRE(132, Tripwire.class, MaterialProperty.TRANSPARENT),
    EMERALD_BLOCK(133, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    SPRUCE_WOOD_STAIRS(134, Stairs.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.FUEL),
    BIRCH_WOOD_STAIRS(135, Stairs.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.FUEL),
    JUNGLE_WOOD_STAIRS(136, Stairs.class, MaterialProperty.SOLID, MaterialProperty.FLAMMABLE, MaterialProperty.BURNABLE, MaterialProperty.FUEL),
    COMMAND(137, Command.class, MaterialProperty.SOLID, MaterialProperty.OCCLUDING),
    BEACON(138, MaterialProperty.SOLID),
    COBBLE_WALL(139, MaterialProperty.SOLID),
    FLOWER_POT(140, FlowerPot.class, MaterialProperty.TRANSPARENT), // shouldn't this be solid??? is it really transparent???
    CARROT(141, MaterialProperty.TRANSPARENT),
    POTATO(142, MaterialProperty.TRANSPARENT),
    WOOD_BUTTON(143, Button.class, MaterialProperty.TRANSPARENT),
    SKULL(144, Skull.class, MaterialProperty.TRANSPARENT), // shouldn't this be solid??? is it really transparent???
    ANVIL(145, MaterialProperty.SOLID),
    // ----- Item Separator -----
    IRON_SPADE(256, 1, 250),
    IRON_PICKAXE(257, 1, 250),
    IRON_AXE(258, 1, 250),
    FLINT_AND_STEEL(259, 1, 64),
    APPLE(260, MaterialProperty.EDIBLE),
    BOW(261, 1, 384),
    ARROW(262),
    COAL(263, Coal.class, MaterialProperty.FUEL),
    DIAMOND(264),
    IRON_INGOT(265),
    GOLD_INGOT(266),
    IRON_SWORD(267, 1, 250),
    WOOD_SWORD(268, 1, 59, MaterialProperty.FUEL),
    WOOD_SPADE(269, 1, 59, MaterialProperty.FUEL),
    WOOD_PICKAXE(270, 1, 59, MaterialProperty.FUEL),
    WOOD_AXE(271, 1, 59, MaterialProperty.FUEL),
    STONE_SWORD(272, 1, 131),
    STONE_SPADE(273, 1, 131),
    STONE_PICKAXE(274, 1, 131),
    STONE_AXE(275, 1, 131),
    DIAMOND_SWORD(276, 1, 1561),
    DIAMOND_SPADE(277, 1, 1561),
    DIAMOND_PICKAXE(278, 1, 1561),
    DIAMOND_AXE(279, 1, 1561),
    STICK(280, MaterialProperty.FUEL),
    BOWL(281),
    MUSHROOM_SOUP(282, 1, MaterialProperty.EDIBLE),
    GOLD_SWORD(283, 1, 32),
    GOLD_SPADE(284, 1, 32),
    GOLD_PICKAXE(285, 1, 32),
    GOLD_AXE(286, 1, 32),
    STRING(287),
    FEATHER(288),
    SULPHUR(289),
    WOOD_HOE(290, 1, 59, MaterialProperty.FUEL),
    STONE_HOE(291, 1, 131),
    IRON_HOE(292, 1, 250),
    DIAMOND_HOE(293, 1, 1561),
    GOLD_HOE(294, 1, 32),
    SEEDS(295),
    WHEAT(296),
    BREAD(297, MaterialProperty.EDIBLE),
    LEATHER_HELMET(298, 1, 55),
    LEATHER_CHESTPLATE(299, 1, 80),
    LEATHER_LEGGINGS(300, 1, 75),
    LEATHER_BOOTS(301, 1, 65),
    CHAINMAIL_HELMET(302, 1, 165),
    CHAINMAIL_CHESTPLATE(303, 1, 240),
    CHAINMAIL_LEGGINGS(304, 1, 225),
    CHAINMAIL_BOOTS(305, 1, 195),
    IRON_HELMET(306, 1, 165),
    IRON_CHESTPLATE(307, 1, 240),
    IRON_LEGGINGS(308, 1, 225),
    IRON_BOOTS(309, 1, 195),
    DIAMOND_HELMET(310, 1, 363),
    DIAMOND_CHESTPLATE(311, 1, 528),
    DIAMOND_LEGGINGS(312, 1, 495),
    DIAMOND_BOOTS(313, 1, 429),
    GOLD_HELMET(314, 1, 77),
    GOLD_CHESTPLATE(315, 1, 112),
    GOLD_LEGGINGS(316, 1, 105),
    GOLD_BOOTS(317, 1, 91),
    FLINT(318),
    PORK(319, MaterialProperty.EDIBLE),
    GRILLED_PORK(320, MaterialProperty.EDIBLE),
    PAINTING(321),
    GOLDEN_APPLE(322, MaterialProperty.EDIBLE),
    SIGN(323, 16),
    WOOD_DOOR(324, 1),
    BUCKET(325, 16),
    WATER_BUCKET(326, 1),
    LAVA_BUCKET(327, 1, MaterialProperty.FUEL),
    MINECART(328, 1),
    SADDLE(329, 1),
    IRON_DOOR(330, 1),
    REDSTONE(331),
    SNOW_BALL(332, 16),
    BOAT(333, 1),
    LEATHER(334),
    MILK_BUCKET(335, 1, MaterialProperty.DRINKABLE),
    CLAY_BRICK(336),
    CLAY_BALL(337),
    SUGAR_CANE(338),
    PAPER(339),
    BOOK(340),
    SLIME_BALL(341),
    STORAGE_MINECART(342, 1),
    POWERED_MINECART(343, 1),
    EGG(344, 16),
    COMPASS(345),
    FISHING_ROD(346, 1, 64),
    WATCH(347),
    GLOWSTONE_DUST(348),
    RAW_FISH(349, MaterialProperty.EDIBLE),
    COOKED_FISH(350, MaterialProperty.EDIBLE),
    INK_SACK(351, Dye.class),
    BONE(352),
    SUGAR(353),
    CAKE(354, 1),
    BED(355, 1),
    DIODE(356),
    COOKIE(357, MaterialProperty.EDIBLE),
    /**
     * @see MapView
     */
    MAP(358, MaterialData.class),
    SHEARS(359, 1, 238),
    MELON(360, MaterialProperty.EDIBLE),
    PUMPKIN_SEEDS(361),
    MELON_SEEDS(362),
    RAW_BEEF(363, MaterialProperty.EDIBLE),
    COOKED_BEEF(364, MaterialProperty.EDIBLE),
    RAW_CHICKEN(365, MaterialProperty.EDIBLE),
    COOKED_CHICKEN(366, MaterialProperty.EDIBLE),
    ROTTEN_FLESH(367, MaterialProperty.EDIBLE),
    ENDER_PEARL(368, 16),
    BLAZE_ROD(369, MaterialProperty.FUEL),
    GHAST_TEAR(370),
    GOLD_NUGGET(371),
    NETHER_STALK(372),
    /**
     * @see Potion
     */
    POTION(373, 1, MaterialData.class, MaterialProperty.DRINKABLE),
    GLASS_BOTTLE(374),
    SPIDER_EYE(375, MaterialProperty.EDIBLE),
    FERMENTED_SPIDER_EYE(376),
    BLAZE_POWDER(377),
    MAGMA_CREAM(378),
    BREWING_STAND_ITEM(379),
    CAULDRON_ITEM(380),
    EYE_OF_ENDER(381),
    SPECKLED_MELON(382),
    MONSTER_EGG(383, 64, SpawnEgg.class),
    EXP_BOTTLE(384, 64),
    FIREBALL(385, 64),
    BOOK_AND_QUILL(386, 1),
    WRITTEN_BOOK(387, 1),
    EMERALD(388, 64),
    ITEM_FRAME(389),
    FLOWER_POT_ITEM(390),
    CARROT_ITEM(391, MaterialProperty.EDIBLE),
    POTATO_ITEM(392, MaterialProperty.EDIBLE),
    BAKED_POTATO(393, MaterialProperty.EDIBLE),
    POISONOUS_POTATO(394, MaterialProperty.EDIBLE),
    EMPTY_MAP(395),
    GOLDEN_CARROT(396, MaterialProperty.EDIBLE),
    SKULL_ITEM(397),
    CARROT_STICK(398, 1, 25),
    NETHER_STAR(399),
    PUMPKIN_PIE(400, MaterialProperty.EDIBLE),
    FIREWORK(401),
    FIREWORK_CHARGE(402),
    ENCHANTED_BOOK(403, 1),
    GOLD_RECORD(2256, 1, MaterialProperty.RECORD),
    GREEN_RECORD(2257, 1, MaterialProperty.RECORD),
    RECORD_3(2258, 1, MaterialProperty.RECORD),
    RECORD_4(2259, 1, MaterialProperty.RECORD),
    RECORD_5(2260, 1, MaterialProperty.RECORD),
    RECORD_6(2261, 1, MaterialProperty.RECORD),
    RECORD_7(2262, 1, MaterialProperty.RECORD),
    RECORD_8(2263, 1, MaterialProperty.RECORD),
    RECORD_9(2264, 1, MaterialProperty.RECORD),
    RECORD_10(2265, 1, MaterialProperty.RECORD),
    RECORD_11(2266, 1, MaterialProperty.RECORD),
    RECORD_12(2267, 1, MaterialProperty.RECORD),
    ;

    private final int id;
    private final Constructor<? extends MaterialData> ctor;
    private static Material[] byId = new Material[383];
    private final static Map<String, Material> BY_NAME = Maps.newHashMap();
    private final Set<MaterialProperty> properties;
    private final int maxStack;
    private final short durability;

    private Material(final int id, final MaterialProperty... props) {
        this(id, 64, props);
    }

    private Material(final int id, final int stack, final MaterialProperty... props) {
        this(id, stack, MaterialData.class, props);
    }

    private Material(final int id, final int stack, final int durability, final MaterialProperty... props) {
        this(id, stack, durability, MaterialData.class, props);
    }

    private Material(final int id, final Class<? extends MaterialData> data, final MaterialProperty... props) {
        this(id, 64, data, props);
    }

    private Material(final int id, final int stack, final Class<? extends MaterialData> data, final MaterialProperty... props) {
        this(id, stack, 0, data, props);
    }

    private Material(final int id, final int stack, final int durability, final Class<? extends MaterialData> data, final MaterialProperty... props) {
        this.id = id;
        this.durability = (short) durability;
        this.maxStack = stack;
        if(id < 256) {
            this.properties = EnumSet.of(MaterialProperty.BLOCK, props);
        } else {
            this.properties = EnumSet.of(MaterialProperty.ITEM, props);
        }
        // try to cache the constructor for this material
        try {
            this.ctor = data.getConstructor(int.class, byte.class);
        } catch (NoSuchMethodException ex) {
            throw new AssertionError(ex);
        } catch (SecurityException ex) {
            throw new AssertionError(ex);
        }
    }

    /**
     * Gets the item ID or block ID of this Material
     *
     * @return ID of this material
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the maximum amount of this material that can be held in a stack
     *
     * @return Maximum stack size for this material
     */
    public int getMaxStackSize() {
        return maxStack;
    }

    /**
     * Gets the maximum durability of this material
     *
     * @return Maximum durability for this material
     */
    public short getMaxDurability() {
        return durability;
    }

    /**
     * Gets the MaterialData class associated with this Material
     *
     * @return MaterialData associated with this Material
     */
    public Class<? extends MaterialData> getData() {
        return ctor.getDeclaringClass();
    }

    /**
     * Constructs a new MaterialData relevant for this Material, with the given
     * initial data
     *
     * @param raw Initial data to construct the MaterialData with
     * @return New MaterialData with the given data
     */
    public MaterialData getNewData(final byte raw) {
        try {
            return ctor.newInstance(id, raw);
        } catch (InstantiationException ex) {
            final Throwable t = ex.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            if (t instanceof Error) {
                throw (Error) t;
            }
            throw new AssertionError(t);
        } catch (Throwable t) {
            throw new AssertionError(t);
        }
    }

    /**
     * Checks if this Material is a placable block
     *
     * @return true if this material is a block
     */
    public boolean isBlock() {
        return id < 256;
    }

    /**
     * Checks if this Material is edible.
     *
     * @return true if this Material is edible.
     */
    public boolean isEdible() {
    	return hasProperty(MaterialProperty.EDIBLE);
    }

    /**
     * Attempts to get the Material with the given ID
     *
     * @param id ID of the material to get
     * @return Material if found, or null
     */
    public static Material getMaterial(final int id) {
        if (byId.length > id) {
            return byId[id];
        } else {
            return null;
        }
    }

    /**
     * Attempts to get the Material with the given name.
     * This is a normal lookup, names must be the precise name they are given
     * in the enum.
     *
     * @param name Name of the material to get
     * @return Material if found, or null
     */
    public static Material getMaterial(final String name) {
        return BY_NAME.get(name);
    }

    /**
     * Attempts to match the Material with the given name.
     * This is a match lookup; names will be converted to uppercase, then stripped
     * of special characters in an attempt to format it like the enum
     *
     * @param name Name of the material to get
     * @return Material if found, or null
     */
    public static Material matchMaterial(final String name) {
        Validate.notNull(name, "Name cannot be null");

        Material result = null;

        try {
            result = getMaterial(Integer.parseInt(name));
        } catch (NumberFormatException ex) {}

        if (result == null) {
            String filtered = name.toUpperCase();

            filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
            result = BY_NAME.get(filtered);
        }

        return result;
    }

    static {
        for (Material material : values()) {
            if (byId.length > material.id) {
                byId[material.id] = material;
            } else {
                byId = Java15Compat.Arrays_copyOfRange(byId, 0, material.id + 2);
                byId[material.id] = material;
            }
            BY_NAME.put(material.name(), material);
        }
    }

    /**
     * @return True if this material represents a playable music disk.
     */
    public boolean isRecord() {
        return hasProperty(MaterialProperty.RECORD);
    }

    /**
     * Check if the material is a block and solid (cannot be passed through by a player)
     *
     * @return True if this material is a block and solid
     */
    public boolean isSolid() {
    	return hasProperty(MaterialProperty.SOLID);
    }

    /**
     * Check if the material is a block and does not block any light
     *
     * @return True if this material is a block and does not block any light
     */
    public boolean isTransparent() {
    	return hasProperty(MaterialProperty.TRANSPARENT);
    }

    /**
     * Check if the material is a block and can catch fire
     *
     * @return True if this material is a block and can catch fire
     */
    public boolean isFlammable() {
    	return hasProperty(MaterialProperty.FLAMMABLE);
    }

    /**
     * Check if the material is a block and can burn away
     *
     * @return True if this material is a block and can burn away
     */
    public boolean isBurnable() {
        return hasProperty(MaterialProperty.BURNABLE);
    }

    /**
     * Check if the material is a block and completely blocks vision
     *
     * @return True if this material is a block and completely blocks vision
     */
    public boolean isOccluding() {
        return hasProperty(MaterialProperty.OCCLUDING);
    }

    private boolean hasProperty(MaterialProperty prop) {
        return properties.contains(prop);
    }
}
