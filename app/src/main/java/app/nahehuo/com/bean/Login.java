package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/7.
 */
public class Login {

    /**
     * message : 验证成功
     * data : {"user":{"id":10000,"name":"lock","email":"lock888@tom.com"},"accessToken":"ZuxwD4ZV2mlwwljY8xsB3jI3Q5TX6ynjAGdwzlpn","accessTokenExpiration":604800,"refreshToken":"eyJpdiI6ImJXdU95bEdLQU9DOXg4bG9HRW1VZ2c9PSIsInZhbHVlIjoiMnJiSUF0bzhBWXlicGllUExKU0FJWDVWdFZGSkdBQnVkenhHWXZFZlBweWRYWXVPSWhiY0VjQXRrQnpkaThvejN2OXdVYTJEQmhDbkpjUFZvOVZGU1E9PSIsIm1hYyI6IjI2ZjI1MTlhZmJlMDZhNjQ3YmViZTY4YzdhMDgwYzU1OWVkOWEyYmZlNTczMTZlNzFkZmYzZDYyYWE2NzE3NzMifQ=="}
     * code : 200
     */

    private String message;
    /**
     * user : {"id":10000,"name":"lock","email":"lock888@tom.com"}
     * accessToken : ZuxwD4ZV2mlwwljY8xsB3jI3Q5TX6ynjAGdwzlpn
     * accessTokenExpiration : 604800
     * refreshToken : eyJpdiI6ImJXdU95bEdLQU9DOXg4bG9HRW1VZ2c9PSIsInZhbHVlIjoiMnJiSUF0bzhBWXlicGllUExKU0FJWDVWdFZGSkdBQnVkenhHWXZFZlBweWRYWXVPSWhiY0VjQXRrQnpkaThvejN2OXdVYTJEQmhDbkpjUFZvOVZGU1E9PSIsIm1hYyI6IjI2ZjI1MTlhZmJlMDZhNjQ3YmViZTY4YzdhMDgwYzU1OWVkOWEyYmZlNTczMTZlNzFkZmYzZDYyYWE2NzE3NzMifQ==
     */

    private DataEntity data;
    private int code;


    public void setMessage(String message) { this.message = message;}


    public void setData(DataEntity data) { this.data = data;}


    public void setCode(int code) { this.code = code;}


    public String getMessage() { return message;}


    public DataEntity getData() { return data;}


    public int getCode() { return code;}


    public static class DataEntity {
        /**
         * id : 10000
         * name : lock
         * email : lock888@tom.com
         */

        private UserEntity user;
        private String accessToken;
        private int accessTokenExpiration;
        private String refreshToken;


        public void setUser(UserEntity user) { this.user = user;}


        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }


        public void setAccessTokenExpiration(int accessTokenExpiration) {
            this.accessTokenExpiration = accessTokenExpiration;
        }


        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }


        public UserEntity getUser() { return user;}


        public String getAccessToken() { return accessToken;}


        public int getAccessTokenExpiration() { return accessTokenExpiration;}


        public String getRefreshToken() { return refreshToken;}


        public static class UserEntity {
            private int id;
            private String name;
            private String email;


            public void setId(int id) { this.id = id;}


            public void setName(String name) { this.name = name;}


            public void setEmail(String email) { this.email = email;}


            public int getId() { return id;}


            public String getName() { return name;}


            public String getEmail() { return email;}
        }
    }
}
