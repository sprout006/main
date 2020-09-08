package springboot.domain.counselor;

        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import springboot.domain.BaseTimeEntity;

        import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Counselor extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private Long age;
    @Column(length = 500, nullable = false)
    private String name;
    @Column(length = 500, nullable = false)
    private String job;


    @Builder
    public Counselor(Long id, Long age, String name, String job){
        this.id = id;
        this.age = age;
        this.name = name;
        this.job = job;
    }
    public void update(Long age, String name, String job) {
        this.age = age;
        this.name = name;
        this.job = job;
    }
}