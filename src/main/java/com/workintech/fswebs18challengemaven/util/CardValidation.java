package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;

public class CardValidation {
    public static boolean isValid(Card card) {
        if (card == null) {
            return true; // Null kontrolü
        }

        if (card.getType() == Type.JOKER) {
            // Eğer kart türü JOKER ise, value ve color null olmalı.
            if (card.getValue() != null || card.getColor() != null) {
                return false;
            }
        } else {
            // Eğer kart türü JOKER değilse, hem type hem value birlikte olmamalı.
            if (card.getType() != null && card.getValue() != null) {
                return false;
            }
        }

        return true; // Diğer durumlar geçerli
    }
}
