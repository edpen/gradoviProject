package glavni.controller;

import glavni.ResponseObject.Response;
import glavni.service.GradoviServiceimpl;
import glavni.dto.GradoviDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/gradovi")
public class GradoviController {
    @Autowired
    GradoviServiceimpl gradoviService;
    private long counter=1;
    List list =new ArrayList<>();
    @RequestMapping(path="/add",method = RequestMethod.POST)
    public @ResponseBody Response<GradoviDto> addNewGrad (@Valid @RequestBody GradoviDto grad, Errors errors) {
        if(errors.hasErrors())
            return new Response<GradoviDto>("409","The request body is not valid it should be something like the following: {",
                    new GradoviDto(1,"Sarajevo","BiH"));

        gradoviService.createNewGradovi(grad);
        grad.setId(counter);
        list.add(counter);
        counter++;
        return  new Response<GradoviDto>("201","created",grad);    }

    @GetMapping(path="/all")
    public @ResponseBody Response<Iterable<GradoviDto>> getAll() {
        if(gradoviService.returnAll()==null)
            return  new Response<Iterable<GradoviDto>>("404","There are no entries");
        return  new Response<Iterable<GradoviDto>>("200","Success", gradoviService.returnAll());
    }

    @GetMapping("find/{someID}")
    public @ResponseBody
    Response<Iterable<GradoviDto>> getByCountry(@PathVariable(value="someID") String s) {
        if(gradoviService.returnByCountry(s)==null)
            return new Response<Iterable<GradoviDto>>("404", "Not Found");
        return new Response<Iterable<GradoviDto>>("200", "Success", gradoviService.returnByCountry(s));
    }

    @DeleteMapping("/delete/{someID}")
    public @ResponseBody
    Response<GradoviDto> deleteByGrad(@PathVariable(value="someID") long id) {
        if(list.size()<id)
            return new Response<GradoviDto>("204","no content");
        if (list.get((int)id-1)!=null){
            GradoviDto grad=new GradoviDto(gradoviService.returnById(id).getId(),gradoviService.returnById(id).
                    getGrad(),gradoviService.returnById(id).getDrzava());
            list.set((int)id-1,null);
            gradoviService.deleteGrad(id);
            return new Response<GradoviDto>("200","Deleted",grad);
        }
        else
            return new Response<GradoviDto>("404","not existing");
    }

    @PutMapping("/edit/{someID}")
    public @ResponseBody Response<GradoviDto> updateGrad(@Valid @RequestBody GradoviDto grad,Errors errors,@PathVariable(value="someID") long s) {
        if(errors.hasErrors())
            return new Response<GradoviDto>("409","The request body is not valid it should be something like the following: {",
                    new GradoviDto(1,"Sarajevo","BiH"));
        try{
            gradoviService.editGrad(grad,s);}
        catch(Exception e){
            return new Response<GradoviDto>("404","does not exist");
        }
        grad.setId(gradoviService.returnById(s).getId());
        return new Response<GradoviDto>("200","Updated",grad);
    }

    @DeleteMapping("/deleteAll")
    public @ResponseBody
    Response<GradoviDto> deleteAll() {
        gradoviService.deleteAll();
        for(int i=0;i<list.size();i++)
            list.set(i,null);
        return new Response<GradoviDto>("200","All is deleted");
    }
}


