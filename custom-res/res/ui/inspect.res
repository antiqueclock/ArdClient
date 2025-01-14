Haven Resource 1 src    LocalInspect.java /* Preprocessed source code */
package haven.res.ui.inspect;

import haven.*;

/* >wdg: LocalInspect */
public class LocalInspect extends Widget {
    public MapView mv;
    public Hover last = null, cur = null;

    public static Widget mkwidget(UI ui, Object... args) {
	return(new LocalInspect());
    }

    protected void added() {
	super.added();
	mv = getparent(GameUI.class).map;
	move(Coord.z);
	resize(parent.sz);
    }

    public void destroy() {
	super.destroy();
    }

    public class Hover extends MapView.Hittest {
	public volatile boolean done = false;
	public Coord2d mc;
	public ClickData inf;
	public Gob ob;
	public Object tip;

	public Hover(Coord c) {
	    mv.super(c);
	}

	protected void hit(Coord pc, Coord2d mc, ClickData inf) {
	    this.mc = mc;
	    this.inf = inf;
	    this.done = true;
	    if(inf != null) {
		for(Object o : inf.array()) {
		    if(o instanceof Gob) {
			ob = (Gob)o;
			break;
		    }
		}
	    }
	}

	protected void nohit(Coord pc) {
	    done = true;
	}

	public Object tip() {
	    if(ob != null) {
		GobIcon icon = ob.getattr(GobIcon.class);
		if(icon != null) {
		    Resource.Tooltip name = icon.res.get().layer(Resource.tooltip);
		    if(name != null)
			return(name.t);
		}
	    }
	    if(mc != null) {
		int tid = ui.sess.glob.map.gettile(mc.floor(MCache.tilesz));
		Resource tile = ui.sess.glob.map.tilesetr(tid);
		Resource.Tooltip name = tile.layer(Resource.tooltip);
		if(name != null)
		    return(name.t);
	    }
	    return(null);
	}
    }

    public boolean active() {
	return(true);
    }

    public void tick(double dt) {
	super.tick(dt);
	if((cur != null) && cur.done) {
	    last = cur;
	    cur = null;
	}
	if(active()) {
	    if(cur == null) {
		Coord mvc = mv.rootxlate(ui.mc);
		if(mv.area().contains(mvc)) {
		    (cur = new Hover(mvc)).run();
		}
	    }
	} else {
	    last = null;
	}
    }

