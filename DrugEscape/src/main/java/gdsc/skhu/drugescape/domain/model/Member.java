package gdsc.skhu.drugescape.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String point;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // public void updatePoint(String op, int point) {
    //    if(op.equals("+")) this.point += point;
    //    else if(op.equals("-") && point > 0) this.point -= point;
    // }
}