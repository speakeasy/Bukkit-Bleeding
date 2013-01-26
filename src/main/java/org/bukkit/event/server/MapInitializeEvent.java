package org.bukkit.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.map.MapView;

/**
 * Called when a map is initialized.
 */
public class MapInitializeEvent extends ServerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final MapView mapView;
    private final InitializeCause cause;

    public MapInitializeEvent(final MapView mapView) {
        this(mapView, InitializeCause.CUSTOM);
    }

    public MapInitializeEvent(final MapView mapView, final InitializeCause cause) {
        this.mapView = mapView;
        this.cause = cause;
    }

    /**
     * Gets the map initialized in this event.
     *
     * @return Map for this event
     */
    public MapView getMap() {
        return mapView;
    }

    /**
     * Get the cause of the event.
     *
     * @return The InitializeCause of the event.
     */
    public InitializeCause getCause() {
        return cause;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * An enum to specify the cause of the map initialization.
     */
    public static enum InitializeCause {
        /**
         * A new map was created using an Empty Map.
         */
        CREATE,

        /**
         * A map was crafted with paper to zoom out.
         */
        EXPAND,

        /**
         * An existing map was loaded.
         */
        LOAD,

        /**
         * Other reason.
         */
        CUSTOM
    }
}