    public Object tooltip(Coord c, Widget prev) {
	if(active()) {
	    if(last != null)
		return(last.tip());
	}
	return(super.tooltip(c, prev));
    }
}
code �  haven.res.ui.inspect.LocalInspect$Hover ����   4 �	  7	 8 9
 : ;
  <	  =	  >	  ?
 @ A B	  C D
 	 E	  F G H I	  J
  K L	  O	 8 P	 Q R	 S T	 U V	 W X
 Y Z
 W [
 W \ ] ` done Z mc Lhaven/Coord2d; inf Lhaven/ClickData; ob Lhaven/Gob; tip Ljava/lang/Object; this$0 #Lhaven/res/ui/inspect/LocalInspect; <init> 3(Lhaven/res/ui/inspect/LocalInspect;Lhaven/Coord;)V Code LineNumberTable hit 0(Lhaven/Coord;Lhaven/Coord2d;Lhaven/ClickData;)V StackMapTable b nohit (Lhaven/Coord;)V ()Ljava/lang/Object; 
SourceFile LocalInspect.java ( ) c d e f g h * i     ! " # j k l 	haven/Gob $ % haven/GobIcon m n o p q r 4 haven/Resource s t u x haven/Resource$Tooltip Tooltip InnerClasses y z { | } ~  � � � � � � � � ! � � � � � � � 'haven/res/ui/inspect/LocalInspect$Hover Hover � haven/MapView$Hittest Hittest [Ljava/lang/Object; !haven/res/ui/inspect/LocalInspect mv Lhaven/MapView; java/lang/Object getClass ()Ljava/lang/Class; (Lhaven/MapView;Lhaven/Coord;)V haven/ClickData array ()[Ljava/lang/Object; getattr "(Ljava/lang/Class;)Lhaven/GAttrib; res Lhaven/Indir; haven/Indir get tooltip Ljava/lang/Class; layer � Layer )(Ljava/lang/Class;)Lhaven/Resource$Layer; t Ljava/lang/String; ui 
Lhaven/UI; haven/UI sess Lhaven/Session; haven/Session glob Lhaven/Glob; 
haven/Glob map Lhaven/MCache; haven/MCache tilesz haven/Coord2d floor (Lhaven/Coord2d;)Lhaven/Coord; gettile (Lhaven/Coord;)I tilesetr (I)Lhaven/Resource; haven/MapView haven/Resource$Layer inspect.cjava !      A        !    " #    $ %    & '   ( )     * +  ,   =     *+� *+� Y� W,� *� �    -          !    "  . /  ,   �     J*,� *-� *� -� 9-� :�66� $2:� 	� *� 	� 
� 	���۱    0    � ! 1!�  -   * 
   %  & 
 '  (  ) / * 7 + @ , C ) I 0  2 3  ,   "     *� �    -   
    3  4  & 4  ,   �     �*� 
� 3*� 
� � L+� "+� �  � � � � M,� ,� �*� � J*� � � � � *� � � � <*� � � � � � M,� � � N-� -� ��    0    7� M -   6    7  8  9  : . ; 2 < 7 ? > @ \ A q B | C � D � F  5    � N   "    M   8 ^   _ a v  wcode :  haven.res.ui.inspect.LocalInspect ����   4 w
  7	  8	  9 :
  7
  ; <
  =	  >	  ?	 @ A
  B	  C	  D
  E
  F
  G	  H
  I	  J	 K L
 M N
 M O
 P Q R
  S
  T
  U
  V W Hover InnerClasses mv Lhaven/MapView; last )Lhaven/res/ui/inspect/LocalInspect$Hover; cur <init> ()V Code LineNumberTable mkwidget -(Lhaven/UI;[Ljava/lang/Object;)Lhaven/Widget; added destroy active ()Z tick (D)V StackMapTable tooltip /(Lhaven/Coord;Lhaven/Widget;)Ljava/lang/Object; 
SourceFile LocalInspect.java & ' # $ % $ !haven/res/ui/inspect/LocalInspect , ' haven/GameUI X Y Z " ! " [ \ ] ^ _ ` a b ] c _ - ' 0 1 d e . / f g h i ] j k l m n o p q 'haven/res/ui/inspect/LocalInspect$Hover & r s ' t u 3 4 haven/Widget 	getparent !(Ljava/lang/Class;)Lhaven/Widget; map haven/Coord z Lhaven/Coord; move (Lhaven/Coord;)V parent Lhaven/Widget; sz resize done Z ui 
Lhaven/UI; haven/UI mc haven/MapView 	rootxlate (Lhaven/Coord;)Lhaven/Coord; area ()Lhaven/Area; 
haven/Area contains (Lhaven/Coord;)Z 3(Lhaven/res/ui/inspect/LocalInspect;Lhaven/Coord;)V run tip ()Ljava/lang/Object; inspect.cjava !       ! "    # $    % $     & '  (   +     *� *� *� �    )   
       � * +  (         � Y� �    )         , '  (   O     '*� **� � � 	� 
*� � **� � � �    )              &   - '  (   !     *� �    )   
        . /  (        �    )       K  0 1  (   �     h*'� *� � *� � � **� � *� *� � ;*� � 9*� 
*� � � N*� 
� -� � *� Y*-� Z� � � *� �    2    #; )   2    O  P  Q  R # T * U 1 V @ W N X _ Z b \ g ^  3 4  (   J     *� � *� � *� � �*+,� �    2     )       a  b  c  e  5    v     
     codeentry )   wdg haven.res.ui.inspect.LocalInspect   