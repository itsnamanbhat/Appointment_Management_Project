
package com.graymatter.entities;



import com.graymatter.dto.DiagnosticCenterDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingDiagnosticCenterRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean approved;
    private String username;
    private String email;
    private String password;

}

