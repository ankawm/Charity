package pl.coderslab.charity.converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.domain.Institution;

public class InstitutionConverter implements Converter<String, Institution> {
        @Autowired
        InstitutionRepository institutionRepository;

        @Override
        public Institution convert(String sourceId) {

            try {
                return institutionRepository.findById(Long.parseLong(sourceId)).orElseThrow(Exception::new);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
