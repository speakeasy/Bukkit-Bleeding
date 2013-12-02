package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.FishSpecies;

public class CookedFish extends MaterialData {
    public CookedFish() {
        super(Material.COOKED_FISH);
    }

    public CookedFish(FishSpecies species) {
        this();
        setSpecies(species);
    }

    /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public CookedFish(final int type) {
       super(type);
   }

   public CookedFish(final Material type) {
       super(type);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public CookedFish(final int type, final byte data) {
       super(type, data);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public CookedFish(final Material type, final byte data) {
       super(type, data);
   }

   /**
    * Gets the current species of this CookedFish
    *
    * @return FishSpecies of this CookedFish
    */
   public FishSpecies getSpecies() {
       switch (getData()) {
           case 0x0:
               return FishSpecies.GENERIC;
           case 0x1:
               return FishSpecies.SALMON;
           default:
               return null;
       }
   }

   /**
    * Sets the species of this CookedFish
    *
    * @param species New species of this CookedFish
    */
   public void setSpecies(FishSpecies species) {
       switch (species) {
           case GENERIC:
               setData((byte) 0x0);
               return;
           case SALMON:
               setData((byte) 0x1);
               return;
       }
   }

   @Override
   public String toString() {
       return getSpecies() + " " + super.toString();
   }

   @Override
   public CookedFish clone() {
       return (CookedFish) super.clone();
   }
}
