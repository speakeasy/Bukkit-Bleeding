package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.SandType;

public class Sand extends MaterialData {
    public Sand() {
        super(Material.SAND);
    }

    public Sand(SandType type) {
        this();
        setType(type);
    }

    /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Sand(final int type) {
       super(type);
   }

   public Sand(final Material type) {
       super(type);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Sand(final int type, final byte data) {
       super(type, data);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public Sand(final Material type, final byte data) {
       super(type, data);
   }

   /**
    * Gets the current type of this sand
    *
    * @return SandType of this sand
    */
   public SandType getType() {
       switch (getData()) {
           case 0x0:
               return SandType.SAND;
           case 0x1:
               return SandType.RED_SAND;
           default:
               return null;
       }
   }

   /**
    * Sets the type of this sand
    *
    * @param type New type of this sand
    */
   public void setType(SandType type) {
       switch (type) {
           case SAND:
               setData((byte) 0x0);
               return;
           case RED_SAND:
               setData((byte) 0x1);
               return;
       }
   }

   @Override
   public String toString() {
       return getType() + " " + super.toString();
   }

   @Override
   public Sand clone() {
       return (Sand) super.clone();
   }
}
