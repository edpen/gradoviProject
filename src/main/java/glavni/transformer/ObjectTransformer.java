package glavni.transformer;

import glavni.dto.GradoviDto;
import glavni.domain.Gradovi;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ObjectTransformer {
    public ObjectTransformer(){

    }
    public GradoviDto domainToDTO(Gradovi grad){
        return new GradoviDto(grad.getId(),grad.getGrad(),grad.getDrzava());
    }
    public Gradovi DTOToDomain(GradoviDto dto){
        return new Gradovi(dto.getId(),dto.getGrad(),dto.getDrzava());
    }
    public List<GradoviDto> domainToDtoList(List<Gradovi> gradovi){
        List<GradoviDto> dtos=new ArrayList<GradoviDto>();
        gradovi.stream().forEach(p->dtos.add(new GradoviDto(p.getId(),p.getGrad(),p.getDrzava())));
        return dtos;
    }
}
