package glavni.service;

import glavni.dto.GradoviDto;
import org.springframework.stereotype.Component;

@Component
public interface GradoviService {
    void createNewGradovi(GradoviDto grad);
    Iterable<GradoviDto> returnAll();
    Iterable<GradoviDto> returnByCountry(String s);
    GradoviDto returnById(long id);
    void deleteGrad(long id);
    void deleteAll();
    void editGrad(GradoviDto grad,long id);
}
