package model.attack;

import model.PTypes;
import model.effects.IEffect;
import model.effects.effectTypes.*;

import java.util.ArrayList;
import java.util.List;

public class AttackFactory {


    public static Attack createByName(String attackName){
        switch (attackName) {
            case "Tackle": return getTackle();
            case "SwordsDance": return getSwordsDance();
            default : throw new IllegalArgumentException("Unknown Attack " + attackName);
        }
    }

    public static Attack getTackle(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new DoDamage(40, PTypes.NORMAL));
        return new Attack("Tackle",3, effects);
    }

    public static Attack getQuickAttack(){
        List<IEffect> effects = new ArrayList<>();  
        effects.add(new DoDamage(40, PTypes.NORMAL));
        return new Attack("Quick attack", 1, effects);
    }

    public static Attack getSwordsDance(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new ModifyAttackPower(2));
        return new Attack("Swords dance", 3, effects);
    }

    public static Attack getAbsorb(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new HpSteal(20, PTypes.GRASS));
        return new Attack("Absorb",3, effects);
    }

    public static Attack getDoubleEdge(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new RecoilDamage(120, PTypes.NORMAL, 1/3));
        return new Attack("Double Edge",3, effects);
    }


}
