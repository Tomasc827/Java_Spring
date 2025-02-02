package lt.techin.Library.dtos;

public record LoginDTO(String email, String password) {
    public LoginDTO {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }
    }
}
