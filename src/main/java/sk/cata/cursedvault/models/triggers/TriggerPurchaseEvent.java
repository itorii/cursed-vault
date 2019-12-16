package sk.cata.cursedvault.models.triggers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@UserDefinedType("triggerPurchaseEvent")
public class TriggerPurchaseEvent {

    private String id;
    private String name;

    private Set<Long> upcs = new HashSet<>();

}
