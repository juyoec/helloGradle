package com.crassirostris.example.lombok;

import com.crassirostris.example.lombok.model.CellPhone;
import com.crassirostris.example.lombok.model.ESystemKeyType;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 3
 * Time: 오후 2:05
 * To change this template use File | Settings | File Templates.
 */
public class HelloLombok {
    public void hello() {
        System.out.println("hello");

        CellPhone galaxyS3 = CellPhone.create("Galaxy S3");
        System.out.println(galaxyS3);
        System.out.println(galaxyS3.hashCode());

        CellPhone galaxyS3MockUp = CellPhone.create("Galaxy S3");
        if (galaxyS3.equals(galaxyS3MockUp)) {
            System.out.println("galaxyS3 equals galaxyS3MockUp");
        }

        CellPhone vegaIron = CellPhone.create();
        vegaIron.setName("VegaIron");
        vegaIron.setSystemKeyType(ESystemKeyType.SOFTWARE);
        System.out.println(vegaIron);
        System.out.println(vegaIron.hashCode());

        // @AllArgsContsructor 의 경우 usable 하지 않다....상황에 따라 써야한다.
        //CellPhone optimusG = CellPhone.create( "OptimusG", ESystemKeyType.SOFTWARE, Maps.newHashMap());
        //System.out.println(optimusG);


    }
}
