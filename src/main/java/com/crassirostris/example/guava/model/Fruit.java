package com.crassirostris.example.guava.model;

import com.google.common.base.Objects;
import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 3
 * Time: 오후 3:30
 * To change this template use File | Settings | File Templates.
 */
@Data
@RequiredArgsConstructor(staticName = "create")
public class Fruit {
    @NonNull private String name;
    @NonNull private String color;

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name",this.name).add("color",this.color).toString();
    }
}
