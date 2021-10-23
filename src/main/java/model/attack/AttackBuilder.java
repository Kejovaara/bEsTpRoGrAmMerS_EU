package model.attack;

import model.PTypes;
import model.effects.IEffect;
import model.effects.effectTypes.*;

import java.util.ArrayList;
import java.util.List;

public class AttackBuilder {

    /**
     * @param attackName is the name of the attack.
     * @return an attack.
     */
    public static Attack createByName(String attackName){
        switch (attackName) {
            case "Tackle": return getTackle();
            case "SwordsDance": return getSwordsDance();
            case "Absorb": return  getAbsorb();
            case "QuickAttack": return getQuickAttack();
            case "DoubleEdge":return getDoubleEdge();
            case "Flamethrower":return getFlamethrower();
            default : throw new IllegalArgumentException("Unknown Attack " + attackName);
        }
    }

    /**
     * @return an attack named Tackle.
     */
    public static Attack getTackle(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new DoDamage(40, PTypes.NORMAL));
        return new Attack("Tackle",3, effects,15, PTypes.NORMAL);
    }

    /**
     * @return an attack named QuickAttack.
     */
    public static Attack getQuickAttack(){
        List<IEffect> effects = new ArrayList<>();  
        effects.add(new DoDamage(40, PTypes.NORMAL));
        return new Attack("Quick attack", 1, effects,10,PTypes.NORMAL);
    }

    /**
     * @return an attack named Swords Dance.
     */
    public static Attack getSwordsDance(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new ModifyAttackPower(2));
        return new Attack("Swords dance", 3, effects,3,PTypes.NORMAL);
    }

    /**
     * @return an attack named Absorb.
     */
    public static Attack getAbsorb(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new HpSteal(20, PTypes.GRASS, 0.5f));
        return new Attack("Absorb",3, effects,5,PTypes.GRASS);
    }

    /**
     * @return an attack named Double Edge.
     */
    public static Attack getDoubleEdge(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new RecoilDamage(120, PTypes.NORMAL, (float)1/3));
        return new Attack("Double Edge",3, effects, 7,PTypes.NORMAL);
    }

    /**
     * @return an attack named Flamethrower.
     */
    public static Attack getFlamethrower(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new DoDamage(80, PTypes.FIRE));
        return new Attack("Flamethrower",3, effects, 10,PTypes.FIRE);
    }


}
