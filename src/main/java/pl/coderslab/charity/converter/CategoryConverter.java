package pl.coderslab.charity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.domain.Category;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

public class CategoryConverter implements Converter<String, Category> {
        @Autowired
        CategoryRepository categoryRepository;

        @Override
        public Category convert(String sourceId) {

            try {
                return categoryRepository.findById(Long.parseLong(sourceId)).orElseThrow(Exception::new);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
