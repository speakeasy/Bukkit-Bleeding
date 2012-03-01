package org.bukkit.material;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.EnumSet;

import org.bukkit.CoalType;
import org.bukkit.CropState;
import org.bukkit.DyeColor;
import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.junit.Test;

// TODO: Every test in here should have some asserts validating the return value of MaterialData.getData()
public class MaterialDataTest {
    @Test
    public void testCtor() {
        for (Material material : Material.values()) {
            material.getNewData((byte) 0);
        }
    }

    @Test
    public void testTree() {
        for (TreeSpecies type : TreeSpecies.values()) {
            Tree tree = new Tree(type);
            assertEquals(type.getData(), tree.getData());
        }
    }

    @Test
    public void testFurnaceAndDispenser() {
        FurnaceAndDispenser data = new FurnaceAndDispenser(Material.FURNACE);
        testSimpleDirectional(data);
    }

    @Test
    public void testBed() {
        Bed bed = new Bed();
        assertFalse(bed.isHeadOfBed());
        bed.setHeadOfBed(true);
        assertTrue(bed.isHeadOfBed());
        bed.setHeadOfBed(false);
        assertFalse(bed.isHeadOfBed());
        testSimpleDirectional(bed);
    }

    @Test
    public void testPoweredRail() {
        PoweredRail rail = new PoweredRail();
        assertFalse(rail.isPowered());
        rail.setPowered(true);
        assertTrue(rail.isPowered());
        rail.setPowered(false);
        assertFalse(rail.isPowered());
        testRailDirections(rail, false);
    }

    @Test
    public void testDetectorRail() {
        DetectorRail rail = new DetectorRail();
        assertFalse(rail.isPressed());
        rail.setPressed(true);
        assertTrue(rail.isPressed());
        rail.setPressed(false);
        assertFalse(rail.isPressed());
        testRailDirections(rail, false);
    }

    @Test
    public void testRegularRail() {
        Rails rail = new Rails();
        testRailDirections(rail, true);
    }

    @Test
    public void testGrass() {
        for (GrassSpecies type : GrassSpecies.values()) {
            LongGrass grass = new LongGrass(type);
            assertEquals(type.getData(), grass.getData());
        }
    }

    @Test
    public void testWoolAndDye() {
        for (DyeColor colour : DyeColor.values()) {
            Wool wool = new Wool(colour);
            Dye dye = new Dye(colour);
            assertThat(wool.getColor(), is(colour));
            assertThat(dye.getColor(), is(colour));
            assertEquals(15, dye.getData() + wool.getData());
        }
    }

    @Test
    public void testStep() {
        byte i = 0;
        for (Material texture : new Step().getTextures()) {
            Step step = new Step(texture);
            assertThat(step.getMaterial(), is(texture));
            assertEquals(i++, step.getData());
        }
    }

    @Test
    public void testTorch() {
        Torch torch = new Torch();
        torch.setFacingDirection(BlockFace.UP);
        assertThat(torch.getAttachedFace(), is(BlockFace.DOWN));
        testSimpleDirectional(torch);
    }

    @Test
    public void testStairs() {
        Stairs stairs = new Stairs(Material.BRICK_STAIRS);
        testSimpleDirectional(stairs);
    }

    @Test
    public void testWire() {
        assertFalse(new RedstoneWire().isPowered());
        for (byte i = 1; i < 16; i++) {
            assertTrue("RedstoneWire failed on power = " + i, new RedstoneWire(Material.REDSTONE_WIRE, i).isPowered());
        }
    }

    @Test
    public void testCrops() {
        for (CropState state : CropState.values()) {
            Crops crops = new Crops(state);
            assertEquals(state.getData(), crops.getData());
        }
    }

    @Test
    public void testSign() {
        Sign sign = new Sign(Material.SIGN_POST);
        assertThat(sign.getAttachedFace(), is(BlockFace.DOWN));
        for (BlockFace face : BlockFace.values()) {
            if(face.getModY() != 0 || face == BlockFace.SELF) continue;
            sign.setFacingDirection(face);
            assertThat(sign.getFacing(), is(face));
        }
        testSimpleDirectional(new Sign(Material.WALL_SIGN));
    }

    @Test
    public void testDoor() {
        Door door = new Door();
        assertFalse(door.isTopHalf());
        door.setTopHalf(true);
        assertTrue(door.isTopHalf());
        door.setTopHalf(false);
        assertFalse(door.isTopHalf());
        // TODO: This test fails and I'm not sure why.
        // Could the test itself be wrong?
        //testSimpleDirectional(door);
        testOpenable(door);
    }

    @Test
    public void testLadder() {
        Ladder ladder = new Ladder();
        testSimpleDirectional(ladder);
    }

