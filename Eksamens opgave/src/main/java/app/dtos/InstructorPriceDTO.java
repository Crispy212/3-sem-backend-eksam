package app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstructorPriceDTO {
    private long instructorId;
    private double totalPrice;
}
