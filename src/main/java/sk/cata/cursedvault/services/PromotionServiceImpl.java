package sk.cata.cursedvault.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.cata.cursedvault.models.Promotion;
import sk.cata.cursedvault.models.exception.NotFoundException;
import sk.cata.cursedvault.models.exception.ServiceException;
import sk.cata.cursedvault.repos.PromotionRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PromotionServiceImpl implements PromotionService {

    private PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion findById(String id) throws ServiceException {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        if (promotion.isPresent()) {
            return promotion.get();
        } else {
            throw new NotFoundException(String.format("Promotion %s not found", id));
        }
    }

    @Override
    public List<Promotion> findAll() throws ServiceException {
        try{
            return promotionRepository.findAll();
        }catch(Exception e){
            throw new ServiceException("Couldn't get promotions", e);
        }
    }

    @Override
    public Promotion create(Promotion promotion) throws ServiceException {
        try {
            return promotionRepository.insert(promotion);
        } catch (Exception e) {
            throw new ServiceException("Couldn't create promotion", e);
        }
    }

    @Override
    public Promotion update(Promotion promotion) throws ServiceException {
        if (promotionRepository.existsById(promotion.getId())) {
            try {
                return promotionRepository.save(promotion);
            } catch (Exception e) {
                throw new ServiceException(String.format("Couldn't update promotion %s ", promotion.getId()), e);
            }
        } else {
            throw new ServiceException(String.format("Couldn't update promotion %s - promotion not found.", promotion.getId()));
        }
    }

    @Override
    public void deleteById(String promotionId) throws ServiceException {
        if (promotionRepository.existsById(promotionId)) {
            promotionRepository.deleteById(promotionId);
            if (log.isDebugEnabled()) {
                log.debug("Deleted promotion {}", promotionId);
            }
        } else {
            throw new ServiceException(String.format("Couldn't delete promotion %s - promotion not found.", promotionId));
        }

    }


}
