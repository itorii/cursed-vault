package sk.cata.cursedvault.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sk.cata.cursedvault.models.Promotion;
import sk.cata.cursedvault.models.exception.NotFoundException;
import sk.cata.cursedvault.models.exception.ServiceException;
import sk.cata.cursedvault.services.PromotionService;

import java.util.List;

@Slf4j
@RestController
public class PromotionControllerImpl implements PromotionController {

    private PromotionService promotionService;

    public PromotionControllerImpl(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    @GetMapping("/{id}")
    public Promotion findById(@PathVariable String id) {
        try {
            return promotionService.findById(id);
        } catch (NotFoundException e) {
            log.error("Couldn't get promotion {}", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    @GetMapping("/")
    public List<Promotion> findAll() {
        try {
            return promotionService.findAll();
        } catch (ServiceException e) {
            log.error("Couldn't find promotions ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @PostMapping("/create")
    public Promotion create( @RequestBody Promotion promotion) {
        if(promotion == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            return promotionService.create(promotion);
        } catch (ServiceException e) {
            log.error("Couldn't create promotion ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @PutMapping("/update")
    public Promotion update(@RequestBody Promotion promotion) {
        if(promotion == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            return promotionService.update(promotion);
        } catch (ServiceException e) {
            log.error("Couldn't update promotion {} ", promotion.getId(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        try {
            promotionService.deleteById(id);
        } catch (ServiceException e) {
            log.error("Couldn't delete promotion {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
