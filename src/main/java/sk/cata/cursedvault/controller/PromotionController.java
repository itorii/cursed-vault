package sk.cata.cursedvault.controller;

import sk.cata.cursedvault.models.Promotion;
import sk.cata.cursedvault.models.exception.ServiceException;

import java.util.List;

public interface PromotionController {

    Promotion findById(String id) throws ServiceException;

    List<Promotion> findAll();

    Promotion create(Promotion promotion);

    Promotion update(Promotion promotion);

    void deleteById(String id);
}
