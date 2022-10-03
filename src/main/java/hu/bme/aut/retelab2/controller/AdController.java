package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.SecretGenerator;
import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.time.LocalDateTime;
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
        ad.setSecretKey(SecretGenerator.generate());
        return adRepository.save(ad);
    }

    @GetMapping("/search")
    public List<Ad> getByRange(
        @RequestParam(required = false, defaultValue = "0") Integer min,
        @RequestParam(required = false, defaultValue = "10000000") Integer max
    ) {
        List<Ad> queryResults = adRepository.getByRange(min, max);
        for (Ad result:
                queryResults) {
            result.setSecretKey(null);
        }
        return queryResults;
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Ad ad) {
        try {
            Ad result = adRepository.update(ad);
            return ResponseEntity.ok(result);
        } catch(NonTransientDataAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/{tag}")
    public List<Ad> getByTag(
            @PathVariable("tag") String tag
    ) {
        return adRepository.findByTag(tag);
    }

    @Scheduled(fixedDelay = 6000)
    @DeleteMapping
    public ResponseEntity<?> delete() {
        adRepository.deleteAllExpiredAd(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }
}
