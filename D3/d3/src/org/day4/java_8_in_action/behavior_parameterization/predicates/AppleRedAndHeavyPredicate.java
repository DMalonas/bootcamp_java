package org.day4.java_8_in_action.behavior_parameterization.predicates;

import org.day4.java_8_in_action.behavior_parameterization.Apple;

public class AppleRedAndHeavyPredicate implements ApplePredicate{
        public boolean test(Apple apple){
                return "red".equals(apple.getColor())
                       && apple.getWeight() > 150;
        }
}
