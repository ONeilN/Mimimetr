package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.CatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Aizat Nugumanov
 */
@Service
@Transactional(readOnly = true)
@SessionScope
public class CatsService {

    @Value("${image.upload.path}")
    private String imageUploadPath;
    private final CatsRepository catsRepository;
    private final PairsService pairsService;

    @Autowired
    public CatsService(CatsRepository catsRepository, PairsService pairsService) {
        this.catsRepository = catsRepository;
        this.pairsService = pairsService;
    }

    public List<Cat> getAllCats() {
        return catsRepository.findAll();
    }

    public List<Cat> getCatsFromPair(Pair pair) {
        return Arrays.asList(
                catsRepository.findById(pair.getLeftCatID()).orElse(null),
                catsRepository.findById(pair.getRightCatID()).orElse(null)
        );
    }

    public List<Cat> getOrderedCats() {
        return catsRepository.findTop10ByOrderByVoicesDesc();
    }

    @Transactional
    public void addCat(Cat cat, MultipartFile image) throws IOException {
        if (image != null) {
            File uploadDir = new File(imageUploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String newImageName = UUID.randomUUID() + "_" + image.getOriginalFilename();

            image.transferTo(new File(imageUploadPath + "\\" + newImageName));

            cat.setImgName(newImageName);
        }

        catsRepository.save(cat);
        pairsService.createPairs(cat);
    }
}
