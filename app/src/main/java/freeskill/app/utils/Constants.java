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

    public class JSONparameters {
        public static final String PERIMETER = "perimeter";
        public static final String NOTIF_MATCH = "notif_match";
        public static final String NOTIF_MESSAGE = "notif_message";
        public static final String NOTIF_MEETING = "notif_meeting";
        public static final String NOTIF_REMINDER = "notif_reminder";
        public static final String NOTIF_MARK = "notif_mark";
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
        public class SetProfile {
            public static final String URI = "https://freeskill.ddns.net/user/SetProfile?";
        }
        public class SetJudgement{
            public static final String URI = "https://freeskill.ddns.net/user/SetJudgement?";
        }
        public class Register{
            public static final String URI = "https://freeskill.ddns.net/auth/register";
        }

        public class GetMarks {
            public static final String URI = "https://freeskill.ddns.net/user/GetMarks/";
        }
    }

    public class Preferences{
        public static final String SHARED_PREFERENCES_CONNECTION = "conection";
        public static final String SHARED_PREFERENCES_PROFILE = "profile";
        public static final String SHARED_PREFERENCES_SETTINGS = "settings";
    }

    public class PreferencesConnection{
        public static final String PREF_LOGIN = "email";
    }

    public class PreferencesSettings{
        public static final String PREF_PERIMETER = "perimeter";
        public static final String PREF_NOTIF_MATCH = "notif_match";
        public static final String PREF_NOTIF_MESSAGE = "notif_message";
        public static final String PREF_NOTIF_MEETING = "notif_meeting";
        public static final String PREF_NOTIF_REMINDER = "notif_reminder";
        public static final String PREF_NOTIF_MARK = "notif_mark";
    }



}
