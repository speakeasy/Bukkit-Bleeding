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

    /**
     * Clears the existing book pages, and sets the book to use the provided pages
     *
     * @param pages A list of pages to set the book to use
     */
    void setPages(List<String> pages);

    /**
     * Clear the existing book pages and add the provided pages
     *
     * @param pages An array of strings, each index being a page
     */
    void setPages(String... pages);

    /**
     * Adds new pages to the end of the book
     *
     * @param pages An array of strings to add
     */
    void addPage(String... pages);

    int getPageCount();
}
