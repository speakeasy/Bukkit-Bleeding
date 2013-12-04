package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.GoldenAppleType;

public class GoldenApple extends MaterialData {
    public GoldenApple() {
        super(Material.GOLDEN_APPLE);
    }

    public GoldenApple(GoldenAppleType type) {
        this();
        setType(type);
    }

    /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public GoldenApple(final int type) {
       super(type);
   }

   public GoldenApple(final Material type) {
       super(type);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public GoldenApple(final int type, final byte data) {
       super(type, data);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public GoldenApple(final Material type, final byte data) {
       super(type, data);
   }

   /**
    * Gets the current type of this GoldenApple
    *
    * @return GoldenAppleType of this GoldenApple
    */
   public GoldenAppleType getType() {
       switch (getData()) {
           case 0x0:
               return GoldenAppleType.GOLDEN_APPLE;
           case 0x1:
               return GoldenAppleType.ENCHANTED_GOLDEN_APPLE;
           default:
               return null;
       }
   }

   /**
    * Sets the type of this GoldenApple
    *
    * @param type New type of this GoldenApple
    */
   public void setType(GoldenAppleType type) {
       switch (type) {
           case GOLDEN_APPLE:
               setData((byte) 0x0);
               return;
           case ENCHANTED_GOLDEN_APPLE:
               setData((byte) 0x1);
               return;
       }
   }

   @Override
   public String toString() {
       return getType() + " " + super.toString();
   }

   @Override
   public GoldenApple clone() {
       return (GoldenApple) super.clone();
   }
}
