package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    private AdRepository adRepository;

    public AdController(
        @Autowired AdRepository adRepository
    ) {
        this.adRepository = adRepository;
    }

    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        return adRepository.save(ad);
    }
}
