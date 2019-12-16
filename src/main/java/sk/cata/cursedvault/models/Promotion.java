package sk.cata.cursedvault.models;

import com.datastax.driver.core.DataType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import sk.cata.cursedvault.models.triggers.TriggerPurchaseEvent;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table
public class Promotion {

    @PrimaryKey
    private String id;

    private String startDate;

    private String stopDate;

    private boolean requiresContextProcessing = false;

    private String reward;

@CassandraType(type = DataType.Name.LIST, userTypeName = "triggerPurchaseEvent") private List<TriggerPurchaseEvent> triggers;

}
