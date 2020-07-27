package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.domain.*;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class DonationController {

      private final DonationRepository donationRepository;
      private final CategoryRepository categoryRepository;
      private final InstitutionRepository institutionRepository;
      private final UserRepository userRepository;
      public DonationController(UserRepository userRepository, DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
          this.userRepository = userRepository;
          this.donationRepository = donationRepository;
          this.categoryRepository = categoryRepository;
          this.institutionRepository  = institutionRepository;
      }

      @RequestMapping(value = "confirmation", method = RequestMethod.GET)
      public String getConfirmation(@AuthenticationPrincipal CurrentUser customUser, Model model) {
          User newCurrentUser = userRepository.findByUsername(customUser.getUser().getUsername());
          model.addAttribute("currentUser", newCurrentUser);
          return "user/form-confirmation";
      }

      @RequestMapping(value = "donation", method = RequestMethod.GET)
      public String getForm(@AuthenticationPrincipal CurrentUser customUser,Model model) {
          User newCurrentUser = userRepository.findByUsername(customUser.getUser().getUsername());
          model.addAttribute("currentUser", newCurrentUser);
          model.addAttribute("donation", new Donation());
          return "user/form";
      }

      @RequestMapping(value = "donation", method = RequestMethod.POST)
      public String create(@Valid Donation donation, BindingResult result) {
          if (result.hasErrors()) {
              return "user/form";
          }
          donationRepository.save(donation);
          return "redirect:/user/confirmation";
      }
//
//    @RequestMapping(value = "editForm/{id}", method = RequestMethod.GET)
//    public String getEditForm(Model model, @PathVariable long id) {
//        Tweet tweet= null;
//        try {
//            tweet = tweetRepository.findById(id).orElseThrow(Exception::new);
//            model.addAttribute("tweet", tweet);
//            return "tweetUpdate";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @RequestMapping(value = "editForm/{id}", method = RequestMethod.POST)
//    public RedirectView update(@ModelAttribute Tweet tweet, @PathVariable long id) {
//        try {
//            Tweet oldTweet = tweetRepository.findById(id).orElseThrow(Exception::new);
//            oldTweet.setTitle(tweet.getTitle());
//            oldTweet.setTweeterUser(tweet.getTweeterUser());
//            oldTweet.setTweetText(tweet.getTweetText());
//            tweetRepository.save(oldTweet);
//            return new RedirectView("/tweet/all");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public RedirectView delete(@PathVariable long id) throws Exception {
//        try {
//            Tweet tweet = tweetRepository.findById(id).orElseThrow(Exception::new);
//            tweetRepository.delete(tweet);
//            return new RedirectView("/tweet/all");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
      @ModelAttribute("categories")
      public Collection<Category> categories() {
            return this.categoryRepository.findAll();
      }
      @ModelAttribute("institutions")
      public Collection<Institution> institutions() {
            return this.institutionRepository.findAll();
      }
}
