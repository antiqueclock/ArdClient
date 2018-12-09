package haven.livestock;

import java.util.*;
import java.util.stream.Collectors;
import haven.Button;

import haven.*;
import haven.Label;
import haven.Window;
import haven.Utils;
import haven.purus.BotUtils;
import java.awt.Color;

public class LivestockManager extends Window {
    private final Panel cattle, horses, sheep, pigs, goats, ResetHighlights, Inspect, Slaughter, DropEntrails, DropIntestines, DropMeat, DropBones;
    private Panel current;
    public static final int COLUMN_TITLE_X = 60;
    public static final int ENTRY_X = 20;
    private static final int WINDOW_WIDTH = 1000;
    public CheckBox DropEntrailsBox, DropIntestinesBox, DropMeatBox, DropBonesBox, InspectBox, SlaughterBox;
    public Animal pendingAnimal;
    private GameUI gui;
    public static int quality, breedquality, hide, meat, milk;
    public static boolean combined, combinedhide, combinedmeat, combinedmilk = false;



    public LivestockManager() {

        super(new Coord(WINDOW_WIDTH, 600), "Livestock Manager");
        Coord pc = new Coord(20, 55);
        cattle = add(new Panel(), pc);
        horses = add(new Panel(), pc);
        sheep = add(new Panel(), pc);
        pigs = add(new Panel(), pc);
        goats = add(new Panel(), pc);
        ResetHighlights = add(new Panel(), pc);
        Inspect = add(new Panel(), pc);
        Slaughter = add(new Panel(), pc);
        DropEntrails = add(new Panel(), pc);
        DropIntestines = add(new Panel(), pc);
        DropMeat = add(new Panel(), pc);
        DropBones = add(new Panel(), pc);



        add(new PButton(80, "Cattle", cattle), new Coord(20, 10));
        add(new PButton(80, "Horses", horses), new Coord(110, 10));
        add(new PButton(80, "Sheep", sheep), new Coord(200, 10));
        add(new PButton(80, "Pigs", pigs), new Coord(290, 10));
        add(new PButton(80, "Goats", goats), new Coord(380, 10));
        add(new PButton(80, "Reset Highlights", ResetHighlights), new Coord(470, 10));
        add(new PButton(80, "Inspect", Inspect), new Coord(600, 10));
        add(new PButton(80, "Slaughter", Slaughter), new Coord(690, 10));
        add(new PButton(80, "Drop Entrails", DropEntrails), new Coord(20, 40));
        add(new PButton(80, "Drop Intestines", DropIntestines), new Coord(155, 40));
        add(new PButton(80, "Drop Meat", DropMeat), new Coord(300, 40));
        add(new PButton(80, "Drop Bones", DropBones), new Coord(420, 40));


        Utils.loadprefchklist("flowersel", Config.flowermenus);

         InspectBox = new CheckBox("") {
            {
                for(CheckListboxItem itm :Config.flowermenus.values()) {
                    if (itm.name.equals("Inspect")) {
                        if (itm.selected) {
                            a = true;
                        } else {
                            a = false;
                        }
                    }
                }
        }

            public void set(boolean val) {
                for(CheckListboxItem itm :Config.flowermenus.values()) {
                    if (itm.name.equals("Inspect")) {
                        itm.selected =val;
                        a = val;
                        Utils.setprefchklst("flowersel", Config.flowermenus);
                    }
                }
            }


        };
    add(InspectBox,640,40);

    SlaughterBox = new CheckBox("") {
            {
                for(CheckListboxItem itm :Config.flowermenus.values()) {
                    if (itm.name.equals("Slaughter")) {
                        if (itm.selected) {
                            a = true;
                        } else {
                            a = false;
                        }
                    }
                }
            }

            public void set(boolean val) {
                for(CheckListboxItem itm :Config.flowermenus.values()) {
                    if (itm.name.equals("Slaughter")) {
                        itm.selected =val;
                        a = val;
                        Utils.setprefchklst("flowersel", Config.flowermenus);
                    }
                }
            }
        };
    add(SlaughterBox,730,40);

        DropEntrailsBox = new CheckBox("") {
            {
              if(Config.DropEntrails)
                            a = true;
                         else
                            a = false;
            }

            public void set(boolean val) {
                Config.DropEntrails =val;
                        a = val;
                Utils.setprefb("DropEntrails", val);
                    }
        };
        add(DropEntrailsBox,120,45);

        DropIntestinesBox = new CheckBox("") {
            {
                if(Config.DropIntestines)
                    a = true;
                else
                    a = false;
            }

            public void set(boolean val) {
                Config.DropIntestines =val;
                a = val;
                Utils.setprefb("DropIntestines", val);
            }
        };
        add(DropIntestinesBox,270,45);

        DropMeatBox = new CheckBox("") {
            {
                if(Config.DropMeat)
                    a = true;
                else
                    a = false;
            }

            public void set(boolean val) {
                Config.DropMeat =val;
                a = val;
                Utils.setprefb("DropMeat", val);
            }
        };
        add(DropMeatBox,390,45);

        DropBonesBox = new CheckBox("") {
            {
                if(Config.DropBones)
                    a = true;
                else
                    a = false;
            }

            public void set(boolean val) {
                Config.DropBones =val;
                a = val;
                Utils.setprefb("DropBones", val);
            }
        };
        add(DropBonesBox,520,45);

        createHeader(horses, Horses.columns);
        createHeader(cattle, Cattle.columns);
        createHeader(sheep, Sheep.columns);
        createHeader(pigs, Pigs.columns);
        createHeader(goats, Goat.columns);


        chpanel(cattle);
    }






