package org.bukkit;

import java.util.List;
import org.bukkit.entity.LivingEntity;

public interface Village {

    public int getAge();

    public int setAge();

    public boolean isAbandoned();

    public void abandon();

    public int getPopulation();

    public Location getCentre();

    public void setCentre(Location centre);

    public List<VillageDoor> getDoors();

    public void addDoor(VillageDoor door);

    public List<LivingEntity> getAgressors();
}
