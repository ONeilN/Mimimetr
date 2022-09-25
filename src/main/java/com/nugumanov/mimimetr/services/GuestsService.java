package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Guest;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.GuestsRepository;
import com.nugumanov.mimimetr.repositories.PairsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Aizat Nugumanov
 */
@Service
@Transactional(readOnly = true)
@SessionScope
public class GuestsService {

    private final GuestsRepository guestsRepository;
    private final PairsRepository pairsRepository;

    @Getter
    private Guest guest;

    @Autowired
    public GuestsService(GuestsRepository guestsRepository, PairsRepository pairsRepository) {
        this.guestsRepository = guestsRepository;
        this.pairsRepository = pairsRepository;
    }

    @Transactional
    public void setGuest(String cookie) {

        Optional<Guest> guestByCookieValue = guestsRepository.findGuestByCookieValue(cookie);

        if (guestByCookieValue.isPresent()) {
            guest = guestByCookieValue.orElse(null);
        } else {
            Guest tmpGuest = new Guest(cookie);
            guestsRepository.save(tmpGuest);
            guest = tmpGuest;
        }
    }

    @Transactional
    public void registerGuest() {
        Guest tmpGuest = new Guest(UUID.randomUUID().toString());
        guestsRepository.save(tmpGuest);
        guest = tmpGuest;
    }

    @Transactional
    public void markPair(Pair pair) {

        if (guest.getPairList() == null) {
            guest.setPairList(new ArrayList<>(List.of(pair)));
            pair.setGuestList(new ArrayList<>(List.of(guest)));
        } else {
            guest.getPairList().add(pair);
            pair.getGuestList().add(guest);
        }

        guestsRepository.save(guest);
        pairsRepository.save(pair);
    }
}
