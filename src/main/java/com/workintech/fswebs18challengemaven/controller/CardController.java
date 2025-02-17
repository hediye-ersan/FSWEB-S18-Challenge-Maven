package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cards")
public class CardController {

    private CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getFindByColor(@PathVariable String color){
        return cardRepository.findByColor(color);
    }

    @PostMapping
    public Card postSaveCard(@RequestBody Card card){
        CardValidation.isValid(card);
        return cardRepository.save(card);
    }

    @PutMapping("/")
    public Card getUpdateCard(@RequestBody Card card){
        CardValidation.isValid(card);
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public Card getRemoveCard(@PathVariable Long id){
        return cardRepository.remove(id);
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getByValue(@PathVariable("value") Integer value){
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> getByType(@PathVariable("type") String type){
        return cardRepository.findByType(type);
    }
}
