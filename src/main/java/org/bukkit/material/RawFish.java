package org.bukkit.material;

import org.bukkit.FishSpecies;
import org.bukkit.Material;

public class RawFish extends MaterialData {
    public RawFish() {
        super(Material.RAW_FISH);
    }

    public RawFish(FishSpecies species) {
        this();
        setSpecies(species);
    }

    /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public RawFish(final int type) {
       super(type);
   }

   public RawFish(final Material type) {
       super(type);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public RawFish(final int type, final byte data) {
       super(type, data);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public RawFish(final Material type, final byte data) {
       super(type, data);
   }

   /**
    * Gets the current species of this raw fish
    *
    * @return FishSpecies of this fish
    */
   public FishSpecies getSpecies() {
       switch (getData()) {
           case 0x0:
               return FishSpecies.GENERIC;
           case 0x1:
               return FishSpecies.SALMON;
           case 0x2:
               return FishSpecies.CLOWNFISH;
           case 0x3:
               return FishSpecies.PUFFERFISH;
           default:
               return null;
       }
   }

   /**
    * Sets the species of this raw fish
    *
    * @param species New species of this fish
    */
   public void setSpecies(FishSpecies species) {
       switch (species) {
           case GENERIC:
               setData((byte) 0x0);
               return;
           case SALMON:
               setData((byte) 0x1);
               return;
           case CLOWNFISH:
               setData((byte) 0x2);
               return;
           case PUFFERFISH:
               setData((byte) 0x3);
               return;
       }
   }

   @Override
   public String toString() {
       return getSpecies() + " " + super.toString();
   }

   @Override
   public RawFish clone() {
       return (RawFish) super.clone();
   }
}
