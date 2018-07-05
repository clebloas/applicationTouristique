package com.cercle.jds.user.domain;

import com.cercle.jds.user.domain.common.Identity;

public class PartyId extends Identity<Party> {
    public PartyId(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "{PartyId:"+identity()+"}";
    }
}
