package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "card", schema = "fsweb")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    public Card(Long id, Integer value, Color color) {
        this.id = id;
        this.value = value;
        this.type = null;
        this.color = color;
    }

    public Card(Long id, Type type, Color color) {
        this.id = id;
        this.value=null;
        this.type = type;
        this.color = color;
    }

    public Card(Long id) {
        this.id = id;
        this.value= null;
        this.type = Type.JOKER;
        this.color=null;

    }
}
