package org.bukkit;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.material.Colorable;
import org.bukkit.material.Dye;
import org.bukkit.material.Wool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DyeColorTest {

    @Parameters(name= "{index}: {0}")
    public static List<Object[]> data() {
        List<Object[]> list = new ArrayList<Object[]>();
        for (DyeColor dye : DyeColor.values()) {
            list.add(new Object[] {dye});
        }
        return list;
    }

    @Parameter public DyeColor dye;

    @Test
    public void getDyeDyeColor() {
        testColorable(new Dye(this.dye));
    }

    @Test
    public void getWoolDyeColor() {
        testColorable(new Wool(this.dye));
    }

    private void testColorable(final Colorable colorable) {
        assertThat(colorable.getColor(), is(this.dye));
    }
}
