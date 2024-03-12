package dto;


public class UserDto {

        private String id;
        private String name;

        public UserDto() {
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public UserDto(String id, String name, String password, String email) {
            this.id = id;
            this.name = name;
            this.password = password;
            this.email = email;
        }

        private String password;

        private String email;
}
