package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

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

    @GetMapping("/search")
    public List<Ad> getByRange(
        @RequestParam(required = false, defaultValue = "0") Integer min,
        @RequestParam(required = false, defaultValue = "10000000") Integer max
    ) {
        return adRepository.getByRange(min, max);
    }
}
