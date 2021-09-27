package model.attack;

import model.PTypes;
import model.effects.IEffect;
import model.effects.effectTypes.*;

import java.util.ArrayList;
import java.util.List;

public class AttackFactory {

    public static Attack getTackle(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new DoDamage(40, PTypes.NORMAL));
        return new Attack(3, effects);
    }

    public static Attack getQuickAttack(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new DoDamage(40, PTypes.NORMAL));
        return new Attack(1, effects);
    }




}
