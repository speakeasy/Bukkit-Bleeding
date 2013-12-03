package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TallPlantSpecies;

public class TallPlant extends MaterialData {
    public TallPlant() {
        super(Material.DOUBLE_PLANT);
    }

    public TallPlant(TallPlantSpecies species) {
        this();
        setSpecies(species);
    }

    /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public TallPlant(final int type) {
       super(type);
   }

   public TallPlant(final Material type) {
       super(type);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public TallPlant(final int type, final byte data) {
       super(type, data);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public TallPlant(final Material type, final byte data) {
       super(type, data);
   }

   /**
    * Gets the current species of this flower
    *
    * @return FlowerSpecies of this flower
    */
   public TallPlantSpecies getSpecies() {
       byte data = getData();

       if ((data & 0x8) != 0) {
           return null;
       }

       switch (data) {
           case 0x0:
               return TallPlantSpecies.SUNFLOWER;
           case 0x1:
               return TallPlantSpecies.LILAC;
           case 0x2:
               return TallPlantSpecies.DOUBLE_TALLGRASS;
           case 0x3:
               return TallPlantSpecies.LARGE_FERN;
           case 0x4:
               return TallPlantSpecies.ROSE_BUSH;
           case 0x5:
               return TallPlantSpecies.PEONY;
           default:
               return null;
       }
   }

   /**
    * Sets the species of this flower
    *
    * @param species New species of this flower
    */
   public void setSpecies(TallPlantSpecies species) {
       switch (species) {
           case SUNFLOWER:
               setData((byte) 0x0);
               return;
           case LILAC:
               setData((byte) 0x1);
               return;
           case DOUBLE_TALLGRASS:
               setData((byte) 0x2);
               return;
           case LARGE_FERN:
               setData((byte) 0x3);
               return;
           case ROSE_BUSH:
               setData((byte) 0x4);
               return;
           case PEONY:
               setData((byte) 0x5);
               return;
       }
   }

   @Override
   public String toString() {
       return getSpecies() + " " + super.toString();
   }

   @Override
   public TallPlant clone() {
       return (TallPlant) super.clone();
   }
}
