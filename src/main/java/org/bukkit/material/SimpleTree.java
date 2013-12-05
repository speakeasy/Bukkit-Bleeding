package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;

/**
 * Simple utility class to handle the two types of tree MaterialData.
 */
public class SimpleTree extends Rotateable {
    public SimpleTree(Material type, TreeSpecies species) {
        this(type);
        setSpecies(species);
    }

    public SimpleTree(Material type, TreeSpecies species, BlockFace dir) {
        this(type);
        setSpecies(species);
        setFacingDirection(dir);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SimpleTree(final int type) {
        super(type);
    }

    public SimpleTree(final Material type) {
        super(type);
    }

    /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public SimpleTree(final int type, final byte data) {
       super(type, data);
   }

   /**
    *
    * @deprecated Magic value
    */
   @Deprecated
   public SimpleTree(final Material type, final byte data) {
       super(type, data);
   }

   /**
    * Gets the current species of this log
    *
    * @return TreeSpecies of this log
    */
   public TreeSpecies getSpecies() {
       Material type = getItemType();

       switch (getData() & 0x3) {
           case 0x0:
               return type == Material.LOG ? TreeSpecies.GENERIC : (type == Material.LOG_2 ? TreeSpecies.ACACIA : null);
           case 0x1:
               return type == Material.LOG ? TreeSpecies.REDWOOD : (type == Material.LOG_2 ? TreeSpecies.DARK_OAK : null);
           case 0x2:
               return type == Material.LOG ? TreeSpecies.BIRCH : null ;
           case 0x3:
               return type == Material.LOG ? TreeSpecies.JUNGLE : null;
           default:
               return null;
       }
   }

   /**
    * Sets the species of this log
    *
    * @param species New species of this log
    * @throws IllegalArgumentException if the given {@link TreeSpecies} is not supported by the underlying Material
    */
   public void setSpecies(TreeSpecies species) throws IllegalArgumentException {
       Material type = getItemType();

       if (type == Material.LOG) {
           switch (species) {
               case GENERIC:
                   setData((byte) (getData() & 0xC | 0));
                   return;
               case REDWOOD:
                   setData((byte) (getData() & 0xC | 1));
                   return;
               case BIRCH:
                   setData((byte) (getData() & 0xC | 2));
                   return;
               case JUNGLE:
                   setData((byte) (getData() & 0xC | 3));
                   return;
               default:
                   throw new IllegalArgumentException("TreeSpecies " + species + " is not supported by " + type);
           }
       } else if (type == Material.LOG_2) {
           switch (species) {
               case ACACIA:
                   setData((byte) (getData() & 0xC | 0));
                   return;
               case DARK_OAK:
                   setData((byte) (getData() & 0xC | 1));
                   return;
               default:
                   throw new IllegalArgumentException("TreeSpecies " + species + " is not supported by " + type);
           }
       }
   }

   /**
    * Get direction of the log
    *
    * @return BlockFace.TOP for upright (default), BlockFace.NORTH (east-west), BlockFace.WEST (north-sout), BlockFace.SELF (directionless)
    * @deprecated use getFacing() from {@link Directional} instead
    */
   @Deprecated
   public BlockFace getDirection() {
       return getFacing();
   }

   /**
    * Set direction of the log
    *
    * @param dir - direction of end of log (BlockFace.SELF for no direction)
    * @deprecated use setFacingDirection() from {@link Directional} instead
    */
   @Deprecated
   public void setDirection(BlockFace dir) {
       setFacingDirection(dir);
   }

   @Override
   public String toString() {
       return getSpecies() + " " + getFacing() + " " + super.toString();
   }

   @Override
   public SimpleTree clone() {
       return (SimpleTree) super.clone();
   }
}
