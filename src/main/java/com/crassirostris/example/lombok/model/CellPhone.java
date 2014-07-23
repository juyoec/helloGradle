package com.crassirostris.example.lombok.model;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 3
 * Time: 오후 2:07
 * To change this template use File | Settings | File Templates.
 */

import java.util.Map;

import lombok.*;

import com.google.common.collect.Maps;

@Data
@RequiredArgsConstructor(staticName = "create")
@NoArgsConstructor(staticName = "create")
@AllArgsConstructor(staticName = "create")
@ToString(exclude = {"address"})
@EqualsAndHashCode(exclude = {"address"})
public class CellPhone {

    @NonNull private String name;
    private ESystemKeyType systemKeyType = ESystemKeyType.HARDWARE;
    private Map<String, String> address = Maps.newHashMap();
}
