package lawFirm.rest.contracts;

import lawFirm.entities.LawCase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LawyerDto {

    private String name;
    private String specialization;
}