package sk.cata.cursedvault.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.cata.cursedvault.models.Promotion;
import sk.cata.cursedvault.models.exception.NotFoundException;
import sk.cata.cursedvault.models.exception.ServiceException;
import sk.cata.cursedvault.services.PromotionService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("promotions")
public class PromotionControllerImpl implements PromotionController {

    private PromotionService promotionService;

    public PromotionControllerImpl(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    public Promotion findById(@PathVariable String id) {
        try {
            return promotionService.findById(id);
        } catch (NotFoundException e) {
            log.error("Couldn't get promotion {}", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    @GetMapping(value = "/", produces = "application/json")
    public List<Promotion> findAll() {
        try {
            return promotionService.findAll();
        } catch (ServiceException e) {
            log.error("Couldn't find promotions ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @PostMapping(value = "/create", produces = "application/json")
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
    @PutMapping(produces = "application/json")
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
