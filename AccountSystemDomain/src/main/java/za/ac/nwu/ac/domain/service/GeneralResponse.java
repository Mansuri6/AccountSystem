package za.ac.nwu.ac.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class GeneralResponse<T> implements Serializable {

    private final boolean successful;
    private final transient T product;
}
