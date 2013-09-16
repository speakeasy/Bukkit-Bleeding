package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.SkullType;

public class SkullItem extends MaterialData {
    public SkullItem() {
        super(Material.SKULL_ITEM);
    }

    public SkullItem(SkullType type) {
        this();
        setSkullType(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SkullItem(final int type) {
        super(type);
    }

    public SkullItem(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SkullItem(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SkullItem(final Material type, final byte data) {
        super(type, data);
    }

    public SkullType getSkullType() {
        switch (getData()) {
            case 0x0:
            default:
                return SkullType.SKELETON;

            case 0x1:
                return SkullType.WITHER;

            case 0x2:
                return SkullType.ZOMBIE;

            case 0x3:
                return SkullType.PLAYER;

            case 0x4:
                return SkullType.CREEPER;
        }
    }

    public void setSkullType(SkullType type) {
        switch (type) {
            case SKELETON:
            default:
                setData((byte) 0x0);
                break;

            case WITHER:
                setData((byte) 0x1);
                break;

            case ZOMBIE:
                setData((byte) 0x2);
                break;

            case PLAYER:
                setData((byte) 0x3);
                break;

            case CREEPER:
                setData((byte) 0x4);
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " of type " + getSkullType();
    }

    @Override
    public SkullItem clone() {
        return (SkullItem) super.clone();
    }
}
