package glavni.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Gradovi {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    //@Size(min = 1, message = "error.id.lessThanZero")
    private  long id;
    @NotNull
    private  String grad;
    @NotNull
    private  String drzava;

    public Gradovi (long id, String grad,String drzava)
    {
       this.id=id;
       this.grad=grad;
       this.drzava=drzava;
    }
    public Gradovi(){}
    public long getId(){
        return id;
    }
    public void setId(long id){ this.id=id;}
    public String getGrad(){
        return grad;
    }
    public void setGrad(String grad){this.grad=grad;}
    public String getDrzava(){
        return drzava;
    }
    public void setDrzava(String drzava){this.drzava=drzava;}
}