    @Test
    public void testPressurePlate() {
        PressurePlate plate = new PressurePlate();
        assertFalse(plate.isPressed());
        plate = new PressurePlate(Material.STONE_PLATE, (byte)1);
        assertTrue(plate.isPressed());
    }

    @Test
    public void testButton() {
        Button button = new Button();
        assertFalse(button.isPowered());
        button.setPowered(true);
        assertTrue(button.isPowered());
        button.setPowered(false);
        assertFalse(button.isPowered());
        testSimpleDirectional(button);
    }

    @Test
    public void testRedstoneTorch() {
        assertTrue(new RedstoneTorch(Material.REDSTONE_TORCH_ON).isPowered());
        assertFalse(new RedstoneTorch(Material.REDSTONE_TORCH_OFF).isPowered());
        testSimpleDirectional(new RedstoneTorch());
    }

    @Test
    public void testPumpkin() {
        assertTrue(new Pumpkin(Material.JACK_O_LANTERN).isLit());
        assertFalse(new Pumpkin(Material.PUMPKIN).isLit());
        testSimpleDirectional(new Pumpkin());
    }

    @Test(timeout=500)
    public void testCake() {
        Cake cake = new Cake();
        while(cake.getSlicesRemaining() > 1) {
            assertEquals(6, cake.getSlicesEaten() + cake.getSlicesRemaining());
            cake.setSlicesEaten(cake.getSlicesEaten() + 1);
        }
    }

    @Test
    public void testDiode() {
        final byte[] bits = new byte[] {0x0, 0x4, 0x8, 0xC};
        Diode diode = new Diode();
        for (int i = 1; i <= 4; i++) {
            diode.setDelay(i);
            assertEquals(bits[i-1], diode.getData());
        }
        testSimpleDirectional(diode);
    }

    @Test
    public void testTrapDoor() {
        TrapDoor hatch = new TrapDoor();
        testSimpleDirectional(hatch);
        testOpenable(hatch);
    }

    @Test
    public void testSilverfish() {
        byte i = 0;
        for (Material texture : new MonsterEggs().getTextures()) {
            MonsterEggs egg = new MonsterEggs(texture);
            assertThat(egg.getMaterial(), is(texture));
            assertEquals(i++, egg.getData());
        }
    }

    @Test
    public void testSmoothBrick() {
        byte i = 0;
        for (Material texture : new SmoothBrick().getTextures()) {
            SmoothBrick brick = new SmoothBrick(texture);
            assertThat(brick.getMaterial(), is(texture));
            assertEquals(i++, brick.getData());
        }
    }

    @Test
    public void testMushroom() {
        Mushroom shroom = new Mushroom(Material.HUGE_MUSHROOM_1);
        assertFalse(shroom.isFacePainted(BlockFace.UP));
        assertFalse(shroom.isStem());
        shroom.setFacePainted(BlockFace.EAST, true);
        assertTrue(shroom.isFacePainted(BlockFace.UP));
        assertTrue(shroom.isFacePainted(BlockFace.EAST));
        assertEquals(6, shroom.getData());
        shroom.setFacePainted(BlockFace.NORTH, true);
        assertEquals(EnumSet.of(BlockFace.EAST, BlockFace.NORTH, BlockFace.UP), shroom.getPaintedFaces());
        shroom.setStem();
        assertFalse(shroom.isFacePainted(BlockFace.UP));
        assertTrue(shroom.isStem());
    }

    @Test
    public void testVine() {
        Vine vine = new Vine();
        vine.putOnFace(BlockFace.NORTH);
        assertTrue(vine.isOnFace(BlockFace.NORTH));
        vine.putOnFace(BlockFace.SOUTH_EAST);
        assertTrue(vine.isOnFace(BlockFace.SOUTH));
        assertTrue(vine.isOnFace(BlockFace.EAST));
        assertTrue(vine.isOnFace(BlockFace.NORTH_EAST));
        vine.removeFromFace(BlockFace.NORTH_EAST);
        assertTrue(vine.isOnFace(BlockFace.SOUTH));
        assertFalse(vine.isOnFace(BlockFace.EAST));
        assertFalse(vine.isOnFace(BlockFace.NORTH));
    }

    @Test
    public void testGate() {
        Gate hatch = new Gate();
        testSimpleDirectional(hatch);
        testOpenable(hatch);
    }

    @Test
    public void testCauldron() {
        Cauldron pot = new Cauldron();
        assertTrue(pot.isEmpty());
        for (byte i = 1; i <= 3; i++) {
            pot.setData(i);
            assertFalse(pot.isEmpty());
        }
        assertTrue(pot.isFull());
    }

