package freeskill.app.utils;

/**
 * Created by Sofiane-e on 04/02/2018.
 */

public class Constants {

    public class General {
        public static final String KEY_ACCESS_TOKEN = "x-access-token";
    }
    //Key from JSON WEB TOKEN TO DECODE
    public class JWT {
        public static final String ID_USER = "idUser";
        public static final String EMAIL = "email";
    }
    //API Freeskill
    public class API {
        public class Connection {
            public static final String URI = "https://freeskill.ddns.net/auth/connection";
            public static final String param1 = "email";
            public static final String param2 = "password";
        }

        public class GetProfile {
            public static final String URI = "https://freeskill.ddns.net/user/GetProfile";
        }
        public class GetImage {
            public static final String URI = "https://freeskill.ddns.net/user/GetImage/";
        }
    }


}
