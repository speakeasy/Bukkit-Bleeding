package org.bukkit.material;

import org.bukkit.Material;

public class Cake extends MaterialData {
    private static final byte MAX_SLICES = 6;

    public Cake() {
        super(Material.CAKE_BLOCK);
    }

    public Cake(int type) {
        super(type);
    }

    public Cake(Material type) {
        super(type);
    }

    public Cake(int type, byte data) {
        super(type, data);
    }

    public Cake(Material type, byte data) {
        super(type, data);
    }

    /**
     * Gets the number of slices eaten from this cake
     *
     * @return The number of slices eaten
     */
    public int getSlicesEaten() {
        return getData();
    }

    /**
     * Gets the number of slices remaining on this cake
     *
     * @return The number of slices remaining
     */
    public int getSlicesRemaining() {
        return MAX_SLICES - getData();
    }

    /**
     * Sets the number of slices eaten from this cake
     *
     * @param n The number of slices eaten
     */
    public void setSlicesEaten(int n) {
        if (n < MAX_SLICES) {
            setData((byte) n);
        } // TODO: else destroy the block? Probably not possible though
    }

    /**
     * Sets the number of slices remaining on this cake
     *
     * @param n The number of slices remaining
     */
    public void setSlicesRemaining(int n) {
        if (n > MAX_SLICES) {
            n = MAX_SLICES;
        }
        setData((byte) (MAX_SLICES - n));
    }

    @Override
    public String toString() {
        return super.toString() + " " + getSlicesEaten() + "/" + getSlicesRemaining() + " slices eaten/remaining";
    }

    @Override
    public Cake clone() {
        return (Cake) super.clone();
    }
}
