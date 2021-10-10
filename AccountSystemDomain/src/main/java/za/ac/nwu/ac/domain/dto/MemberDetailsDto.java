package za.ac.nwu.ac.domain.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
public class MemberDetailsDto {
    private Long memberId;
    private Long miles;
}
