package com.nicson.financasapi.model;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

public class Transacao {

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    
    // rest of the class implementation
    
}