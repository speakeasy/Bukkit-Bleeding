package org.bukkit.bans;

import java.util.Date;

public interface Ban {
    String getName();

    Date getCreated();

    String getSource();

    Date getExpires();

    String getReason();
}