    private void createHeader(Panel panel, Map<String, Column> columns) {
        List<Map.Entry<String, Column>> cols = columns.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(c -> c.getValue().index))
                .collect(Collectors.toList());

        int offx = LivestockManager.COLUMN_TITLE_X - LivestockManager.ENTRY_X;

        for (Map.Entry<String, Column> col : cols) {
            Column pos = col.getValue();
            panel.add(pos.lbl, new Coord(pos.x + offx, 10));
        }

        panel.pack();
    }

    @Override
    public void wdgmsg(Widget sender, String msg, Object... args) {
        if (sender == cbtn) {
            Config.DropEntrails =false;
            Config.DropBones = false;
            Config.DropMeat = false;
            Config.DropIntestines = false;
            InspectBox.a = false;
            SlaughterBox.a = false;
            DropEntrailsBox.a = false;
            DropIntestinesBox.a = false;
            DropMeatBox.a = false;
            DropBonesBox.a = false;
            Utils.loadprefchklist("flowersel", Config.flowermenus);
            for(CheckListboxItem itm :Config.flowermenus.values()) {
                if (itm.name.equals("Inspect"))
                    itm.selected = false;
                if (itm.name.equals("Slaughter"))
                    itm.selected = false;
            }
            hide();
        }
        else
            super.wdgmsg(sender, msg, args);
    }

    @Override
    public boolean type(char key, java.awt.event.KeyEvent ev) {
        if (key == 27) {
            Config.DropEntrails =false;
            Config.DropBones = false;
            Config.DropMeat = false;
            Config.DropIntestines = false;
            InspectBox.a = false;
            SlaughterBox.a = false;
            DropEntrailsBox.a = false;
            DropIntestinesBox.a = false;
            DropMeatBox.a = false;
            DropBonesBox.a = false;
            Utils.loadprefchklist("flowersel", Config.flowermenus);
            for(CheckListboxItem itm :Config.flowermenus.values()) {
                if (itm.name.equals("Inspect"))
                    itm.selected = false;
                if (itm.name.equals("Slaughter"))
                    itm.selected = false;
            }
            hide();
            return true;
        }
        return super.type(key, ev);
    }

    public void chpanel(Panel p) {
        if (current != null)
            current.hide();
        (current = p).show();
    }

    public class Panel extends Widget {
        public final List<Animal> list = new ArrayList<>();

        public Scrollport scrollPort;

        public Panel() {
            visible = false;
            c = Coord.z;
           // scrollPort = new Scrollport(new Coord(WINDOW_WIDTH - 40, 290)) {
                scrollPort = new Scrollport(new Coord(WINDOW_WIDTH - 40, 520), 25) {
                @Override
                public void draw(GOut g) {
                    g.chcolor(0, 0, 0, 128);
                    g.frect(Coord.z, sz);
                    g.chcolor();
                    super.draw(g);
                }
            };
           // add(scrollPort, new Coord(0, 25));
            add(scrollPort, new Coord(0, 22));
        }

        public void delete(Animal animal) {
            for (Iterator<Animal> iterator = list.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == animal) {
                    iterator.remove();
                    break;
                }
            }
        }
    }


    private class PButton extends Button {
        public final Panel tgt;

        public PButton(int w, String title, Panel tgt) {


            super(w, title);
            this.tgt = tgt;

        }

        @Override
        public void click() {

            if (tgt == ResetHighlights)
                MapView.markedGobs.clear();
            else if (tgt == Inspect)
                BotUtils.sysMsg("Not yet implemented.",Color.white);
            else if (tgt == Slaughter)
                BotUtils.sysMsg("Not yet implemented.",Color.white);
            else if (tgt==DropEntrails){
                if (Config.DropEntrails) {
                    Config.DropEntrails = false;
                    DropEntrailsBox.a = false;
                    BotUtils.sysMsg("No longer dropping entrails.",Color.white);
                }
                else
                    {
                    Config.DropEntrails =true;
                    DropEntrailsBox.a = true;
                    BotUtils.sysMsg("Auto dropping Entrails.",Color.white);
                }
            }
            else if (tgt==DropIntestines){
                if (Config.DropIntestines) {
                    Config.DropIntestines = false;
                    BotUtils.sysMsg("No longer dropping Intestines.",Color.white);
                    DropIntestinesBox.a = false;
                }
                else
                {
                    Config.DropIntestines =true;
                    DropIntestinesBox.a = true;
                    BotUtils.sysMsg("Auto dropping Intestines.",Color.white);
                }
            }
            else if(tgt==DropMeat){
                if (Config.DropMeat) {
                    Config.DropMeat = false;
                    DropMeatBox.a = false;
                    BotUtils.sysMsg("No longer dropping Meat.",Color.white);
                }
                else
                {
                    Config.DropMeat =true;
                    DropMeatBox.a = true;
                    BotUtils.sysMsg("Auto dropping Meat.",Color.white);
                }
            }
            else if(tgt==DropBones){
                if (Config.DropBones) {
                    Config.DropBones = false;
                    DropBonesBox.a = false;
                    BotUtils.sysMsg("No longer dropping Bones.",Color.white);
                }
                else
                {
                    Config.DropBones =true;
                    DropBonesBox.a = true;
                    BotUtils.sysMsg("Auto dropping Bones.",Color.white);
                }
            }
            else chpanel(tgt);
            }




    }


    public void initPendingAnimal(long wdgid, String type) {
       switch (type) {
            case "Bull":
            case "Cow":
                pendingAnimal = new Cattle(wdgid, type);
                break;
            case "Mare":
            case "Stallion":
                pendingAnimal = new Horses(wdgid, type);
                break;
            case "Ram":
            case "Ewe":
                pendingAnimal = new Sheep(wdgid, type);
                break;
            case "Hog":
            case "Sow":
                pendingAnimal = new Pigs(wdgid, type);
                break;
           case "Nanny":
           case "Billy":
               pendingAnimal = new Goat(wdgid, type);
               break;
        }
    }

    public Panel getAnimalPanel(String type) {
        switch (type) {
            case "Bull":
            case "Cow":
                return cattle;
            case "Mare":
            case "Stallion":
                return horses;
            case "Ram":
            case "Ewe":
                return sheep;
            case "Hog":
            case "Sow":
                return pigs;
            case "Nanny":
            case "Billy":
                return goats;
            default:
                return null;
        }
    }

    public void applyId(Widget wdg) {
        if (pendingAnimal != null && !pendingAnimal.hasAllAttributes()) {
            pendingAnimal.gobid = ((Avaview) wdg).avagob;
            pendingAnimal.attributeResolved();
        }
    }

    public void applyName(Widget wdg) {
        if (pendingAnimal != null && !pendingAnimal.hasAllAttributes()) {
            pendingAnimal.name = ((TextEntry) wdg).text;
            pendingAnimal.attributeResolved();
        }
    }

    public void applyAttr(String type, Widget wdg) {
        if (!(wdg.prev instanceof Label))
            return;

        String name = ((Label) wdg.prev).texts;

        if (pendingAnimal == null || !pendingAnimal.containsKey(name) || pendingAnimal.hasAllAttributes())
            return;

        String valStr = ((Label) wdg).texts;
        if (valStr.endsWith("%"))
            valStr = valStr.substring(0, valStr.length() - 1);
        Integer val = new Integer(valStr);
        if (name.equals("Quality:"))
        quality = val;
        if (name.equals("Breeding quality:"))
            breedquality = val;
        if(name.equals("Meat quality:"))
            meat = val;
        if(name.equals("Hide quality:"))
            hide = val;
        if(name.equals("Milk quality:"))
            milk = val;
        if(meat > 0 && quality > 0 && !combinedmeat){
            pendingAnimal.put("Meat quality2:", quality * meat / 100);
         //   System.out.println("attributes status : "+pendingAnimal.hasAllAttributes()+" size : "+pendingAnimal.size()+" resolved combined meat");
            pendingAnimal.attributeResolved();
            combinedmeat = true;
        }
        if(milk > 0 && quality > 0 && !combinedmilk){
            pendingAnimal.put("Milk quality2:", quality * milk / 100);
          //  System.out.println("attributes status : "+pendingAnimal.hasAllAttributes()+" size : "+pendingAnimal.size()+" resolved combined milk");
            pendingAnimal.attributeResolved();
            combinedmilk = true;
        }
        if(hide > 0 && quality > 0 && !combinedhide){
            pendingAnimal.put("Hide quality2:", quality * hide / 100);
          //  System.out.println("attributes status : "+pendingAnimal.hasAllAttributes()+" size : "+pendingAnimal.size()+" resolved combined hide");
            pendingAnimal.attributeResolved();
            combinedhide = true;
        }
        if (quality > 0 && breedquality > 0 && !combined) {
            pendingAnimal.put("Combined quality:", quality + breedquality);
          //  System.out.println("attributes status : "+pendingAnimal.hasAllAttributes()+" size : "+pendingAnimal.size()+" resolved combined quality");
            pendingAnimal.attributeResolved();
            combined = true;
        }
        pendingAnimal.put(name, val);
        pendingAnimal.attributeResolved();
      //  System.out.println("attributes status : "+pendingAnimal.hasAllAttributes()+" size : "+pendingAnimal.size()+" resolved is "+name);




        if (pendingAnimal.hasAllAttributes()) {
          //  System.out.println("has all attributes");
            combined = false;
            combinedmeat = false;
            combinedmilk = false;
            combinedhide = false;
            Panel p = getAnimalPanel(type);

            if (p.list.stream().anyMatch(x -> x.gobid == pendingAnimal.gobid)) {
                pendingAnimal = null;
                return;
            }

            p.list.add(pendingAnimal);

            int y = 0;
            for (Widget child = p.scrollPort.cont.lchild; child != null; child = child.prev) {
                if (child instanceof DetailsWdg && child.c.y >= y)
                    y = child.c.y + DetailsWdg.HEIGHT;
            }

            DetailsWdg details = new DetailsWdg(pendingAnimal);
            //p.scrollPort.cont.add(details, new Coord(0, y));
          //  details.sz = new Coord(p.scrollPort.cont.sz.x, DetailsWdg.HEIGHT);
            Scrollport.Scrollcont scrollCont = p.scrollPort.cont;
            scrollCont.add(details, new Coord(0, y));
            p.scrollPort.bar.max = Math.max(0, scrollCont.contentsz().y - scrollCont.sz.y + DetailsWdg.HEIGHT);
            details.sz = new Coord(scrollCont.sz.x, DetailsWdg.HEIGHT);

            chpanel(p);
            pendingAnimal = null;
            quality = 0;
            breedquality = 0;
            hide = 0;
            milk = 0;
            meat = 0;
        }
    }
}
