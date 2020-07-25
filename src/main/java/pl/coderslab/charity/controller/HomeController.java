package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/")
public class HomeController {
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    public HomeController(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository  = institutionRepository;

    }
    @GetMapping("")
    public String getHomeData(Model model){
        List<Institution> institutions = institutionRepository.findAll();
        Collections.shuffle(institutions);
        int randomSeriesLength = 4;
        List<Institution> randomSeries = institutions.subList(0, randomSeriesLength);
        model.addAttribute("institutions", randomSeries);

        Integer sumOfDonation = donationRepository.sumOfQuantity();
        model.addAttribute("sumOfDonation", sumOfDonation);
        Integer countOfCategory = donationRepository.countOfCategory();
        model.addAttribute("countOfCategory", countOfCategory);

        return "index";
    }


}
