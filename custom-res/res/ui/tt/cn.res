Haven Resource 1 src   CustName.java /* Preprocessed source code */
import haven.*;

/* >tt: CustName */
public class CustName implements ItemInfo.InfoFactory {
    public ItemInfo build(ItemInfo.Owner owner, ItemInfo.Raw raw, Object... args) {
	return(new ItemInfo.Name(owner, (String)args[1]));
    }
}
code �  CustName ����   4 $
    
      <init> ()V Code LineNumberTable build  Owner InnerClasses   Raw O(Lhaven/ItemInfo$Owner;Lhaven/ItemInfo$Raw;[Ljava/lang/Object;)Lhaven/ItemInfo; 
SourceFile CustName.java  	 ! haven/ItemInfo$Name Name java/lang/String  " CustName java/lang/Object haven/ItemInfo$InfoFactory InfoFactory haven/ItemInfo$Owner haven/ItemInfo$Raw haven/ItemInfo +(Lhaven/ItemInfo$Owner;Ljava/lang/String;)V cn.cjava !          	  
        *� �            �    
   '     � Y+-2� � �                 #    "    	    	    	   	codeentry    tt CustName   