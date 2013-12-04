package org.bukkit;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * All supported color values for dyes and cloth
 */
public enum DyeColor {

    /**
     * Represents white dye
     */
    WHITE(Color.WHITE, Color.fromRGB(0xF0F0F0)),
    /**
     * Represents orange dye
     */
    ORANGE(Color.fromRGB(0xD87F33), Color.fromRGB(0xEB8844)),
    /**
     * Represents magenta dye
     */
    MAGENTA(Color.fromRGB(0xB24CD8), Color.fromRGB(0xC354CD)),
    /**
     * Represents light blue dye
     */
    LIGHT_BLUE(Color.fromRGB(0x6699D8), Color.fromRGB(0x6689D3)),
    /**
     * Represents yellow dye
     */
    YELLOW(Color.fromRGB(0xE5E533), Color.fromRGB(0xDECF2A)),
    /**
     * Represents lime dye
     */
    LIME(Color.fromRGB(0x7FCC19), Color.fromRGB(0x41CD34)),
    /**
     * Represents pink dye
     */
    PINK(Color.fromRGB(0xF27FA5), Color.fromRGB(0xD88198)),
    /**
     * Represents gray dye
     */
    GRAY(Color.fromRGB(0x4C4C4C), Color.fromRGB(0x434343)),
    /**
     * Represents silver dye
     */
    SILVER(Color.fromRGB(0x999999), Color.fromRGB(0xABABAB)),
    /**
     * Represents cyan dye
     */
    CYAN(Color.fromRGB(0x4C7F99), Color.fromRGB(0x287697)),
    /**
     * Represents purple dye
     */
    PURPLE(Color.fromRGB(0x7F3FB2), Color.fromRGB(0x7B2FBE)),
    /**
     * Represents blue dye
     */
    BLUE(Color.fromRGB(0x334CB2), Color.fromRGB(0x253192)),
    /**
     * Represents brown dye
     */
    BROWN(Color.fromRGB(0x664C33), Color.fromRGB(0x51301A)),
    /**
     * Represents green dye
     */
    GREEN(Color.fromRGB(0x667F33), Color.fromRGB(0x3B511A)),
    /**
     * Represents red dye
     */
    RED(Color.fromRGB(0x993333), Color.fromRGB(0xB3312C)),
    /**
     * Represents black dye
     */
    BLACK(Color.fromRGB(0x191919), Color.fromRGB(0x1E1B1B));

    private final Color color;
    private final Color firework;
    private final static Map<Color, DyeColor> BY_COLOR;
    private final static Map<Color, DyeColor> BY_FIREWORK;

    private DyeColor(Color color, Color firework) {
        this.color = color;
        this.firework = firework;
    }

    /**
     * Gets the color that this dye represents
     *
     * @return The {@link Color} that this dye represents
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the firework color that this dye represents
     *
     * @return The {@link Color} that this dye represents
     */
    public Color getFireworkColor() {
        return firework;
    }

    /**
     * Gets the DyeColor with the given color value
     *
     * @param color Color value to get the dye by
     * @return The {@link DyeColor} representing the given value, or null if it doesn't exist
     */
    public static DyeColor getByColor(final Color color) {
        return BY_COLOR.get(color);
    }

    /**
     * Gets the DyeColor with the given firework color value
     *
     * @param color Color value to get dye by
     * @return The {@link DyeColor} representing the given value, or null if it doesn't exist
     */
    public static DyeColor getByFireworkColor(final Color color) {
        return BY_FIREWORK.get(color);
    }

    static {
        ImmutableMap.Builder<Color, DyeColor> byColor = ImmutableMap.builder();
        ImmutableMap.Builder<Color, DyeColor> byFirework = ImmutableMap.builder();

        for (DyeColor color : values()) {
            byColor.put(color.getColor(), color);
            byFirework.put(color.getFireworkColor(), color);
        }

        BY_COLOR = byColor.build();
        BY_FIREWORK = byFirework.build();
    }
}
