package edu.mtisw.lab1_mingeso.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="reloj")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelojEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String fecha;
    private String hora;
    private String rut_empleado_reloj;
}
