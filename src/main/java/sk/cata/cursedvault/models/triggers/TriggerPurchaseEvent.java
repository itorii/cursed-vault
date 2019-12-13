package sk.cata.cursedvault.models.triggers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TriggerPurchaseEvent implements Trigger {

    private String id;

    private String name;

    private Set<Long> upcs;

}