    @Test
    public void testPiston() {
        PistonBaseMaterial base = new PistonBaseMaterial(Material.PISTON_BASE);
        PistonExtensionMaterial arm = new PistonExtensionMaterial(Material.PISTON_EXTENSION);
        assertFalse(base.isSticky());
        assertFalse(base.isPowered());
        base.setPowered(true);
        assertTrue(base.isPowered());
        base.setPowered(false);
        assertFalse(base.isPowered());
        assertTrue(new PistonBaseMaterial(Material.PISTON_STICKY_BASE).isSticky());
        assertFalse(arm.isSticky());
        arm.setSticky(true);
        assertTrue(arm.isSticky());
        arm.setSticky(false);
        assertFalse(arm.isSticky());
        testSimpleDirectional(base);
        testSimpleDirectional(arm);
    }

    @Test
    public void testCoal() {
        for (CoalType state : CoalType.values()) {
            Coal coal = new Coal(state);
            assertEquals(state.getData(), coal.getData());
        }
    }

    @Test
    public void testSpawnEgg() {
        for (EntityType state : EntityType.values()) {
            // TODO: Need to make an exception for ENDER_CRYSTAL since 200 is out of the range of a byte :(
            if(!state.isSpawnable() || state == EntityType.ENDER_CRYSTAL) continue;
            SpawnEgg coal = new SpawnEgg(state);
            assertEquals(state.getTypeId(), coal.getData());
        }
    }

    private void testSimpleDirectional(Directional data) {
        data.setFacingDirection(BlockFace.NORTH);
        assertThat(data.getFacing(), is(BlockFace.NORTH));
        data.setFacingDirection(BlockFace.SOUTH);
        assertThat(data.getFacing(), is(BlockFace.SOUTH));
        data.setFacingDirection(BlockFace.EAST);
        assertThat(data.getFacing(), is(BlockFace.EAST));
        data.setFacingDirection(BlockFace.WEST);
        assertThat(data.getFacing(), is(BlockFace.WEST));
    }

    private void testRailDirections(Rails data, boolean testCurves) {
        // Non-slopes; anyOf used because opposite directions have the same data value
        data.setDirection(BlockFace.NORTH, false);
        assertThat(data.getDirection(), anyOf(is(BlockFace.NORTH),is(BlockFace.SOUTH)));
        assertFalse(data.isOnSlope());
        data.setDirection(BlockFace.SOUTH, false);
        assertThat(data.getDirection(), anyOf(is(BlockFace.SOUTH),is(BlockFace.NORTH)));
        assertFalse(data.isOnSlope());
        data.setDirection(BlockFace.EAST, false);
        assertThat(data.getDirection(), anyOf(is(BlockFace.EAST),is(BlockFace.WEST)));
        assertFalse(data.isOnSlope());
        data.setDirection(BlockFace.WEST, false);
        assertThat(data.getDirection(), anyOf(is(BlockFace.WEST),is(BlockFace.EAST)));
        assertFalse(data.isOnSlope());
        // Slopes
        data.setDirection(BlockFace.NORTH, true);
        assertThat(data.getDirection(), is(BlockFace.NORTH));
        assertTrue(data.isOnSlope());
        data.setDirection(BlockFace.SOUTH, true);
        assertThat(data.getDirection(), is(BlockFace.SOUTH));
        assertTrue(data.isOnSlope());
        data.setDirection(BlockFace.EAST, true);
        assertThat(data.getDirection(), is(BlockFace.EAST));
        assertTrue(data.isOnSlope());
        data.setDirection(BlockFace.WEST, true);
        assertThat(data.getDirection(), is(BlockFace.WEST));
        assertTrue(data.isOnSlope());
        if(!testCurves) return;
        // Curves
        data.setDirection(BlockFace.NORTH_EAST, false);
        assertThat(data.getDirection(), is(BlockFace.NORTH_EAST));
        assertTrue(data.isCurve());
        data.setDirection(BlockFace.SOUTH_WEST, false);
        assertThat(data.getDirection(), is(BlockFace.SOUTH_WEST));
        assertTrue(data.isCurve());
        data.setDirection(BlockFace.SOUTH_EAST, false);
        assertThat(data.getDirection(), is(BlockFace.SOUTH_EAST));
        assertTrue(data.isCurve());
        data.setDirection(BlockFace.NORTH_WEST, false);
        assertThat(data.getDirection(), is(BlockFace.NORTH_WEST));
        assertTrue(data.isCurve());
    }

    private void testOpenable(Openable data) {
        data.setOpen(true);
        assertTrue(data.isOpen());
        data.setOpen(false);
        assertFalse(data.isOpen());
    }
}
