package org.bukkit.inventory.meta;

import java.util.List;

public interface BookMeta extends ItemMeta {
    String getTitle();

    // Between 0->16 characters
    boolean setTitle(String title);

    String getAuthor();

    void setAuthor(String author);

    String getPage(int page);

    boolean setPage(int page, String data);

    List<String> getPages();

    int getPageCount();
}
