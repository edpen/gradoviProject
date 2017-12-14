package glavni.service;

import glavni.Response;
import glavni.dao.GradoviRepository;
import glavni.dto.GradoviDto;
import glavni.domain.Gradovi;
import glavni.transformer.ObjectTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradoviServiceimpl implements GradoviService {

    @Autowired
    private GradoviRepository gradoviRepository;
    @Autowired
    private ObjectTransformer oTransformer;

    public GradoviServiceimpl(){

    }
    @Override
    public void createNewGradovi( GradoviDto grad){
        gradoviRepository.save(oTransformer.DTOToDomain(grad));
    }

    @Override
    public
    Iterable<GradoviDto> returnAll(){
        List<Gradovi> list=new ArrayList<Gradovi>();
        list.addAll(gradoviRepository.findAll());
        if(list.isEmpty())
            return null;
        return oTransformer.domainToDtoList(list);
    }
    @Override
    public  Iterable<GradoviDto> returnByCountry(String s){
        List<Gradovi> list=new ArrayList<Gradovi>();
        list.addAll((ArrayList<Gradovi>) gradoviRepository.findAllByDrzavaEquals(s));
        if (list.size()==0)
            return null ;
        else
        return oTransformer.domainToDtoList(list);
    }
    @Override
    public GradoviDto returnById(long id){
        return oTransformer.domainToDTO(gradoviRepository.findByIdEquals(id));
    }
    @Override
    public void deleteGrad(long id){
            gradoviRepository.delete(id);
    }

    @Override
    public void editGrad(GradoviDto grad,long id){
        gradoviRepository.findOne(id).setGrad(grad.getGrad());
        gradoviRepository.findOne(id).setDrzava(grad.getDrzava());
        gradoviRepository.save(gradoviRepository.findOne(id));
    }
    @Override
    public void deleteAll(){
        gradoviRepository.deleteAll();
    }
}
