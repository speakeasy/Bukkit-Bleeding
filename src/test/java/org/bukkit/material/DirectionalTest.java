package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

// @RunWith(Parameterized.class)
public class DirectionalTest {
    static final BlockFace[] nsewArray = new BlockFace[] {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
    static final List<DirectionTest> testList = new ArrayList<DirectionTest>();

    static {
        addTest(new Bed());
        addTest(new Button());
        addTest(new Chest());
        addTest(new CocoaPlant());
        addTest(new Diode());
        addTest(new Dispenser());
        addTest(new Door());
        addTest(new EnderChest());
        addTest(new Furnace());
        addTest(new Gate());
        addTest(new Ladder());
        addTest(new Lever());
        addTest(new PistonBaseMaterial(Material.PISTON_BASE));
        addTest(new PistonExtensionMaterial(Material.PISTON_EXTENSION));
        addTest(new Pumpkin());
        addTest(new RedstoneTorch());
        addTest(new Sign(Material.SIGN_POST));
        addTest(new Sign(Material.SIGN));
        addTest(new Skull());
        addTest(new Stairs(Material.WOOD));
        addTest(new Torch());
        addTest(new TrapDoor());
        addTest(new TripwireHook());

    }

    static void addTest(Directional object) {
        addTest(object, nsewArray);
    }

    static void addTest(Directional object, BlockFace[] faces) {
        addTest(new DirectionTest(object, faces));
    }

    static void addTest(DirectionTest value) {
        testList.add(value);
    }

    static class DirectionTest {
        // @Parameter(1)
        final String testName;
        final Directional object;
        final BlockFace[] faces;

        DirectionTest(final Directional object, final BlockFace[] faces) {
            this.testName = object.toString();
            this.object = object;
            this.faces = faces;
        }

        void runTest() {
            for (BlockFace setFace : faces) {
                object.setFacingDirection(setFace);
                Assert.assertEquals("[+" + testid + "]" + testName + " did not have correct facing values", setFace, object.getFacing());
            }
        }
    }

    static int testid = 0;

    @Test
    public void runTest() {
        for (DirectionTest test : testList) {
            test.runTest();
            testid++;
        }
    }

    /*@Parameters(name="[{index}]:{1}")
    public static List<DirectionTest> data() {
        return testList;
    }

    @Parameter(0)
    private DirectionTest currentTest;

    @Test
    public void runTest() {
        currentTest.runTest();
    }*/
}
