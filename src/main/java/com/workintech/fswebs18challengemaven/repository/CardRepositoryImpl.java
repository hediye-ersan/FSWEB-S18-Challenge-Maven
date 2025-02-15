package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class CardRepositoryImpl implements CardRepository{

    private final EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Card save(Card card) {
        if (card == null) {
            throw new CardException("Card cannot be null", HttpStatus.BAD_REQUEST);
        }
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        if (color == null) {
            throw new CardException("Color cannot be null", HttpStatus.BAD_REQUEST);
        }
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color" , Card.class);
        query.setParameter("color", color);
        return query.getResultList();
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> foundAll = entityManager.createQuery(
                "SELECT c FROM Card c", Card.class
        );
        return foundAll.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        if (value == null) {
            throw new CardException("Value cannot be null", HttpStatus.BAD_REQUEST);
        }
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value" , Card.class);
        query.setParameter("value", value);
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        if (type == null) {
            throw new CardException("Type cannot be null", HttpStatus.BAD_REQUEST);
        }
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type", Card.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Card update(Card card) {
        if (card == null || card.getId() == null) {
            throw new CardException("Card or Card ID cannot be null", HttpStatus.BAD_REQUEST);
        }
        entityManager.merge(card);
        return card;
    }

    @Transactional
    @Override
    public Card remove(Long id) {
        if (id == null) {
            throw new CardException("Card ID cannot be null", HttpStatus.BAD_REQUEST);
        }
        Card card = entityManager.find(Card.class, id);
        entityManager.remove(card);
        return card;
    }
}
