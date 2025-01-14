Haven Resource 1 src �  Satiate.java /* Preprocessed source code */
import haven.*;
import java.awt.image.BufferedImage;

/* >tt: Satiate */
public class Satiate implements ItemInfo.InfoFactory {
    public ItemInfo build(ItemInfo.Owner owner, ItemInfo.Raw raw, Object... args) {
	final Indir<Resource> res = owner.context(Resource.Resolver.class).getres((Integer)args[1]);
	final double f = ((Number)args[2]).doubleValue();
	return(new ItemInfo.Tip(owner) {
		public BufferedImage tipimg() {
		    BufferedImage t1 = Text.render("Satiate ").img;
		    int h = t1.getHeight();
		    BufferedImage icon = PUtils.convolvedown(res.get().layer(Resource.imgc).img, new Coord(h, h), CharWnd.Constipations.tflt);
		    BufferedImage t2 = RichText.render(String.format("%s by $col[255,128,128]{%d%%}", res.get().layer(Resource.tooltip).t, (int)Math.round((1.0 - f) * 100)), 0).img;
		    return(catimgsh(0, t1, icon, t2));
		}
	    });
    }
}
code :	  Satiate$1 ����   4 �	 ! 7	 ! 8	 ! 9
 " : ;
 < =	 > ?
  @ A B C	 
 D
 
 E F	  ? H
  I	 J K
 L M N O	 
 P Q	  S@Y      
 T U
 V W
 X Y
 Z [	 Z ? \
 ! ] ^ ` val$res Lhaven/Indir; val$f D this$0 	LSatiate; <init> b Owner InnerClasses 0(LSatiate;Lhaven/ItemInfo$Owner;Lhaven/Indir;D)V Code LineNumberTable tipimg  ()Ljava/awt/image/BufferedImage; 
SourceFile Satiate.java EnclosingMethod c d g ' ( # $ % & ) h Satiate  i j l m n o p q r s t haven/Resource u v w z haven/Resource$Image Image haven/Coord ) { }  � � � � %s by $col[255,128,128]{%d%%} java/lang/Object � v haven/Resource$Tooltip Tooltip � � � � � � � � � � � � j � java/awt/image/BufferedImage � � 	Satiate$1 � haven/ItemInfo$Tip Tip haven/ItemInfo$Owner Satiate build � Raw O(Lhaven/ItemInfo$Owner;Lhaven/ItemInfo$Raw;[Ljava/lang/Object;)Lhaven/ItemInfo; (Lhaven/ItemInfo$Owner;)V 
haven/Text render Line %(Ljava/lang/String;)Lhaven/Text$Line; haven/Text$Line img Ljava/awt/image/BufferedImage; 	getHeight ()I haven/Indir get ()Ljava/lang/Object; imgc Ljava/lang/Class; layer � Layer )(Ljava/lang/Class;)Lhaven/Resource$Layer; (II)V � haven/CharWnd$Constipations Constipations tflt � Convolution Lhaven/PUtils$Convolution; haven/PUtils convolvedown e(Ljava/awt/image/BufferedImage;Lhaven/Coord;Lhaven/PUtils$Convolution;)Ljava/awt/image/BufferedImage; tooltip t Ljava/lang/String; java/lang/Math round (D)J java/lang/Integer valueOf (I)Ljava/lang/Integer; java/lang/String format 9(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String; haven/RichText 8(Ljava/lang/String;I[Ljava/lang/Object;)Lhaven/RichText; catimgsh @(I[Ljava/awt/image/BufferedImage;)Ljava/awt/image/BufferedImage; haven/ItemInfo haven/ItemInfo$Raw haven/Resource$Layer haven/CharWnd haven/PUtils$Convolution ft-de.cjava   ! "    # $   % &   ' (      ) -  .   .     *+� *-� *� *,� �    /       	  0 1  .   �     �� � L+� =*� � 	 � 
� � � � � Y� � � N� Y*� � 	 � 
� � � � SY*� g k� �� S� � � � :� Y+SY-SYS�  �    /        	    6  {   2    � ,   R 
 * _ +	  
 G   
 R  !       " _ a	 e _ f 	 > < k 	 x 
 y J | ~ 	 � L �	 4    5 6code �  Satiate ����   4 :
       
  !  " #
  $ %
 	 & ' ( * InnerClasses <init> ()V Code LineNumberTable build , Owner - Raw O(Lhaven/ItemInfo$Owner;Lhaven/ItemInfo$Raw;[Ljava/lang/Object;)Lhaven/ItemInfo; 
SourceFile Satiate.java   . haven/Resource$Resolver Resolver / 0 java/lang/Integer 1 2 3 4 java/lang/Number 5 6 	Satiate$1  7 Satiate java/lang/Object 8 haven/ItemInfo$InfoFactory InfoFactory haven/ItemInfo$Owner haven/ItemInfo$Raw haven/Resource context %(Ljava/lang/Class;)Ljava/lang/Object; intValue ()I getres (I)Lhaven/Indir; doubleValue ()D 0(LSatiate;Lhaven/ItemInfo$Owner;Lhaven/Indir;D)V haven/ItemInfo ft-de.cjava !                    *� �            �       T     4+�  � -2� � �  :-2� � 9� 	Y*+� 
�              & 	      9    *  	        ) 	  )  	   	  ) +	codeentry    tt Satiate   