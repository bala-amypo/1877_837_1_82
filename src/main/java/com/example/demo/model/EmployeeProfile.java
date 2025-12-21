@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "employeeId"),
    @UniqueConstraint(columnNames = "email")
})
public class EmployeeProfile {

    @Id @GeneratedValue
    private Long id;

    private String employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private String role;
    private Boolean active = true;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
