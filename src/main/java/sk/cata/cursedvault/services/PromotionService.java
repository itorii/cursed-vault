package sk.cata.cursedvault.services;

import sk.cata.cursedvault.models.Promotion;
import sk.cata.cursedvault.models.exception.NotFoundException;
import sk.cata.cursedvault.models.exception.ServiceException;

import java.util.List;

public interface PromotionService {

    Promotion findById(String id) throws NotFoundException;

    List<Promotion> findAll() throws ServiceException;

    Promotion create(Promotion promotion) throws ServiceException;

    Promotion update(Promotion promotion) throws ServiceException;

    void deleteById(String id) throws ServiceException;


}
