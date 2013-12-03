package org.bukkit.material;

import org.bukkit.FlowerSpecies;
import org.bukkit.Material;

public class Flower extends MaterialData {
    public Flower() {
        super(Material.RED_ROSE);
    }

    public Flower(FlowerSpecies species) {
        this();
        setSpecies(species);
    }

    /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Flower(final int type) {
       super(type);
   }

   public Flower(final Material type) {
       super(type);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Flower(final int type, final byte data) {
       super(type, data);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Flower(final Material type, final byte data) {
       super(type, data);
   }

   /**
    * Gets the current species of this flower
    *
    * @return FlowerSpecies of this flower
    */
   public FlowerSpecies getSpecies() {
       switch (getData()) {
           case 0x0:
               return FlowerSpecies.POPPY;
           case 0x1:
               return FlowerSpecies.BLUE_ORCHID;
           case 0x2:
               return FlowerSpecies.ALLIUM;
           case 0x3:
               return FlowerSpecies.AZURE_BLUET;
           case 0x4:
               return FlowerSpecies.RED_TULIP;
           case 0x5:
               return FlowerSpecies.ORANGE_TULIP;
           case 0x6:
               return FlowerSpecies.WHITE_TULIP;
           case 0x7:
               return FlowerSpecies.PINK_TULIP;
           case 0x8:
               return FlowerSpecies.OXEYE_DAISY;
           default:
               return null;
       }
   }

   /**
    * Sets the species of this flower
    *
    * @param species New species of this flower
    */
   public void setSpecies(FlowerSpecies species) {
       switch (species) {
           case POPPY:
               setData((byte) 0x0);
               return;
           case BLUE_ORCHID:
               setData((byte) 0x1);
               return;
           case ALLIUM:
               setData((byte) 0x2);
               return;
           case AZURE_BLUET:
               setData((byte) 0x3);
               return;
           case RED_TULIP:
               setData((byte) 0x4);
               return;
           case ORANGE_TULIP:
               setData((byte) 0x5);
               return;
           case WHITE_TULIP:
               setData((byte) 0x6);
               return;
           case PINK_TULIP:
               setData((byte) 0x7);
               return;
           case OXEYE_DAISY:
               setData((byte) 0x8);
               return;
       }
   }

   @Override
   public String toString() {
       return getSpecies() + " " + super.toString();
   }

   @Override
   public Flower clone() {
       return (Flower) super.clone();
   }
}
