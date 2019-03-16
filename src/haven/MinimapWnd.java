package haven;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import haven.Coord;
import haven.IButton;
import haven.LocalMiniMap;
import haven.DefSettings;


public class MinimapWnd extends ResizableWnd {
    private LocalMiniMap minimap;
    private final int header;
    public static Tex biometex;
    private boolean minimized;
    private Coord szr;
    public MapWnd mapfile;



    public MinimapWnd(final LocalMiniMap mm) {
        super(Coord.z, "Minimap");
        this.minimap = mm;
        final int spacer = 5;

        makeHidable();
        final IButton pclaim = add(new IButton("gfx/hud/wndmap/btns/claim", "Show Personal Claims", () ->{
            if((ui.gui.map != null) && !ui.gui.map.visol(0)) {
                DefSettings.SHOWPCLAIM.set(true);
                ui.gui.map.enol(0, 1);
            } else {
                DefSettings.SHOWPCLAIM.set(false);
                ui.gui.map.disol(0, 1);
            }
        }), new Coord(0, 0));
        final IButton vclaim = add(new IButton("gfx/hud/wndmap/btns/vil", "Show Village Claims", () ->{
            if((ui.gui.map != null) && !ui.gui.map.visol(2)) {
                DefSettings.SHOWVCLAIM.set(true);
                ui.gui.map.enol(2, 3);
            } else {
                DefSettings.SHOWVCLAIM.set(false);
                ui.gui.map.disol(2, 3);
            }
        }), pclaim.c.add(pclaim.sz.x + spacer, 0));
        final IButton realm = add(new IButton("gfx/hud/wndmap/btns/realm", "Show Kingdom Claims", () ->{
            if((ui.gui.map != null) && !ui.gui.map.visol(4)) {
                ui.gui.map.enol(4, 5);
                DefSettings.SHOWKCLAIM.set(true);
            } else {
                DefSettings.SHOWKCLAIM.set(false);
                ui.gui.map.disol(4, 5);
            }
        }), vclaim.c.add(vclaim.sz.x + spacer, 0));
        final IButton mapwnd = add(new IButton("gfx/hud/wndmap/btns/map", "Open Map", () -> gameui().toggleMap()), realm.c.add(realm.sz.x + spacer,0));
        final IButton geoloc = new IButton("gfx/hud/wndmap/btns/geoloc", "", "", "") {
            @Override
            public Object tooltip(Coord c, Widget prev) {
                Pair<String, String> coords = getCurCoords();
                if (coords != null)
                    tooltip = Text.render(String.format("Current location: %s x %s", coords.a, coords.b));
                else
                    tooltip = Text.render("Unable to determine your current location.");
                return super.tooltip(c, prev);
            }

            @Override
            public void click() {
                Pair<String, String> coords = getCurCoords();
                if (coords != null) {
                    try {
                        WebBrowser.self.show(new URL(String.format("http://odditown.com/haven/map/#x=%s&y=%s&zoom=9", coords.a, coords.b)));
                    } catch (WebBrowser.BrowserException e) {
                        getparent(GameUI.class).error("Could not launch web browser.");
                    } catch (MalformedURLException e) {
                    }
                } else {
                    getparent(GameUI.class).error("Unable to determine your current location.");
                }
            }

            private Pair<String, String> getCurCoords() {
                return minimap.cur != null ? Config.gridIdsMap.get(minimap.cur.grid.id) : null;
            }
        };add(geoloc,mapwnd.c.add(mapwnd.sz.x + spacer,0));
        final IButton center = add(new IButton("gfx/hud/wndmap/btns/center", "Center map on player", () -> mm.center()),
                geoloc.c.add(geoloc.sz.x + spacer, 0));
        final IButton grid = add(new IButton("gfx/hud/wndmap/btns/grid", "Toggle grid on minimap", () -> gameui().toggleMapGrid()),
                center.c.add(center.sz.x + spacer, 0));
        final IButton viewdist = add(new IButton("gfx/hud/wndmap/btns/viewdist", "Toggle view range", () -> gameui().toggleMapViewDist()),
                grid.c.add(grid.sz.x + spacer, 0));

        header = pclaim.sz.y + spacer;
        add(mm, new Coord(0, header));
        pack();
    }

    @Override
    public void close()
    {
    //    hide();
        minimize();
    }

    @Override
    protected void added() {
        super.added();
        minimap.sz = asz.sub(0, header);
    }

    @Override
    public void resize(Coord sz) {
        super.resize(sz);
        minimap.sz = asz.sub(0, header);
    }
    private void minimize() {
        minimized = !minimized;
        if (minimized) {
            this.minimap.hide();
        } else {
            this.minimap.show();
        }

        if (minimized) {
            szr = asz;
            resize(new Coord(asz.x, 24));
        } else {
            resize(szr);
        }
    }
}
