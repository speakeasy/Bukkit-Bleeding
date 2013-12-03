package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.DirtType;

public class Dirt extends MaterialData {
    public Dirt() {
        super(Material.DIRT);
    }

    public Dirt(DirtType type) {
        this();
        setType(type);
    }

    /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Dirt(final int type) {
       super(type);
   }

   public Dirt(final Material type) {
       super(type);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Dirt(final int type, final byte data) {
       super(type, data);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Dirt(final Material type, final byte data) {
       super(type, data);
   }

   /**
    * Gets the current type of this Dirt
    *
    * @return DirtType of this Dirt
    */
   public DirtType getType() {
       switch (getData()) {
           case 0x0:
               return DirtType.DIRT;
           case 0x1:
               return DirtType.GRASSLESS;
           case 0x2:
               return DirtType.PODZOL;
           default:
               return null;
       }
   }

   /**
    * Sets the type of this Dirt
    *
    * @param type New type of this Dirt
    */
   public void setType(DirtType type) {
       switch (type) {
           case DIRT:
               setData((byte) 0x0);
               return;
           case GRASSLESS:
               setData((byte) 0x1);
               return;
           case PODZOL:
               setData((byte) 0x2);
               return;
       }
   }

   @Override
   public String toString() {
       return getType() + " " + super.toString();
   }

   @Override
   public Dirt clone() {
       return (Dirt) super.clone();
   }
}
