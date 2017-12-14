package glavni;

import glavni.dto.GradoviDto;

public class JsonBodyCheck
{
  public static boolean  isInvalid(GradoviDto g){
      if(g.getDrzava()==null || g.getGrad()==null)
          return true;
    return false;
  }
}
